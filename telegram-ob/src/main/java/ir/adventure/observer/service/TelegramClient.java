package ir.adventure.observer.service;

import ir.adventure.observer.client.Client;
import ir.adventure.observer.client.entity.Observer;
import ir.adventure.observer.client.util.CoreRepositories;
import ir.adventure.observer.client.model.Repositories;

/**
 * Created by jalil on 10/23/2017.
 */

public class TelegramClient {
    public Client getKernel() {
        return kernel;
    }

    private Client kernel;
    private Observer observer;

    public Observer getObserver() {
        return observer;
    }

    public TelegramClient(Observer observer, Repositories repositories, CoreRepositories coreRepositories, ChannelService channelService) {
        this.observer = observer;
        kernel = new Client(observer, coreRepositories);

    }


}