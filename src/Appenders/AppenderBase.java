package Appenders;

import java.util.HashMap;
import java.util.Map;

import Events.MessageAvailableEvent;
import Events.MessageAvailableEventListener;

public class AppenderBase implements Appender {
	
	@Override
	public void Write(MessageAvailableEvent e) {
		System.out.println(e.getType()+";"+e.getMessage()+";"+e.getCreatorName());
	}

}
