package ir.adventure.observer.client.core.org.telegram.api.messages.stickers.recent;

import ir.adventure.observer.client.core.org.telegram.api.document.TLAbsDocument;
import ir.adventure.observer.client.core.org.telegram.tl.StreamingUtils;
import ir.adventure.observer.client.core.org.telegram.tl.TLContext;
import ir.adventure.observer.client.core.org.telegram.tl.TLObject;
import ir.adventure.observer.client.core.org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 07 of August of 2016
 */
public class TLMessagesRecentStickers extends TLObject {
    public static final int CLASS_ID = 0x5ce20970;

    private int hash;
    private TLVector<TLAbsDocument> stickers;

    public TLMessagesRecentStickers() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getHash() {
        return hash;
    }

    public TLVector<TLAbsDocument> getStickers() {
        return stickers;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(hash, stream);
        StreamingUtils.writeTLVector(stickers, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        hash = StreamingUtils.readInt(stream);
        stickers = StreamingUtils.readTLVector(stream, context, TLAbsDocument.class);
    }

    @Override
    public String toString() {
        return "messages.recentStickers#5ce20970";
    }
}
