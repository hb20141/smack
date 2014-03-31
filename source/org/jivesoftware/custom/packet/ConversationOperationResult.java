package org.jivesoftware.custom.packet;

import org.jivesoftware.smack.packet.IQ;

public class ConversationOperationResult extends IQ {
	
	private String operation; // remove notify
	private String name;  // 调整会话的name
	private String value; // 是否屏蔽某个会话
	
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public String isValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
	public String getChildElementXML() {
		return null;
	}

}
