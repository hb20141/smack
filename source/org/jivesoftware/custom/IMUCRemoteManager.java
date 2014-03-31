package org.jivesoftware.custom;

import java.util.List;

import org.jivesoftware.custom.packet.ConversationMembers;
import org.jivesoftware.custom.packet.MUCOperationResult;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.ServiceDiscoveryManager;

/**
 * 从会话相关操作分离出的一部分用于明确表示群组的操作,如踢人(kick),
 * 离开(leave),重命名(rename),邀请(invite)等操作, 目前rename没有在此执行,
 * 待调整
 */
public class IMUCRemoteManager {

    public static MUCOperationResult exeMUCOperation(Connection connection, String sid, List<String> userIds, String operation) {
        MUCOperationResult result = null;
        try {
            result = ServiceDiscoveryManager
                    .getInstanceFor(connection).retrieveMUCOperation(sid, userIds, operation);
            return result;
        }
        catch (XMPPException e) {
            e.printStackTrace();
            result = new MUCOperationResult();
            result.setError(e.getXMPPError());
            return result;
        }

    }

    public static ConversationMembers retrieveConversationMembers(Connection connection, String target) {
        ConversationMembers result = null;
        try {
            result = ServiceDiscoveryManager
                    .getInstanceFor(connection).retrieveConversationMembers(target);
            return result;
        }
        catch (XMPPException e) {
            e.printStackTrace();
            result = new ConversationMembers();
            result.setError(e.getXMPPError());
            return result;
        }
    }

}
