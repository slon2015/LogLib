package Hierarchy;

import java.util.HashSet;
import java.util.Set;

public abstract class HierarchyNode {
	abstract public boolean canHaveChildren();
	public HierarchyNode(String n,HierarchyNode p,Set<HierarchyNode> c) {
		name=n;
		parent=p;
		if(c==null)
			childrens=new HashSet<HierarchyNode>();
		else
			childrens=c;
	}
	private HierarchyNode parent;
	public HierarchyNode getParent() {
		return parent;
	}
	
	private Set<HierarchyNode> childrens;
	public Set<HierarchyNode> getChildrens(){
		return childrens;
	}
	
	private final String name;
	public String getName() {
		return name;
	}
}
