package ir.adventure.observer.dto;

/**
 * Created by jalil on 12/10/2017.
 */
public class ObserverJoinedChannel {
    private String username;
    private Long observerId;

    public ObserverJoinedChannel(String username, Long observerId) {
        this.username = username;
        this.observerId = observerId;
    }
}
