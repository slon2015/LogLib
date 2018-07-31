package Hierarchy.Nodes;

import java.util.Set;

import Appenders.Appender;
import Events.EventLevel;

public class LoggerNode extends HierarchyNode implements Logger{
	public LoggerNode(String n, HierarchyNode p, Set<HierarchyNode> c) {
		super(n, p, c);
	}
	
	@Override
	public void AddChildren(HierarchyNode node) {
		if(node instanceof Appender)
			super.AddChildren(node);
	}
	
	public void Info(String Message) {
		fireMessageAvailableEvent(EventLevel.Info,Message,fullName);
	}
	
	public void Debug(String Message) {
		fireMessageAvailableEvent(EventLevel.Debug,Message,fullName);
	}
	
	public void Warning(String Message) {
		fireMessageAvailableEvent(EventLevel.Warn,Message,fullName);
	}
	
	public void Error(String Message) {
		fireMessageAvailableEvent(EventLevel.Error,Message,fullName);
	}
	
	public void Fatal(String Message) {
		fireMessageAvailableEvent(EventLevel.Fatal,Message,fullName);
	}
}
