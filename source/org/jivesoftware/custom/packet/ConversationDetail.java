package org.jivesoftware.custom.packet;

import org.jivesoftware.smack.packet.IQ;

import java.util.HashMap;

/**
 * 会话详情的Model
 */
public class ConversationDetail extends IQ {
	
	private HashMap<String, String> resultMap = new HashMap<String, String>();
	
	public void putValue(String key, String value) {
		this.resultMap.put(key, value);
	}
	
	public String getValue(String key) {
		return this.resultMap.get(key);
	}

	@Override
	public String getChildElementXML() {
		return null;
	}

}
