package ir.adventure.observer.client.core.org.telegram.bot.handlers.interfaces;

import ir.adventure.observer.client.core.org.telegram.api.chat.TLAbsChat;

import java.util.List;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 01 of April of 2016
 */
public interface IChatsHandler {
    void onChats(List<TLAbsChat> chats);
}
