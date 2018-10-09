package ir.adventure.observer.service;

import ir.adventure.observer.client.entity.ChatImpl;
import ir.adventure.observer.client.entity.Observer;
import ir.adventure.observer.client.util.CoreRepositories;
import ir.adventure.observer.dto.JoinResponse;
import ir.adventure.observer.client.model.Repositories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jalil on 10/25/2017.
 */
@Service
public class TelegramClients {

    @Autowired
    ChannelService channelService;
    @Autowired
    Repositories repositories;
    @Autowired
    CoreRepositories coreRepositories;

    private Logger logger = LoggerFactory.getLogger(TelegramClients.class);


    @Value("${observer.adRemoverId}")
    private Long adRemoverObserverId;

    // Estimate24 Vars
    @Value("${observer.estimate24.beginHour}")
    private Integer beginHour;
    @Value("${observer.estimate24.endHour}")
    private Integer endHour;

    private Map<Observer, TelegramClient> services = new HashMap<>();
    @Value("${channel.maxObserverChannelJoin}")
    private Long maxObserverChannelJoin;

    @PostConstruct
    public void init() {
        Iterable<Observer> all = repositories.getObserverRepository().findAll();
        for (Observer observer : all) {
            disableObserver(observer);
        }
        loadServices();
    }

    private void loadServices() {
        loadObservers();
    }

    private void disableObserver(Observer observer) {
        observer.setEnabled(false);
        repositories.getObserverRepository().save(observer);
    }

    public void loadObservers() {

        Iterable<Observer> observers = repositories.getObserverRepository().findByReadyToLoad(true);
        for (Observer observer : observers) {
            addTelegramClient(observer);

        }
    }

    private void addTelegramClient(Observer observer) {
        if (!services.containsKey(observer)) {
            TelegramClient telegramClient = new TelegramClient(observer, repositories, coreRepositories, channelService);
            services.put(observer, telegramClient);
            System.out.println("load observer " + observer.getPhoneNumber());
        }
    }

    public void getViews() {
        for (Observer observer : services.keySet()) {
            TelegramClient telegramClient = services.get(observer);
            channelService.getHistories(telegramClient.getKernel());
        }
    }

    public Long deleteAdMessage(Long adId) {
        Observer key = new Observer();
        key.setId(adRemoverObserverId);
        TelegramClient telegramClient = services.get(key);
        return channelService.deleteAdMessage(telegramClient, adId);
    }

    public Long getAdViews(Long adId) {
        Observer key = new Observer();
        key.setId(adRemoverObserverId);
        TelegramClient telegramClient = services.get(key);
        return channelService.getAdMessageViews(telegramClient, adId);
    }

    private TelegramClient getAvailableClient() {
        for (TelegramClient telegramClient : services.values()) {
            long chatCounts = telegramClient.getKernel().getChatCounts();
            if (chatCounts < maxObserverChannelJoin)
                return telegramClient;
        }
        return null;
    }

    public JoinResponse joinChannel(String username) {
        Boolean isPublic = false;
        if (username.startsWith("@")) {
            isPublic = true;
            username = username.substring(1);
        }
        TelegramClient client = getAvailableClient();
        if (client == null) {
            return new JoinResponse(0, "no available observer", false);
        }
        ChatImpl chat = null;
        if (isPublic) {
            chat = client.getKernel().resolveAndJoinPublicChannel(username);
        } else
            chat = client.getKernel().resolveAndJoinPrivateChannel(username);
        if (chat == null)
            return new JoinResponse(0, "channel does not exist", false);
        return new JoinResponse(chat.getId(), "OK", true);
    }

    public void getEstimateView24() {
        Calendar calendar = Calendar.getInstance();


        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        logger.error("estimate 24 task");
        if (!(hour >= beginHour && hour <= endHour))
            return;
        logger.error("estimating 24 task");
        for (Observer observer : services.keySet()) {
            new Thread(() -> {
                logger.error("Thread to estimate view 24 observer : " + observer.getId());
                TelegramClient telegramClient = services.get(observer);
                telegramClient.getKernel().getChannelView24();
            }).start();
        }

    }


    public void leaveChannel(int chatId) {
        for (TelegramClient telegramClient : services.values()) {
            ChatImpl chat = telegramClient.getKernel().leaveChannel(chatId);
            if (chat != null)
                System.out.println("delete chat " + chat.getObserverId());
        }
    }
}
