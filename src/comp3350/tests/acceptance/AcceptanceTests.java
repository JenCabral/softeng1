package comp3350.tests.acceptance;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AcceptanceTests
{
	public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Acceptance tests");
        suite.addTestSuite(CreateCollectionsTest.class);
        suite.addTestSuite(EditCollectionsTest.class);
        suite.addTestSuite(EditItemsTest.class);
        suite.addTestSuite(AddRemoveItemsTest.class);
        suite.addTestSuite(SortCollectionsTest.class);
        suite.addTestSuite(SearchCollectionsTest.class);
        suite.addTestSuite(SortItemsTest.class);
        suite.addTestSuite(SearchItemsTest.class);
        return suite;
    }
}
