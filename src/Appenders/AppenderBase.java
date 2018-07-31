package Appenders;

import java.util.HashMap;
import java.util.Map;

import Events.MessageAvailableEvent;
import Events.MessageAvailableEventListener;

public class AppenderBase implements Appender {

	public AppenderBase(Map<String, ProcessMessage> map) {
		Processors.putAll(map);
		Appender app=this;
		
		listener=new MessageAvailableEventListener() {
			@Override
			public void MessageAvailable(MessageAvailableEvent e) {
				if(Processors.containsKey(e.getType()))
					Processors.get(e.getType()).Process(e.getType(), e.getMessage(), e.getCreatorName(), app);
				else
					Processors.get("Default").Process(e.getType(), e.getMessage(), e.getCreatorName(), app);
			}
		};
		
		if(!Processors.containsKey("Default")) {
			ProcessMessage DefaultProcessor=new ProcessMessage() {
				@Override
				public void Process(String Type,String Message, String Name, Appender appender) {
					appender.Write(Type+";"+Name+";"+Message);
				}
			};
			Processors.put("Default", DefaultProcessor);
		}
	}
	
	public HashMap<String,ProcessMessage> Processors=new HashMap<String,ProcessMessage>();
	
	@Override
	public void Write(String message) {
		System.out.println(message);
	}
	
	MessageAvailableEventListener listener;
	
	public MessageAvailableEventListener getListener() {
		return listener;
	}

}
