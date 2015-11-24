package com.advert.tool.common.util.ac;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.advert.cms.core.utils.ac.TestAhoCorasick;

public class TestAll extends TestCase {
    public static Test suite() {
	TestSuite suite = new TestSuite();
	suite.addTestSuite(TestState.class);
	suite.addTestSuite(TestAhoCorasick.class);
	suite.addTestSuite(TestQueue.class);
	return suite;
    }
}
