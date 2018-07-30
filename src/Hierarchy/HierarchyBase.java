package Hierarchy;

import java.util.*;
import java.util.stream.Collectors;

import Hierarchy.Nodes.StandartHierarchyLevel;

public class HierarchyBase {
	public final HierarchyNode rootNode=new StandartHierarchyLevel("root",null,null);
	public HierarchyBase() {
		hierarchy=new LinkedList<HierarchyNode>();
		hierarchy.add(rootNode);
	}
	
	HierarchyNode findNode(String subName,Set<HierarchyNode> collection) {
		
	}
	
	HierarchyNode findNode(String name) {
		StringTokenizer tokenizer=new StringTokenizer(name,".");
		String subName=tokenizer.nextToken();
		return instance.hierarchy.stream().reduce(rootNode, (a,n)->{
			if(n.getName()==subName)
			{
				a=n;
				subName=tokenizer.nextToken();
			}
			return a;
		}));
	}
	static final HierarchyBase instance=new HierarchyBase();
	
	private List<HierarchyNode> hierarchy;
	
}
