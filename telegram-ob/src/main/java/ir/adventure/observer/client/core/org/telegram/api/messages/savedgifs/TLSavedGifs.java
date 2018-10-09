/**
 * This file is part of Support Bot.
 *
 *     Foobar is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Foobar is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
package ir.adventure.observer.client.core.org.telegram.api.messages.savedgifs;

import ir.adventure.observer.client.core.org.telegram.api.document.TLAbsDocument;
import ir.adventure.observer.client.core.org.telegram.tl.StreamingUtils;
import ir.adventure.observer.client.core.org.telegram.tl.TLContext;
import ir.adventure.observer.client.core.org.telegram.tl.TLObject;
import ir.adventure.observer.client.core.org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 13 of February of 2016
 */
public class TLSavedGifs extends TLObject {
    public static final int CLASS_ID = 0x2e0709a5;

    private int hash;
    private TLVector<TLAbsDocument> gifs;

    public TLSavedGifs() {
        super();
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    public TLVector<TLAbsDocument> getGifs() {
        return gifs;
    }

    public void setGifs(TLVector<TLAbsDocument> gifs) {
        this.gifs = gifs;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(hash, stream);
        StreamingUtils.writeTLVector(gifs, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        hash = StreamingUtils.readInt(stream);
        gifs = (TLVector<TLAbsDocument>) StreamingUtils.readTLVector(stream, context);
    }

    @Override
    public String toString() {
        return "savedgifs#2e0709a5";
    }
}
