package comp3350.tests.persistence;

import junit.framework.Test;
import junit.framework.TestSuite;

public class PersistenceTests
{
	public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Persistence tests");
    	suite.addTestSuite(DataAccessTest.class);
    	suite.addTestSuite(NextIDTest.class);
        return suite;
    }
}
