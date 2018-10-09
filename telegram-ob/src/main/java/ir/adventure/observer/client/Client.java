package ir.adventure.observer.client;

import ir.adventure.observer.client.core.config.BotConfigImpl;
import ir.adventure.observer.client.core.handlers.TelegramClientCore;
import ir.adventure.observer.client.core.org.telegram.api.chat.channel.TLChannel;
import ir.adventure.observer.client.core.org.telegram.api.chat.channel.TLChannelFull;
import ir.adventure.observer.client.core.org.telegram.bot.structure.BotConfig;
import ir.adventure.observer.client.core.org.telegram.bot.structure.Chat;
import ir.adventure.observer.client.core.org.telegram.bot.structure.LoginStatus;
import ir.adventure.observer.client.core.org.telegram.tl.TLIntVector;
import ir.adventure.observer.client.dto.ChannelHistoryResult;
import ir.adventure.observer.client.entity.ChatImpl;
import ir.adventure.observer.client.entity.Message;
import ir.adventure.observer.client.entity.Observer;
import ir.adventure.observer.client.service.ApiService;
import ir.adventure.observer.client.service.MessageService;
import ir.adventure.observer.client.service.UpdateService;
import ir.adventure.observer.client.util.CoreRepositories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

/**
 * Created by jalil on 3/26/2018.
 */
public class Client {

    private Logger logger = LoggerFactory.getLogger(Client.class);


    TelegramClientCore kernel;

