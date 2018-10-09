package ir.adventure.observer.client.core.org.telegram.api.functions.help;

import ir.adventure.observer.client.core.org.telegram.api.help.TLInviteText;
import ir.adventure.observer.client.core.org.telegram.tl.StreamingUtils;
import ir.adventure.observer.client.core.org.telegram.tl.TLContext;
import ir.adventure.observer.client.core.org.telegram.tl.TLMethod;
import ir.adventure.observer.client.core.org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type TL request help get invite text.
 */
public class TLRequestHelpGetInviteText extends TLMethod<TLInviteText> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4d392343;

    /**
     * Instantiates a new TL request help get invite text.
     */
    public TLRequestHelpGetInviteText() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLInviteText deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLInviteText))
            return (TLInviteText) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.help.TLInviteText, got: " + res.getClass().getCanonicalName());
    }

    public String toString() {
        return "help.getInviteText#4d392343";
    }
}