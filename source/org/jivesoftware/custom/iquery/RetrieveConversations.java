package org.jivesoftware.custom.iquery;

import org.jivesoftware.smack.packet.IQ;

/**
 * 获取会话列表的IQ
 */
public class RetrieveConversations extends IQ {

	private String max;
	private String index;
	private String before;
	
	public RetrieveConversations(String max, String index, String before) {
		this.max = max;
		this.index = index;
		this.before = before;
	}
	
	@Override
	public String getChildElementXML() {
		return "<retrieve xmlns=\"urn:xmpp:hb_session_log\">"
				+ "<set>"
				+ "<max>" + max + "</max>"
				+ "<index>" + index + "</index>"
				+ "<before>" + before + "</before>"
				+ "</set>"
				+ "</retrieve>";
	}

}
