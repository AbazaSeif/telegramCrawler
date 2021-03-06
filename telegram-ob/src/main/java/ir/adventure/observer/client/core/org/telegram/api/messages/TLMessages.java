package ir.adventure.observer.client.core.org.telegram.api.messages;

import ir.adventure.observer.client.core.org.telegram.api.chat.TLAbsChat;
import ir.adventure.observer.client.core.org.telegram.api.message.TLAbsMessage;
import ir.adventure.observer.client.core.org.telegram.api.user.TLAbsUser;
import ir.adventure.observer.client.core.org.telegram.tl.StreamingUtils;
import ir.adventure.observer.client.core.org.telegram.tl.TLContext;
import ir.adventure.observer.client.core.org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL messages.
 */
public class TLMessages extends TLAbsMessages {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8c718e87;

    /**
     * Instantiates a new TL messages.
     */
    public TLMessages() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLVector(this.messages, stream);
        StreamingUtils.writeTLVector(this.chats, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.messages = (TLVector<TLAbsMessage>) StreamingUtils.readTLVector(stream, context);
        this.chats = (TLVector<TLAbsChat>) StreamingUtils.readTLVector(stream, context);
        this.users = (TLVector<TLAbsUser>) StreamingUtils.readTLVector(stream, context);
    }

    public String toString() {
        return "messages.messages#8c718e87";
    }
}