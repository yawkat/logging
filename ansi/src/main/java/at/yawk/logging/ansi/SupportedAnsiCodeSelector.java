/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

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