    public Observer getObserver() {
        return observer;
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    Observer observer;
    CoreRepositories coreRepositories;
    UpdateService updateService;
    ApiService apiService;
    MessageService messageService;
    private int view24No = 100;
    private Long daysToNewEstimate = 3l;
    private int observerEstimate24MessagePerRequest = 20;
    private Integer sleepPerRequest = 120000;

    public Client(Observer observer, CoreRepositories coreRepositories) {
        this.observer = observer;
        this.coreRepositories = coreRepositories;
        BotConfig botConfig = new BotConfigImpl(observer.getPhoneNumber());
        messageService = new MessageService(coreRepositories, observer.getId());

        disableObserver(observer);
        kernel = new TelegramClientCore(botConfig, observer.getApiId(), observer.getApiHash());

        try {
            LoginStatus status = kernel.init();
            if (status == LoginStatus.CODESENT) {
                String codeOfObserver = getCodeOfObserver(observer);
                if (codeOfObserver == null)
                    return;
                boolean success = kernel.getKernelAuth().setAuthCode(codeOfObserver);
                if (success) {
                    status = LoginStatus.ALREADYLOGGED;
                }
            }

            if (status == LoginStatus.ALREADYLOGGED) {
                apiService = new ApiService(kernel, coreRepositories, observer.getId());
                clearObserverCode(observer);
                updateService = new UpdateService(kernel, coreRepositories, messageService, apiService, observer.getId());
                updateService.start();

            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public ChatImpl getChat(Integer id) {
        return apiService.getChat(id);
    }

    public TelegramClientCore getKernel() {
        return kernel;
    }

    public ChatImpl resolveAndJoinPublicChannel(String username) {

        TLChannel channel = apiService.resolveChannel(username);
        if (channel == null)
            return null;
        ChatImpl chat = new ChatImpl(channel, observer.getId());
        channel = joinChannel(chat);
        chat.set(channel, observer.getId());
        chat.setDeleted(null);
        coreRepositories.getChatRepository().save(chat);
        return chat;


    }

    public ChatImpl resolveAndJoinPrivateChannel(String username) {
        TLChannel channel = joinChatInvite(username);
        if (channel == null)
            return null;
        if (channel.getUsername() != null)
            return resolveAndJoinPublicChannel(channel.getUsername());

        ChatImpl chat = new ChatImpl(channel, observer.getId());
        chat.setDeleted(null);
        coreRepositories.getChatRepository().save(chat);
        return chat;

    }

    public TLChannelFull getChannelFull(ChatImpl chat) {
        return apiService.getChannelFull(chat);
    }

    public long getChatCounts() {
        if (observer.getId() == 1) {
            return 30000;
        }
        return coreRepositories.getChatRepository().countByObserverIdAndDeletedIsNull(observer.getId());
    }

    public TLChannel joinChannel(ChatImpl chat) {
        return apiService.joinChannel(chat);
    }

    public Long getEstimate24Channel(ChatImpl chat, int observerEstimate24MessagePerRequest) {
        return apiService.getEstimate24Channel(chat, observerEstimate24MessagePerRequest);
    }

    public void getChannelView24() {
        List<ChatImpl> chats = apiService.getChats();

        int i = 0;
        for (ChatImpl chat : chats) {
            if (isReguiredToView24(chat)) {
                getEstimate24Channel(chat, observerEstimate24MessagePerRequest);
                try {
                    Thread.sleep(sleepPerRequest);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i++;
            if (i > view24No)
                break;
        }
    }

    public Boolean isReguiredToView24(ChatImpl chat) {
        if (chat.getUpdateView24() == null)
            return true;
        return daysBetween(chat.getUpdateView24(), new Date()) >= daysToNewEstimate;

    }

    private Long daysBetween(Date begin, Date end) {
        return (end.getTime() - begin.getTime()) / (1000 * 60 * 60 * 24);
    }

    public Long getHistories(ChatImpl chat, int observerMessagePerRequest) {
        Integer offset = 0;
        Long messageCount = 0l;
        while (true) {

            ChannelHistoryResult channelHistory = getChannelHistory(chat, offset, observerMessagePerRequest);
            if (!channelHistory.getMessages().isEmpty())
                // TODO
                messageService.addMessage(channelHistory.getMessages());
            offset += observerMessagePerRequest;
            messageCount += channelHistory.getMessages().size();
            // Check End
            if (channelHistory.getFinal())
                break;

            try {
                Thread.sleep(120000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return messageCount;
    }

    private ChannelHistoryResult getChannelHistory(ChatImpl chat, Integer offset, int observerMessagePerRequest) {
        return apiService.getChannelHistory(chat, offset, observerMessagePerRequest);
    }

    public TLChannel joinChatInvite(String username) {
        TLChannel channel = apiService.joinChatInvite(username);
        if (channel == null)
            return null;
        if (channel.getUsername() != null) {

        }
        return channel;
    }

    public int deleteMessages(Chat chat, TLIntVector messageVec) {
        return apiService.deleteMessages(chat, messageVec);
    }


    public List<Message> getMessages(Chat chat, TLIntVector messageVec) {
        List<Message> messages = apiService.getMessages(chat, messageVec, observer.getId());
        messageService.addMessage(messages);
        return messages;
    }

    private void disableObserver(Observer observer) {
        observer.setEnabled(false);
        coreRepositories.getObserverRepository().save(observer);
    }

    private void clearObserverCode(Observer observer) {
        observer.setCode(null);
        observer.setEnabled(true);
        coreRepositories.getObserverRepository().save(observer);
    }

    private String getCodeOfObserver(Observer observer) {
        Integer i = 0;
        System.out.println("waiting for observer " + observer.getPhoneNumber() + " " + observer.getName());
        for (i = 0; i < 30000; i++) {
            Observer loadedObserver = coreRepositories.getObserverRepository().findOne(observer.getId());
            if (loadedObserver.getCode() != null) {
                System.out.println("get code of observer " + observer.getPhoneNumber() + " " + observer.getName());
                return loadedObserver.getCode().trim();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("failed get code of observer " + observer.getPhoneNumber() + " " + observer.getName());
        return null;
    }

    public ChatImpl leaveChannel(int chatId) {
        if (observer.getId() <= 1)
            return null;
        ChatImpl chat = getChat(chatId);
        if (chat == null)
            return null;
        ChatImpl chat1 = apiService.leaveChannel(chat);
        if (chat1 != null)
            deleteChat(chat1);
        return chat1;
    }

    private void deleteChat(ChatImpl chat1) {
        apiService.deleteChat(chat1.getId());
    }
}
