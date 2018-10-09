package ir.adventure.observer.client.service;

import ir.adventure.observer.client.core.handlers.TelegramClientCore;
import ir.adventure.observer.client.core.org.telegram.api.chat.TLAbsChat;
import ir.adventure.observer.client.core.org.telegram.api.chat.TLAbsChatFull;
import ir.adventure.observer.client.core.org.telegram.api.chat.channel.TLChannel;
import ir.adventure.observer.client.core.org.telegram.api.chat.channel.TLChannelFull;
import ir.adventure.observer.client.core.org.telegram.api.chat.invite.TLAbsChatInvite;
import ir.adventure.observer.client.core.org.telegram.api.chat.invite.TLChatInviteAlready;
import ir.adventure.observer.client.core.org.telegram.api.contacts.TLResolvedPeer;
import ir.adventure.observer.client.core.org.telegram.api.engine.RpcException;
import ir.adventure.observer.client.core.org.telegram.api.functions.channels.*;
import ir.adventure.observer.client.core.org.telegram.api.functions.contacts.TLRequestContactsResolveUsername;
import ir.adventure.observer.client.core.org.telegram.api.functions.messages.TLRequestMessagesCheckChatInvite;
import ir.adventure.observer.client.core.org.telegram.api.functions.messages.TLRequestMessagesGetHistory;
import ir.adventure.observer.client.core.org.telegram.api.functions.messages.TLRequestMessagesImportChatInvite;
import ir.adventure.observer.client.core.org.telegram.api.input.chat.TLInputChannel;
import ir.adventure.observer.client.core.org.telegram.api.input.peer.TLInputPeerChannel;
import ir.adventure.observer.client.core.org.telegram.api.message.TLAbsMessage;
import ir.adventure.observer.client.core.org.telegram.api.message.TLMessage;
import ir.adventure.observer.client.core.org.telegram.api.messages.TLAbsMessages;
import ir.adventure.observer.client.core.org.telegram.api.messages.TLAffectedMessages;
import ir.adventure.observer.client.core.org.telegram.api.messages.TLMessagesChatFull;
import ir.adventure.observer.client.core.org.telegram.api.updates.TLAbsUpdates;
import ir.adventure.observer.client.core.org.telegram.api.updates.TLUpdates;
import ir.adventure.observer.client.core.org.telegram.bot.structure.Chat;
import ir.adventure.observer.client.core.org.telegram.tl.TLIntVector;
import ir.adventure.observer.client.dto.ChannelHistoryResult;
import ir.adventure.observer.client.entity.*;
import ir.adventure.observer.client.util.CoreRepositories;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by jalil on 4/1/2018.
 */

public class ApiService {

    private Logger logger = LoggerFactory.getLogger(ApiService.class);

    CoreRepositories repositories;
    Long observerId;
    TelegramClientCore kernel;


    public ApiService(TelegramClientCore kernel, CoreRepositories repositories, Long observerId) {
        this.kernel = kernel;
        this.repositories = repositories;
        this.observerId = observerId;
    }

    public ChannelInfo setChannelInfo(ChatImpl chat) {
        if (!chat.isChannel() || chat.getAccessHash() == null)
            return null;

        TLChannelFull channelFull = getChannelFull(chat);
        if (channelFull != null) {
            ChannelInfo channelInfo = new ChannelInfo();
            channelInfo.setChannelId((long) chat.getId());
            channelInfo.setMembers((long) channelFull.getParticipantsCount());
            setChatMembers(channelInfo.getChannelId(), channelInfo.getMembers());
            saveChannelInfoOnceADay(channelInfo);
            return channelInfo;
        }
        return null;
    }

    private void saveChannelInfoOnceADay(@NotNull ChannelInfo channelInfo) {
        List<ChannelInfo> channelInfos = repositories.getChannelInfoRepository().findByChannelIdAndTime(channelInfo.getChannelId(), new Date());
        ChannelInfo channelInfo1 = channelInfo;
        if (channelInfos.size() > 0) {
            channelInfo1 = channelInfos.get(0);
            if (channelInfo.getMembers() != null) {
                channelInfo1.setMembers(channelInfo.getMembers());
            }
            if (channelInfo.getView24() != null)
                channelInfo1.setView24(channelInfo.getView24());
        }
        repositories.getChannelInfoRepository().save(channelInfo1);

    }

