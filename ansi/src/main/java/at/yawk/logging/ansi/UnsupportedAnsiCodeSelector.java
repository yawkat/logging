/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package at.yawk.logging.ansi;

/**
 * @author yawkat
 */
class UnsupportedAnsiCodeSelector extends AbstractAnsiCodeSelector implements CharSequence {
    static final UnsupportedAnsiCodeSelector instance = new UnsupportedAnsiCodeSelector();

    @Override
    public AnsiCode code(SupportedAnsiCode code) {
        return UnsupportedAnsiCode.instance;
    }

    @Override
    public AnsiCodeSelector requireSupported() {
        return this;
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

    @Override
    public String toString() {
        return "";
    }
}
