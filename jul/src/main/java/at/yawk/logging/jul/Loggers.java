package at.yawk.logging.jul;

import java.util.logging.Handler;
import java.util.logging.Logger;

/**
 * @author yawkat
 */
public class Loggers {
    private Loggers() {}

    public static void clearHandlers(Logger logger) {
        logger.setUseParentHandlers(false);
        for (Handler handler : logger.getHandlers()) {
            logger.removeHandler(handler);
        }
    }

    public static void replaceHandlers(Logger logger, Handler... replacements) {
        clearHandlers(logger);
        for (Handler handler : replacements) {
            logger.addHandler(handler);
        }
    }
}
