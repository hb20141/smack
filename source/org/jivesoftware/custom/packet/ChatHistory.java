package org.jivesoftware.custom.packet;

import java.io.Serializable;
import java.util.ArrayList;

import org.jivesoftware.smack.packet.IQ;

/**
 * IQ packet that serves for parsing history message
 * @author clerkmaxwell
 *
 */
public class ChatHistory extends IQ implements Serializable {
	
	private static final long serialVersionUID = 1083398679901752758L;
	
	private String target = null;
	private String start = null;
	private String chatType = null;
	private int first;
	private int last;
	private int count;
	private ArrayList<Message> messages = null;

	public void addMessage(Message message) {
		if (messages == null)
			messages = new ArrayList<Message>();
		messages.add(message);
	}

	@Override
	public String getChildElementXML() {
		return null;
	}
	
	public static class Message implements Serializable {
		
		private static final long serialVersionUID = -4778345328463443270L;
		
		String messageId = null;
		String from = null;
		String to = null;
		String type = null;
		String body = null;
		String stamp = null;
        String user = null;
        String attachmentType = null;
        String attachmentId = null;
        String duration = null;
        String clientid = null;
        String prevmid = null;
        ConversationOperationResultMessage operationMessage = new ConversationOperationResultMessage();
        Delay delay = null;


		public String getMessageId() {
			return messageId;
		}
		public void setMessageId(String messageId) {
			this.messageId = messageId;
		}
		public String getFrom() {
			return from;
		}
		public void setFrom(String from) {
			this.from = from;
		}
		public String getTo() {
			return to;
		}
		public void setTo(String to) {
			this.to = to;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getBody() {
			return body;
		}
		public void setBody(String body) {
			this.body = body;
		}
		public String getStamp() {
			return stamp;
		}
		public void setStamp(String stamp) {
			this.stamp = stamp;
		}

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getAttachmentType() {
            return attachmentType;
        }

        public void setAttachmentType(String attachmentType) {
            this.attachmentType = attachmentType;
        }

        public String getAttachmentId() {
            return attachmentId;
        }

        public void setAttachmentId(String attachmentId) {
            this.attachmentId = attachmentId;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getClientid() {
            return clientid;
        }

        public void setClientid(String clientid) {
            this.clientid = clientid;
        }

        public String getPrevmid() {
            return prevmid;
        }

        public void setPrevmid(String prevmid) {
            this.prevmid = prevmid;
        }

        public ConversationOperationResultMessage getOperationMessage() {
            return operationMessage;
        }

        public void setOperationMessage(ConversationOperationResultMessage operationMessage) {
            this.operationMessage = operationMessage;
        }

        public Delay getDelay() {
            return delay;
        }

        public void setDelay(Delay delay) {
            this.delay = delay;
        }
    }

    public static class Delay implements Serializable {

        private static final long serialVersionUID = 5040433934240761941L;

        private String from = null;
        private String stamp = null;
        public String getFrom() {
            return from;
        }
        public void setFrom(String from) {
            this.from = from;
        }
        public String getStamp() {
            return stamp;
        }
        public void setStamp(String stamp) {
            this.stamp = stamp;
        }
    }

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getChatType() {
		return chatType;
	}

	public void setChatType(String chatType) {
		this.chatType = chatType;
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

	public ArrayList<Message> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}

}
