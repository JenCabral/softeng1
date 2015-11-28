package comp3350.tests.business;

import junit.framework.Test;
import junit.framework.TestSuite;

public class BusinessTests
{
	public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Business tests");
        suite.addTestSuite(CountItemCountTest.class);
        suite.addTestSuite(GetByIDTest.class);
        suite.addTestSuite(SearchCollectionTest.class);
        suite.addTestSuite(SearchItemTest.class);
        suite.addTestSuite(SortCollectionTest.class);
        suite.addTestSuite(SortItemTest.class);
        return suite;
    }
}
