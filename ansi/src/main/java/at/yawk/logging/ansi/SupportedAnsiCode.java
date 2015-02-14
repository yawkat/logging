package at.yawk.logging.ansi;

/**
 * @author yawkat
 */
public enum SupportedAnsiCode implements AnsiCode {
    RESET(0),

    BOLD(1),
    DIM(2),
    UNDERLINE(4),
    BLINK(5),
    INVERT(7),
    HIDE(8),

    RESET_BOLD(21),
    RESET_DIM(22),
    RESET_UNDERLINE(24),
    RESET_BLINK(25),
    RESET_INVERT(27),
    RESET_HIDE(28),

    DEFAULT(39),
    BLACK(30),
    RED(31),
    GREEN(32),
    YELLOW(33),
    BLUE(34),
    MAGENTA(35),
    CYAN(36),
    LIGHT_GRAY(37),
    DARK_GRAY(90),
    LIGHT_RED(91),
    LIGHT_GREEN(92),
    LIGHT_YELLOW(93),
    LIGHT_BLUE(94),
    LIGHT_MAGENTA(95),
    LIGHT_CYAN(96),
    WHITE(97),

    BG_DEFAULT(49),
    BG_BLACK(40),
    BG_RED(41),
    BG_GREEN(42),
    BG_YELLOW(43),
    BG_BLUE(44),
    BG_MAGENTA(45),
    BG_CYAN(46),
    BG_LIGHT_GRAY(47),
    BG_DARK_GRAY(100),
    BG_LIGHT_RED(101),
    BG_LIGHT_GREEN(102),
    BG_LIGHT_YELLOW(103),
    BG_LIGHT_BLUE(104),
    BG_LIGHT_MAGENTA(105),
    BG_LIGHT_CYAN(106),
    BG_WHITE(107);

    private static final SupportedAnsiCode[] byId = new SupportedAnsiCode[108];

    private final int id;

    private SupportedAnsiCode bg;
    private SupportedAnsiCode fg;
    private SupportedAnsiCode reset;

    static {
        for (SupportedAnsiCode code : values()) {
            byId[code.getId()] = code;
        }

        SupportedAnsiCode[] values = values();
        for (int i = 0; i < values.length; i++) {
            SupportedAnsiCode code = values[i];
            if ((code.id >= 30 && code.id < 40) ||
                (code.id >= 90 && code.id < 100)) {
                code.bg = byId(code.id + 10);
            }
            if ((code.id >= 40 && code.id < 50) || code.id >= 100) {
                code.bg = byId(code.id - 10);
            }
            if (code.id >= 1 && code.id < 10) {
                code.reset = byId(code.id + 20);
            }
        }
    }

    SupportedAnsiCode(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public SupportedAnsiCode bg() {
        if (bg == null) {
            throw new UnsupportedOperationException(name() + " has no background equivalent");
        }
        return bg;
    }

    @Override
    public AnsiCode requireSupported() {
        return Ansi.isSupported() ? this : UnsupportedAnsiCode.instance;
    }

    public SupportedAnsiCode fg() {
        if (fg == null) {
            throw new UnsupportedOperationException(name() + " has no foreground equivalent");
        }
        return fg;
    }

    @Override
    public SupportedAnsiCode reset() {
        if (reset == null) {
            throw new UnsupportedOperationException(name() + " has no reset equivalent");
        }
        return reset;
    }

    @Override
    public String toString() {
        return "\033[" + id + "m";
    }

    public static SupportedAnsiCode byId(int id) {
        if (id >= byId.length || id < 0) { return null; }
        return byId[id];
    }
}
