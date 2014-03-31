package org.jivesoftware.custom.provider;

import org.jivesoftware.custom.packet.ConversationDetail;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

public class ConversationDetailProvider implements IQProvider {
	
	public static final String NAMESPACE = "http://jabber.org/protocol/disco#info";
	public static final String ELEMENT = "query";

	@Override
	public IQ parseIQ(XmlPullParser parser) throws Exception {
		boolean done = false;
		int eventType;
		ConversationDetail result = new ConversationDetail();
		String key = null;
		String value = null;
		
		while (!done) {
			
			eventType = parser.next();
			
			if(eventType == XmlPullParser.START_DOCUMENT) {
				
			} else if(eventType == XmlPullParser.START_TAG) {
				String elementName = parser.getName();
	            String namespace = parser.getNamespace();
	            
	            if (elementName.equalsIgnoreCase(ELEMENT)
	            		&& namespace.equalsIgnoreCase(NAMESPACE)) {
	            	
	            } else if (elementName.equalsIgnoreCase("x")) {
	            	
	            } else if (elementName.equalsIgnoreCase("field")) {
	            	key = parser.getAttributeValue("", "var");
	            } else if (elementName.equalsIgnoreCase("value")) {
	            	value = parser.nextText();
	            	result.putValue(key, value);
	            }
			} else if(eventType == XmlPullParser.END_TAG) {
				if (parser.getName().equalsIgnoreCase(ELEMENT))
					done = true;
			} else if(eventType == XmlPullParser.TEXT) {
				
			}
			 
		}
		return result;

	}

}
