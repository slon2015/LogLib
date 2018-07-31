package Hierarchy;

import java.util.*;
import java.util.stream.Collectors;

import Appenders.Appender;
import Hierarchy.Nodes.AppenderNode;
import Hierarchy.Nodes.HierarchyNode;
import Hierarchy.Nodes.Logger;
import Hierarchy.Nodes.LoggerNode;
import Hierarchy.Nodes.StandartHierarchyLevel;

public class HierarchyBase {
	
	final HierarchyNode rootNode=new StandartHierarchyLevel("root",null,null);

	private List<HierarchyNode> hierarchy;

	private static HierarchyBase instance;
	
	private HierarchyBase() {
		hierarchy=new LinkedList<HierarchyNode>();
		hierarchy.add(rootNode);
		
	}
	
	public void BuildNewLevel(String name) {
		String parentName=name.substring(0, name.lastIndexOf("."));
		HierarchyNode parent=instance.findNode(parentName);
		String myName=name.substring(name.lastIndexOf(".")+1);
		CheckForCreate(parent, myName);
		hierarchy.add(new StandartHierarchyLevel(myName,parent,null));
	}
	
	private HierarchyNode findNode(String subName,Set<HierarchyNode> collection) {
		if(subName.equals("root"))
			return rootNode;
		HierarchyNode acc=null;
		for(HierarchyNode node:collection) {
			if(node.getName().equals(subName)) {
				acc=node;
				break;
			}
		}
		return acc;
	}
	
	private HierarchyNode findNode(String name) {
		StringTokenizer tokenizer=new StringTokenizer(name,".");
		
		HierarchyNode ret=rootNode;
		while(tokenizer.hasMoreTokens()) {
			String subName=tokenizer.nextToken();
			if(ret==null)
				throw new IllegalArgumentException();
			ret=findNode(subName,ret.getChildrens());
		}
		return ret;
	}
	
	public static HierarchyBase getInstance() {
		if(instance==null)
			instance=new HierarchyBase();
		return instance;
	}
	
	public Set<String> getChildrenOf(String name) {
		HierarchyNode parent=findNode(name);
		return parent.getChildrens().stream().map(n->n.getName()).collect(Collectors.toSet());
	}

	public Logger CreateLogger(String name) {
		String parentName=name.substring(0, name.lastIndexOf("."));
		HierarchyNode parent=instance.findNode(parentName);
		String myName=name.substring(name.lastIndexOf(".")+1);
		CheckForCreate(parent, myName);
		Logger ret=(Logger) new LoggerNode(myName,parent,null);
		hierarchy.add((LoggerNode)ret);
		return ret;
	}

	private void CheckForCreate(HierarchyNode parent, String myName) {
		if(parent==null)
			throw new IllegalArgumentException("Can't find parent");
		if(parent.getChildrens().stream().anyMatch(c->c.getName()==myName))
			throw new IllegalArgumentException("Children already exists");
	}
	
	public void AddAppender(String name,Appender appender){
		String parentName=name.substring(0, name.lastIndexOf("."));
		HierarchyNode parent=instance.findNode(parentName);
		String myName=name.substring(name.lastIndexOf(".")+1);
		CheckForCreate(parent, myName);
		hierarchy.add(new AppenderNode(myName,parent,null,appender));
	}
}
