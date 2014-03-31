package org.jivesoftware.custom.packet;

import java.util.ArrayList;

import org.jivesoftware.smack.packet.IQ;

public class MUCOperationResult extends IQ {
	
	private String operation;
	private ArrayList<Item> items;
	
	public MUCOperationResult() {
		items = new ArrayList<Item>();
	}
	
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
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

	@Override
	public String getChildElementXML() {
		return null;
	}
	
	public static class Item{
		
		public String jid;

		public String getJid() {
			return jid;
		}

		public void setJid(String jid) {
			this.jid = jid;
		}
		
	} 

}
