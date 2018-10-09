package ir.adventure.observer.client.core.handlers;

import ir.adventure.observer.client.core.org.telegram.api.chat.channel.TLChannel;
import ir.adventure.observer.client.core.org.telegram.api.engine.RpcException;
import ir.adventure.observer.client.core.org.telegram.api.user.TLUser;
import ir.adventure.observer.client.core.org.telegram.bot.TelegramFunctionCallback;
import ir.adventure.observer.client.core.org.telegram.bot.structure.Chat;
import ir.adventure.observer.client.core.org.telegram.bot.structure.IUser;
import ir.adventure.observer.client.core.org.telegram.tl.TLMethod;
import ir.adventure.observer.client.core.org.telegram.tl.TLObject;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutionException;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Interface for the helper class to perform Telegram API request
 * @date 22 of March of 2016
 */
public interface IKernelComm{
    boolean init();

    public void performMarkAsReadUser(@NotNull TLUser user, int messageId) throws RpcException ;
    <T extends TLObject> T doRpcCallSync(TLMethod<T> method) throws ExecutionException, RpcException;

    <T extends TLObject> T doRpcCallSyncNoAuth(TLMethod<T> method) throws ExecutionException, RpcException;

    <T extends TLObject> void doRpcCallAsync(TLMethod<T> method, TelegramFunctionCallback<T> callback);

    void doRpcCallAsyncNoReturn(TLMethod<TLObject> method);

    void performMarkAsRead(@NotNull IUser user, int messageId) throws RpcException;

    void performMarkGroupAsRead(@NotNull Chat group, int messageId) throws RpcException;
    void performMarkChannelAsRead(@NotNull TLChannel channel, int messageId) throws RpcException;

    int getCurrentUserId();

    TelegramApi getApi();
}
