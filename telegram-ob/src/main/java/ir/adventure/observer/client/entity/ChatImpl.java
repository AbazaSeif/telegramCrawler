package ir.adventure.observer.client.entity;

import ir.adventure.observer.client.core.org.telegram.api.chat.channel.TLChannel;
import ir.adventure.observer.client.core.org.telegram.bot.structure.Chat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 16 of October of 2016
 */
@Entity
@IdClass(ChatImplPK.class)
@Table(name = "obs_chat")
public class ChatImpl implements Chat {
    @Id
    private Integer id;
    @Id
    private Long observerId;
    private Long members;
    @JoinColumn(name = "observerId", insertable = false, updatable = false)
    private Observer observer;
    private Long accessHash;
    private Boolean isChannel;
    private String username;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
    private String title;
    private Boolean deleted;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, insertable = false)
    private Date updateView24;
    @Column(updatable = false, insertable = false)
    private Long view24;

    public Long getView24() {
        return view24;
    }

    public ChatImpl(int id) {
        this.id = id;
    }

    public ChatImpl() {
        updated = new Date();
    }

    public ChatImpl(TLChannel channel, Long observerId) {
        this.id = channel.getId();
        this.observerId = observerId;
        this.accessHash = channel.getAccessHash();
        this.isChannel = true;
        if (channel.getUsername() != null)
            this.username = channel.getUsername().toLowerCase();
        updated = new Date();
    }

    public Date getUpdateView24() {
        return updateView24;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @PreUpdate
    @PrePersist
    public void setDate() {
        updated = new Date();
    }

    public Long getMembers() {
        return members;
    }

    public void setMembers(Long members) {
        this.members = members;
    }

    public Observer getObserver() {
        return observer;
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    public Long getObserverId() {
        return observerId;
    }

    public void setObserverId(Long observerId) {
        this.observerId = observerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return Math.toIntExact(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getAccessHash() {
        return accessHash;
    }

    public void setAccessHash(Long accessHash) {
        this.accessHash = accessHash;
    }

    public boolean isChannel() {
        return isChannel;
    }

    public void setChannel(Boolean channel) {
        isChannel = channel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatImpl chat = (ChatImpl) o;

        return id.equals(chat.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public void set(TLChannel channel, Long observerId) {
        this.accessHash = channel.getAccessHash();
        if (channel.getUsername() != null)
            this.username = channel.getUsername().toLowerCase();
        if (channel.getTitle() != null)
            this.title = channel.getTitle();
        this.observerId = observerId;
        updated = new Date();
    }
}
