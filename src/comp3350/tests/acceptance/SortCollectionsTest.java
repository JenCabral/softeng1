package comp3350.tests.acceptance;

import junit.framework.Assert;

import com.robotium.solo.Condition;
import com.robotium.solo.Solo;

import comp3350.organizr.objects.Collection;
import comp3350.organizr.presentation.HomeActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;

public class SortCollectionsTest extends ActivityInstrumentationTestCase2<HomeActivity> 
{

	private Solo solo;

	public SortCollectionsTest()
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
	
	public void testSortCollection()
	{
		solo.waitForActivity("HomeActivity");
		solo.clickOnMenuItem("Sort");
		solo.clickOnMenuItem("Ascending");
		
		Condition figuresOnTop = new Condition() 
		{
			@Override
			public boolean isSatisfied() 
			{
				ListView list = (ListView) solo.getView("collection_list");
				Collection top = (Collection) list.getItemAtPosition(0);
				return "80s Action Figures".equals(top.getCollectionName());
			}
		};
		solo.waitForCondition(figuresOnTop, 5000);
		Assert.assertTrue(figuresOnTop.isSatisfied());

		solo.clickOnMenuItem("Sort");
		solo.clickOnMenuItem("Descending");

		Condition deckOnTop = new Condition() 
		{
			@Override
			public boolean isSatisfied() 
			{
				ListView list = (ListView) solo.getView("collection_list");
				Collection top = (Collection) list.getItemAtPosition(0);
				return "Rat Deck".equals(top.getCollectionName());
			}
		};
		solo.waitForCondition(deckOnTop, 5000);
		Assert.assertTrue(deckOnTop.isSatisfied());
	}
}