package org.jivesoftware.custom.provider;

import org.jivesoftware.custom.packet.ConversationOperationResultMessage;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.provider.PacketExtensionProvider;
import org.xmlpull.v1.XmlPullParser;

public class MUCOperationMessageProvider implements PacketExtensionProvider {
	
	public static String SESSION_NAMESPACE   = "http://huoban.com/protocol/session";
	public static String SESSION_ELEMENTNAME = "x";  

	@Override
	public PacketExtension parseExtension(XmlPullParser parser) throws Exception {
		
		boolean done = false;
		int eventType;
		ConversationOperationResultMessage operationMessage = new ConversationOperationResultMessage();
		
		while (!done) {
			eventType = parser.getEventType();
			
			if(eventType == XmlPullParser.START_DOCUMENT) {
				
			} else if(eventType == XmlPullParser.START_TAG) {
				String elementName = parser.getName();
	            String namespace = parser.getNamespace();
	            
	            if (elementName.equalsIgnoreCase(SESSION_ELEMENTNAME) 
	            		&& namespace.equalsIgnoreCase(SESSION_NAMESPACE)) {
	            	String operator = parser.getAttributeValue("", "operator");
	            	String operation = parser.getAttributeValue("", "operation");
                    String value = parser.getAttributeValue("", "value");

	            	operationMessage.setOpetator(operator);
	            	operationMessage.setOperaton(operation);
                    operationMessage.setValue(value);
	            	
	            }  else if (elementName.equalsIgnoreCase("item")) {
	            	operationMessage.addItem(parseInviteMessage(parser));
	            }
			} else if(eventType == XmlPullParser.END_TAG) {
				if (parser.getName().equalsIgnoreCase(SESSION_ELEMENTNAME))
					done = true;
			} else if(eventType == XmlPullParser.TEXT) {
				
			}
			 
			if (!done) parser.next();
			
		}
		
		return operationMessage;
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
