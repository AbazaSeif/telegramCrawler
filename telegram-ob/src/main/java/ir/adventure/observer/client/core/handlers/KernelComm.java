package ir.adventure.observer.client.core.handlers;


import ir.adventure.observer.client.core.org.telegram.api.chat.channel.TLChannel;
import ir.adventure.observer.client.core.org.telegram.api.engine.*;
import ir.adventure.observer.client.core.org.telegram.api.engine.storage.AbsApiState;
import ir.adventure.observer.client.core.org.telegram.api.functions.channels.TLRequestChannelsReadHistory;
import ir.adventure.observer.client.core.org.telegram.api.functions.messages.TLRequestMessagesReadHistory;
import ir.adventure.observer.client.core.org.telegram.api.input.chat.TLInputChannel;
import ir.adventure.observer.client.core.org.telegram.api.input.peer.TLAbsInputPeer;
import ir.adventure.observer.client.core.org.telegram.api.input.peer.TLInputPeerChannel;
import ir.adventure.observer.client.core.org.telegram.api.message.entity.TLAbsMessageEntity;
import ir.adventure.observer.client.core.org.telegram.api.message.entity.TLMessageEntityBold;
import ir.adventure.observer.client.core.org.telegram.api.message.entity.TLMessageEntityCode;
import ir.adventure.observer.client.core.org.telegram.api.message.entity.TLMessageEntityItalic;
import ir.adventure.observer.client.core.org.telegram.api.updates.TLAbsUpdates;
import ir.adventure.observer.client.core.org.telegram.api.user.TLUser;
import ir.adventure.observer.client.core.org.telegram.bot.GenericErrorTelegramFunctionCallback;
import ir.adventure.observer.client.core.org.telegram.bot.TelegramFunctionCallback;
import ir.adventure.observer.client.core.org.telegram.bot.services.BotLogger;
import ir.adventure.observer.client.core.org.telegram.bot.structure.Chat;
import ir.adventure.observer.client.core.org.telegram.bot.structure.IUser;
import ir.adventure.observer.client.core.org.telegram.tl.TLMethod;
import ir.adventure.observer.client.core.org.telegram.tl.TLObject;
import ir.adventure.observer.client.core.org.telegram.tl.TLVector;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.concurrent.*;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hendrik Hofstadt
 * @author Ruben Bermudez
 * @version 2.0
 * @brief Communication service to send messages via Telegram
 * @date 16.03.14
 */
public class KernelComm implements IKernelComm {
    private static final String LOGTAG = "KERNELCOMM";
    private static final String LANGUAGE_EN = "en";
    private static final String botVersion = "0.1";
    private static final String botName = "Adventure";
    private static final int BYTES = 1024;

    private static final Pattern boldMarkdownRegex = Pattern.compile("\\*.+?\\*");
    private static final Pattern italicMarkdownRegex =  Pattern.compile("\\_.+?\\_");
    private static final Pattern codeMarkdownRegex = Pattern.compile("\\`.+?\\`");

    private final ExecutorService exe = Executors.newCachedThreadPool();
    private final SecureRandom random = new SecureRandom();
    private final AbsApiState apiState;
    private final int apiKey;
    private TelegramApi api;

    public KernelComm(int apiKey, AbsApiState apiState) {

        this.apiKey = apiKey;
        this.apiState = apiState;
        try {
            final File URANDOM_FILE = new File("/dev/urandom");
            final FileInputStream sUrandomIn = new FileInputStream(URANDOM_FILE);
            final byte[] buffer = new byte[BYTES];
            sUrandomIn.read(buffer);
            sUrandomIn.close();
            this.random.setSeed(buffer);
        } catch (FileNotFoundException e) {
            BotLogger.info(LOGTAG, e);
        } catch (Exception e) {
            BotLogger.error(LOGTAG, e);
        }
    }

    @Override
    public boolean init() {
        BotLogger.debug(LOGTAG, "Initializing kernelComm");
        createApi();
        BotLogger.debug(LOGTAG, "KernelComm initialized");
        return true;
    }



    private void createApi() {
        BotLogger.trace(LOGTAG, "Creating TelegramApi");
        this.api = new TelegramApi(apiState, new AppInfo(apiKey, "bot", botName, botVersion, LANGUAGE_EN), new ApiCallback() {

            @Override
            public void onAuthCancelled(TelegramApi api) {
                apiState.resetAuth();
                BotLogger.severe(LOGTAG, "Auth cancelled");
            }

            @Override
            public void onUpdatesInvalidated(TelegramApi api) {
                if (apiState.isAuthenticated()) {

                }
            }

            @Override
            public void onUpdate(TLAbsUpdates updates) {

            }
        });
        BotLogger.debug(LOGTAG, "Api created");
    }



