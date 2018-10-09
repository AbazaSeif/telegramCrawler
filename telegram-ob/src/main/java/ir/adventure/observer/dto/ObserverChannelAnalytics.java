package ir.adventure.observer.dto;

/**
 * Created by jalil on 12/10/2017.
 */
public class ObserverChannelAnalytics {
    private Long observerId;
    private Long channelNo;

    public Long getObserverId() {
        return observerId;
    }

    public void setObserverId(Long observerId) {
        this.observerId = observerId;
    }

    public Long getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(Long channelNo) {
        this.channelNo = channelNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObserverChannelAnalytics that = (ObserverChannelAnalytics) o;

        return observerId != null ? observerId.equals(that.observerId) : that.observerId == null;

    }

    @Override
    public int hashCode() {
        return observerId != null ? observerId.hashCode() : 0;
    }

    public ObserverChannelAnalytics(Long observerId, Long channelNo) {
        this.observerId = observerId;
        this.channelNo = channelNo;
    }
}
