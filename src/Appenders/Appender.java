package Appenders;

import java.util.HashMap;
import java.util.function.Function;

import Events.MessageAvailableEventListener;

public interface Appender {

	public MessageAvailableEventListener getListener();
	void Write(String message);
}
