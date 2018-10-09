package ir.adventure.observer.client.dto;

import ir.adventure.observer.client.entity.Message;

import java.util.List;

/**
 * Created by jalil on 10/31/2017.
 */
public class ChannelHistoryResult {
    private List<Message> messages;
    private Boolean isFinal;

    public ChannelHistoryResult(){

    }
    public ChannelHistoryResult(List<Message> messages, Boolean isFinal) {
        this.messages = messages;
        this.isFinal = isFinal;
    }

    public List<Message> getMessages() {

        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Boolean getFinal() {
        return isFinal;
    }

    public void setFinal(Boolean aFinal) {
        isFinal = aFinal;
    }
}
