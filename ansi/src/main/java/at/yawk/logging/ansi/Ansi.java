/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package at.yawk.logging.ansi;

/**
 * @author yawkat
 */
public class Ansi {
    private static final boolean supported = Boolean.parseBoolean(
            System.getProperty("at.yawk.logging.ansi.enable",
                               Boolean.toString(System.console() != null))
    );

    private Ansi() {}

    public static boolean isSupported() {
        return supported;
    }

    public static AnsiCode unsupportedCode() {
        return UnsupportedAnsiCode.instance;
    }

    public static AnsiCodeSelector supported() {
        return SupportedAnsiCodeSelector.instance;
    }

    public static AnsiCodeSelector unsupported() {
        return UnsupportedAnsiCodeSelector.instance;
    }

    public static AnsiCodeSelector select() {
        return isSupported() ? supported() : unsupported();
    }

    public static AnsiCode reset() {
        return select().reset();
    }

    public static AnsiCode bold() {
        return select().bold();
    }

    public static AnsiCode dim() {
        return select().dim();
    }

    public static AnsiCode underline() {
        return select().underline();
    }

    public static AnsiCode blink() {
        return select().blink();
    }

    public static AnsiCode invert() {
        return select().invert();
    }

    public static AnsiCode hide() {
        return select().hide();
    }

    public static AnsiCode defaultColor() {
        return select().defaultColor();
    }

    public static AnsiCode black() {
        return select().black();
    }

    public static AnsiCode red() {
        return select().red();
    }

    public static AnsiCode green() {
        return select().green();
    }

    public static AnsiCode yellow() {
        return select().yellow();
    }

    public static AnsiCode blue() {
        return select().blue();
    }

    public static AnsiCode magenta() {
        return select().magenta();
    }

    public static AnsiCode cyan() {
        return select().cyan();
    }

    public static AnsiCode lightGray() {
        return select().lightGray();
    }

    public static AnsiCode lightRed() {
        return select().lightRed();
    }

    public static AnsiCode lightGreen() {
        return select().lightGreen();
    }

    public static AnsiCode lightYellow() {
        return select().lightYellow();
    }

    public static AnsiCode lightBlue() {
        return select().lightBlue();
    }

    public static AnsiCode lightMagenta() {
        return select().lightMagenta();
    }

    public static AnsiCode lightCyan() {
        return select().lightCyan();
    }

    public static AnsiCode white() {
        return select().white();
    }
}
