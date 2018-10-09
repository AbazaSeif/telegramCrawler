package ir.adventure.observer.client.core.org.telegram.api.input.document;

import ir.adventure.observer.client.core.org.telegram.tl.StreamingUtils;
import ir.adventure.observer.client.core.org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input document.
 */
public class TLInputDocument extends TLAbsInputDocument {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x18798952;

    /**
     * Instantiates a new TL input document.
     */
    public TLInputDocument() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeLong(this.id, stream);
        StreamingUtils.writeLong(this.accessHash, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readLong(stream);
        this.accessHash = StreamingUtils.readLong(stream);
    }

    public String toString() {
        return "inputDocument#18798952";
    }
}