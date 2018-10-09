package ir.adventure.observer.client.core.org.telegram.api.functions.account;

import ir.adventure.observer.client.core.org.telegram.api.wallpaper.TLAbsWallPaper;
import ir.adventure.observer.client.core.org.telegram.tl.StreamingUtils;
import ir.adventure.observer.client.core.org.telegram.tl.TLContext;
import ir.adventure.observer.client.core.org.telegram.tl.TLMethod;
import ir.adventure.observer.client.core.org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type TL request account get wall papers.
 */
public class TLRequestAccountGetWallPapers extends TLMethod<TLVector<TLAbsWallPaper>> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc04cfac2;

    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsWallPaper> deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        return StreamingUtils.readTLVector(stream, context);
    }

    public String toString() {
        return "account.getWallPapers#c04cfac2";
    }
}