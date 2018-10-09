package ir.adventure.observer.client.entity;

import java.io.Serializable;

/**
 * Created by jalil on 10/24/2017.
 */
public class MessagePK implements Serializable {
    private Long toId;
    private Long messageId;

    public MessagePK() {

    }

    public MessagePK(Long toId, Long messageId, Long observerId) {
        this.toId = toId;
        this.messageId = messageId;
        this.observerId = observerId;
    }

    private Long observerId;
}
