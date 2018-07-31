package Appenders;

@FunctionalInterface
public interface ProcessMessage {

	public void Process(String Type,String Message, String Name, Appender appender);
}
