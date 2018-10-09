package ir.adventure.observer.client.core.org.telegram.api.functions.contacts;

import ir.adventure.observer.client.core.org.telegram.tl.StreamingUtils;
import ir.adventure.observer.client.core.org.telegram.tl.TLContext;
import ir.adventure.observer.client.core.org.telegram.tl.TLIntVector;
import ir.adventure.observer.client.core.org.telegram.tl.TLMethod;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type TL request contacts export card.
 */
public class TLRequestContactsExportCard extends TLMethod<TLIntVector> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x84e53737;

    /**
     * Instantiates a new TL request contacts export card.
     */
    public TLRequestContactsExportCard() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLIntVector deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        return StreamingUtils.readTLIntVector(stream, context);
    }

    public String toString() {
        return "contacts.exportCard#84e53737";
    }
}