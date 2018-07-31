package Hierarchy.Nodes;

import java.util.Set;

import Appenders.Appender;

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
		fireMessageAvailableEvent("Info",Message,fullName);
	}
	
	public void Debug(String Message) {
		fireMessageAvailableEvent("Debug",Message,fullName);
	}
	
	public void Warning(String Message) {
		fireMessageAvailableEvent("Warning",Message,fullName);
	}
	
	public void Error(String Message) {
		fireMessageAvailableEvent("Error",Message,fullName);
	}
	
	public void CustomLevelMessage(String Level,String Message) {
		fireMessageAvailableEvent(Level,Message,fullName);
	}
}
