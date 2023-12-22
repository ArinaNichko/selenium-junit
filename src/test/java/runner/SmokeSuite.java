package runner;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import testCases.loginPortal.LoginPortalTests;
import testCases.options.OptionsTests;

@Suite
@SelectClasses({LoginPortalTests.class, OptionsTests.class})
public class SmokeSuite {
}