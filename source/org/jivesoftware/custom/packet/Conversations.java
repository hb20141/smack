package org.jivesoftware.custom.packet;

import java.io.Serializable;
import java.util.ArrayList;

import org.jivesoftware.smack.packet.IQ;

public class Conversations extends IQ implements Serializable {
	
	private static final long serialVersionUID = 146593172970310915L;
	
	private int first;
	private int last;
	private int count;
	private ArrayList<Item> items;
	
	public void addItem(Item item) {
		if (items == null) 
			items = new ArrayList<Item>();
		items.add(item);
	}
	
	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public static class Item implements Serializable {
		
		private static final long serialVersionUID = 458084856438969918L;
		
		private String jid = null;
		private String type = null;
		private ChatHistory.Message message = null;
        private String name = null;
        private String unread = null;
        private String notify = null;
        private String isDeleted = null;
        private String created = null;
        private String changed = null;

        private ArrayList<String> members = null; // 单聊/群聊聊天对方

		public String getJid() {
			return jid;
		}
		public void setJid(String jid) {
			this.jid = jid;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public ChatHistory.Message getMessage() {
			return message;
		}
		public void setMessage(ChatHistory.Message message) {
			this.message = message;
		}

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUnread() {
            return unread;
        }

        public void setUnread(String unread) {
            this.unread = unread;
        }

        public String getNotify() {
            return notify;
        }

        public void setNotify(String notify) {
            this.notify = notify;
        }

        public String getDeleted() {
            return isDeleted;
        }

        public void setDeleted(String deleted) {
            isDeleted = deleted;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getChanged() {
            return changed;
        }

        public void setChanged(String changed) {
            this.changed = changed;
        }

        public ArrayList<String> getMembers() {
            return members;
        }

        public void addMember(String member) {
            if (members == null) members = new ArrayList<String>();
            this.members.add(member);
        }
    }
	
	@Override
	public String getChildElementXML() {
		return null;
	}

}
