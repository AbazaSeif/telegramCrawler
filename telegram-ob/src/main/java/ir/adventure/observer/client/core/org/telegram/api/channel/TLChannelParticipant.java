package ir.adventure.observer.client.core.org.telegram.api.channel;

import ir.adventure.observer.client.core.org.telegram.api.channel.participants.TLAbsChannelParticipant;
import ir.adventure.observer.client.core.org.telegram.api.user.TLAbsUser;
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
 * @brief Channel participants
 * @date 24 of September of 2015
 */
public class TLChannelParticipant extends TLObject {
    public static final int CLASS_ID = 0xd0d9b163;

    private TLAbsChannelParticipant participant;
    private TLVector<TLAbsUser> users;

    public TLChannelParticipant() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsUser> getUsers() {
        return users;
    }

    public void setUsers(TLVector<TLAbsUser> users) {
        this.users = users;
    }

    public TLAbsChannelParticipant getParticipant() {
        return participant;
    }

    public void setParticipant(TLAbsChannelParticipant participant) {
        this.participant = participant;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(participant, stream);
        StreamingUtils.writeTLVector(users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.participant = (TLAbsChannelParticipant) StreamingUtils.readTLObject(stream, context);
        this.users = (TLVector<TLAbsUser>) StreamingUtils.readTLVector(stream, context);
    }

    @Override
    public String toString() {
        return "channel.TLChannelParticipant#d0d9b163";
    }

}