    public List<ChatImpl> getChats(){
        return repositories.getChatRepository().findByObserverIdAndDeletedIsNullOrderByUpdateView24Asc(observerId);
    }
    public void setChatMembers(Long channelId, Long members) {
        List<ChatImpl> chats = repositories.getChatRepository().findById(channelId);
        for (ChatImpl chat : chats) {
            chat.setMembers(members);
            repositories.getChatRepository().save(chat);
        }
    }

    public void setChatView24(Long channelId, Long view24) {
        List<ChatView24> chats = repositories.getChatView24Repository().findById(Math.toIntExact(channelId));
        for (ChatView24 chat : chats) {
            chat.setView24(view24);
            chat.setUpdateView24(new Date());
            repositories.getChatView24Repository().save(chat);
        }
    }
    public ChatImpl getChat(Integer id) {
        ChatImplPK pk = new ChatImplPK(id, observerId);
        ChatImpl one = repositories.getChatRepository().findOne(pk);
        if (one == null)
            return null;
        if (one.getDeleted() != null && one.getDeleted())
            return null;
        return one;
    }

    public void deleteChat(int id) {
        ChatImplPK pk = new ChatImplPK(id, observerId);
        ChatImpl chat = repositories.getChatRepository().findOne(pk);
        if (chat == null)
            return;
        chat.setDeleted(true);
        repositories.getChatRepository().save(chat);
    }

    public void markAsRead(TLChannel channel) throws RpcException {
        kernel.getKernelComm().performMarkChannelAsRead(channel, 0);
    }

