package org.jivesoftware.custom.provider;

import org.jivesoftware.custom.packet.HBSession;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

public class HBSessionProvider implements IQProvider {
	
	public static String SESSION_NAMESPACE   = "http://huoban.com/protocol/session";
	public static String SESSION_ELEMENTNAME = "create"; 

	@Override
	public IQ parseIQ(XmlPullParser parser) throws Exception {
		
		boolean done = false;
		int eventType;
		HBSession session = new HBSession();
		
		while (!done) {
			eventType = parser.getEventType();
			
			if(eventType == XmlPullParser.START_DOCUMENT) {
				
			} else if(eventType == XmlPullParser.START_TAG) {
				String elementName = parser.getName();
	            String namespace = parser.getNamespace();
	            
	            if (elementName.equalsIgnoreCase(SESSION_ELEMENTNAME) 
	            		&& namespace.equalsIgnoreCase(SESSION_NAMESPACE)) {
	            	String sid = parser.getAttributeValue("", "sid");
	            	String chatType = parser.getAttributeValue("", "type");
	            	String with = parser.getAttributeValue("", "with");
                    String name = parser.getAttributeValue("", "name");
	            	
	            	session.setSid(sid);
	            	session.setChatType(chatType);
	            	session.setWith(with);
                    session.setName(name);
	            } 
			} else if(eventType == XmlPullParser.END_TAG) {
				if (parser.getName().equalsIgnoreCase(SESSION_ELEMENTNAME))
					done = true;
			} else if(eventType == XmlPullParser.TEXT) {
				
			}
			 
			if (!done) parser.next();
			
		}
		
		return session;
	}

}
