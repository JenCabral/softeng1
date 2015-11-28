package comp3350.tests.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import comp3350.organizr.application.Main;
import comp3350.organizr.application.Services;
import comp3350.organizr.business.AccessCollections;
import comp3350.organizr.objects.Collection;
import comp3350.organizr.persistence.DataAccess;
import comp3350.tests.persistence.DataAccessStub;
import junit.framework.TestCase;

public class SortCollectionTest extends TestCase 
{
	List<Collection> list;
	Collection collection;
	String query;
	
	private static String dbName = Main.dbName;
	
	public SortCollectionTest(String args)
	{
		super(args);
	}
	
	public void setUp()
	{
		Services.closeDataAccess();
		Services.createDataAccess((DataAccess) new DataAccessStub(dbName));
	}
	
	public void tearDown()
	{
		Services.closeDataAccess();
	}
	
	public void testSorted()
	{
		System.out.println("\nStarting SortCollectionTest#testSorted");
		
		list = new ArrayList<Collection>();
		boolean result = true;
		
		AccessCollections ac = new AccessCollections();
		ac.sortCollection(list,"asc");
		
		for (int i = 1; i < list.size(); i++)
		{
			if(!(list.get(i-1).getCollectionName().toLowerCase(Locale.CANADA)
					.compareTo(list.get(i).getCollectionName().toLowerCase(Locale.CANADA)) <= 0))
			{
				result = false;
			}
		}
		
		assertTrue(result);
		System.out.println("Finished SortCollectionTest#testSorted");
	}
	
	public void testSortedDesc()
	{
		System.out.println("\nStarting SortCollectionTest#testSortedDesc");
		
		list = new ArrayList<Collection>();
		boolean result = true;
		
		AccessCollections ac = new AccessCollections();
		ac.sortCollection(list,"desc");
		
		for (int i = 1; i < list.size(); i++)
		{
			if(!(list.get(i-1).getCollectionName().toLowerCase(Locale.CANADA)
					.compareTo(list.get(i).getCollectionName().toLowerCase(Locale.CANADA)) > 0))
			{
				result = false;
			}
		}
		
		assertTrue(result);
		System.out.println("Finished SortCollectionTest#testSortedDesc");
	}
}
