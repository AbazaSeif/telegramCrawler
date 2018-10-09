package ir.adventure.observer.client.entity;

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
public class ChatView24 {
    @Id
    private Integer id;
    @Id
    private Long observerId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateView24;
    private Long view24;
    private Boolean deleted;


    public Long getView24() {
        return view24;
    }

    public void setView24(Long view24) {
        this.view24 = view24;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatView24 chat = (ChatView24) o;

        return id.equals(chat.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getObserverId() {
        return observerId;
    }

    public void setObserverId(Long observerId) {
        this.observerId = observerId;
    }

    public Date getUpdateView24() {
        return updateView24;
    }

    public void setUpdateView24(Date updateView24) {
        this.updateView24 = updateView24;
    }
}
