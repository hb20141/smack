package org.jivesoftware.custom.iquery;

import java.util.List;

import org.jivesoftware.custom.IMRemoteConsants;
import org.jivesoftware.custom.provider.MUCOperationProvider;
import org.jivesoftware.smack.packet.IQ;

public class MUCOperation extends IQ {

	private String sid;
	private List<String> userIds;
	private String operation;
	
	public MUCOperation(String sid, List<String> userIds, String operation) {
		this.sid = sid;
		this.userIds = userIds;
		this.operation = operation;
		
		this.setType(Type.SET);
		this.setTo(this.sid + "@" + IMRemoteConsants.XMPP_TARGET_SERVER);
	}
	@Override
	public String getChildElementXML() {
		String xml1 = "<" + operation + " xmlns=\"" + MUCOperationProvider.SESSION_NAMESPACE + "\">";
		String xml2 = "";
		for (String user : userIds) {
			user += "@" + IMRemoteConsants.XMPP_SERVER;
			xml2 += "<item jid=\"" + user + "\"/>";
		}
		String xml3 = "</" + operation + ">";
		return xml1 + xml2 + xml3;
	}

}
