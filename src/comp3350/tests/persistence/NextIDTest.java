package comp3350.tests.persistence;

import junit.framework.TestCase;
import comp3350.organizr.objects.Item;
import comp3350.organizr.persistence.DataAccess;

public class NextIDTest extends TestCase
{
	private DataAccess dataAccess;
	long nextID;	

	public NextIDTest(String arg0)
	{
		super(arg0);
	}

	public void setUp()
	{
		dataAccess = new DataAccessStub("Test");
	}
	
	public void testEmtpyCollectionList()
	{
		System.out.println("\nStarting NextIDTest#testEmptyCollection: Get next ID for a null Collection list");

		nextID = dataAccess.getNextID("collection");
		assertNotNull(nextID);
		assertEquals(0 , nextID);

		System.out.println("Finished NextIDTest#testEmptyCollection: Get next ID for a null Collection list");
	}
	
	public void testEmptyItemsList()
	{
		System.out.println("\nStarting NextIDTest#testEmptyItem: Get next ID for a null Item list");

		nextID = dataAccess.getNextID("item");
		assertNotNull(nextID);
		assertEquals(0 , nextID);

		System.out.println("Finished NextIDTest#testEmptyItem: Get next ID for a null Item list");
	}
	
	public void testTwoItemsList()
	{
		System.out.println("\nStarting NextIDTest#testEmptyCollection: Get next ID for a 2 Item list");

		Item item;

		item = new Item(1, "Jaga", "Thundercats", 1986);
		dataAccess.insertItem(item, 12345);
		item = new Item(2, "Lion-o", "Thundercats", 1986);
		dataAccess.insertItem(item, 12345);
		
		nextID = dataAccess.getNextID("item");
		assertEquals(3 , nextID);
		
		System.out.println("Finished NextIDTest#testEmptyCollection: Get next ID for a 2 Item list");
	}
	
	public void testAfterDeleteItem()
	{
		System.out.println("\nStarting NextIDTest#testAfterDeleteItem: Get next ID after Item deleted");

		Item item;

		item = new Item(1, "Jaga", "Thundercats", 1986);
		dataAccess.insertItem(item, 12345);
		item = new Item(2, "Lion-o", "Thundercats", 1986);
		dataAccess.insertItem(item, 12345);
		item = new Item(3, "Tygra", "Thundercats", 1986);
		dataAccess.insertItem(item, 12345);
		
		dataAccess.deleteItem(item);
		
		nextID = dataAccess.getNextID("item");
		assertEquals(3 , nextID);
		
		System.out.println("Finished NextIDTest#testAfterDeleteItem: Get next ID after Item deleted");
	}
}