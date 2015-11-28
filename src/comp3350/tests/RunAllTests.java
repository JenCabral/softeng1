package comp3350.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import comp3350.tests.business.BusinessTests;
import comp3350.tests.integration.IntegrationTests;
import comp3350.tests.objects.ObjectTests;
import comp3350.tests.persistence.PersistenceTests;

public class RunAllTests
{
	public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("All tests");   
        suite.addTest(ObjectTests.suite());
        suite.addTest(BusinessTests.suite());
        suite.addTest(IntegrationTests.suite());
        suite.addTest(PersistenceTests.suite());
        return suite;
    }
}
