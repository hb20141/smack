package org.jivesoftware.custom.provider;

import org.jivesoftware.custom.packet.MUCOperationResult;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

/**
 * 群组相关操作的Provider
 */
public class MUCOperationProvider implements IQProvider {
	
	public static final String SESSION_NAMESPACE   = "http://huoban.com/protocol/session";
	public static final String INVITE_ELEMENTNAME  = "invite";
	public static final String KICK_ELEMENTNAME    = "kick";
	public static final String LEAVE_ELEMENTNAME   = "leave";
    public static final String RENAME_ELEMENTNAME  = "rename";

	@Override
	public IQ parseIQ(XmlPullParser parser) throws Exception {
		boolean done = false;
		int eventType;
		MUCOperationResult message = new MUCOperationResult();
		
		while (!done) {
			eventType = parser.getEventType();
			
			if(eventType == XmlPullParser.START_DOCUMENT) {
				
			} else if(eventType == XmlPullParser.START_TAG) {
				String elementName = parser.getName();
	            String namespace = parser.getNamespace();
	            
	            if ((elementName.equalsIgnoreCase(INVITE_ELEMENTNAME) 
	            		|| elementName.equalsIgnoreCase(KICK_ELEMENTNAME) 
	            		|| elementName.equalsIgnoreCase(LEAVE_ELEMENTNAME))
	            		&& namespace.equalsIgnoreCase(SESSION_NAMESPACE)) {
	            	message.setOperation(elementName);
	            } else if (elementName.equalsIgnoreCase("item")) {
	            	message.addItem(parseInviteMessage(parser));
	            }
			} else if(eventType == XmlPullParser.END_TAG) {
				if (parser.getName().equalsIgnoreCase(INVITE_ELEMENTNAME) 
						|| parser.getName().equalsIgnoreCase(KICK_ELEMENTNAME) 
						|| parser.getName().equalsIgnoreCase(LEAVE_ELEMENTNAME))
					done = true;
			} else if(eventType == XmlPullParser.TEXT) {
				
			}
			 
			if (!done) parser.next();
			
		}
		
		return message;
	}
	
	public MUCOperationResult.Item parseInviteMessage(XmlPullParser parser) throws Exception {
		boolean done = false;
		int eventType;
		MUCOperationResult.Item item = new MUCOperationResult.Item();
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
