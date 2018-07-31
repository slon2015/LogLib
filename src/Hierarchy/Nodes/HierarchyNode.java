package Hierarchy.Nodes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Appenders.Appender;
import Events.EventLevel;
import Events.MessageAvailableEvent;
import Events.MessageAvailableEventListener;

public abstract class HierarchyNode {
	private HashSet<Appender> Appenders;
	private HierarchyNode parent;
	private Set<HierarchyNode> childrens;
	protected String fullName;
	private final String name;
	
	public HierarchyNode(String n,HierarchyNode p,Set<HierarchyNode> c) {
		name=n;
		childrens=new HashSet<HierarchyNode>();
		if(c!=null)
			for(HierarchyNode node: c)
				AddChildren(node);
		if(p!=null)
			p.AddChildren(this);
		if(parent==null)
			fullName=name;
		Appenders=new HashSet<Appender>();
	}
	
	public HierarchyNode getParent() {
		return parent;
	}
	
	public void AddChildren(HierarchyNode node) {
		childrens.add(node);
		node.parent=this;
		node.fullName=fullName+"."+node.name;
	}
	
	public Set<HierarchyNode> getChildrens(){
		return childrens;
	}
	
	public String getName() {
		return name;
	}
	
	public void AddAppender(Appender appender) {
		if(!Appenders.contains(appender))
			Appenders.add(appender);
	}
	
	public void RemoveAppender(Appender appender) {
		if(Appenders.contains(appender))
			Appenders.remove(appender);
	}
	
	void fireMessageAvailableEvent(EventLevel Type,String Message,String Name) {
		MessageAvailableEvent eventObject=new MessageAvailableEvent(Type,Message,Name);
		fireMessageAvailableEvent(eventObject);
	}
	
	void fireMessageAvailableEvent(MessageAvailableEvent eventObject) {
		for(Appender listener:Appenders) {
			listener.Write(eventObject);
		}
		if(parent!=null)
			parent.fireMessageAvailableEvent(eventObject);
	}
}
