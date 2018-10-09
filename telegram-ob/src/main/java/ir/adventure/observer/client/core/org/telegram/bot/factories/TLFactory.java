package ir.adventure.observer.client.core.org.telegram.bot.factories;

import ir.adventure.observer.client.core.org.telegram.api.input.peer.*;
import ir.adventure.observer.client.core.org.telegram.api.input.user.TLAbsInputUser;
import ir.adventure.observer.client.core.org.telegram.api.input.user.TLInputUser;
import ir.adventure.observer.client.core.org.telegram.api.input.user.TLInputUserSelf;
import ir.adventure.observer.client.core.org.telegram.bot.structure.Chat;
import ir.adventure.observer.client.core.org.telegram.bot.structure.IUser;

/**
 * @author Ruben Bermudez
 * @version 2.0
 * @brief Factory for TLAbsInputUser
 * @date 27 of May of 2015
 */
public class TLFactory {
    public static TLAbsInputUser createTLInputUser(IUser user) {
        final TLAbsInputUser tlAbsInputUser;
        if (user == null) {
            tlAbsInputUser = new TLInputUserSelf();
        } else {
            final TLInputUser tlInputUser = new TLInputUser();
            tlInputUser.setUserId(user.getUserId());
            tlInputUser.setAccessHash(user.getUserHash());
            tlAbsInputUser = tlInputUser;
        }
        return tlAbsInputUser;
    }

    public static TLAbsInputPeer createTLInputPeer(IUser user, Chat chat) {
        final TLAbsInputPeer tlInputPeer;
        if (user == null) {
            if (chat == null) {
                tlInputPeer = new TLInputPeerSelf();
            } else {
                if (chat.isChannel()) {
                    final TLInputPeerChannel inputPeerChannel = new TLInputPeerChannel();
                    inputPeerChannel.setChannelId(chat.getId());
                    inputPeerChannel.setAccessHash(chat.getAccessHash());
                    tlInputPeer = inputPeerChannel;
                } else {
                    final TLInputPeerChat tlInputPeerChat = new TLInputPeerChat();
                    tlInputPeerChat.setChatId(chat.getId());
                    tlInputPeer = tlInputPeerChat;
                }
            }
        } else {
            final TLInputPeerUser tlInputPeerUser = new TLInputPeerUser();
            tlInputPeerUser.setUserId(user.getUserId());
            tlInputPeerUser.setAccessHash(user.getUserHash());
            tlInputPeer = tlInputPeerUser;
        }

        return tlInputPeer;
    }
}
