package comp3350.tests.acceptance;

import junit.framework.Assert;

import com.robotium.solo.Solo;

import comp3350.organizr.presentation.HomeActivity;
import android.test.ActivityInstrumentationTestCase2;

//Because clean up of an add or remove naturally coincide, let's just test add and remove together
public class AddRemoveItemsTest extends ActivityInstrumentationTestCase2<HomeActivity> 
{
	private Solo solo;

	public AddRemoveItemsTest()
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
	
	public void testAddRemoveItems()
	{
		solo.waitForActivity("HomeActivity");
		solo.clickOnText("80s Action Figures");

		solo.assertCurrentActivity("Expected activity ViewItemsActivity", "ViewItemsActivity");
		solo.clickOnButton("Add item");

		//Add an item
		solo.assertCurrentActivity("Expected activity EditItemActivity", "EditItemActivity");
		solo.enterText(0, "Breaker");
		solo.clickOnButton("Cancel");
		
		solo.assertCurrentActivity("Expected activity ViewItemsActivity", "ViewItemsActivity");
		Assert.assertFalse(solo.searchText("Breaker"));
		solo.clickOnButton("Add item");
		
		solo.assertCurrentActivity("Expected activity EditItemActivity", "EditItemActivity");
		solo.enterText(0, "Hawk");
		solo.clearEditText(1);
		solo.enterText(1, "1982");
		solo.enterText(2, "GI Joe A Real American Hero");
		solo.clickOnButton("OK");
		
		solo.assertCurrentActivity("Expected activity ViewItemsActivity", "ViewItemsActivity");
		Assert.assertTrue(solo.searchText("Hawk"));
		Assert.assertTrue(solo.searchText("GI Joe A Real American Hero"));
		
		//Remove an item
		solo.clickOnText("Hawk");
		
		solo.assertCurrentActivity("Expected activity EditItemActivity", "EditItemActivity");
		Assert.assertTrue(solo.searchText("Hawk"));
		Assert.assertTrue(solo.searchText("1982"));
		Assert.assertTrue(solo.searchText("GI Joe A Real American Hero"));
		solo.clickOnButton("Delete");
		
		solo.assertCurrentActivity("Expected activity ViewItemsActivity", "ViewItemsActivity");
		Assert.assertFalse(solo.searchText("Hawk"));
		Assert.assertFalse(solo.searchText("GI Joe A Real American Hero"));
		
		//clean up
		solo.goBack();
		solo.assertCurrentActivity("Expected activity HomeActivity", "HomeActivity");
	}
}
