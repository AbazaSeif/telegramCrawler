package ir.adventure.observer.client.core.org.telegram.api.page.block;

import ir.adventure.observer.client.core.org.telegram.api.richtext.TLAbsRichText;
import ir.adventure.observer.client.core.org.telegram.tl.StreamingUtils;
import ir.adventure.observer.client.core.org.telegram.tl.TLContext;
import ir.adventure.observer.client.core.org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLPageBlockSlideshow extends TLAbsPageBlock {
    public static final int CLASS_ID = 0x130c8963;

    private TLVector<TLAbsPageBlock> items;
    private TLAbsRichText caption;

    public TLPageBlockSlideshow() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsPageBlock> getItems() {
        return items;
    }

    public TLAbsRichText getCaption() {
        return caption;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(items, stream);
        StreamingUtils.writeTLObject(caption, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        items = StreamingUtils.readTLVector(stream, context, TLAbsPageBlock.class);
        caption = StreamingUtils.readTLObject(stream, context, TLAbsRichText.class);
    }

    @Override
    public String toString() {
        return "pageBlockSlideshow#130c8963";
    }
}
