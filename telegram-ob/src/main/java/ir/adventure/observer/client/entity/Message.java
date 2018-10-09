package ir.adventure.observer.client.entity;

import ir.adventure.observer.client.core.org.telegram.api.message.TLMessage;
import ir.adventure.observer.client.core.org.telegram.api.message.media.TLMessageMediaDocument;
import ir.adventure.observer.client.core.org.telegram.api.message.media.TLMessageMediaPhoto;
import ir.adventure.observer.client.core.org.telegram.api.updates.TLUpdateShortMessage;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jalil on 10/24/2017.
 */
@Entity
@IdClass(MessagePK.class)
@Table(name = "obs_message")
public class Message {
    @Id
    private Long toId;
    @Id
    private Long messageId;
    @Id
    private Long observerId;

    private String message;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private Long fromId;

    private Long fwdFromId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fwdDate;

    private Long fwdChannelId;
    private Long fwdChannelPost;
    private Long views;
    @Temporal(TemporalType.TIMESTAMP)
    private Date deleted;

    public Message(){

    }

    public Message(TLUpdateShortMessage message){
        this.messageId = Long.valueOf(message.getId());
        this.message = message.getMessage();
        this.date = new Date(Long.valueOf(message.getDate())*1000);
        this.toId = Long.valueOf(message.getUserId());

    }

    public Message(TLMessage message){

        this.messageId = Long.valueOf(message.getId());
        this.message = message.getMessage();

        if (message.getMedia() instanceof TLMessageMediaPhoto)
        {
            this.message = (((TLMessageMediaPhoto) message.getMedia()).getCaption());
        }
        if (message.getMedia() instanceof TLMessageMediaDocument)
            this.message = ((TLMessageMediaDocument) message.getMedia()).getCaption();
        this.date = new Date(Long.valueOf(message.getDate())*1000);

        this.fromId = Long.valueOf(message.getFromId());
        if (message.getToId() != null)
            this.toId = Long.valueOf(message.getToId().getId());
        if (message.getFwdFrom() != null)
        {
            this.fwdFromId = Long.valueOf(message.getFwdFrom().getFromId());
            this.fwdChannelId = Long.valueOf(message.getFwdFrom().getChannelId());
            this.fwdChannelPost = Long.valueOf(message.getFwdFrom().getChannelPost());
            this.fwdDate = new Date(Long.valueOf(message.getFwdFrom().getDate())*1000);
        }
        this.views = Long.valueOf(message.getViews());
    }

    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }

    public Long getObserverId() {
        return observerId;
    }

    public void setObserverId(Long observerId) {
        this.observerId = observerId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public Long getToId() {
        return toId;
    }

    public void setToId(Long toId) {
        this.toId = toId;
    }

    public Long getFwdFromId() {
        return fwdFromId;
    }

    public void setFwdFromId(Long fwdFromId) {
        this.fwdFromId = fwdFromId;
    }

    public Date getFwdDate() {
        return fwdDate;
    }

    public void setFwdDate(Date fwdDate) {
        this.fwdDate = fwdDate;
    }

    public Long getFwdChannelId() {
        return fwdChannelId;
    }

    public void setFwdChannelId(Long fwdChannelId) {
        this.fwdChannelId = fwdChannelId;
    }

    public Long getFwdChannelPost() {
        return fwdChannelPost;
    }

    public void setFwdChannelPost(Long fwdChannelPost) {
        this.fwdChannelPost = fwdChannelPost;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }
}
