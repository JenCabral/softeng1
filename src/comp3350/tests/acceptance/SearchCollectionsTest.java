package comp3350.tests.acceptance;

import junit.framework.Assert;

import com.robotium.solo.Condition;
import com.robotium.solo.Solo;

import comp3350.organizr.presentation.HomeActivity;
import android.test.ActivityInstrumentationTestCase2;

public class SearchCollectionsTest extends ActivityInstrumentationTestCase2<HomeActivity> 
{
	private Solo solo;

	public SearchCollectionsTest()
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
	
	public void testSearchCollections()
	{
		solo.waitForActivity("HomeActivity");
		solo.enterText(0, "Rat Deck");
		solo.clickOnButton("Search");
		
		Condition no80s = new Condition() 
		{
			@Override
			public boolean isSatisfied() 
			{
				return !solo.searchText("80s");
			}
		};
		solo.waitForCondition(no80s, 5000);
		Assert.assertTrue(no80s.isSatisfied());
		Assert.assertTrue(solo.searchText("Magic"));
	}
}