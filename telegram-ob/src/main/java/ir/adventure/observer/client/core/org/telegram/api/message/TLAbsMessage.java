package ir.adventure.observer.client.core.org.telegram.api.message;

import ir.adventure.observer.client.core.org.telegram.tl.TLObject;

/**
 * The type TL abs message.
 */
public abstract class TLAbsMessage extends TLObject {

    /**
     * Instantiates a new TL abs message.
     */
    protected TLAbsMessage() {
        super();
    }

    public abstract int getChatId();
}