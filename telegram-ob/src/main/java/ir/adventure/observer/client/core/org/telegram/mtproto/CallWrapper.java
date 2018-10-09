package ir.adventure.observer.client.core.org.telegram.mtproto;

import ir.adventure.observer.client.core.org.telegram.tl.TLMethod;
import ir.adventure.observer.client.core.org.telegram.tl.TLObject;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 07.11.13
 * Time: 3:56
 */
public interface CallWrapper {
    TLObject wrapObject(TLMethod srcRequest);
}
