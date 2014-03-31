package org.jivesoftware.custom.provider;

import org.jivesoftware.custom.packet.ConversationOperationResult;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

public class ConversationOperationProvider implements IQProvider {
	
	public static String NAMESPACE = "http://huoban.com/protocol/session";
    public static String COP_READ_ELEMENT   = "read";
	public static String COP_REMOVE_ELEMENT = "remove";
	public static String COP_RENAME_ELEMENT = "rename";
	public static String COP_NOTIFY_ELEMENT = "notify";

	@Override
	public IQ parseIQ(XmlPullParser parser) throws Exception {
		boolean done = false;
		int eventType;
		ConversationOperationResult result = new ConversationOperationResult();
		
		while (!done) {
			eventType = parser.getEventType();
			
			if(eventType == XmlPullParser.START_DOCUMENT) {
				
			} else if(eventType == XmlPullParser.START_TAG) {
				String elementName = parser.getName();
	            String namespace = parser.getNamespace();

                if (elementName.equalsIgnoreCase(COP_READ_ELEMENT)
                        && namespace.equalsIgnoreCase(NAMESPACE)) {
                    result.setOperation(COP_READ_ELEMENT);
                } else if (elementName.equalsIgnoreCase(COP_REMOVE_ELEMENT)
	            		&& namespace.equalsIgnoreCase(NAMESPACE)) {
	            	result.setOperation(COP_REMOVE_ELEMENT);
	            } else if (elementName.equalsIgnoreCase(COP_RENAME_ELEMENT)
	            		&& namespace.equalsIgnoreCase(NAMESPACE)) {
	            	result.setOperation(COP_RENAME_ELEMENT);
	            	String name = parser.getAttributeValue("", "name");
	            	result.setName(name);
	            } else if (elementName.equalsIgnoreCase(COP_NOTIFY_ELEMENT)
	            		&& namespace.equalsIgnoreCase(NAMESPACE)) {
	            	result.setOperation(COP_NOTIFY_ELEMENT);
	            	String value = parser.getAttributeValue("", "value");
	            	result.setValue(value);
	            } 
			} else if(eventType == XmlPullParser.END_TAG) {
				if (parser.getName().equalsIgnoreCase(COP_REMOVE_ELEMENT) 
						|| parser.getName().equalsIgnoreCase(COP_RENAME_ELEMENT) 
						|| parser.getName().equalsIgnoreCase(COP_NOTIFY_ELEMENT)
                        || parser.getName().equalsIgnoreCase(COP_READ_ELEMENT))
					done = true;
			} else if(eventType == XmlPullParser.TEXT) {
				
			}
			 
			if (!done) parser.next();
			
		}
		
		return result;
	}

}