    @Override
    public <T extends TLObject> T doRpcCallSync(final TLMethod<T> method) throws ExecutionException, RpcException {
        T answer = null;
        if (getApi() != null) {
            final Future<T> result = this.exe.submit(() -> {
                T internalAnswer = null;
                try {
                    internalAnswer = getApi().doRpcCall(method);
                } catch (RpcException e) {
                    BotLogger.debug(LOGTAG, "Rpc call failed", e);
                    throw e;
                } catch (TimeoutException e) {
                    BotLogger.debug(LOGTAG, "timeout");
                } catch (Exception e) {
                    BotLogger.error(LOGTAG, "Bot threw an unexpected exception at KernelComm-doRpcCallSync", e);
                }

                return internalAnswer;
            });
            try {
                answer = result.get();
            } catch (InterruptedException e) {
                BotLogger.error(LOGTAG, e);
            } catch (Exception e) {
                BotLogger.error(LOGTAG, "Bot threw an unexpected exception at KernelComm-doRpcCallSync", e);
            }
        }


        return answer;
    }

    @Override
    public <T extends TLObject> T doRpcCallSyncNoAuth(final TLMethod<T> method) throws ExecutionException {
        T answer = null;
        final Future<T> result = this.exe.submit(() -> {
            T answerInternal = null;
            try {
                answerInternal = this.api.doRpcCallNonAuth(method);
            } catch (RpcException e) {
                BotLogger.error(LOGTAG, "Rpc call failed", e);
                throw e;
            } catch (Exception e) {
                BotLogger.error(LOGTAG, "Bot threw an unexpected exception at KernelComm-doRpcCallSyncNoAuth");
            }
            return answerInternal;
        });
        try {
            answer = result.get();
        } catch (InterruptedException e) {
            BotLogger.error(LOGTAG, e);
        } catch (Exception e) {
            BotLogger.severe(LOGTAG, "Bot threw an unexpected exception at KernelComm-doRpcCallSyncNoAuth");
        }

        return answer;
    }

    @Override
    public <T extends TLObject> void doRpcCallAsync(final TLMethod<T> method, TelegramFunctionCallback<T> callback) {
        if (getApi() != null) {
            final CompletableFuture<T> result = CompletableFuture.supplyAsync(() -> {
                T internalAnswer = null;
                try {
                    internalAnswer = getApi().doRpcCall(method);

                } catch (RpcException e) {
                    BotLogger.debug(LOGTAG, "Rpc call failed", e);
                    callback.onRpcError(e);
                } catch (TimeoutException e) {
                    BotLogger.debug(LOGTAG, "timeout");
                   // callback.onTimeout(e);
                } catch (Exception e) {
                    BotLogger.error(LOGTAG, "Bot threw an unexpected exception at KernelComm-doRpcCallSync", e);
                    callback.onUnknownError(e);
                }

                return internalAnswer;
            });
            result.handleAsync((ok, ex) -> {
                if (ok != null) {
                    callback.onSuccess(ok);
                } else {
                    callback.onUnknownError(ex);
                }
                return null;
            });
        }
    }

    @Override
    public void doRpcCallAsyncNoReturn(final TLMethod<TLObject> method) {
        this.exe.submit(() -> {
            try {
                this.api.doRpcCall(method);
            } catch (RpcException e) {
                BotLogger.severe(LOGTAG, "Rpc call failed", e);
            } catch (TimeoutException e) {
                BotLogger.severe(LOGTAG, "timeout");
            } catch (IOException e) {
                BotLogger.error(LOGTAG, e);
            } catch (Exception e) {
                BotLogger.error(LOGTAG, "Bot threw an unexpected exception at KernelComm-doRpcCallAsyncNoReturn");
            }
        });
    }

    @Override
    public void performMarkAsRead(@NotNull IUser user, int messageId) throws RpcException {
        performMarkAsReadInternal(TLFactory.createTLInputPeer(user, null), messageId);
    }
    @Override
    public void performMarkAsReadUser(@NotNull TLUser user, int messageId) throws RpcException {
        performMarkAsReadInternal(TLFactory.createTLInputPeerUser(user), messageId);
    }

    @Override
    public void performMarkGroupAsRead(@NotNull Chat group, int messageId) throws RpcException {
        performMarkAsReadInternal(TLFactory.createTLInputPeer(null, group), messageId);
    }
    @Override
    public void performMarkChannelAsRead(@NotNull TLChannel channel, int messageId) throws RpcException {
        performMarkAsReadInternal(TLFactory.createTLInputPeerChannel(channel), messageId);
    }

