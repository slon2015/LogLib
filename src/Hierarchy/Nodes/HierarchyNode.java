package Hierarchy.Nodes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Appenders.Appender;
import Events.MessageAvailableEvent;
import Events.MessageAvailableEventListener;

public abstract class HierarchyNode {
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
	}
	private HierarchyNode parent;
	public HierarchyNode getParent() {
		return parent;
	}
	
	public void AddChildren(HierarchyNode node) {
		childrens.add(node);
		node.parent=this;
		node.fullName=fullName+"."+node.name;
		if(node instanceof Appender)
			AddListener(((Appender)node).getListener());
	}
	
	private Set<HierarchyNode> childrens;
	public Set<HierarchyNode> getChildrens(){
		return childrens;
	}
	protected String fullName;
	
	private final String name;
	public String getName() {
		return name;
	}
	
	public void AddListener(MessageAvailableEventListener listener) {
		if(!listeners.contains(listener))
			listeners.add(listener);
	}
	
	public void RemoveListener(MessageAvailableEventListener listener) {
		if(listeners.contains(listener))
			listeners.remove(listener);
	}
	
	ArrayList<MessageAvailableEventListener> listeners = new ArrayList<MessageAvailableEventListener>();
	
	void fireMessageAvailableEvent(String Type,String Message,String Name) {
		MessageAvailableEvent eventObject=new MessageAvailableEvent(Type,Message,Name);
		fireMessageAvailableEvent(eventObject);
	}
	
	void fireMessageAvailableEvent(MessageAvailableEvent eventObject) {
		for(MessageAvailableEventListener listener:listeners) {
			listener.MessageAvailable(eventObject);
		}
		if(parent!=null)
			parent.fireMessageAvailableEvent(eventObject);
	}
}
