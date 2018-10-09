package ir.adventure.observer.client.entity;

import java.io.Serializable;

/**
 * Created by jalil on 11/6/2017.
 */
public class ChatImplPK  implements Serializable {
    private Integer id;
    private Long observerId;

    public ChatImplPK(Integer id, Long observerId) {
        this.id = id;
        this.observerId = observerId;
    }

    public ChatImplPK(){

    }
}
