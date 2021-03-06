package ir.adventure.observer.client.core.org.telegram.api.functions.channels;

import ir.adventure.observer.client.core.org.telegram.api.channel.participants.role.TLAbsChannelParticipantRole;
import ir.adventure.observer.client.core.org.telegram.api.input.chat.TLAbsInputChannel;
import ir.adventure.observer.client.core.org.telegram.api.input.user.TLAbsInputUser;
import ir.adventure.observer.client.core.org.telegram.api.updates.TLAbsUpdates;
import ir.adventure.observer.client.core.org.telegram.tl.StreamingUtils;
import ir.adventure.observer.client.core.org.telegram.tl.TLContext;
import ir.adventure.observer.client.core.org.telegram.tl.TLMethod;
import ir.adventure.observer.client.core.org.telegram.tl.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request channel edit admin
 */
public class TLRequestChannelsEditAdmin extends TLMethod<TLAbsUpdates> {
    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xeb7611d0;

    private TLAbsInputChannel channel;
    private TLAbsInputUser userId;
    private TLAbsChannelParticipantRole role;

    /**
     * Instantiates a new TL request channel edit admin
     */
    public TLRequestChannelsEditAdmin() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLAbsUpdates)) {
            return (TLAbsUpdates) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLAbsUpdates.class.getName() +", got: " + res.getClass().getName());
    }

    public TLAbsInputChannel getChannel() {
        return channel;
    }

    public void setChannel(TLAbsInputChannel channel) {
        this.channel = channel;
    }

    public TLAbsInputUser getUserId() {
        return userId;
    }

    public void setUserId(TLAbsInputUser userId) {
        this.userId = userId;
    }

    public TLAbsChannelParticipantRole getRole() {
        return role;
    }

    public void setRole(TLAbsChannelParticipantRole role) {
        this.role = role;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.channel, stream);
        StreamingUtils.writeTLObject(this.userId, stream);
        StreamingUtils.writeTLObject(this.role, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.channel = (TLAbsInputChannel) StreamingUtils.readTLObject(stream, context);
        this.userId = (TLAbsInputUser) StreamingUtils.readTLObject(stream, context);
        this.role = (TLAbsChannelParticipantRole) StreamingUtils.readTLObject(stream, context);
    }

    public String toString() {
        return "channels.editAdmin#eb7611d0";
    }
}