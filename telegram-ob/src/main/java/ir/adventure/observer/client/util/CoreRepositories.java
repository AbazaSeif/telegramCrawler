package ir.adventure.observer.client.util;

import ir.adventure.observer.client.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jalil on 10/27/2017.
 */
@Component
public class CoreRepositories {
    @Autowired
    ChatRepository chatRepository;
    @Autowired
    ChannelInfoRepository channelInfoRepository;
    @Autowired
    ObserverRepository observerRepository;
    @Autowired
    DifferencesDataRepository differencesDataRepository;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    MessageViewRepository messageViewRepository;
    @Autowired
    ChatView24Repository chatView24Repository;

    public ChatView24Repository getChatView24Repository() {
        return chatView24Repository;
    }

    public ChannelInfoRepository getChannelInfoRepository() {
        return channelInfoRepository;
    }

    public ChatRepository getChatRepository() {
        return chatRepository;
    }

    public MessageRepository getMessageRepository() {
        return messageRepository;
    }

    public MessageViewRepository getMessageViewRepository() {
        return messageViewRepository;
    }

    public ObserverRepository getObserverRepository() {
        return observerRepository;
    }

    public DifferencesDataRepository getDifferencesDataRepository() {
        return differencesDataRepository;
    }
}
