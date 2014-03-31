package org.jivesoftware.custom.packet;

import org.jivesoftware.smack.packet.IQ;

import java.util.ArrayList;

public class ConversationMembers extends IQ {

	private ArrayList<Item> items;
	
	public void addItem(Item item) {
		if (items == null) items = new ArrayList<Item>();
		this.items.add(item);
	}
	
	public ArrayList<Item> getItems() {
		return this.items;
	}
	
	@Override
	public String getChildElementXML() {
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
