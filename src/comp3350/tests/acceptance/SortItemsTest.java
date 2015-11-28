package comp3350.tests.acceptance;

import junit.framework.Assert;

import com.robotium.solo.Condition;
import com.robotium.solo.Solo;

import comp3350.organizr.objects.Item;
import comp3350.organizr.presentation.HomeActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;

public class SortItemsTest extends ActivityInstrumentationTestCase2<HomeActivity> 
{

	private Solo solo;

	public SortItemsTest()
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
	
	public void testSortItems()
	{
		solo.waitForActivity("HomeActivity");
		solo.clickOnText("Rat Deck");
		
		solo.assertCurrentActivity("Expected activity ViewItemsActivity", "ViewItemsActivity");
		solo.clickOnMenuItem("Sort");
		solo.clickOnMenuItem("By Name");
		solo.clickOnMenuItem("Ascending");
		
		Condition doomBladeOnTop = new Condition() 
		{
			@Override
			public boolean isSatisfied() 
			{
				ListView list = (ListView) solo.getView("item_list");

				// 1 because the collection is what's really at the top
				Item top = (Item) list.getItemAtPosition(1);
				return "Doom Blade".equals(top.getItemName());
			}
		};
		solo.waitForCondition(doomBladeOnTop, 5000);
		Assert.assertTrue(doomBladeOnTop.isSatisfied());

		solo.clickOnMenuItem("Sort");
		solo.clickOnMenuItem("By Year");
		solo.clickOnMenuItem("Descending");

		Condition trepanationOnTop = new Condition() 
		{
			@Override
			public boolean isSatisfied() 
			{
				ListView list = (ListView) solo.getView("item_list");

				// 1 because the collection is what's really at the top
				Item top = (Item) list.getItemAtPosition(1);
				return "Trepanation Blade".equals(top.getItemName());
			}
		};
		solo.waitForCondition(trepanationOnTop, 5000);
		Assert.assertTrue(trepanationOnTop.isSatisfied());
	}
}