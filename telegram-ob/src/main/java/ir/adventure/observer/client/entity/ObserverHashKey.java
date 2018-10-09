package ir.adventure.observer.client.entity;

/**
 * Created by jalil on 11/6/2017.
 */
public class ObserverHashKey {
    private Long id;
    private Long observerId;

    public ObserverHashKey(Long id, Long observerId) {
        this.id = id;
        this.observerId = observerId;
    }

    public ObserverHashKey() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObserverHashKey that = (ObserverHashKey) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return observerId != null ? observerId.equals(that.observerId) : that.observerId == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (observerId != null ? observerId.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getObserverId() {
        return observerId;
    }

    public void setObserverId(Long observerId) {
        this.observerId = observerId;
    }
}
