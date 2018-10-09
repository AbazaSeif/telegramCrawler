package ir.adventure.observer.client.core.org.telegram.api.peer;

import ir.adventure.observer.client.core.org.telegram.tl.StreamingUtils;
import ir.adventure.observer.client.core.org.telegram.tl.TLContext;
import ir.adventure.observer.client.core.org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL peer notify settings.
 */
public class TLPeerSettings extends TLObject {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x818426cd;

    private static final int FLAG_REPORT_SPAM   = 0x00000001; // 0

    private int flags;

    /**
     * Instantiates a new TL peer notify settings.
     */
    public TLPeerSettings() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.flags = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "peerSettings#818426cd";
    }
}