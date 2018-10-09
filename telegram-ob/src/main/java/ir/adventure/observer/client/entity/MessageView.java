package ir.adventure.observer.client.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jalil on 10/23/2017.
 */
@Entity
@Table(name = "obs_messageView")
public class MessageView {
    @Temporal(TemporalType.TIMESTAMP)
    Date time;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long messageId;
    private Long views;
    private Long toId;
    private Long observerId;

    public Long getObserverId() {
        return observerId;
    }

    public void setObserverId(Long observerId) {
        this.observerId = observerId;
    }

    public MessageView(){

    }
    public MessageView(Message message){
        this.toId = message.getToId();
        this.observerId = message.getObserverId();
        this.views = message.getViews();
        this.messageId = message.getMessageId();
    }

    public Long getToId() {
        return toId;
    }

    public void setToId(Long toId) {
        this.toId = toId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @PrePersist
    public void setTimeStamp(){
        this.time = new Date();
    }

}
