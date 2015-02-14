package at.yawk.logging.ansi;

/**
 * @author yawkat
 */
public interface AnsiCode {
    AnsiCode reset();

    AnsiCode bg();

    AnsiCode requireSupported();
}
