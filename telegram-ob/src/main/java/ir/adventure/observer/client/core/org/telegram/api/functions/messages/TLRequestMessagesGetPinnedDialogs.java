package ir.adventure.observer.client.core.org.telegram.api.functions.messages;

import ir.adventure.observer.client.core.org.telegram.api.messages.TLMessagesPeerDialogs;
import ir.adventure.observer.client.core.org.telegram.tl.StreamingUtils;
import ir.adventure.observer.client.core.org.telegram.tl.TLContext;
import ir.adventure.observer.client.core.org.telegram.tl.TLMethod;
import ir.adventure.observer.client.core.org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;

public class TLRequestMessagesGetPinnedDialogs extends TLMethod<TLMessagesPeerDialogs> {
    public static final int CLASS_ID = 0xe254d64e;

    public TLRequestMessagesGetPinnedDialogs() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLMessagesPeerDialogs deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLMessagesPeerDialogs)) {
            return (TLMessagesPeerDialogs) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLMessagesPeerDialogs.class.getName() +
                ", got: " + res.getClass().getCanonicalName());
    }

    public String toString() {
        return "messages.getPinnedDialogs#e254d64e";
    }
}