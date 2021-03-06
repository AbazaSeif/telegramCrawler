package ir.adventure.observer.client.core.org.telegram.api.functions.auth;

import ir.adventure.observer.client.core.org.telegram.tl.*;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type TL request auth log out.
 */
public class TLRequestAuthLogOut extends TLMethod<TLBool> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5717da40;

    public int getClassId() {
        return CLASS_ID;
    }

    public TLBool deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLBool))
            return (TLBool) res;
        throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
    }

    public String toString() {
        return "auth.logOut#5717da40";
    }
}