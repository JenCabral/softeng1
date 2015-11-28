package comp3350.tests.business;

import java.util.ArrayList;
import java.util.List;

import comp3350.organizr.application.Main;
import comp3350.organizr.application.Services;
import comp3350.organizr.business.AccessCollections;
import comp3350.organizr.objects.Collection;
import comp3350.organizr.persistence.DataAccess;
import comp3350.tests.persistence.DataAccessStub;
import junit.framework.TestCase;

public class SearchCollectionTest extends TestCase 
{
	List<Collection> list;
	Collection collection;
	String query;
	
	private static String dbName = Main.dbName;
	
	public SearchCollectionTest(String args)
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
		list = new ArrayList<Collection>();
		
		query = "test";
		
		AccessCollections ac = new AccessCollections();
		ac.searchCollection(list, query);
		
		assertTrue(list.isEmpty());
	}
	
	public void testMatch()
	{
		list = new ArrayList<Collection>();
		
		query = "Action";
		
		AccessCollections ac = new AccessCollections();
		ac.searchCollection(list, query);
		
		assertFalse(list.isEmpty());
	}
	
	public void testBlankQuery()
	{
		list = new ArrayList<Collection>();
		query = "";
		
		AccessCollections ac = new AccessCollections();
		ac.searchCollection(list, query);
		
		assertFalse(list.isEmpty());
	}
}
