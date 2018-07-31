package Events;

import java.util.EventObject;

public class MessageAvailableEvent extends EventObject {

	private final EventLevel type;
	private final String message;
	private final String creatorName;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MessageAvailableEvent(EventLevel Type,String Message, String Creator) {
		super(Creator);
		type=Type;
		message=Message;
		creatorName=Creator;
	}
	
	public EventLevel getType() {
		return type;
	}
	
	
	public String getMessage() {
		return message;
	}
	
	
	public String getCreatorName() {
		return creatorName;
	}

	
}
