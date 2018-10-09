package ir.adventure.observer.client.service;

import ir.adventure.observer.client.core.handlers.DifferenceParametersService;
import ir.adventure.observer.client.core.handlers.TelegramClientCore;
import ir.adventure.observer.client.core.org.telegram.api.channel.filters.TLChannelMessagesFilterEmpty;
import ir.adventure.observer.client.core.org.telegram.api.chat.TLAbsChat;
import ir.adventure.observer.client.core.org.telegram.api.chat.channel.TLChannel;
import ir.adventure.observer.client.core.org.telegram.api.engine.RpcException;
import ir.adventure.observer.client.core.org.telegram.api.functions.updates.TLRequestUpdatesGetChannelDifference;
import ir.adventure.observer.client.core.org.telegram.api.functions.updates.TLRequestUpdatesGetDifference;
import ir.adventure.observer.client.core.org.telegram.api.functions.updates.TLRequestUpdatesGetState;
import ir.adventure.observer.client.core.org.telegram.api.input.chat.TLInputChannel;
import ir.adventure.observer.client.core.org.telegram.api.message.TLAbsMessage;
import ir.adventure.observer.client.core.org.telegram.api.message.TLMessage;
import ir.adventure.observer.client.core.org.telegram.api.update.*;
import ir.adventure.observer.client.core.org.telegram.api.updates.TLUpdatesState;
import ir.adventure.observer.client.core.org.telegram.api.updates.channel.differences.TLAbsUpdatesChannelDifferences;
import ir.adventure.observer.client.core.org.telegram.api.updates.channel.differences.TLUpdatesChannelDifferences;
import ir.adventure.observer.client.core.org.telegram.api.updates.channel.differences.TLUpdatesChannelDifferencesEmpty;
import ir.adventure.observer.client.core.org.telegram.api.updates.channel.differences.TLUpdatesChannelDifferencesTooLong;
import ir.adventure.observer.client.core.org.telegram.api.updates.difference.TLAbsDifference;
import ir.adventure.observer.client.core.org.telegram.api.updates.difference.TLDifference;
import ir.adventure.observer.client.core.org.telegram.api.updates.difference.TLDifferenceSlice;
import ir.adventure.observer.client.core.org.telegram.api.updates.difference.TLDifferenceTooLong;
import ir.adventure.observer.client.core.org.telegram.api.user.TLUser;
import ir.adventure.observer.client.core.org.telegram.tl.TLVector;
import ir.adventure.observer.client.entity.ChannelInfo;
import ir.adventure.observer.client.entity.ChatImpl;
import ir.adventure.observer.client.entity.DifferencesData;
import ir.adventure.observer.client.entity.Message;
import ir.adventure.observer.client.util.CoreRepositories;

import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * Created by jalil on 3/26/2018.
 */
public class UpdateService extends Thread {

    DifferenceParametersService differenceParametersService;
    TelegramClientCore kernel;
    CoreRepositories repositories;
    MessageService messageService;
    ApiService apiService;
    Long observerId;
    DifferencesService differencesService;

    public UpdateService(TelegramClientCore kernel, CoreRepositories coreRepositories, MessageService messageService, ApiService apiService, Long observerId) {
        this.kernel = kernel;
        this.differencesService = new DifferencesService(coreRepositories, observerId);
        this.differenceParametersService = new DifferenceParametersService(differencesService);
        repositories = coreRepositories;
        this.messageService = messageService;
        this.apiService = apiService;

        this.observerId = observerId;
    }

