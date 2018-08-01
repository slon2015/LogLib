package Appenders;

import java.util.HashMap;
import java.util.function.Function;

import Events.MessageAvailableEvent;
import Events.MessageAvailableEventListener;

public interface Appender {

	void Write(MessageAvailableEvent e);
}
