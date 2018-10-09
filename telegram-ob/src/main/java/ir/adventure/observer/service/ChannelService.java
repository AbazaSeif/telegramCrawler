package ir.adventure.observer.service;

import com.google.common.collect.Lists;
import ir.adventure.observer.client.Client;
import ir.adventure.observer.client.core.org.telegram.tl.TLIntVector;
import ir.adventure.observer.client.entity.ChatImpl;
import ir.adventure.observer.client.entity.Message;
import ir.adventure.observer.client.entity.Observer;
import ir.adventure.observer.client.repo.ChatRepository;
import ir.adventure.observer.client.entity.AdMessage;
import ir.adventure.observer.client.entity.ImportChannel;
import ir.adventure.observer.client.repo.AdMessageRepository;
import ir.adventure.observer.client.repo.ImportChannelRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by jalil on 10/23/2017.
 */
@Service
public class ChannelService {

    private static Logger log = getLogger(ChannelService.class);
    @Autowired
    AdMessageRepository adMessageRepository;
    @Autowired
    private ImportChannelRepository importChannelRepository;
    @Autowired
    private ChatRepository chatRepository;

    @Value("${observer.messagePerRequest}")
    private Integer observerMessagePerRequest;


    protected List<ImportChannel> getObserverChannels(Observer observer) {
        return importChannelRepository.findByObserverId(observer.getId());
    }

    public Long deleteAdMessage(TelegramClient telegramClient, Long adId) {
        List<AdMessage> messages = adMessageRepository.findByAdId(adId);
        if (messages.size() <= 0)
            return 0L;

        AdMessage adMessage = messages.get(0);

        ChatImpl chat = telegramClient.getKernel().getChat(Math.toIntExact(adMessage.getChannelId()));
        if (chat == null)
            return 0L;


        List<List<AdMessage>> partitionMessages = Lists.partition(messages, 90);


        Long deleted = 0l;
        for (List<AdMessage> partitionMessage : partitionMessages) {
            TLIntVector messageVec = new TLIntVector();
            for (AdMessage message : partitionMessage) {
                messageVec.add(Math.toIntExact(message.getMessageId()));
            }
            int deleteMessages = telegramClient.getKernel().deleteMessages(chat, messageVec);
            deleted += deleteMessages;
        }

        return deleted;
    }

    public int getAdMessageCount(Long adId) {
        List<AdMessage> messages = adMessageRepository.findByAdId(adId);
        return messages.size();
    }

    public Long getAdMessageViews(TelegramClient telegramClient, Long adId) {
        List<AdMessage> messages = adMessageRepository.findByAdId(adId);
        if (messages.size() <= 0)
            return 0L;
        AdMessage adMessage = messages.get(0);

        ChatImpl chat = telegramClient.getKernel().getChat(Math.toIntExact(adMessage.getChannelId()));
        if (chat == null)
            return 0L;
        List<List<AdMessage>> partitionMessages = Lists.partition(messages, 90);

        Long views = 0L;
        for (List<AdMessage> partitionMessage : partitionMessages) {
            TLIntVector messageVec = new TLIntVector();
            for (AdMessage message : partitionMessage) {
                messageVec.add(Math.toIntExact(message.getMessageId()));
            }
            List<Message> tlMessages = telegramClient.getKernel().getMessages(chat, messageVec);
            log.error("ad messages get views adId:" + adId + " message count:" + tlMessages.size());
            views += tlMessages.size();
        }

        return views;


    }


    private ChatImpl getChat(ImportChannel channel) {
        return chatRepository.findByIdAndObserverId(channel.getChatId(), channel.getObserverId());
    }

    public void getHistories(Client kernel) {

        List<ImportChannel> channels = getObserverChannels(kernel.getObserver());
        if (channels != null)
            for (ImportChannel importChannel : channels) {
                ChatImpl chat = getChat(importChannel);
                Long histories = kernel.getHistories(chat, observerMessagePerRequest);
                log.error("get histories messages No:" + histories);
            }
    }


}
