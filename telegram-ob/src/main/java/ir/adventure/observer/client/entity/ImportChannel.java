package ir.adventure.observer.client.entity;

import javax.persistence.*;

/**
 * Created by jalil on 10/23/2017.
 */
@Entity
@Table(name = "obs_importChannel")
public class ImportChannel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    private Long observerId;
    /**
     * Max last channel post to be imported. if null read all channel
     */
    private Long maxHistoryLenght;
    @JoinColumn(name = "observerId", updatable = false, insertable = false)
    private Observer observer;

    public ImportChannel() {

    }


    public Long getMaxHistoryLenght() {
        return maxHistoryLenght;
    }

    public void setMaxHistoryLenght(Long maxHistoryLenght) {
        this.maxHistoryLenght = maxHistoryLenght;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImportChannel that = (ImportChannel) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public Observer getObserver() {
        return observer;
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Long getObserverId() {
        return observerId;
    }

    public void setObserverId(Long observerId) {
        this.observerId = observerId;
    }
}
