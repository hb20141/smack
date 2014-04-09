package org.jivesoftware.custom.provider;

import org.jivesoftware.custom.packet.ChatHistory;
import org.jivesoftware.custom.packet.ConversationOperationResultMessage;
import org.jivesoftware.custom.packet.Conversations;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

/**
 * 会话列表对应的Provider
 */
public class ConversationsProvider implements IQProvider {

    public static final String CONVERSATION_NAMESPACE = "urn:xmpp:hb_session_log";
    public static final String CONVERSATION_ELEMENT_NAME = "retrieve";
    public static final String CONVERSATION_GROUP_ELEMENT_NAME = "retrievegroupchat";
    public static final String CONVERSATION_ITEM_NAMESPACE = "http://jabber.org/protocol/rsm";

    @Override
    public IQ parseIQ(XmlPullParser parser) throws Exception {
        boolean done = false;
        Conversations conversation = new Conversations();
        int eventType;
        while (!done) {
            eventType = parser.getEventType();

            if(eventType == XmlPullParser.START_DOCUMENT) {

            } else if(eventType == XmlPullParser.START_TAG) {
                String elementName = parser.getName();
                String namespace = parser.getNamespace();

                if (elementName.equalsIgnoreCase("retrieve")
                        && namespace.equalsIgnoreCase(CONVERSATION_NAMESPACE)) {

                } else if (elementName.equalsIgnoreCase("retrievegroupchat")
                        && namespace.equalsIgnoreCase(CONVERSATION_NAMESPACE)) {

                } else if (elementName.equalsIgnoreCase("item") && namespace.equalsIgnoreCase(CONVERSATION_NAMESPACE)) {
                    conversation.addItem(parseItem(parser));
                } else if (elementName.equalsIgnoreCase("set")
                        && namespace.equals(CONVERSATION_ITEM_NAMESPACE)) {

                } else if (elementName.equalsIgnoreCase("first")
                        && namespace.equals(CONVERSATION_ITEM_NAMESPACE)) {
                    String first = parser.nextText();
                    conversation.setFirst(Integer.valueOf(first));
                } else if (elementName.equalsIgnoreCase("last")
                        && namespace.equals(CONVERSATION_ITEM_NAMESPACE)) {
                    String last = parser.nextText();
                    conversation.setLast(Integer.valueOf(last));
                } else if (elementName.equalsIgnoreCase("count")
                        && namespace.equals(CONVERSATION_ITEM_NAMESPACE)) {
                    String count = parser.nextText();
                    conversation.setCount(Integer.valueOf(count));
                }

            } else if(eventType == XmlPullParser.END_TAG) {
                if (parser.getName().equalsIgnoreCase("retrieve")
                        || parser.getName().equalsIgnoreCase("retrievegroupchat"))
                    done = true;
            } else if(eventType == XmlPullParser.TEXT) {

            }

            if (!done) parser.next();

        }

        return conversation;
    }

    private Conversations.Item parseItem(XmlPullParser parser) throws Exception {
        boolean done = false;
        int eventType;
        Conversations.Item item = new Conversations.Item();
        while(!done) {
            eventType = parser.getEventType();
            if (eventType == XmlPullParser.START_TAG) {
                String elementName = parser.getName();
                if (elementName.equalsIgnoreCase("item")) {
                    String jid = parser.getAttributeValue("", "jid");
                    String type = parser.getAttributeValue("", "type");
                    String name = parser.getAttributeValue("", "name");
                    String unread = parser.getAttributeValue("", "unread");
                    String notify = parser.getAttributeValue("", "notify");
                    String isDeleted = parser.getAttributeValue("", "isdeleted");
                    String created = parser.getAttributeValue("", "created");
                    String changed = parser.getAttributeValue("", "changed");

                    item.setJid(jid);
                    item.setType(type);
                    item.setUnread(unread);
                    item.setName(name);
                    item.setNotify(notify);
                    item.setDeleted(isDeleted);
                    item.setCreated(created);
                    item.setChanged(changed);
                } else if (elementName.equalsIgnoreCase("member")) {
                    String jid = parser.getAttributeValue("", "jid");
                    item.addMember(jid);
                } else if (elementName.equalsIgnoreCase("message")) {
                    item.setMessage(parseItemMessage(parser));
                }
            } else if (eventType == XmlPullParser.END_TAG) {
                if (parser.getName().equalsIgnoreCase("item"))
                    done = true;
            }
            if (!done) parser.next();
        }
        return item;
    }


    private ChatHistory.Message parseItemMessage(XmlPullParser parser) throws Exception {
        ChatHistory.Message message = new ChatHistory.Message();
        ChatHistory.Delay delay = new ChatHistory.Delay();
        ConversationOperationResultMessage orm = new ConversationOperationResultMessage();
        int eventType;
        boolean done = false;
        while (!done) {
            eventType = parser.getEventType();
            if (eventType == XmlPullParser.START_TAG) {
                String elementName = parser.getName();
                if (elementName.equalsIgnoreCase("message")) {
                    String to = parser.getAttributeValue("", "to");
                    String type = parser.getAttributeValue("", "type");
                    String from = parser.getAttributeValue("", "from");
                    String messageId = parser.getAttributeValue("", "mid");
//                    String dateTime = parser.getAttributeValue("", "datetime");
                    String user = parser.getAttributeValue("", "user");

                    message.setTo(to);
                    message.setFrom(from);
                    message.setType(type);
                    message.setMessageId(messageId);
//                    message.setDateTime(dateTime);
                    message.setUser(user);
                } else if (elementName.equalsIgnoreCase("delay")) {
                    String from = parser.getAttributeValue("", "from");
                    String timeStamp = parser.getAttributeValue("", "stamp");
                    delay.setFrom(from);
                    delay.setStamp(timeStamp);
                } else if (elementName.equalsIgnoreCase("body")) {
                    String body = parser.nextText();
                    message.setBody(body);
                } else if (elementName.equalsIgnoreCase("payload")) { // 有附件的情况
                    String fileId = parser.getAttributeValue("", "id");
                    String fileType = parser.getAttributeValue("", "type");
                    String duration = parser.getAttributeValue("", "duration");
                    String width = parser.getAttributeValue("", "width");
                    String height = parser.getAttributeValue("", "height");
                    String token = parser.getAttributeValue("", "token");
                    message.setAttachmentId(fileId);
                    message.setAttachmentType(fileType);
                    message.setDuration(duration);
                    message.setAttachmentWidth(width);
                    message.setAttachmentHeight(height);
                    message.setToken(token);

                } else if (elementName.equalsIgnoreCase("x")) {
                    String operator = parser.getAttributeValue("", "operator");
                    String operation = parser.getAttributeValue("", "operation");
                    String value = parser.getAttributeValue("", "value");

                    orm.setOpetator(operator);
                    orm.setOperaton(operation);
                    orm.setValue(value);
                } else if (elementName.equalsIgnoreCase("item")) {
                    String jid = parser.getAttributeValue("", "jid");
                    ConversationOperationResultMessage.Item item = new ConversationOperationResultMessage.Item();
                    item.setJid(jid);
                    orm.addItem(item);
                }
            } else if (eventType == XmlPullParser.END_TAG) {
                if (parser.getName().equalsIgnoreCase("message"))
                    done = true;
            }

            if (!done) parser.next();

        }
        message.setDelay(delay);
        message.setOperationMessage(orm);
        return message;
    }

}
