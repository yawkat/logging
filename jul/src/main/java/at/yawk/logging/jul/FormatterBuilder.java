package at.yawk.logging.jul;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.logging.*;

/**
 * @author yawkat
 */
public class FormatterBuilder {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator", "\n");

    private boolean time;
    private boolean date;
    private boolean level;
    private ColorMode colorMode = ColorMode.AUTO;

    private FormatterBuilder() {}

    public static FormatterBuilder create() {
        return createDefault();
    }

    public static FormatterBuilder createDefault() {
        return createTimeLevel();
    }

    public static FormatterBuilder createTimeLevel() {
        return new FormatterBuilder()
                .showDate(false)
                .showTime(true)
                .showLevel(true);
    }

    public static FormatterBuilder createTimeDateLevel() {
        return createTimeLevel().showDate(true);
    }

    public FormatterBuilder showTime(boolean time) {
        this.time = time;
        return this;
    }

    public FormatterBuilder showDate(boolean date) {
        this.date = date;
        return this;
    }

    public FormatterBuilder showLevel(boolean level) {
        this.level = level;
        return this;
    }

    public FormatterBuilder colorMode(ColorMode mode) {
        if (mode == null) { throw new NullPointerException("mode"); }
        this.colorMode = mode;
        return this;
    }

    public Formatter build() {
        return build(false); // assume this isn't a console handler
    }

    private Formatter build(boolean isConsoleHandler) {
        boolean color = false;
        switch (colorMode) {
        case AUTO:
            // no color if we aren't in console
            if (isConsoleHandler && System.console() == null) { break; }
        case ALWAYS:
            color = true;
            break;
        }
        return new FormatterImpl(time, date, level, color);
    }

    public void build(Handler handler) {
        boolean isConsoleHandler = false;
        if (handler instanceof ConsoleHandler) {
            isConsoleHandler = true;
        } else if (handler instanceof StreamHandler) {
            // check if this is a StreamHandler with output set to stdout or stderr
            try {
                Field outputField = StreamHandler.class.getDeclaredField("output");
                outputField.setAccessible(true);
                Object output = outputField.get(handler);
                isConsoleHandler =
                        output == System.out ||
                        output == System.err;

                // ignore reflection and security exceptions
            } catch (Exception ignored) {}
        }
        handler.setFormatter(build(isConsoleHandler));
    }

    private static class FormatterImpl extends Formatter {
        private static final int MAX_LEVEL_LENGTH = 7;

        private final boolean time;
        private final boolean date;
        private final boolean level;
        private final boolean ansiColor;

        private FormatterImpl(boolean time, boolean date, boolean level, boolean ansiColor) {
            this.time = time;
            this.date = date;
            this.level = level;
            this.ansiColor = ansiColor;
        }

        @Override
        public String format(LogRecord record) {
            StringBuilder builder = new StringBuilder();
            if (time || date) {
                long millis = record.getMillis();
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(millis);

                if (ansiColor) { builder.append("\033[90m"); }
                builder.append('[');
                // who needs date formats?
                if (date) {
                    int y = calendar.get(Calendar.YEAR);
                    int m = calendar.get(Calendar.MONTH);
                    int d = calendar.get(Calendar.DAY_OF_MONTH);
                    if (y < 10) { builder.append('0'); }
                    builder.append(y);
                    builder.append('-');
                    if (m < 10) { builder.append('0'); }
                    builder.append(m);
                    builder.append('-');
                    if (d < 10) { builder.append('0'); }
                    builder.append(d);
                    if (time) { builder.append(' '); }
                }
                if (time) {
                    int h = calendar.get(Calendar.HOUR_OF_DAY);
                    int m = calendar.get(Calendar.MINUTE);
                    int s = calendar.get(Calendar.SECOND);
                    if (h < 10) { builder.append('0'); }
                    builder.append(h);
                    builder.append(':');
                    if (m < 10) { builder.append('0'); }
                    builder.append(m);
                    builder.append(':');
                    if (s < 10) { builder.append('0'); }
                    builder.append(s);
                }
                builder.append("] ");
            }

            if (level) {
                builder.append('[');
                Level lvl = record.getLevel();
                boolean resetColor = ansiColor;
                if (ansiColor) {
                    int levelValue = lvl.intValue();
                    if (levelValue >= Level.SEVERE.intValue()) {
                        builder.append("\033[31m"); // red
                    } else if (levelValue >= Level.WARNING.intValue()) {
                        builder.append("\033[33m"); // yellow
                    } else if (levelValue >= Level.INFO.intValue()) {
                        builder.append("\033[36m"); // cyan
                    } else {
                        resetColor = false;
                    }
                }
                String levelName = lvl.getName();
                // pad with spaces
                for (int i = levelName.length(); i < MAX_LEVEL_LENGTH; i++) { builder.append(' '); }
                builder.append(levelName);
                if (resetColor) {
                    builder.append("\033[90m");
                }
                builder.append("] ");
                if (resetColor) {
                    builder.append("\033[39m");
                }
            }

            String message = record.getMessage();
            if (message != null) {
                builder.append(message);
            }
            builder.append(LINE_SEPARATOR);

            @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
            Throwable exception = record.getThrown();
            if (exception != null) {
                StringWriter sw = new StringWriter();
                exception.printStackTrace(new PrintWriter(sw));
                builder.append(sw.getBuffer());
            }

            return builder.toString();
        }
    }
}
