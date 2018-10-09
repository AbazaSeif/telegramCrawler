package ir.adventure.observer.client.core.org.telegram.api.update;

import ir.adventure.observer.client.core.org.telegram.tl.TLObject;

/**
 * The type TL abs update.
 */
public abstract class TLAbsUpdate extends TLObject {
    /**
     * Instantiates a new TL abs update.
     */
    protected TLAbsUpdate() {
        super();
    }

    public int getPts() {
        return 0;
    }

    public int getPtsCount() {
        return 0;
    };
}