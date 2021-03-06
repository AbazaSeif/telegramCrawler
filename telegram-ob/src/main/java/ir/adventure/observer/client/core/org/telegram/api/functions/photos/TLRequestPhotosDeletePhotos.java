package ir.adventure.observer.client.core.org.telegram.api.functions.photos;

import ir.adventure.observer.client.core.org.telegram.api.input.photo.TLAbsInputPhoto;
import ir.adventure.observer.client.core.org.telegram.tl.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request photos delete photos.
 */
public class TLRequestPhotosDeletePhotos extends TLMethod<TLLongVector> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x87cf7f2f;

    private TLVector<TLAbsInputPhoto> id;

    /**
     * Instantiates a new TL request photos delete photos.
     */
    public TLRequestPhotosDeletePhotos() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLLongVector deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if ((res instanceof TLLongVector))
            return (TLLongVector) res;
        throw new IOException("Incorrect response type. Expected org.telegram.TLLongVector, got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public TLVector<TLAbsInputPhoto> getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(TLVector<TLAbsInputPhoto> id) {
        this.id = id;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.id, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readTLVector(stream, context);
    }

    public String toString() {
        return "photos.deletePhotos#87cf7f2f";
    }
}