    private void performMarkAsReadInternal(TLAbsInputPeer inputPeer, int messageId) throws RpcException {
        try {
            if (inputPeer instanceof TLInputPeerChannel) {
                final TLInputChannel inputChannel = new TLInputChannel();
                inputChannel.setChannelId(((TLInputPeerChannel) inputPeer).getChannelId());
                inputChannel.setAccessHash(((TLInputPeerChannel) inputPeer).getAccessHash());
                final TLRequestChannelsReadHistory tlRequestChannelsReadHistory = new TLRequestChannelsReadHistory();
                tlRequestChannelsReadHistory.setChannel(inputChannel);
                tlRequestChannelsReadHistory.setMaxId(messageId);
                doRpcCallSync(tlRequestChannelsReadHistory);
            } else {
                final TLRequestMessagesReadHistory tlRequestMessagesReadHistory = new TLRequestMessagesReadHistory();
                tlRequestMessagesReadHistory.setPeer(inputPeer);
                tlRequestMessagesReadHistory.setMaxId(messageId);
                doRpcCallSync(tlRequestMessagesReadHistory);
            }

        } catch (ExecutionException e) {
            BotLogger.severe(LOGTAG, e);
        }
    }

    @Override
    public int getCurrentUserId() {
        return this.apiState.getUserId();
    }

    @NotNull
    private String readEntities(@NotNull String message, @NotNull TLVector<TLAbsMessageEntity> entities) {
        message = readBoldEntities(entities, message);
        message = readItalicEntities(entities, message);
        message = readCodeEntities(entities, message);

        return message;
    }

    @NotNull
    private String readBoldEntities(@NotNull TLVector<TLAbsMessageEntity> entities, @NotNull String message) {
        final StringBuilder finalMessage = new StringBuilder();
        int lastAddedIndex = 0;
        final Matcher matcher = boldMarkdownRegex.matcher(message);

        while (matcher.find()) {
            final int startIndex = matcher.start();
            final int lastIndex = matcher.end();
            finalMessage.append(message.substring(lastAddedIndex, startIndex));
            final int initMarkdown = finalMessage.length();
            finalMessage.append(message.substring(startIndex + 1, lastIndex-1));
            lastAddedIndex = lastIndex;
            final TLMessageEntityBold boldEntity = new TLMessageEntityBold();
            boldEntity.setOffset(initMarkdown);
            boldEntity.setLength(lastIndex - startIndex - 2);
            entities.add(boldEntity);
        }

        if (lastAddedIndex != message.length()) {
            finalMessage.append(message.substring(lastAddedIndex));
        }

        return finalMessage.toString();
    }

    @NotNull
    private String readItalicEntities(@NotNull TLVector<TLAbsMessageEntity> entities, @NotNull String message) {
        final StringBuilder finalMessage = new StringBuilder();
        int lastAddedIndex = 0;
        final Matcher matcher = italicMarkdownRegex.matcher(message);

        while (matcher.find()) {
            final int startIndex = matcher.start();
            final int lastIndex = matcher.end();
            finalMessage.append(message.substring(lastAddedIndex, startIndex));
            final int initMarkdown = finalMessage.length();
            finalMessage.append(message.substring(startIndex + 1, lastIndex-1));
            lastAddedIndex = lastIndex;
            final TLMessageEntityItalic italicEntity = new TLMessageEntityItalic();
            italicEntity.setOffset(initMarkdown);
            italicEntity.setLength(lastIndex - startIndex - 2);
            entities.add(italicEntity);
        }

        if (lastAddedIndex != message.length()) {
            finalMessage.append(message.substring(lastAddedIndex));
        }

        return finalMessage.toString();
    }

    @NotNull
    private String readCodeEntities(@NotNull TLVector<TLAbsMessageEntity> entities, @NotNull String message) {
        final StringBuilder finalMessage = new StringBuilder();
        int lastAddedIndex = 0;
        final Matcher matcher = codeMarkdownRegex.matcher(message);

        while (matcher.find()) {
            final int startIndex = matcher.start();
            final int lastIndex = matcher.end();
            finalMessage.append(message.substring(lastAddedIndex, startIndex));
            final int initMarkdown = finalMessage.length();
            finalMessage.append(message.substring(startIndex + 1, lastIndex-1));
            lastAddedIndex = lastIndex;
            final TLMessageEntityCode codeEntity = new TLMessageEntityCode();
            codeEntity.setOffset(initMarkdown);
            codeEntity.setLength(lastIndex - startIndex - 2);
            entities.add(codeEntity);
        }

        if (lastAddedIndex != message.length()) {
            finalMessage.append(message.substring(lastAddedIndex));
        }

        return finalMessage.toString();
    }

    @Override
    public TelegramApi getApi() {
        return this.api;
    }

    @Contract("_ -> !null")
    private TelegramFunctionCallback<TLAbsUpdates> getDefaultSendMessageCallback(final @NotNull IUser user) {
        return new GenericErrorTelegramFunctionCallback<TLAbsUpdates>() {
            @Override
            public void onError(Throwable e) {
                BotLogger.error(LOGTAG, "Failed sending message to: " + user.getUserId(), e);
            }

            @Override
            public void onSuccess(TLAbsUpdates result) {

            }
        };
    }


    @Override
    protected void finalize() throws Throwable {
        //notificationsService.removeObserver(this, notificationsService.updatesInvalidated);
        super.finalize();
    }
}