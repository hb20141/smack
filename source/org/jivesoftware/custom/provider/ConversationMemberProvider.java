package org.jivesoftware.custom.provider;

import org.jivesoftware.custom.packet.ConversationMembers;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

public class ConversationMemberProvider implements IQProvider {

	public static final String NAMESPACE = "http://jabber.org/protocol/disco#items";
	public static final String ELEMENT = "query";
	
	@Override
	public IQ parseIQ(XmlPullParser parser) throws Exception {
		boolean done = false;
		int eventType;
		ConversationMembers members = new ConversationMembers();
		
		while (!done) {
			eventType = parser.getEventType();
			
			if(eventType == XmlPullParser.START_DOCUMENT) {
				
			} else if(eventType == XmlPullParser.START_TAG) {
				String elementName = parser.getName();
	            String namespace = parser.getNamespace();
	            
	            if ((elementName.equalsIgnoreCase(ELEMENT)) 
	            		&& namespace.equalsIgnoreCase(NAMESPACE)) {
	            } else if (elementName.equalsIgnoreCase("item")) {
	            	members.addItem(parseInviteMessage(parser));
	            }
			} else if(eventType == XmlPullParser.END_TAG) {
				if (parser.getName().equalsIgnoreCase(ELEMENT))
					done = true;
			} else if(eventType == XmlPullParser.TEXT) {
				
			}
			 
			if (!done) parser.next();
			
		}
		
		return members;
	}
	
	public ConversationMembers.Item parseInviteMessage(XmlPullParser parser) throws Exception {
		boolean done = false;
		int eventType;
		ConversationMembers.Item item = new ConversationMembers.Item();
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
