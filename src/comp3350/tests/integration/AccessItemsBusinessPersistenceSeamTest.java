package comp3350.tests.integration;

import java.util.ArrayList;

import junit.framework.TestCase;

import comp3350.organizr.application.Main;
import comp3350.organizr.application.Services;
import comp3350.organizr.business.AccessItems;
import comp3350.organizr.objects.Item;
import comp3350.tests.persistence.DataAccessStub;

public class AccessItemsBusinessPersistenceSeamTest extends TestCase
{
	public AccessItemsBusinessPersistenceSeamTest(String arg0)
	{
		super(arg0);
	}
	
	public void testAccessItemsCRUD()
	{		
		System.out.println("\nStarting AccessItemsBusinessPersistenceSeamTest#AccessItemsCRUD (using default DB)");
		Services.closeDataAccess();
		Services.createDataAccess(Main.dbName);
		Services.getDataAccess(Main.dbName);
		accessItemsCRUDTest();
		System.out.println("Finished AccessItemsBusinessPersistenceSeamTest#AccessItemsCRUD (using default DB)");
		
		System.out.println("\nStarting AccessItemsBusinessPersistenceSeamTest#AccessItemsCRUD (using stub)");
		Services.closeDataAccess();
		Services.createDataAccess(new DataAccessStub(Main.dbName));
		accessItemsCRUDTest();
		Services.closeDataAccess();
		System.out.println("Finished AccessItemsBusinessPersistenceSeamTest#AccessItemsCRUD (using stub)");
	}
	
	private void accessItemsCRUDTest()
	{
		AccessItems ai;
		Item item;
		String result;
		long newItemID = 0;
		
		//Exercise getting a new ID, reading, and inserting
		ai = new AccessItems();
		item = ai.getItemByID(1);
		
		assertNotNull(item);
		assertTrue(1 == item.getItemID());
		
		newItemID = ai.getNextID();
		
		assertTrue(newItemID > 0);
		assertTrue(newItemID > item.getItemID());
		
		item = new Item(1);
		item.setItemID(newItemID);
		result = ai.insertItem(item, 1111);
		assertNull(result);
		
		item = ai.getItemByID(newItemID);
		
		assertNotNull(item);
		assertTrue(newItemID == item.getItemID());
		
		item.setItemName("Drainpipe Vermin");
		result = ai.updateItem(item);
		item = ai.getItemByID(newItemID);
		
		assertNotNull(item);
		assertEquals(item.getItemName(),"Drainpipe Vermin");

		result = ai.deleteItem(item);
		
		assertNull(result);
		
		item = ai.getItemByID(newItemID);
		
		assertNull(item);
	}
	
	public void testAccessItemsCRUDInvalid()
	{		
		System.out.println("\nStarting AccessItemsBusinessPersistenceSeamTest#testAccessItemsCRUDInvalid (using default DB)");
		Services.closeDataAccess();
		Services.createDataAccess(Main.dbName);
		accessItemsCRUDInvalidTest();
		System.out.println("Finished AccessItemsBusinessPersistenceSeamTest#testAccessItemsCRUDInvalid (using default DB)");
		
		System.out.println("\nStarting AccessItemsBusinessPersistenceSeamTest#testAccessItemsCRUDInvalid (using stub)");
		Services.closeDataAccess();
		Services.createDataAccess(new DataAccessStub(Main.dbName));
		accessItemsCRUDInvalidTest();
		Services.closeDataAccess();
		System.out.println("Finished AccessItemsBusinessPersistenceSeamTest#testAccessItemsCRUDInvalid (using stub)");
	}
	
	private void accessItemsCRUDInvalidTest()
	{
		AccessItems ai;
		Item item;
		String result;
		long newItemID = 0;

		//Exercise incorrectly getting a new ID, reading, and inserting
		ai = new AccessItems();
		item = ai.getItemByID(99);
		
		assertNull(item);
		
		item = new Item(99);
		newItemID = ai.getNextID()-1;
		
		item.setItemID(newItemID);
		result = ai.insertItem(item, 1111);
		
		assertNotNull(result);
		
		item = ai.getItemByID(newItemID);
		
		assertNotNull(item);
		
		item = new Item(99);
		result = ai.updateItem(item);
		
		assertNotNull(result);
		
		item = ai.getItemByID(99);
		
		assertNull(item);

		result = ai.deleteItem(item);
		
		assertNotNull(result);
		
		item = ai.getItemByID(99);
		
		assertNull(item);
	}
	
