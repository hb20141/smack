package org.jivesoftware.custom;

import org.jivesoftware.custom.packet.ChatHistory;
import org.jivesoftware.custom.packet.ConversationDetail;
import org.jivesoftware.custom.packet.ConversationOperationResult;
import org.jivesoftware.custom.packet.Conversations;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.ServiceDiscoveryManager;

/**
 * 对会话列表进行相关操作的封装类,也包含对单个会话进行的操作
 */
public class IMRemoteConversationsManager {

    /**
     * 回去一个会话的聊天历史
     * @param connection
     * @param sid
     * @param UTC
     * @param limit
     * @param index
     * @param order 按时间向前(before)或向后取(after)
     * @return
     */
    public static ChatHistory retrieveChatHistory(Connection connection, String sid,
                     String UTC, String limit, String index, String order) {
        ChatHistory chatHistory = null;
        try {
             chatHistory = ServiceDiscoveryManager
                    .getInstanceFor(connection).retrieveChatHistory(sid, UTC, limit, index, order);
            return chatHistory;
        }
        catch (XMPPException e) {
            chatHistory = new ChatHistory();
            chatHistory.setError(e.getXMPPError());
            e.printStackTrace();
            return chatHistory;
        }

    }

    /**
     * 将会话标记为已读
     * @param connection
     * @param target
     * @return
     */
    public static ConversationOperationResult readConversation(Connection connection, String target) {
        ConversationOperationResult coResult = null;
        try {
             coResult = ServiceDiscoveryManager
                    .getInstanceFor(connection).readConversation(target);;
            return coResult;
        }
        catch (XMPPException e) {
            e.printStackTrace();
            coResult = new ConversationOperationResult();
            coResult.setError(e.getXMPPError());
            return coResult;
        }
    }

    /**
     * 删除一个会话
     * @param connection
     * @param sid
     * @return
     */
    public static ConversationOperationResult removeConversation(Connection connection, String sid) {
        ConversationOperationResult coResult = null;
        try {
             coResult = ServiceDiscoveryManager
                    .getInstanceFor(connection).removeConversation(sid);;
            return coResult;
        }
        catch (XMPPException e) {
            e.printStackTrace();
            coResult = new ConversationOperationResult();
            coResult.setError(e.getXMPPError());
            return coResult;
        }
    }

    public static ConversationOperationResult renameConversation(Connection connection, String target, String name) {
        ConversationOperationResult coResult = null;
        try {
             coResult = ServiceDiscoveryManager
                    .getInstanceFor(connection).renameConversation(target, name);
            return coResult;
        }
        catch (XMPPException e) {
            e.printStackTrace();
            coResult = new ConversationOperationResult();
            coResult.setError(e.getXMPPError());
            return coResult;
        }
    }

    /**
     * 屏蔽或者接收一个会话的消息
     * @param connection
     * @param target
     * @param notify
     * @return
     */
    public static ConversationOperationResult notifyConversation(Connection connection, String target, boolean notify) {
        ConversationOperationResult coResult = null;
        try {
             coResult = ServiceDiscoveryManager
                    .getInstanceFor(connection).notifyConversation(target, notify);
            return coResult;
        }
        catch (XMPPException e) {
            e.printStackTrace();
            coResult = new ConversationOperationResult();
            coResult.setError(e.getXMPPError());
            return coResult;
        }
    }

    /**
     * 获取会话的详细信息
     * @param connection
     * @param target
     * @return
     */
    public static ConversationDetail retrieveConversationDetail(Connection connection, String target) {
        ConversationDetail result = null;
        try {
             result = ServiceDiscoveryManager
                    .getInstanceFor(connection).retrieveConversationDetail(target);
            return result;
        }
        catch (XMPPException e) {
            e.printStackTrace();
            result = new ConversationDetail();
            result.setError(e.getXMPPError());
            return result;
        }
    }

    /***
     * 获取会话列表
     * @param connection
     * @param max
     * @param index
     * @param before
     * @return
     */
    public static Conversations retrieveConversations(Connection connection, String max, String index, String before) {
        Conversations conversation = null;
        try {
             conversation = ServiceDiscoveryManager
                    .getInstanceFor(connection).retrieveConversations(max, index, before);
            return conversation;
        }
        catch (XMPPException e) {
            e.printStackTrace();
            conversation = new Conversations();
            conversation.setError(e.getXMPPError());
            return conversation;
        }
    }

    /**
     * 获取群组会话列表,目前貌似没用上
     * @param connection
     * @param max
     * @param index
     * @param before
     * @return
     */
    public static Conversations retrieveGroupConversations(Connection connection, String max, String index, String before) {
        Conversations conversation = null;
        try {
             conversation = ServiceDiscoveryManager
                    .getInstanceFor(connection).retrieveGroupConversationList(max, index, before);
            return conversation;
        }
        catch (XMPPException e) {
            e.printStackTrace();
            conversation = new Conversations();
            conversation.setError(e.getXMPPError());
            return conversation;
        }
    }
}
