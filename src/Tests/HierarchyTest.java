package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import Appenders.Appender;
import Appenders.AppenderBase;
import Hierarchy.*;
import Hierarchy.Nodes.Logger;
import junit.framework.Assert;
import Appenders.ProcessMessage;
import java.util.*;

class HierarchyTest {

	@SuppressWarnings("deprecation")
	@Test
	void testBuildNewLevel() {
		String newNodeName="test";
		HierarchyBase.getInstance().BuildNewLevel("root."+newNodeName);
		Set<String> ret=HierarchyBase.getInstance().getChildrenOf("root");
		Assert.assertEquals(1, ret.size());
		Assert.assertTrue(ret.contains(newNodeName));
	}
	
	@Test
	void testAppenderOnNode() {
		Logger lg=HierarchyBase.getInstance().CreateLogger("root.logger");
		HierarchyBase.getInstance().AddAppender("root.logger.appender1", new AppenderBase(new HashMap<String,ProcessMessage>()));
		lg.Info("Hello");
	}
	
	@Test
	void testAppenderUnderNode() {
		Logger lg=HierarchyBase.getInstance().CreateLogger("root.logger");
		HierarchyBase.getInstance().AddAppender("root.appender1", new AppenderBase(new HashMap<String,ProcessMessage>()));
		lg.Info("Hello");
	}
	
	@Test
	void testAppenderWithIgnoreError() {
		Logger lg=HierarchyBase.getInstance().CreateLogger("root.logger");
		HashMap<String,ProcessMessage> map=new HashMap<String,ProcessMessage>();
		map.put("Error", new ProcessMessage() {
			
			@Override
			public void Process(String Type,String Message, String Name, Appender appender) {
				System.out.println("Message ignored");
			}
		});
		Appender app=new AppenderBase(map);
		HierarchyBase.getInstance().AddAppender("root.appender1", app);
		lg.Error("Hello");
	}
	
	@Test
	void IgnoreParentsEvent() {
		Logger lg=HierarchyBase.getInstance().CreateLogger("root.logger");
		HierarchyBase.getInstance().BuildNewLevel("root.newLevel");
		HashMap<String,ProcessMessage> map=new HashMap<String,ProcessMessage>();
		map.put("Info", new ProcessMessage() {
			
			@Override
			public void Process(String Type,String Message, String Name, Appender appender) {
				System.out.println("Error!!!");
			}
		});
		HierarchyBase.getInstance().AddAppender("root.newLevel.appender1", new AppenderBase(map));
		lg.Info("Hello");
	}

}
