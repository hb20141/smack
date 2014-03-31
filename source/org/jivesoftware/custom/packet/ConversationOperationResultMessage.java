package org.jivesoftware.custom.packet;

import java.util.ArrayList;

import org.jivesoftware.smack.packet.PacketExtension;

/**
 * muc kick leave invite rename系统消息
 *
 */
public class ConversationOperationResultMessage implements PacketExtension {
	
	private String opetator = null;
    private String operaton = null;
    private String value = null;
	
	private ArrayList<Item> items;
	
	public ConversationOperationResultMessage() {
		items = new ArrayList<Item>();
	}
	
	public void addItem(Item item) {
		this.items.add(item);
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
	public String getOpetator() {
		return opetator;
	}

	public void setOpetator(String opetator) {
		this.opetator = opetator;
	}

	public String getOperaton() {
		return operaton;
	}

	public void setOperaton(String operaton) {
		this.operaton = operaton;
	}

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
	public String getElementName() {
		return "x";
	}

	@Override
	public String getNamespace() {
		return "http://huoban.com/protocol/session";
	}

	@Override
	public String toXML() {
		return null;
	}
	
	public static class Item {
		
		private String jid;

		public String getJid() {
			return jid;
		}

		public void setJid(String jid) {
			this.jid = jid;
		}

	} 

}
