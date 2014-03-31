package org.jivesoftware.custom;

import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.ServiceDiscoveryManager;

import org.jivesoftware.custom.packet.HBSession;

/**
 * 用于创建会话
 */
public class IMRemoteSessionCreator {


    public static HBSession retrieveHBSession(Connection connection, String with, String name) {
        HBSession session = null;
        try {
            session = ServiceDiscoveryManager
                    .getInstanceFor(connection).retriveHBSession(with, name);
            return session;
        }
        catch (XMPPException e) {
            e.printStackTrace();
            session = new HBSession();
            session.setError(e.getXMPPError());
            return session;
        }
    }

}
