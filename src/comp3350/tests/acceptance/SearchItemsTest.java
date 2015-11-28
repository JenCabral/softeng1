package comp3350.tests.acceptance;

import junit.framework.Assert;

import com.robotium.solo.Condition;
import com.robotium.solo.Solo;

import comp3350.organizr.presentation.HomeActivity;
import android.test.ActivityInstrumentationTestCase2;

public class SearchItemsTest extends ActivityInstrumentationTestCase2<HomeActivity> 
{

	private Solo solo;

	public SearchItemsTest()
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
	
	public void testSearchItems()
	{
		solo.waitForActivity("HomeActivity");
		solo.clickOnText("80s");
		
		solo.assertCurrentActivity("Expected activity ViewItemsActivity", "ViewItemsActivity");
		solo.enterText(0, "Chee");
		solo.clickOnButton("Search");

		Condition noTMNT = new Condition() 
		{
			@Override
			public boolean isSatisfied() 
			{
				return !solo.searchText("TMNT");
			}
		};
		solo.waitForCondition(noTMNT, 5000);
		
		Assert.assertTrue(solo.searchText("Thundercats"));
		Assert.assertFalse(solo.searchText("Masters of the Universe"));
		Assert.assertFalse(solo.searchText("TMNT"));
	}
}