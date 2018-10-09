package ir.adventure.observer.client.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jalil on 12/4/2017.
 */
@Entity
@Table(name = "obs_adMessage")
public class AdMessage {
    @Id
    private Long messageId;
    private Long channelId;
    private Long adId;


    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }
}
