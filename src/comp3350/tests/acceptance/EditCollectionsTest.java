package comp3350.tests.acceptance;

import junit.framework.Assert;

import com.robotium.solo.Solo;

import comp3350.organizr.presentation.HomeActivity;
import android.test.ActivityInstrumentationTestCase2;

public class EditCollectionsTest extends ActivityInstrumentationTestCase2<HomeActivity> 
{
	
	private Solo solo;

	public EditCollectionsTest()
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
	
	public void testEditCollection()
	{
		solo.waitForActivity("HomeActivity");
		solo.clickOnText("80s Action Figures");

		solo.assertCurrentActivity("Expected activity ViewItemsActivity", "ViewItemsActivity");
		solo.clickOnText("80s Action Figures");

		solo.assertCurrentActivity("Expected activity EditCollectionActivity", "EditCollectionActivity");
		Assert.assertTrue(solo.searchText("80s Action Figures"));
		Assert.assertTrue(solo.searchText("collection of action figures from the 1980s"));
		
		solo.clearEditText(0);
		solo.enterText(0, "90s Memorabilia");
		solo.clearEditText(1);
		solo.enterText(1, "this is more than JUST action figures");
		solo.clickOnButton("OK");
		
		solo.assertCurrentActivity("Expected activity ViewItemsActivity", "ViewItemsActivity");
		Assert.assertTrue(solo.searchText("90s Memorabilia"));
		Assert.assertTrue(solo.searchText("this is more than JUST action figures"));
		solo.goBack();
		
		solo.assertCurrentActivity("Expected activity HomeActivity", "HomeActivity");
		Assert.assertTrue(solo.searchText("90s Memorabilia"));
		Assert.assertTrue(solo.searchText("this is more than JUST action figures"));
		Assert.assertFalse(solo.searchText("80s Action Figures"));
		Assert.assertFalse(solo.searchText("collection of action figures from the 1980s"));
		
		//clean up
		solo.clickOnText("90s Memorabilia");
		
		solo.assertCurrentActivity("Expected activity ViewItemsActivity", "ViewItemsActivity");
		solo.clickOnText("90s Memorabilia");
		
		solo.assertCurrentActivity("Expected activity EditCollectionActivity", "EditCollectionActivity");
		solo.clearEditText(0);
		solo.enterText(0, "80s Action Figures");
		solo.clearEditText(1);
		solo.enterText(1, "collection of action figures from the 1980s");
		solo.clickOnButton("OK");
		
		solo.assertCurrentActivity("Expected activity ViewItemsActivity", "ViewItemsActivity");
		Assert.assertTrue(solo.searchText("80s Action Figures"));
		Assert.assertTrue(solo.searchText("collection of action figures from the 1980s"));
		solo.goBack();
		
		solo.assertCurrentActivity("Expected activity HomeActivity", "HomeActivity");
		Assert.assertTrue(solo.searchText("80s Action Figures"));
		Assert.assertTrue(solo.searchText("collection of action figures from the 1980s"));
	}
}
