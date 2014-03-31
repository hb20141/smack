package org.jivesoftware.custom.iquery;

import org.jivesoftware.custom.IMRemoteConsants;
import org.jivesoftware.custom.provider.HBSessionProvider;
import org.jivesoftware.smack.packet.IQ;

/**
 * 创建会话的IQ
 */
public class RetrieveHBSession extends IQ {
	
	private String with;
	private String name;

	private boolean isGroup = true;
	
	public RetrieveHBSession(String with, String name) {
		this.with = with;
		if (this.with != null && !this.with.equals("")) {
            isGroup = false;
            this.with = this.with +  "@" + IMRemoteConsants.XMPP_SERVER;
        }
        else {
            this.name = name;
        }
		
		setTo(IMRemoteConsants.XMPP_TARGET_SERVER);
	}

	@Override
	public String getChildElementXML() {
		String xml;
		if (isGroup) 
			xml = "<create xmlns=\"" + HBSessionProvider.SESSION_NAMESPACE + "\" type=\"groupchat\" name=\"" + this.name + "\"/>";
		else
			xml = "<create xmlns=\"" + HBSessionProvider.SESSION_NAMESPACE + "\" type=\"chat\" with=\"" + this.with + "\"/>";
		return xml;
	}

}
