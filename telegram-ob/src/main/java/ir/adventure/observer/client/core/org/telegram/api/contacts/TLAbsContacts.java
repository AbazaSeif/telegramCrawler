/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package ir.adventure.observer.client.core.org.telegram.api.contacts;

import ir.adventure.observer.client.core.org.telegram.tl.TLObject;

/**
 * Abstract class to represent information of user's contacts
 * @author Ruben Bermudez
 * @version 2.0
 * @date 02 of May of 2015
 */
public abstract class TLAbsContacts extends TLObject {
    /**
     * Instantiates a new TL abs contacts.
     */
    protected TLAbsContacts() {
        super();
    }
}