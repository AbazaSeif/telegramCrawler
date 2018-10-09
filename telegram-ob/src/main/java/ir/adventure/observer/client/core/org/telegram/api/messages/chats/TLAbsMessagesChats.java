package ir.adventure.observer.client.core.org.telegram.api.messages.chats;

import ir.adventure.observer.client.core.org.telegram.api.chat.TLAbsChat;
import ir.adventure.observer.client.core.org.telegram.tl.TLObject;
import ir.adventure.observer.client.core.org.telegram.tl.TLVector;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public abstract class TLAbsMessagesChats extends TLObject {
    /**
     * The Chats.
     */
    protected TLVector<TLAbsChat> chats;

    public TLVector<TLAbsChat> getChats() {
        return chats;
    }
}
