package ir.adventure.observer.client.core.org.telegram.api.update;

import ir.adventure.observer.client.core.org.telegram.tl.StreamingUtils;
import ir.adventure.observer.client.core.org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update user phone.
 */
public class TLUpdateUserPhone extends TLAbsUpdate {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x12b9417b;
    private int userId;
    private String phone;

    /**
     * Instantiates a new TL update user phone.
     */
    public TLUpdateUserPhone() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public int getUserId() {
        return userId;
    }

    public String getPhone() {
        return phone;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.userId, stream);
        StreamingUtils.writeTLString(this.phone, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.userId = StreamingUtils.readInt(stream);
        this.phone = StreamingUtils.readTLString(stream);
    }

    public String toString() {
        return "updateUserPhone#12b9417b";
    }
}