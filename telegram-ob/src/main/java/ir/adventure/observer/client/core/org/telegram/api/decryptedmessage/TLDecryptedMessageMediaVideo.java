/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package ir.adventure.observer.client.core.org.telegram.api.decryptedmessage;

import ir.adventure.observer.client.core.org.telegram.tl.StreamingUtils;
import ir.adventure.observer.client.core.org.telegram.tl.TLBytes;
import ir.adventure.observer.client.core.org.telegram.tl.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Video media content of an encrypted message
 * @author Ruben Bermudez
 * @version 2.0
 * @date 02 of May of 2015
 */
public class TLDecryptedMessageMediaVideo extends TLAbsDecryptedMessageMedia {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x524a415d;

    private TLBytes thumb; ///< Content of thumbnail file (JPEG file, quality 55, set in a square 90x90)
    private int thumbW; ///< Thumbnail width
    private int thumbH; ///< Thumbnail height
    private int duration; ///< Duration of video in seconds
    private String mimeType; ///< MIME-type of the video file
    private int w; ///< Video width
    private int h; ///< Video height
    private int size; ///< File size
    private TLBytes key; ///< Key to decrypt the attached video file
    private TLBytes iv; ///< Initialization vector

    /**
     * Instantiates a new TL decrypted message media video.
     */
    public TLDecryptedMessageMediaVideo() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets thumb.
     *
     * @return the thumb
     */
    public TLBytes getThumb() {
        return this.thumb;
    }

    /**
     * Sets thumb.
     *
     * @param thumb the thumb
     */
    public void setThumb(TLBytes thumb) {
        this.thumb = thumb;
    }

    /**
     * Gets thumb w.
     *
     * @return the thumb w
     */
    public int getThumbW() {
        return this.thumbW;
    }

    /**
     * Sets thumb w.
     *
     * @param thumbW the thumb w
     */
    public void setThumbW(int thumbW) {
        this.thumbW = thumbW;
    }

    /**
     * Gets thumb h.
     *
     * @return the thumb h
     */
    public int getThumbH() {
        return this.thumbH;
    }

    /**
     * Sets thumb h.
     *
     * @param thumbH the thumb h
     */
    public void setThumbH(int thumbH) {
        this.thumbH = thumbH;
    }

    /**
     * Gets duration.
     *
     * @return the duration
     */
    public int getDuration() {
        return this.duration;
    }

    /**
     * Sets duration.
     *
     * @param duration the duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Gets mime type.
     *
     * @return the mime type
     */
    public String getMimeType() {
        return this.mimeType;
    }

    /**
     * Sets mime type.
     *
     * @param mimeType the mime type
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    /**
     * Gets w.
     *
     * @return the w
     */
    public int getW() {
        return this.w;
    }

    /**
     * Sets w.
     *
     * @param w the w
     */
    public void setW(int w) {
        this.w = w;
    }

    /**
     * Gets h.
     *
     * @return the h
     */
    public int getH() {
        return this.h;
    }

    /**
     * Sets h.
     *
     * @param h the h
     */
    public void setH(int h) {
        this.h = h;
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Sets size.
     *
     * @param size the size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Gets key.
     *
     * @return the key
     */
    public TLBytes getKey() {
        return this.key;
    }

    /**
     * Sets key.
     *
     * @param key the key
     */
    public void setKey(TLBytes key) {
        this.key = key;
    }

    /**
     * Gets iv.
     *
     * @return the iv
     */
    public TLBytes getIv() {
        return this.iv;
    }

    /**
     * Sets iv.
     *
     * @param iv the iv
     */
    public void setIv(TLBytes iv) {
        this.iv = iv;
    }

    @Override
    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLBytes(this.thumb, stream);
        StreamingUtils.writeInt(this.thumbW, stream);
        StreamingUtils.writeInt(this.thumbH, stream);
        StreamingUtils.writeInt(this.duration, stream);
        StreamingUtils.writeTLString(this.mimeType, stream);
        StreamingUtils.writeInt(this.w, stream);
        StreamingUtils.writeInt(this.h, stream);
        StreamingUtils.writeInt(this.size, stream);
        StreamingUtils.writeTLBytes(this.key, stream);
        StreamingUtils.writeTLBytes(this.iv, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.thumb = StreamingUtils.readTLBytes(stream, context);
        this.thumbW = StreamingUtils.readInt(stream);
        this.thumbH = StreamingUtils.readInt(stream);
        this.duration = StreamingUtils.readInt(stream);
        this.mimeType = StreamingUtils.readTLString(stream);
        this.w = StreamingUtils.readInt(stream);
        this.h = StreamingUtils.readInt(stream);
        this.size = StreamingUtils.readInt(stream);
        this.key = StreamingUtils.readTLBytes(stream, context);
        this.iv = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "decryptedMessageMediaVideo#524a415d";
    }
}