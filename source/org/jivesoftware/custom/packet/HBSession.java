package org.jivesoftware.custom.packet;

import org.jivesoftware.smack.packet.IQ;

public class HBSession extends IQ {
	
	private String sid = null;
	private String chatType = null;
	private String with = null;
    private String name = null; // 群名字
	
	public String getSid() {
		return sid;
	}
	
	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getChatType() {
		return this.chatType;
	}

	public void setChatType(String chatType) {
		this.chatType = chatType;
	}
	
	public String getWith() {
		return with;
	}

	public void setWith(String with) {
		this.with = with;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
	public String getChildElementXML() {
		
		return null;
	}

}
