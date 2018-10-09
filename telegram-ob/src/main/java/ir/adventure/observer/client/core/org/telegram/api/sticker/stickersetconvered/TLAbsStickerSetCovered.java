package ir.adventure.observer.client.core.org.telegram.api.sticker.stickersetconvered;

import ir.adventure.observer.client.core.org.telegram.api.document.TLAbsDocument;
import ir.adventure.observer.client.core.org.telegram.api.sticker.set.TLStickerSet;
import ir.adventure.observer.client.core.org.telegram.tl.TLObject;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 07 of August of 2016
 */
public abstract class TLAbsStickerSetCovered extends TLObject {
    protected TLStickerSet set;
    protected TLAbsDocument cover;

    public TLStickerSet getSet() {
        return set;
    }

    public TLAbsDocument getCover() {
        return cover;
    }
}
