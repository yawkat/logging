package at.yawk.logging.ansi;

/**
 * @author yawkat
 */
class UnsupportedAnsiCode implements AnsiCode, CharSequence {
    static final UnsupportedAnsiCode instance = new UnsupportedAnsiCode();

    @Override
    public AnsiCode reset() {
        return this;
    }

    @Override
    public AnsiCode bg() {
        return this;
    }

    @Override
    public AnsiCode requireSupported() {
        return this;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public char charAt(int index) {
        throw new StringIndexOutOfBoundsException();
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        if (start != 0 | end != 0) { throw new StringIndexOutOfBoundsException(); }
        return this;
    }
}
