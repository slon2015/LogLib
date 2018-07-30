package Hierarchy.Nodes;

import java.util.Set;

import Hierarchy.HierarchyNode;

public class StandartHierarchyLevel extends HierarchyNode {

	public StandartHierarchyLevel(String n, HierarchyNode p, Set<HierarchyNode> c) {
		super(n, p, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canHaveChildren() {
		return true;
	}

}
