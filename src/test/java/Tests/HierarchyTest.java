package Tests;



import java.util.Set;

import org.junit.Test;
import static org.junit.Assert.*;

import Appenders.Appender;
import Appenders.AppenderBase;
import Hierarchy.*;
import Hierarchy.Nodes.Logger;
import Events.EventLevel;
import Events.MessageAvailableEvent;

import java.util.*;

class HierarchyTest {

	@Test
	void testBuildNewLevel() {
		String newNodeName="test";
		HierarchyBase.getInstance().BuildNewLevel("root."+newNodeName);
		Set<String> ret=HierarchyBase.getInstance().getChildrenOf("root");
		assertEquals(1, ret.size());
		assertTrue(ret.contains(newNodeName));
	}
	
	@Test
	void testAppenderOnNode() {
		Logger lg=HierarchyBase.getInstance().CreateLogger("root.logger");
		HierarchyBase.getInstance().AddAppender("root.logger.appender1", new AppenderBase());
		lg.Info("Hello");
	}
	
	@Test
	void testAppenderUnderNode() {
		Logger lg=HierarchyBase.getInstance().CreateLogger("root.logger");
		HierarchyBase.getInstance().AddAppender("root.appender1", new AppenderBase());
		lg.Info("Hello");
	}
	
	@Test
	void testAppenderWithIgnoreError() {
		Logger lg=HierarchyBase.getInstance().CreateLogger("root.logger");
		Appender app=new AppenderBase() {
			@Override
			public void Write(MessageAvailableEvent e) {
				if(e.getType()!=EventLevel.Error)
					super.Write(e);
				else
					System.out.println("Message ignored");
			}
		};
		HierarchyBase.getInstance().AddAppender("root.appender1", app);
		lg.Error("Hello");
	}
	
	@Test
	void IgnoreParentsEvent() {
		Logger lg=HierarchyBase.getInstance().CreateLogger("root.logger");
		HierarchyBase.getInstance().BuildNewLevel("root.newLevel");
		HierarchyBase.getInstance().AddAppender("root.newLevel.appender1", new AppenderBase());
		lg.Info("Hello");
	}

}
