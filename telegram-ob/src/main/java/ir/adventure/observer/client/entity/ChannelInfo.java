package ir.adventure.observer.client.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jalil on 11/10/2017.
 */
@Entity
@Table(name = "obs_channelInfo")
@Cacheable(false)
public class ChannelInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long channelId;
    private Long members;
    @Temporal(TemporalType.DATE)
    private Date time;
    private Long view24;

    public Long getView24() {
        return view24;
    }

    public void setView24(Long view24) {
        this.view24 = view24;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Long getMembers() {
        return members;
    }

    public void setMembers(Long members) {
        this.members = members;
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