    @Override
    public void run() {

        DifferencesData differencesData = differenceParametersService.getDifferencesData(0);
        TLUpdatesState state;
        if (differencesData == null || differencesData.isBlank()) {
            state = getUpdatesState();
            differenceParametersService.setNewUpdateParams(0, state.getPts(), state.getSeq(), state.getDate());
        }

        while (true) {

            getUpdate(differenceParametersService.getPts(0), differenceParametersService.getDate(0));
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
            }

        }
    }

    protected TLUpdatesState getUpdatesState() {
        try {
            return kernel.getKernelComm().doRpcCallSync(new TLRequestUpdatesGetState());
        } catch (ExecutionException | RpcException e) {
            return null;
        }
    }

    protected void getUpdate(int pts, int date) {
        final TLRequestUpdatesGetDifference requestUpdatesGetDifference = new TLRequestUpdatesGetDifference();
        requestUpdatesGetDifference.setQts(0);
        TLAbsDifference absDifference = null;
        requestUpdatesGetDifference.setDate(date);
        requestUpdatesGetDifference.setPts((pts == 0) ? 1 : pts);
        try {
            absDifference = kernel.getKernelComm().doRpcCallSync(requestUpdatesGetDifference);
            if (absDifference != null) {

                TLUpdatesState updatesState = null;
                if (absDifference instanceof TLDifferenceSlice) {
                    updatesState = ((TLDifferenceSlice) absDifference).getIntermediateState();
                    handleDifferences(absDifference, updatesState);
                } else if (absDifference instanceof TLDifference) {
                    updatesState = ((TLDifference) absDifference).getState();
                    handleDifferences(absDifference, updatesState);
                } else if (absDifference instanceof TLDifferenceTooLong) {
                    // TODO
                    updatesState = new TLUpdatesState();
                    updatesState.setPts(((TLDifferenceTooLong) absDifference).getPts());
                }
                if (updatesState != null)
                    differenceParametersService.setNewUpdateParams(0, updatesState.getPts(), updatesState.getSeq(), updatesState.getDate());
            }
        } catch (ExecutionException | RpcException e) {
        }

    }

    private void handleDifferences(TLAbsDifference absDifference, TLUpdatesState updatesState) {
        handleNewMessages(absDifference);
        handleOtherUpdates(absDifference);
    }

    private void handleNewMessages(TLAbsDifference absDifference) {
        TLVector<TLAbsMessage> newMessages = absDifference.getNewMessages();
        Set<Integer> newMessageIds = new HashSet<>();
        newMessages.stream().filter(newMessage -> newMessage instanceof TLMessage).forEach(newMessage -> {
            messageService.addMessage(new Message((TLMessage) newMessage));
            newMessageIds.add(((TLMessage) newMessage).getFromId());
        });
        List<TLUser> users = new ArrayList<>();
        absDifference.getUsers().stream().filter(tlAbsUser -> tlAbsUser instanceof TLUser).forEach(tlAbsUser -> {
            TLUser tlUser = (TLUser) tlAbsUser;
            if (newMessageIds.contains(tlUser.getId()))
                users.add(tlUser);
        });
        users.forEach(user -> {
            try {
                kernel.getKernelComm().performMarkAsReadUser(user, 0);
            } catch (RpcException e) {
                e.printStackTrace();
            }
        });

    }

    private void handleOtherUpdates(TLAbsDifference absDifference) {
        TLVector<TLAbsUpdate> updates = absDifference.getOtherUpdates();
        Set<Integer> channelIds = new HashSet<>();

        List<TLChannel> channels = new ArrayList<>();
        updates.forEach(update -> {
            if (update instanceof TLUpdateChannelNewMessage) {
                TLUpdateChannelNewMessage channelNewMessage = (TLUpdateChannelNewMessage) update;
                channelIds.add(channelNewMessage.getChannelId());

            } else if (update instanceof TLUpdateChannelTooLong) {
                TLUpdateChannelTooLong channelTooLong = (TLUpdateChannelTooLong) update;
                channelIds.add(channelTooLong.getChannelId());
            } else if (update instanceof TLUpdateChannel) {
                channelIds.add(((TLUpdateChannel) update).getChannelId());
            }
        });

        channelIds.forEach(channelId -> {
            TLChannel channel = getChannel(absDifference.getChats(), channelId);
            if (channel != null) {

                getChannelUpdate(channel.getId(), channel.getAccessHash());
                channels.add(channel);
                try {
                    apiService.markAsRead(channel);
                } catch (RpcException e) {
                }
            }
        });

        List<ChatImpl> chats = getChats(channels);
        saveChats(chats);
        getMembers(chats);


    }

    private void getMembers(List<ChatImpl> chats) {
        chats.forEach(chat -> {
            if (isRequiredToGetChannelInfo(chat)) {
                apiService.setChannelInfo(chat);
            }
        });
    }

    public boolean isRequiredToGetChannelInfo(ChatImpl chat) {
        if (chat.getAccessHash() == null) {
            return false;
        }

        List<ChannelInfo> byChannelIdAndTimeLike = repositories.getChannelInfoRepository().findByChannelIdAndTimeAndMembersIsNotNull((long) chat.getId(), new Date());
        if (byChannelIdAndTimeLike.isEmpty())
            return true;
        return false;
    }

    private void saveChats(List<ChatImpl> channels) {
        repositories.getChatRepository().save(channels);
    }

    private List<ChatImpl> getChats(List<TLChannel> channels) {
        List<ChatImpl> chats = new ArrayList<>();
        channels.forEach(channel -> {
            ChatImpl chat = differencesService.getChatById(channel.getId());
            if (chat == null)
                chat = new ChatImpl(channel, observerId);
            else
                chat.set(channel, observerId);
            chats.add(chat);
        });
        return chats;
    }

    protected void getChannelUpdate(int chatId, long accessHash) {

        final TLRequestUpdatesGetChannelDifference requestGetChannelDifference = new TLRequestUpdatesGetChannelDifference();
        requestGetChannelDifference.setFilter(new TLChannelMessagesFilterEmpty());
        final TLInputChannel inputChannel = new TLInputChannel();
        inputChannel.setChannelId(chatId);
        inputChannel.setAccessHash(accessHash);
        requestGetChannelDifference.setChannel(inputChannel);
        TLAbsUpdatesChannelDifferences absDifference = null;

        int pts = differenceParametersService.getPts(chatId);
        do {

            requestGetChannelDifference.setPts((pts == 0) ? 1 : pts);
            requestGetChannelDifference.setLimit(100);
            try {
                absDifference = kernel.getKernelComm().doRpcCallSync(requestGetChannelDifference);
                if (absDifference != null) {
                    pts = absDifference.getPts();
                }

                if ((absDifference != null) && !(absDifference instanceof TLUpdatesChannelDifferencesEmpty)) {
                    // TODO some action unreadCount
                    handleChannelDifference(absDifference);
                }
            } catch (ExecutionException | RpcException e) {

            }
            try {
                synchronized (this) {
                    if ((absDifference instanceof TLUpdatesChannelDifferencesTooLong)) {
                        this.wait(100);
                    }
                }
            } catch (InterruptedException e) {
            }
        } while ((absDifference instanceof TLUpdatesChannelDifferencesTooLong));
        differenceParametersService.setNewUpdateParams(chatId, pts, 0, 0);
    }

    private void handleChannelDifference(TLAbsUpdatesChannelDifferences absDifference) {

        TLVector<TLAbsMessage> newMessages = null;
        TLVector<TLAbsUpdate> otherUpdates = null;
        if (absDifference instanceof TLUpdatesChannelDifferences) {
            newMessages = ((TLUpdatesChannelDifferences) absDifference).getNewMessages();
            otherUpdates = ((TLUpdatesChannelDifferences) absDifference).getOtherUpdates();
        } else if (absDifference instanceof TLUpdatesChannelDifferencesTooLong)
            newMessages = ((TLUpdatesChannelDifferencesTooLong) absDifference).getMessages();
        if (newMessages != null) handleChannelMessages(newMessages);
        if (otherUpdates != null) handleChannelOtherUpdates(otherUpdates);
    }

    protected void handleChannelOtherUpdates(TLVector<TLAbsUpdate> otherUpdates) {
        otherUpdates.forEach(update -> {
            if (update instanceof TLUpdateEditChannelMessage) {
                TLUpdateEditChannelMessage channelMessage = (TLUpdateEditChannelMessage) update;
                if (channelMessage.getMessage() instanceof TLMessage) {
                    messageService.editMessage(new Message((TLMessage) channelMessage.getMessage()));
                }
            } else if (update instanceof TLUpdateDeleteChannelMessages) {
                TLUpdateDeleteChannelMessages channelMessages = (TLUpdateDeleteChannelMessages) update;
                channelMessages.getMessages().forEach(deleted -> {
                    messageService.deleteMessage(((long) channelMessages.getChannelId()), deleted.longValue());
                });
            }
        });
    }

    private void handleChannelMessages(TLVector<TLAbsMessage> messages) {
        messages.stream().filter(message -> message instanceof TLMessage).forEach(message -> {
            TLMessage tlMessage = (TLMessage) message;
            Message message1 = new Message(tlMessage);
            messageService.addMessage(message1);
        });

    }

    private TLChannel getChannel(TLVector<TLAbsChat> chats, int channelId) {
        for (TLAbsChat chat : chats) {
            if (chat instanceof TLChannel) {
                TLChannel channel = (TLChannel) chat;
                if (chat.getId() == channelId)
                    return channel;
            }
        }
        return null;
    }

}
