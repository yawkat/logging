package at.yawk.logging.jul;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * @author yawkat
 */
public class FormatterBuilder {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator", "\n");

    private boolean time;
    private boolean date;
    private boolean level;

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

    public Formatter build() {
        return new FormatterImpl(time, date, level);
    }

    public void build(Handler handler) {
        handler.setFormatter(build());
    }

    private static class FormatterImpl extends Formatter {
        private static final int MAX_LEVEL_LENGTH = 7;

        private final boolean time;
        private final boolean date;
        private final boolean level;

        private FormatterImpl(boolean time, boolean date, boolean level) {
            this.time = time;
            this.date = date;
            this.level = level;
        }

        @Override
        public String format(LogRecord record) {
            StringBuilder builder = new StringBuilder();
            if (time || date) {
                long millis = record.getMillis();
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(millis);

                builder.append('[');
                // who needs date formats?
                if (date) {
                    builder.append(calendar.get(Calendar.YEAR))
                            .append('-')
                            .append(calendar.get(Calendar.MONTH))
                            .append('-')
                            .append(calendar.get(Calendar.DAY_OF_MONTH));
                    if (time) { builder.append(' '); }
                }
                if (time) {
                    builder.append(calendar.get(Calendar.HOUR_OF_DAY))
                            .append(':')
                            .append(calendar.get(Calendar.MINUTE))
                            .append(':')
                            .append(calendar.get(Calendar.SECOND));
                }
                builder.append("] ");
            }

            if (level) {
                builder.append('[');
                String levelName = record.getLevel().getName();
                // pad with spaces
                for (int i = levelName.length(); i < MAX_LEVEL_LENGTH; i++) { builder.append(' '); }
                builder.append(levelName);
                builder.append("] ");
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
