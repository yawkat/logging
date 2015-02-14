package at.yawk.logging.ansi;

/**
 * @author yawkat
 */
class SupportedAnsiCodeSelector extends AbstractAnsiCodeSelector {
    static final SupportedAnsiCodeSelector instance = new SupportedAnsiCodeSelector();

    @Override
    public String toString() {
        return "";
    }

    @Override
    public AnsiCode code(SupportedAnsiCode code) {
        return code;
    }

    @Override
    public AnsiCodeSelector requireSupported() {
        return Ansi.isSupported() ? this : UnsupportedAnsiCodeSelector.instance;
    }
}
