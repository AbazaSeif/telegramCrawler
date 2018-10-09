package ir.adventure.observer.client.core.org.telegram.api.keyboard.button;

import ir.adventure.observer.client.core.org.telegram.tl.TLObject;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Represetation of a button in keyboard
 * @date 07 of July of 2015
 */
public abstract class TLAbsKeyboardButton extends TLObject {
    protected String text;

    TLAbsKeyboardButton() {
        super();
    }

    public String getText() {
        return text;
    }
}
