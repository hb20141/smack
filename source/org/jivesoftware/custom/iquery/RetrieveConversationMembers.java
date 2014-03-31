package org.jivesoftware.custom.iquery;

import org.jivesoftware.custom.IMRemoteConsants;
import org.jivesoftware.smack.packet.IQ;

/**
 * 获取会话成员的IQ
 */
public class RetrieveConversationMembers extends IQ {
	
	public RetrieveConversationMembers(String target) {
		target += "@" + IMRemoteConsants.XMPP_TARGET_SERVER;
		setTo(target);
		setType(Type.GET);
	}

	@Override
	public String getChildElementXML() {
		return "<query xmlns=\"http://jabber.org/protocol/disco#items\"></query>";
	}

}
