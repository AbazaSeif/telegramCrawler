package ir.adventure.observer.client.core.org.telegram.mtproto.tl;

import ir.adventure.observer.client.core.org.telegram.tl.TLContext;
import ir.adventure.observer.client.core.org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static ir.adventure.observer.client.core.org.telegram.tl.StreamingUtils.readLong;
import static ir.adventure.observer.client.core.org.telegram.tl.StreamingUtils.writeLong;

/**
 * @author Ruben Bermudez
 * @version 2.0
 * @brief MTDestroySessionNone
 * @date 21/02/15
 */
public class MTDestroySessionNone extends TLObject {
    public static final int CLASS_ID = 0x62d350c9;

    private Long session_id;

    public MTDestroySessionNone() {
    }

    public MTDestroySessionNone(Long session_id) {
        this.session_id = session_id;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        writeLong(this.session_id, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.session_id = readLong(stream);
    }

    public Long getSession_id() {
        return this.session_id;
    }

    public void setSession_id(Long session_id) {
        this.session_id = session_id;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "MTDestroySessionNone#62d350c9";
    }
}
