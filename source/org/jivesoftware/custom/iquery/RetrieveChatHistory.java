package org.jivesoftware.custom.iquery;

import org.jivesoftware.custom.IMRemoteConsants;
import org.jivesoftware.smack.packet.IQ;

/**
 * 获取会话历史的IQ
 */
public class RetrieveChatHistory extends IQ {
	
	private String sid;
	private String UTC;
	private String limit;
	private String index;
    private String order;

	public RetrieveChatHistory(String sid, String UTC, String limit, String index, String order) {
		this.sid = sid + "@" + IMRemoteConsants.XMPP_TARGET_SERVER;
		this.UTC = UTC;
		this.limit = limit;
		this.index = index;
        this.order = order;
	}

	@Override
	public String getChildElementXML() {
		return "<retrieve xmlns=\"urn:xmpp:hb_message_log\" with=\""
				+ sid + "\">"
				+ "<set>" //  xmlns="http://jabber.org/protocol/rsm"
				+ "<max>" + limit + "</max>"
				+ "<index>" + index + "</index>"
				+ "<" + order + ">" + UTC + "</" + order + ">"
				+ "</set>"
				+ "</retrieve>";
	}

}
