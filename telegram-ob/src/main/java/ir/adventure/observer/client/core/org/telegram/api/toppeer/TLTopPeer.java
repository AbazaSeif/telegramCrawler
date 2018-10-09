package ir.adventure.observer.client.core.org.telegram.api.toppeer;

import ir.adventure.observer.client.core.org.telegram.api.peer.TLAbsPeer;
import ir.adventure.observer.client.core.org.telegram.tl.StreamingUtils;
import ir.adventure.observer.client.core.org.telegram.tl.TLContext;
import ir.adventure.observer.client.core.org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 07 of August of 2016
 */
public class TLTopPeer extends TLObject {
    public static final int CLASS_ID = 0xedcdc05b;

    private TLAbsPeer peer;
    private double rating;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsPeer getPeer() {
        return peer;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(peer, stream);
        StreamingUtils.writeDouble(rating, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        peer = StreamingUtils.readTLObject(stream, context, TLAbsPeer.class);
        rating = StreamingUtils.readDouble(stream);
    }

    @Override
    public String toString() {
        return "topPeer#edcdc05b";
    }
}
