package ir.adventure.observer.client.core.handlers;

import ir.adventure.observer.client.core.org.telegram.api.engine.storage.AbsApiState;
import ir.adventure.observer.client.core.org.telegram.bot.kernel.engine.MemoryApiState;
import ir.adventure.observer.client.core.org.telegram.bot.services.BotLogger;
import ir.adventure.observer.client.core.org.telegram.bot.structure.BotConfig;
import ir.adventure.observer.client.core.org.telegram.bot.structure.LoginStatus;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by jalil on 3/25/2018.
 */
public class TelegramClientCore {
    private static final String LOGTAG = "KERNELMAIN";
    private final BotConfig config;
    private final int apiKey;
    private final String apiHash;
    private AbsApiState apiState;
    private KernelAuth kernelAuth;
    private KernelComm kernelComm;


    public TelegramClientCore(BotConfig config, int apiKey, String apiHash) {
        if (config == null) {
            throw new NullPointerException("At least a BotConfig must be added");
        }

        BotLogger.info(LOGTAG, "--------------KERNEL CREATED--------------");
        this.apiKey = apiKey;
        this.apiHash = apiHash;
        this.config = config;

    }

    public KernelAuth getKernelAuth() {
        return kernelAuth;
    }

    public LoginStatus init() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        BotLogger.debug(LOGTAG, "Creating API");
        apiState = new MemoryApiState(config.getAuthfile());
        BotLogger.debug(LOGTAG, "API created");
        createKernelComm(); // Only set up threads and assign api state
        createKernelAuth(); // Only assign api state to kernel auth
        initKernelComm(); // Create TelegramApi and run the updates handler threads
        final LoginStatus loginResult = startKernelAuth(); // Perform login if necessary
        BotLogger.info(LOGTAG, "----------------BOT READY-----------------");
        return loginResult;
    }

    private void createKernelComm() {
        final long start = System.currentTimeMillis();
        this.kernelComm = new KernelComm(apiKey, apiState);
        BotLogger.info(LOGTAG, String.format("%s init in %d ms", kernelComm.getClass().getName(), (start - System.currentTimeMillis()) * -1));
    }
    private void createKernelAuth() {
        final long start = System.currentTimeMillis();
        this.kernelAuth = new KernelAuth(this.apiState, config, this.kernelComm, apiKey, apiHash);
        BotLogger.info(LOGTAG, String.format("%s init in %d ms", this.kernelAuth.getClass().getName(), (start - System.currentTimeMillis()) * -1));
    }

    private void initKernelComm() {
        final long start = System.currentTimeMillis();
        this.kernelComm.init();
        BotLogger.info(LOGTAG, String.format("%s init in %d ms", this.kernelComm.getClass().getName(), (start - System.currentTimeMillis()) * -1));
    }

    public KernelComm getKernelComm() {
        return kernelComm;
    }

    private LoginStatus startKernelAuth() {
        final long start = System.currentTimeMillis();
        final LoginStatus status = this.kernelAuth.start();
        BotLogger.info(LOGTAG, String.format("%s started in %d ms", this.kernelAuth.getClass().getName(), (start - System.currentTimeMillis()) * -1));
        return status;
    }
}
