package comp3350.tests.integration;

import junit.framework.Test;
import junit.framework.TestSuite;

public class IntegrationTests
{
	public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Integration tests");
        suite.addTestSuite(AccessItemsBusinessPersistenceSeamTest.class);
        suite.addTestSuite(AccessCollectionsBusinessPersistenceSeamTest.class);
        suite.addTestSuite(DataAccessHSQLDBTest.class);
        return suite;
    }
}
