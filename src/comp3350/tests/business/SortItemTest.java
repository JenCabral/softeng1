package comp3350.tests.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import comp3350.organizr.application.Main;
import comp3350.organizr.application.Services;
import comp3350.organizr.business.AccessItems;
import comp3350.organizr.objects.Item;
import comp3350.organizr.persistence.DataAccess;
import comp3350.tests.persistence.DataAccessStub;
import junit.framework.TestCase;

public class SortItemTest extends TestCase 
{
	List<Item> list;
	Item item;
	String query;
	
	private static String dbName = Main.dbName;
	
	public SortItemTest(String args)
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
		System.out.println("\nStarting SortItemTest#testSorted");
		
		list = new ArrayList<Item>();
		boolean result = true;
		
		AccessItems ac = new AccessItems();
		ac.sortItems(list,"asc","name");
		
		for (int i = 1; i < list.size(); i++)
		{
			if(!(list.get(i-1).getItemName().toLowerCase(Locale.CANADA)
					.compareTo(list.get(i).getItemName().toLowerCase(Locale.CANADA)) <= 0))
			{
				result = false;
			}
		}
		
		assertTrue(result);
		System.out.println("Finished SortItemTest#testSorted");
	}
	
	public void testSortedDesc()
	{
		System.out.println("\nStarting SortItemTest#testSortedDesc");
		
		list = new ArrayList<Item>();
		boolean result = true;
		
		AccessItems ac = new AccessItems();
		ac.sortItems(list,"desc","name");
		
		for (int i = 1; i < list.size(); i++)
		{
			if(!(list.get(i-1).getItemName().toLowerCase(Locale.CANADA)
					.compareTo(list.get(i).getItemName().toLowerCase(Locale.CANADA)) > 0))
			{
				result = false;
			}
		}
		
		assertTrue(result);
		System.out.println("Finished SortItemTest#testSortedDesc");
	}
	
	public void testSortedByYear()
	{
		System.out.println("\nStarting SortItemTest#testSortedByYear");
		
		list = new ArrayList<Item>();
		boolean result = true;
		
		AccessItems ac = new AccessItems();
		ac.sortItems(list,"asc","year");
		
		for (int i = 1; i < list.size(); i++)
		{
			if(list.get(i-1).getItemYear() > list.get(i).getItemYear())
			{
				result = false;
			}
		}
		
		assertTrue(result);
		System.out.println("Finished SortItemTest#testSortedByYear");
	}
	
	public void testSortedByYearDesc()
	{
		System.out.println("\nStarting SortItemTest#testSortedByYearDesc");
		
		list = new ArrayList<Item>();
		boolean result = true;
		
		AccessItems ac = new AccessItems();
		ac.sortItems(list,"desc","year");
		
		for (int i = 1; i < list.size(); i++)
		{
			if(list.get(i-1).getItemYear() < list.get(i).getItemYear())
			{
				result = false;
			}
		}
		
		assertTrue(result);
		System.out.println("Finished SortItemTest#testSortedByYearDesc");
	}
}