	public void testGetItems()
	{
		System.out.println("\nStarting AccessItemsBusinessPersistenceSeamTest#testGetItems (using default DB)");
		Services.closeDataAccess();
		Services.createDataAccess(Main.dbName);
		getItemsTest();
		System.out.println("Finished AccessItemsBusinessPersistenceSeamTest#testGetItems (using default DB)");
		
		System.out.println("\nStarting AccessItemsBusinessPersistenceSeamTest#testGetItems (using stub)");
		Services.closeDataAccess();
		Services.createDataAccess(new DataAccessStub(Main.dbName));
		getItemsTest();
		Services.closeDataAccess();
		System.out.println("Finished AccessItemsBusinessPersistenceSeamTest#testGetItems (using stub)");
	}
	
	private void getItemsTest()
	{
		AccessItems ai;
		Item item1;
		Item item2;
		ArrayList<Item> items;
		
		ai = new AccessItems();
		items = new ArrayList<Item>();
		item1 = new Item(1, "Jaga", "Thundercats", 1986);
		items.add(item1);
		item2 = new Item(2, "Lion-o", "Thundercats", 1986);
		items.add(item2);
		ai.getItems(items);
		
		assertTrue(items.get(0).equals(item1));
		assertTrue(items.get(1).equals(item2));
	}
	
	public void testGetItemsInvalid()
	{
		System.out.println("\nStarting AccessItemsBusinessPersistenceSeamTest#testGetItemsInvalid (using default DB)");
		Services.closeDataAccess();
		Services.createDataAccess(Main.dbName);
		getItemsInvalidTest();
		System.out.println("Finished AccessItemsBusinessPersistenceSeamTest#testGetItemsInvalid (using default DB)");
		
		System.out.println("\nStarting AccessItemsBusinessPersistenceSeamTest#testGetItemsInvalid (using stub)");
		Services.closeDataAccess();
		Services.createDataAccess(new DataAccessStub(Main.dbName));
		getItemsInvalidTest();
		Services.closeDataAccess();
		System.out.println("Finished AccessItemsBusinessPersistenceSeamTest#testGetItemsInvalid (using stub)");
	}
	
	private void getItemsInvalidTest()
	{
		AccessItems ai;
		Item item1;
		Item item2;
		ArrayList<Item> items;
		
		ai = new AccessItems();
		items = new ArrayList<Item>();
		item1 = new Item(75, "Jaga", "Thundercats", 1986);
		items.add(item1);
		item2 = new Item(36, "Lion-o", "Thundercats", 1986);
		items.add(item2);
		ai.getItems(items);
		
		assertFalse(items.get(0).equals(item1));
		assertFalse(items.get(1).equals(item2));
	}
	
	public void testGetItemsinCollection()
	{
		System.out.println("\nStarting AccessItemsBusinessPersistenceSeamTest#testGetItemsinCollection (using default DB)");
		Services.closeDataAccess();
		Services.createDataAccess(Main.dbName);
		getItemsinCollectionTest();
		System.out.println("Finished AccessItemsBusinessPersistenceSeamTest#testGetItemsinCollection (using default DB)");
		
		System.out.println("\nStarting AccessItemsBusinessPersistenceSeamTest#testGetItemsinCollection (using stub)");
		Services.closeDataAccess();
		Services.createDataAccess(new DataAccessStub(Main.dbName));
		getItemsinCollectionTest();
		Services.closeDataAccess();
		System.out.println("Finished AccessItemsBusinessPersistenceSeamTest#testGetItemsinCollection (using stub)");
	}
	
	private void getItemsinCollectionTest()
	{
		AccessItems ai;
		ArrayList<Item> items, items2;
		
		ai = new AccessItems();
		items = ai.getItemsinCollection(1111);
		items2 = ai.getItemsinCollection(9999);
		
		assertEquals(items.size(), 6);
		assertEquals(items2.size(), 0);
	}
	
	public void testSearchItem()
	{
		System.out.println("\nStarting AccessItemsBusinessPersistenceSeamTest#testSearchItem (using default DB)");
		Services.closeDataAccess();
		Services.createDataAccess(Main.dbName);
		searchItemTest();
		System.out.println("Finished AccessItemsBusinessPersistenceSeamTest#testSearchItem (using default DB)");
		
		System.out.println("\nStarting AccessItemsBusinessPersistenceSeamTest#testSearchItem (using stub)");
		Services.closeDataAccess();
		Services.createDataAccess(new DataAccessStub(Main.dbName));
		searchItemTest();
		Services.closeDataAccess();
		System.out.println("Finished AccessItemsBusinessPersistenceSeamTest#testSearchItem (using stub)");
	}
	
	private void searchItemTest()
	{
		AccessItems ai;
		ArrayList<Item> items;
		
		ai = new AccessItems();
		items = new ArrayList<Item>();
		ai.searchItem(12345, items, "Jaga");
		assertEquals(items.size(), 1);
		
		items.clear();
		ai.searchItem(1111, items, "");
		
		assertEquals(items.size(), 6);
	}
}