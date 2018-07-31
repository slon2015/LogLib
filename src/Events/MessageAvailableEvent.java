package Events;

import java.util.EventObject;

public class MessageAvailableEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MessageAvailableEvent(String Type,String Message, String Creator) {
		super(Creator);
		type=Type;
		message=Message;
		creatorName=Creator;
	}
	
	private String type;
	private String message;
	private String creatorName;
	
	public String getType() {
		return type;
	}
	public String getMessage() {
		return message;
	}
	public String getCreatorName() {
		return creatorName;
	}
	
}
