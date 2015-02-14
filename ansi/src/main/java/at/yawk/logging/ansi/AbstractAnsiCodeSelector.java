package at.yawk.logging.ansi;

/**
 * @author yawkat
 */
abstract class AbstractAnsiCodeSelector implements AnsiCodeSelector {
    @Override
    public AnsiCode reset() {
        return code(SupportedAnsiCode.RESET);
    }

    @Override
    public AnsiCode bold() {
        return code(SupportedAnsiCode.BOLD);
    }

    @Override
    public AnsiCode dim() {
        return code(SupportedAnsiCode.DIM);
    }

    @Override
    public AnsiCode underline() {
        return code(SupportedAnsiCode.UNDERLINE);
    }

    @Override
    public AnsiCode blink() {
        return code(SupportedAnsiCode.BLINK);
    }

    @Override
    public AnsiCode invert() {
        return code(SupportedAnsiCode.INVERT);
    }

    @Override
    public AnsiCode hide() {
        return code(SupportedAnsiCode.HIDE);
    }

    @Override
    public AnsiCode defaultColor() {
        return code(SupportedAnsiCode.DEFAULT);
    }

    @Override
    public AnsiCode black() {
        return code(SupportedAnsiCode.BLACK);
    }

    @Override
    public AnsiCode red() {
        return code(SupportedAnsiCode.RED);
    }

    @Override
    public AnsiCode green() {
        return code(SupportedAnsiCode.GREEN);
    }

    @Override
    public AnsiCode yellow() {
        return code(SupportedAnsiCode.YELLOW);
    }

    @Override
    public AnsiCode blue() {
        return code(SupportedAnsiCode.BLUE);
    }

    @Override
    public AnsiCode magenta() {
        return code(SupportedAnsiCode.MAGENTA);
    }

    @Override
    public AnsiCode cyan() {
        return code(SupportedAnsiCode.CYAN);
    }

    @Override
    public AnsiCode lightGray() {
        return code(SupportedAnsiCode.LIGHT_GRAY);
    }

    @Override
    public AnsiCode lightRed() {
        return code(SupportedAnsiCode.LIGHT_RED);
    }

    @Override
    public AnsiCode lightGreen() {
        return code(SupportedAnsiCode.LIGHT_GREEN);
    }

    @Override
    public AnsiCode lightYellow() {
        return code(SupportedAnsiCode.LIGHT_YELLOW);
    }

    @Override
    public AnsiCode lightBlue() {
        return code(SupportedAnsiCode.LIGHT_BLUE);
    }

    @Override
    public AnsiCode lightMagenta() {
        return code(SupportedAnsiCode.LIGHT_MAGENTA);
    }

    @Override
    public AnsiCode lightCyan() {
        return code(SupportedAnsiCode.LIGHT_CYAN);
    }

    @Override
    public AnsiCode white() {
        return code(SupportedAnsiCode.WHITE);
    }

    @Override
    public abstract String toString();
}
