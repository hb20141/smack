package org.jivesoftware.custom.iquery;

import org.jivesoftware.custom.IMRemoteConsants;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.custom.provider.ConversationOperationProvider;

public class ConversationOperation extends IQ {
	
	private String operation;
	private String param;
	
	public ConversationOperation(String target, String operation, String param) {
        target += "@" + IMRemoteConsants.XMPP_TARGET_SERVER;
		setTo(target);
		setType(Type.SET);
		this.operation = operation;
		this.param = param;
	}

	@Override
	public String getChildElementXML() {
		String xml1 = "<" + this.operation + " xmlns=\"http://huoban.com/protocol/session\"";
		String xml2 = "";
		if (operation.equals(ConversationOperationProvider.COP_RENAME_ELEMENT)) {
			xml2 += " name=\"" + param + "\">";
		} else if (operation.equals(ConversationOperationProvider.COP_NOTIFY_ELEMENT)) {
            int notify = 0;
            if (Boolean.valueOf(param)) notify = 1;
			xml2 += " value=\"" + notify + "\">";
		} else if (operation.equals(ConversationOperationProvider.COP_REMOVE_ELEMENT)
                /*|| operation.equals(ConversationOperationProvider.COP_READ_ELEMENT)*/) {
			xml2 += ">";
		}

        String xml3 = "";
        if (operation.equals(ConversationOperationProvider.COP_READ_ELEMENT)) {
            xml3 += " />";
        } else {
            xml3 += "</" + this.operation + ">";
        }

		return xml1 + xml2 + xml3;
	}

}