    public TLChannel resolveChannel(String username) {
        TLRequestContactsResolveUsername reolve = new TLRequestContactsResolveUsername();
        reolve.setUsername(username);

        try {
            TLResolvedPeer tlResolvedPeer = kernel.getKernelComm().doRpcCallSync(reolve);
            if (tlResolvedPeer == null)
                return null;
            if (tlResolvedPeer.getChats().size() == 0)
                return null;
            TLAbsChat chat = tlResolvedPeer.getChats().get(0);
            if (chat instanceof TLChannel) {
                return (TLChannel) chat;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (RpcException e) {
            e.printStackTrace();
        }

        return null;
    }

    public TLChannelFull getChannelFull(ChatImpl chat) {
        TLRequestChannelsGetFullChannel a = new TLRequestChannelsGetFullChannel();
        TLInputChannel channelInfo = new TLInputChannel();
        channelInfo.setChannelId(chat.getId());
        channelInfo.setAccessHash(chat.getAccessHash());
        a.setChannel(channelInfo);

        try {
            TLMessagesChatFull tlMessagesChatFull = kernel.getKernelComm().doRpcCallSync(a);
            if (tlMessagesChatFull != null && tlMessagesChatFull.getFullChat() != null) {
                TLAbsChatFull fullChat = tlMessagesChatFull.getFullChat();
                if (fullChat instanceof TLChannelFull)
                    return (TLChannelFull) fullChat;
                return null;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (RpcException e) {
            e.printStackTrace();
        }

        return null;
    }

    public TLChannel joinChannel(ChatImpl chat) {
        TLRequestChannelsJoinChannel join = new TLRequestChannelsJoinChannel();

        TLInputChannel inputChannel = new TLInputChannel();

        inputChannel.setChannelId(chat.getId());
        inputChannel.setAccessHash(chat.getAccessHash());

        join.setChannel(inputChannel);

        try {
            TLAbsUpdates tlAbsUpdates = kernel.getKernelComm().doRpcCallSync(join);

            if (tlAbsUpdates instanceof TLUpdates) {
                TLUpdates tlUpdates = (TLUpdates) tlAbsUpdates;
                if (tlUpdates.getChats().size() == 0)
                    return null;
                TLAbsChat tlAbsChat = tlUpdates.getChats().get(0);
                if (!(tlAbsChat instanceof TLChannel))
                    return null;
                return (TLChannel) tlAbsChat;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (RpcException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ChannelHistoryResult getChannelHistory(ChatImpl chat, Integer offset, Integer offsetDate, int observerEstimate24MessagePerRequest) {
        TLRequestMessagesGetHistory requestMessagesGetHistory = new TLRequestMessagesGetHistory();
        TLInputPeerChannel channel = new TLInputPeerChannel();
        channel.setChannelId(chat.getId());
        channel.setAccessHash(chat.getAccessHash());
        requestMessagesGetHistory.setPeer(channel);
        requestMessagesGetHistory.setLimit(observerEstimate24MessagePerRequest);
        requestMessagesGetHistory.setAddOffset(offset);

        requestMessagesGetHistory.setOffsetDate(offsetDate);

        ChannelHistoryResult result = new ChannelHistoryResult();
        result.setFinal(false);
        List<Message> messages = getMessages(requestMessagesGetHistory, result, 0);
        result.setMessages(messages);
        return result;
    }

    private Boolean isForwardMessage(Message message) {
        return message.getFwdChannelId() != null;
    }

    public Long getEstimate24Channel(ChatImpl chat, int observerEstimate24MessagePerRequest) {
        Integer offset = 0;
        Date currentDate = new Date();
        Date yesterday = DateUtils.addDays(currentDate, -1);
        yesterday = DateUtils.addHours(yesterday, -1);
        logger.error("estimating 24 observer id : " + observerId + " chatId: " + chat.getId() + " username:" + chat.getUsername());
        ChannelHistoryResult channelHistory = getChannelHistory(chat, offset, (int) (yesterday.getTime() / 1000), observerEstimate24MessagePerRequest);
        List<Message> messages = new ArrayList<>();
        for (Message message : channelHistory.getMessages()) {
            if (DateUtils.isSameDay(message.getDate(), yesterday) && !isForwardMessage(message))
                messages.add(message);
        }
        Long minView = getMinView(messages);
        if (minView != null) {
            ChannelInfo channelInfo = new ChannelInfo();
            channelInfo.setChannelId(Long.valueOf(chat.getId()));
            channelInfo.setView24(minView);
            List<ChannelInfo> channelInfos = repositories.getChannelInfoRepository().findTop2ByChannelIdAndView24IsNotNullOrderByTimeDesc(Long.valueOf(chat.getId()));
            channelInfos.add(channelInfo);
            Long view24 = getAvg(channelInfos);

            setChatView24(Long.valueOf(chat.getId()), view24);
            saveChannelInfoOnceADay(channelInfo);


            logger.error("estimate saved  observer id : " + observerId + " chatId: " + chat.getId() + " username:" + chat.getUsername() + " view24: " + minView);
        }
        return minView;
    }

    private Long getAvg(List<ChannelInfo> channelInfos) {
        if (channelInfos.size() == 0)
            return 0l;
        Long sum = 0l;
        Long no = 0l;
        for (ChannelInfo channelInfo : channelInfos) {
            sum += channelInfo.getView24();
            no += 1l;
        }
        return sum / no;
    }

    public TLChannel checkAlreadyJoined(String username) {
        TLRequestMessagesCheckChatInvite c = new TLRequestMessagesCheckChatInvite();
        c.setHash(username);


        try {
            TLAbsChatInvite tlAbsChatInvite = kernel.getKernelComm().doRpcCallSync(c);
            if (tlAbsChatInvite instanceof TLChatInviteAlready) {
                TLChatInviteAlready chatInvite = (TLChatInviteAlready) tlAbsChatInvite;
                TLAbsChat chat = chatInvite.getChat();
                if (chat instanceof TLChannel)
                    return (TLChannel) chat;
            }
            return null;

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (RpcException e) {
            e.printStackTrace();
        }
        return null;
    }

    public TLChannel joinChatInvite(String username) {
        TLChannel channel = checkAlreadyJoined(username);
        if (channel != null)
            return channel;
        TLRequestMessagesImportChatInvite in = new TLRequestMessagesImportChatInvite();
        in.setHash(username);

        try {
            TLAbsUpdates tlAbsUpdates = kernel.getKernelComm().doRpcCallSync(in);
            if (tlAbsUpdates == null)
                return null;
            if (!(tlAbsUpdates instanceof TLUpdates))
                return null;
            TLUpdates tlUpdates = (TLUpdates) tlAbsUpdates;
            if (tlUpdates.getChats().size() == 0)
                return null;
            TLAbsChat tlAbsChat = tlUpdates.getChats().get(0);
            if (tlAbsChat instanceof TLChannel)
                return (TLChannel) tlAbsChat;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (RpcException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ChannelHistoryResult getChannelHistory(ChatImpl chat, Integer offset, int observerMessagePerRequest) {
        TLRequestMessagesGetHistory requestMessagesGetHistory = new TLRequestMessagesGetHistory();
        TLInputPeerChannel channel = new TLInputPeerChannel();
        channel.setChannelId(chat.getId());
        channel.setAccessHash(chat.getAccessHash());
        requestMessagesGetHistory.setPeer(channel);
        requestMessagesGetHistory.setLimit(observerMessagePerRequest);
        requestMessagesGetHistory.setAddOffset(offset);

        ChannelHistoryResult result = new ChannelHistoryResult();
        result.setFinal(false);
        List<Message> messages = getMessages(requestMessagesGetHistory, result, observerMessagePerRequest);
        result.setMessages(messages);
        return result;
    }

    private Long getMinView(List<Message> messages) {
        if (messages.size() == 0)
            return null;
        Long views = messages.get(0).getViews();
        for (Message message : messages) {
            if (views > message.getViews() && message.getViews() != 0)
                views = message.getViews();
        }
        return views;
    }

    public int deleteMessages(Chat chat, TLIntVector messageVec) {
        TLInputChannel channel = new TLInputChannel();
        channel.setChannelId(chat.getId());
        channel.setAccessHash(chat.getAccessHash());
        TLRequestChannelsDeleteMessages deleteMessages = new TLRequestChannelsDeleteMessages();
        deleteMessages.setChannel(channel);
        deleteMessages.setId(messageVec);

        try {
            TLAffectedMessages tlAffectedMessages = kernel.getKernelComm().doRpcCallSync(deleteMessages);
            logger.error("delete messages count:" + tlAffectedMessages.getPtsCount());
            return tlAffectedMessages.getPtsCount();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (RpcException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private List<Message> getMessages(TLRequestMessagesGetHistory d, ChannelHistoryResult result, int observerMessagePerRequest) {
        List<Message> messages = new ArrayList<>();
        try {
            TLAbsMessages recMessages = kernel.getKernelComm().doRpcCallSync(d);


            if (recMessages != null && recMessages.getMessages() != null)
                for (TLAbsMessage tlAbsMessage : recMessages.getMessages()) {
                    if (tlAbsMessage instanceof TLMessage) {
                        Message message = new Message((TLMessage) tlAbsMessage);
                        message.setObserverId(observerId);
                        messages.add(message);
                    }
                }
            if (recMessages != null && recMessages.getMessages().size() < observerMessagePerRequest)
                result.setFinal(true);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (RpcException e) {
            e.printStackTrace();
        }
        return messages;
    }

    public List<Message> getMessages(Chat chat, TLIntVector messageVec, Long observerId) {
        TLRequestChannelsGetMessages messagesRequest = new TLRequestChannelsGetMessages();
        messagesRequest.setId(messageVec);
        TLInputChannel channel = new TLInputChannel();
        channel.setChannelId(chat.getId());
        channel.setAccessHash(chat.getAccessHash());
        messagesRequest.setChannel(channel);

        try {
            TLAbsMessages tlAbsMessages = kernel.getKernelComm().doRpcCallSync(messagesRequest);
            List<Message> messages = new ArrayList<>();
            for (TLAbsMessage tlAbsMessage : tlAbsMessages.getMessages()) {
                if (tlAbsMessage instanceof TLMessage) {
                    Message message = new Message((TLMessage) tlAbsMessage);
                    message.setObserverId(observerId);
                    messages.add(message);
                }
            }
            return messages;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (RpcException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ChatImpl leaveChannel(ChatImpl chat) {
        TLRequestChannelsLeaveChannel leave = new TLRequestChannelsLeaveChannel();
        TLInputChannel channel = new TLInputChannel();
        channel.setChannelId(chat.getId());
        channel.setAccessHash(chat.getAccessHash());
        leave.setChannel(channel);

        try {
            TLAbsUpdates tlAbsUpdates = kernel.getKernelComm().doRpcCallSync(leave);
            if (tlAbsUpdates == null)
                return null;
            return chat;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (RpcException e) {
            e.printStackTrace();
        }
        return null;
    }
}
