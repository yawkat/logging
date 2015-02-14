/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package at.yawk.logging.ansi;

/**
 * @author yawkat
 */
public interface AnsiCodeSelector {
    AnsiCode code(SupportedAnsiCode code);

    AnsiCode reset();

    AnsiCode bold();

    AnsiCode dim();

    AnsiCode underline();

    AnsiCode blink();

    AnsiCode invert();

    AnsiCode hide();

    AnsiCode defaultColor();

    AnsiCode black();

    AnsiCode red();

    AnsiCode green();

    AnsiCode yellow();

    AnsiCode blue();

    AnsiCode magenta();

    AnsiCode cyan();

    AnsiCode lightGray();

    AnsiCode lightRed();

    AnsiCode lightGreen();

    AnsiCode lightYellow();

    AnsiCode lightBlue();

    AnsiCode lightMagenta();

    AnsiCode lightCyan();

    AnsiCode white();

    AnsiCodeSelector requireSupported();
}
