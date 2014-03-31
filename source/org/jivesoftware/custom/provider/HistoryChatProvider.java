package org.jivesoftware.custom.provider;

import org.jivesoftware.custom.packet.ConversationOperationResultMessage;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

import org.jivesoftware.custom.packet.ChatHistory;

/**
 * The HistoryChatProvider parses ChatHistory packets. (@see ChatHistory)
 */
public class HistoryChatProvider implements IQProvider {
	
	public static final String HISTORY_CHAT_NAMESPACE		 = "urn:xmpp:hb_message_log";
    public static final String HISTORY_CHAT_ELEMENT_NAME 	 = "retrieve";
    public static final String HISTORY_CHAT_ITEM_NAMESPACE 	 = "http://jabber.org/protocol/rsm";

    public static String X_NAMESPACE   = "http://huoban.com/protocol/session";
    public static String X_ELEMENTNAME = "x";

    // 附件情况暂时不考虑
	@Override
	public IQ parseIQ(XmlPullParser parser) throws Exception {
		ChatHistory chatHistory = new ChatHistory();
		boolean done = false;
		int eventType = -1;
		while(!done) {
			
			eventType = parser.getEventType();
			if(eventType == XmlPullParser.START_DOCUMENT) {
				
			} else if(eventType == XmlPullParser.START_TAG) {
				
				String elementName = parser.getName();
				String namespace = parser.getNamespace();
				if (elementName.equalsIgnoreCase("retrieve")) {
					String target = parser.getAttributeValue("", "with");
					String start = parser.getAttributeValue("", "start"); // deprecated
					String type = parser.getAttributeValue("", "type"); // deprecated
                    chatHistory.setChatType(type);
				} else if (elementName.equalsIgnoreCase("message")) {
					chatHistory.addMessage(parseMessage(parser));
				} else if (elementName.equalsIgnoreCase("set")) {
					
				} else if (elementName.equalsIgnoreCase("first")) {
					chatHistory.setFirst(Integer.valueOf(parser.nextText()));
				} else if (elementName.equalsIgnoreCase("last")) {
					chatHistory.setLast(Integer.valueOf(parser.nextText()));
				} else if (elementName.equalsIgnoreCase("count")) {
					String count = parser.nextText();
					chatHistory.setCount(Integer.parseInt(count));
				}
			} else if(eventType == XmlPullParser.END_TAG) {
				if (parser.getName().equalsIgnoreCase("retrieve"))
					done = true;
			} else if(eventType == XmlPullParser.TEXT) {
				
			}
			if (!done) parser.next();
		}
		return chatHistory;
	}
	
	private ChatHistory.Message parseMessage(XmlPullParser parser) throws Exception {
		boolean done = false;
		int eventType = -1;
		
		ChatHistory.Message message = new ChatHistory.Message();
        ConversationOperationResultMessage operationMessage = new ConversationOperationResultMessage();
		while(!done) {
			eventType = parser.getEventType();
			if (eventType == XmlPullParser.START_TAG) {
				String elementName = parser.getName();
				if (elementName.equalsIgnoreCase("message")) {
					String messageId = parser.getAttributeValue("", "mid");
					String from = parser.getAttributeValue("", "from");
					String to = parser.getAttributeValue("", "to");
					String type = parser.getAttributeValue("", "type");
                    String user = parser.getAttributeValue("", "user");
                    String prevmid = parser.getAttributeValue("", "prevmid");
					message.setMessageId(messageId);
					message.setFrom(from);
					message.setTo(to);
					message.setType(type);
                    message.setUser(user);
                    message.setPrevmid(prevmid);
				} else if (elementName.equalsIgnoreCase("body")) {
                    String clientid = parser.getAttributeValue("", "clientid");
                    String body = parser.nextText();
                    message.setBody(body);
                    message.setClientid(clientid);
                } else if (elementName.equalsIgnoreCase("delay")) {
//					String from = parser.getAttributeValue("", "from");
					String stamp = parser.getAttributeValue("", "stamp");
					message.setStamp(stamp);
				} else if (elementName.equalsIgnoreCase("set")) {
					
				} else if (elementName.equals("payload")) {
                    String attachmentType = parser.getAttributeValue("", "type");
                    String attachmentId = parser.getAttributeValue("", "id");
                    String duration = parser.getAttributeValue("", "duration");
                    message.setAttachmentType(attachmentType);
                    message.setAttachmentId(attachmentId);
                    message.setDuration(duration);
                } else if (elementName.equalsIgnoreCase(X_ELEMENTNAME)) {
                    String operator = parser.getAttributeValue("", "operator");
                    String operation = parser.getAttributeValue("", "operation");
                    String value = parser.getAttributeValue("", "value");

                    operationMessage.setOpetator(operator);
                    operationMessage.setOperaton(operation);
                    operationMessage.setValue(value);

                }  else if (elementName.equalsIgnoreCase("item")) {
                    operationMessage.addItem(parseInviteMessage(parser));
                }
			} else if (eventType == XmlPullParser.END_TAG) {
				if (parser.getName().equalsIgnoreCase("message"))
					done = true;
			}
			if (!done) parser.next();
		}
        message.setOperationMessage(operationMessage);
		return message;
	}

    public ConversationOperationResultMessage.Item parseInviteMessage(XmlPullParser parser) throws Exception {
        boolean done = false;
        int eventType;
        ConversationOperationResultMessage.Item item = new ConversationOperationResultMessage.Item();
        while(!done) {
            eventType = parser.getEventType();

            if(eventType == XmlPullParser.START_TAG) {
                String elementName = parser.getName();

                if (elementName.equalsIgnoreCase("item")) {
                    String jid = parser.getAttributeValue("", "jid");

                    item.setJid(jid);
                }
            } else if(eventType == XmlPullParser.END_TAG) {
                if (parser.getName().equalsIgnoreCase("item"))
                    done = true;
            } else if(eventType == XmlPullParser.TEXT) {

            }

            if (!done) parser.next();

        }

        return item;
    }

}
