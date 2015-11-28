package comp3350.tests.business;

import java.util.ArrayList;
import java.util.List;

import comp3350.organizr.application.Main;
import comp3350.organizr.application.Services;
import comp3350.organizr.business.AccessItems;
import comp3350.organizr.objects.Collection;
import comp3350.organizr.objects.Item;
import comp3350.organizr.persistence.DataAccess;
import comp3350.tests.persistence.DataAccessStub;
import junit.framework.TestCase;

public class SearchItemTest extends TestCase 
{
	List<Item> list;
	Collection collection;
	String query;
	
	private static String dbName = Main.dbName;
	
	public SearchItemTest(String args)
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
	
	public void testNoMatch()
	{
		list = new ArrayList<Item>();
		
		query = "test";
		
		AccessItems ac = new AccessItems();
		ac.searchItem(12345, list, query);
		
		assertTrue(list.isEmpty());
	}
	
	public void testMatch()
	{
		list = new ArrayList<Item>();
		
		query = "Lion";
		
		AccessItems ac = new AccessItems();
		ac.searchItem(12345, list, query);
		
		assertFalse(list.isEmpty());
	}
	
	public void testBlankQuery()
	{
		list = new ArrayList<Item>();
		query = "";
		
		AccessItems ac = new AccessItems();
		ac.searchItem(12345, list, query);		
		
		assertFalse(list.isEmpty());
	}
}
