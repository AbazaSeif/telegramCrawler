package ir.adventure.observer.client.core.org.telegram.api.file.location;

import ir.adventure.observer.client.core.org.telegram.tl.StreamingUtils;
import ir.adventure.observer.client.core.org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL file location unavailable.
 */
public class TLFileLocationUnavailable extends TLAbsFileLocation {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7c596b46;

    private long volumeId;
    private int localId;
    private long secret;

    /**
     * Instantiates a new TL file location unavailable.
     */
    public TLFileLocationUnavailable() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets volume id.
     *
     * @return the volume id
     */
    public long getVolumeId() {
        return this.volumeId;
    }

    /**
     * Sets volume id.
     *
     * @param volumeId the volume id
     */
    public void setVolumeId(long volumeId) {
        this.volumeId = volumeId;
    }

    /**
     * Gets local id.
     *
     * @return the local id
     */
    public int getLocalId() {
        return this.localId;
    }

    /**
     * Sets local id.
     *
     * @param localId the local id
     */
    public void setLocalId(int localId) {
        this.localId = localId;
    }

    /**
     * Gets secret.
     *
     * @return the secret
     */
    public long getSecret() {
        return this.secret;
    }

    /**
     * Sets secret.
     *
     * @param secret the secret
     */
    public void setSecret(long secret) {
        this.secret = secret;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeLong(this.volumeId, stream);
        StreamingUtils.writeInt(this.localId, stream);
        StreamingUtils.writeLong(this.secret, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.volumeId = StreamingUtils.readLong(stream);
        this.localId = StreamingUtils.readInt(stream);
        this.secret = StreamingUtils.readLong(stream);
    }

    public String toString() {
        return "fileLocationUnavailable#7c596b46";
    }
}