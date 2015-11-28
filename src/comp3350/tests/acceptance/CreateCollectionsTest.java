package comp3350.tests.acceptance;

import junit.framework.Assert;

import com.robotium.solo.Solo;

import comp3350.organizr.presentation.HomeActivity;
import android.test.ActivityInstrumentationTestCase2;

public class CreateCollectionsTest extends ActivityInstrumentationTestCase2<HomeActivity> 
{

	private Solo solo;

	public CreateCollectionsTest()
	{
		super(HomeActivity.class);
	}

	public void setUp() throws Exception
	{
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	@Override
	public void tearDown() throws Exception
	{
		solo.finishOpenedActivities();
	}
	
	public void testCreateCollection()
	{
		solo.waitForActivity("HomeActivity");
		solo.clickOnButton("Create collection");

		solo.assertCurrentActivity("Expected activity EditCollectionActivity", "EditCollectionActivity");
		solo.enterText(0, "testname");
		solo.enterText(1, "testdescription");
		solo.clickOnButton("OK");

		solo.assertCurrentActivity("Expected activity HomeActivity", "HomeActivity");
		Assert.assertTrue(solo.searchText("testname"));
		Assert.assertTrue(solo.searchText("testdescription"));
		solo.clickOnText("testname");
		
		solo.assertCurrentActivity("Expected activity ViewItemsActivity", "ViewItemsActivity");
		Assert.assertTrue(solo.searchText("testname"));
		Assert.assertTrue(solo.searchText("testdescription"));
		solo.clickOnText("testname");

		solo.assertCurrentActivity("Expected activity EditCollectionActivity", "EditCollectionActivity");
		solo.clickOnText("Cancel");

		solo.assertCurrentActivity("Expected activity ViewItemsActivity", "ViewItemsActivity");
		Assert.assertTrue(solo.searchText("testname"));
		Assert.assertTrue(solo.searchText("testdescription"));
		solo.clickOnText("testname");

		// Clean up
		solo.assertCurrentActivity("Expected activity EditCollectionActivity", "EditCollectionActivity");
		solo.clickOnText("Delete");

		solo.assertCurrentActivity("Expected activity HomeActivity", "HomeActivity");
		Assert.assertFalse(solo.searchText("testname"));
		Assert.assertFalse(solo.searchText("testdescription"));
	}
}