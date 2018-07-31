package Hierarchy.Nodes;

import java.util.Set;

import Appenders.Appender;

public class AppenderNode extends HierarchyNode{

	public AppenderNode(String n, HierarchyNode p, Set<HierarchyNode> c,Appender appender) {
		super(n, p, c);
		this.appender=appender;
		p.AddAppender(appender);
		// TODO Auto-generated constructor stub
	}
	
	Appender appender;
	
	@Override
	public void AddChildren(HierarchyNode node) {
		return;
	}

}
