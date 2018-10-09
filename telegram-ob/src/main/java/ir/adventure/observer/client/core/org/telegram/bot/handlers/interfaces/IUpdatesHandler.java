package ir.adventure.observer.client.core.org.telegram.bot.handlers.interfaces;

import ir.adventure.observer.client.core.org.telegram.api.chat.TLAbsChat;
import ir.adventure.observer.client.core.org.telegram.api.message.TLAbsMessage;
import ir.adventure.observer.client.core.org.telegram.api.update.TLAbsUpdate;
import ir.adventure.observer.client.core.org.telegram.api.updates.TLUpdatesState;
import ir.adventure.observer.client.core.org.telegram.api.updates.difference.TLAbsDifference;
import ir.adventure.observer.client.core.org.telegram.api.user.TLAbsUser;
import ir.adventure.observer.client.core.org.telegram.bot.kernel.UpdateWrapper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Updates handler interface
 * @date 21 of March of 2016
 */
public interface IUpdatesHandler {
    void getDifferences();
    void onTLAbsDifference(@NotNull TLAbsDifference absDifference);
    void onTLChannelDifferences(List<TLAbsUser> users, List<TLAbsMessage> messages, List<TLAbsUpdate> newUpdates, List<TLAbsChat> chats);
    void updateStateModification(TLUpdatesState state);

    boolean checkSeq(int seq, int seqStart, int date);
    void processUpdate(UpdateWrapper updateWrapper);
    void onTLUpdatesTooLong();
    void onUsers(List<TLAbsUser> users);
    void onChats(List<TLAbsChat> chats);
}
