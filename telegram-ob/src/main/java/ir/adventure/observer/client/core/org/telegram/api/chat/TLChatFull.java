/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package ir.adventure.observer.client.core.org.telegram.api.chat;

import ir.adventure.observer.client.core.org.telegram.api.bot.TLBotInfo;
import ir.adventure.observer.client.core.org.telegram.api.chat.invite.TLChatInviteExported;
import ir.adventure.observer.client.core.org.telegram.api.chat.participant.chatparticipants.TLAbsChatParticipants;
import ir.adventure.observer.client.core.org.telegram.api.peer.notify.settings.TLAbsPeerNotifySettings;
import ir.adventure.observer.client.core.org.telegram.api.photo.TLAbsPhoto;
import ir.adventure.observer.client.core.org.telegram.tl.StreamingUtils;
import ir.adventure.observer.client.core.org.telegram.tl.TLContext;
import ir.adventure.observer.client.core.org.telegram.tl.TLVector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Full information of a chat
 * @author Ruben Bermudez
 * @version 2.0
 * @date 02 of May of 2015
 */
public class TLChatFull extends TLAbsChatFull {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x2e02a614;

    private TLAbsChatParticipants participants; ///< List of chat participants
    private TLVector<TLBotInfo> botInfo = new TLVector<>();

    /**
     * Instantiates a new TL chat full.
     */
    public TLChatFull() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets participants.
     *
     * @return the participants
     */
    public TLAbsChatParticipants getParticipants() {
        return this.participants;
    }

    /**
     * Sets participants.
     *
     * @param value the value
     */
    public void setParticipants(TLAbsChatParticipants value) {
        this.participants = value;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeInt(this.id, stream);
        StreamingUtils.writeTLObject(this.participants, stream);
        StreamingUtils.writeTLObject(this.photo, stream);
        StreamingUtils.writeTLObject(this.notifySettings, stream);
        StreamingUtils.writeTLObject(this.exportedInvite, stream);
        StreamingUtils.writeTLVector(this.botInfo, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.id = StreamingUtils.readInt(stream);
        this.participants = ((TLAbsChatParticipants) StreamingUtils.readTLObject(stream, context));
        this.photo = ((TLAbsPhoto) StreamingUtils.readTLObject(stream, context));
        this.notifySettings = ((TLAbsPeerNotifySettings) StreamingUtils.readTLObject(stream, context));
        this.exportedInvite = ((TLChatInviteExported) StreamingUtils.readTLObject(stream, context));
        this.botInfo = (TLVector<TLBotInfo>) StreamingUtils.readTLVector(stream, context);
    }

    public String toString() {
        return "chatFull#2e02a614";
    }
}