package ir.adventure.observer.client.core.org.telegram.api.functions.account;

import ir.adventure.observer.client.core.org.telegram.api.account.TLAccountAuthorizations;
import ir.adventure.observer.client.core.org.telegram.tl.StreamingUtils;
import ir.adventure.observer.client.core.org.telegram.tl.TLContext;
import ir.adventure.observer.client.core.org.telegram.tl.TLMethod;
import ir.adventure.observer.client.core.org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type TL request account get authorizations.
 */
public class TLRequestAccountGetAuthorizations extends TLMethod<TLAccountAuthorizations> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe320c158;

    /**
     * Instantiates a new TL request account get authorizations.
     */
    public TLRequestAccountGetAuthorizations() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAccountAuthorizations deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLAccountAuthorizations))
            return (TLAccountAuthorizations) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.account.TLAuthorizations, got: " + res.getClass().getCanonicalName());
    }

    public String toString() {
        return "account.getAuthorizations#e320c158";
    }
}