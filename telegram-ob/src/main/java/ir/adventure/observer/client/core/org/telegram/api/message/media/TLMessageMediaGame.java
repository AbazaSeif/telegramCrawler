package ir.adventure.observer.client.core.org.telegram.api.message.media;

import ir.adventure.observer.client.core.org.telegram.api.game.TLGame;
import ir.adventure.observer.client.core.org.telegram.tl.StreamingUtils;
import ir.adventure.observer.client.core.org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL message media venue.
 */
public class TLMessageMediaGame extends TLAbsMessageMedia {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xfdb19008;

    private TLGame game;

    /**
     * Instantiates a new TL message media venue.
     */
    public TLMessageMediaGame() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLGame getGame() {
        return game;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(game, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        game = StreamingUtils.readTLObject(stream, context, TLGame.class);
    }

    @Override
    public String toString() {
        return "messageMediaGame#fdb19008";
    }
}