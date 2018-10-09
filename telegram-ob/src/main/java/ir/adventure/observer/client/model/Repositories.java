package ir.adventure.observer.client.model;

import ir.adventure.observer.client.repo.ChannelInfoRepository;
import ir.adventure.observer.client.repo.ChatRepository;
import ir.adventure.observer.client.repo.ObserverRepository;
import ir.adventure.observer.client.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jalil on 10/27/2017.
 */
@Component
public class Repositories {

    @Autowired
    ChannelInfoRepository channelInfoRepository;
    @Autowired
    AccessTokenRepository accessTokenRepository;
    @Autowired
    ImportChannelRepository importChannelRepository;


    @Autowired
    ObserverRepository observerRepository;
    @Autowired
    private AdMessageRepository adMessageRepository;
    @Autowired
    private ChatRepository chatRepository;

    public AdMessageRepository getAdMessageRepository() {
        return adMessageRepository;
    }

    public ChannelInfoRepository getChannelInfoRepository() {
        return channelInfoRepository;
    }


    public ChatRepository getChatRepository() {
        return chatRepository;
    }

    public ImportChannelRepository getImportChannelRepository() {
        return importChannelRepository;
    }


    public ObserverRepository getObserverRepository() {
        return observerRepository;
    }

    public AccessTokenRepository getAccessTokenRepository() {
        return accessTokenRepository;
    }
}
