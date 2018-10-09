package ir.adventure.observer.client.service;

import ir.adventure.observer.client.entity.Message;
import ir.adventure.observer.client.entity.MessagePK;
import ir.adventure.observer.client.entity.MessageView;
import ir.adventure.observer.client.util.CoreRepositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jalil on 3/27/2018.
 */

public class MessageService {

    CoreRepositories repositories;
    private Long telegramId = 777000l;
    Long observerId;

    public MessageService(CoreRepositories repositories, Long observerId) {
        this.repositories = repositories;
        this.observerId = observerId;
    }

    private boolean fromTelegram(Message message) {
        return telegramId.equals(message.getFromId()) || telegramId.equals(message.getToId());
    }

    public void addMessage(Message message) {
        message.setObserverId(observerId);
        if (!fromTelegram(message))
            message.setMessage(null);

        MessageView messageView = new MessageView(message);

        try {
            repositories.getMessageRepository().save(message);
            repositories.getMessageViewRepository().save(messageView);
        } catch (Exception e) {

        }

    }

    public void addMessage(List<Message> messages) {
        List<MessageView> messageViews = new ArrayList<>();
        messages.forEach(message -> {
            if (!fromTelegram(message))
                message.setMessage(null);
            message.setObserverId(observerId);
            MessageView messageView = new MessageView(message);
            messageViews.add(messageView);
        });
        try {
            repositories.getMessageRepository().save(messages);
            repositories.getMessageViewRepository().save(messageViews);
        } catch (Exception e) {

        }
    }

    public void editMessage(Message message) {
        message.setObserverId(observerId);
        if (!fromTelegram(message))
            message.setMessage(null);
        try {
            repositories.getMessageRepository().save(message);
        } catch (Exception e) {
        }
    }

    public void deleteMessage(Long channelId, Long messageId) {
        MessagePK messagePK = new MessagePK(channelId, messageId, observerId);
        Message message1 = repositories.getMessageRepository().findOne(messagePK);
        if (message1 == null)
            return;
        message1.setDeleted(new Date());
        try {
            repositories.getMessageRepository().save(message1);
        } catch (Exception e) {
        }
    }

}
