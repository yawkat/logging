/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package at.yawk.logging.jul;

import at.yawk.logging.ansi.Ansi;
import at.yawk.logging.ansi.AnsiCode;
import at.yawk.logging.ansi.SupportedAnsiCode;
import java.util.logging.Handler;
import java.util.logging.Level;
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

    public static AnsiCode getColor(Level level) {
        int levelValue = level.intValue();
        if (levelValue >= Level.SEVERE.intValue()) {
            return SupportedAnsiCode.RED;
        }
        if (levelValue >= Level.WARNING.intValue()) {
            return SupportedAnsiCode.YELLOW;
        }
        if (levelValue >= Level.INFO.intValue()) {
            return SupportedAnsiCode.CYAN;
        }
        return Ansi.unsupportedCode();
    }
}
