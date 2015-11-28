package comp3350.tests.acceptance;

import junit.framework.Assert;

import com.robotium.solo.Solo;

import comp3350.organizr.presentation.HomeActivity;
import android.test.ActivityInstrumentationTestCase2;

public class EditItemsTest extends ActivityInstrumentationTestCase2<HomeActivity> 
{
	
	private Solo solo;

	public EditItemsTest()
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
	
	public void testEditItems()
	{
		solo.waitForActivity("HomeActivity");
		solo.clickOnText("80s Action Figures");

		solo.assertCurrentActivity("Expected activity ViewItemsActivity", "ViewItemsActivity");
		solo.clickOnText("Jaga");

		solo.assertCurrentActivity("Expected activity EditItemActivity", "EditItemActivity");
		Assert.assertTrue(solo.searchText("Jaga"));
		Assert.assertTrue(solo.searchText("1986"));
		Assert.assertTrue(solo.searchText("Thundercats"));
		
		solo.clearEditText(0);
		solo.enterText(0, "Jaga Limited Edition");
		solo.clearEditText(1);
		solo.enterText(1, "1982");
		solo.clearEditText(2);
		solo.enterText(2, "Super Thundercats");
		solo.clickOnButton("OK");
		
		solo.assertCurrentActivity("Expected activity ViewItemsActivity", "ViewItemsActivity");
		Assert.assertTrue(solo.searchText("Jaga Limited Edition"));
		Assert.assertTrue(solo.searchText("1982"));
		Assert.assertTrue(solo.searchText("Super Thundercats"));
		solo.clickOnText("Lion-o");
		
		solo.assertCurrentActivity("Expected activity EditItemActivity", "EditItemActivity");
		Assert.assertTrue(solo.searchText("Lion-o"));
		Assert.assertTrue(solo.searchText("1986"));
		Assert.assertTrue(solo.searchText("Thundercats"));
		
		solo.clearEditText(0);
		solo.enterText(0, "Lion-o Super Special Edition");
		solo.clearEditText(1);
		solo.enterText(1, "1981");
		solo.clearEditText(2);
		solo.enterText(2, "Super Duper Thundercats");
		solo.clickOnButton("Cancel");
		
		solo.assertCurrentActivity("Expected activity ViewItemsActivity", "ViewItemsActivity");
		Assert.assertTrue(solo.searchText("Lion-o"));
		Assert.assertTrue(solo.searchText("1986"));
		Assert.assertTrue(solo.searchText("Thundercats"));
		
		//clean up
		solo.clickOnText("Jaga Limited Edition");
		
		solo.assertCurrentActivity("Expected activity EditItemActivity", "EditItemActivity");
		solo.clearEditText(0);
		solo.enterText(0, "Jaga");
		solo.clearEditText(1);
		solo.enterText(1, "1986");
		solo.clearEditText(2);
		solo.enterText(2, "Thundercats");
		solo.clickOnButton("OK");
		
		solo.assertCurrentActivity("Expected activity ViewItemsActivity", "ViewItemsActivity");
		Assert.assertTrue(solo.searchText("Jaga"));
		Assert.assertTrue(solo.searchText("1986"));
		Assert.assertTrue(solo.searchText("Thundercats"));
		solo.goBack();
		
		solo.assertCurrentActivity("Expected activity HomeActivity", "HomeActivity");
	}
}
