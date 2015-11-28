package comp3350.tests.persistence;

import junit.framework.TestCase;
import java.util.ArrayList;
import comp3350.organizr.application.Services;
import comp3350.organizr.application.Main;
import comp3350.organizr.objects.Item;
import comp3350.organizr.objects.Collection;
import comp3350.organizr.objects.ItemCollection;
import comp3350.organizr.persistence.DataAccess;

public class DataAccessTest extends TestCase
{
	private static String dbName = Main.dbName;
	
	public DataAccessTest(String arg0)
	{
		super(arg0);
	}
	
	public void testGetItemSequentialTypical()
	{
		System.out.println("\nStarting DataAccessTest#testGetItemSequentialTypical (using stub)");
		
		Services.closeDataAccess();
		Services.createDataAccess((DataAccess) new DataAccessStub(dbName));
		
		getItemSequentialTypicalTest();
		
		System.out.println("\nFinished DataAccessTest#testGetItemSequentialTypical (using stub)");
	}
	
	public static void getItemSequentialTypicalTest()
	{
		DataAccess dataAccess;
		Item item;
		ArrayList<Item> items;
		String result;
		
		items = new ArrayList<Item>();
		dataAccess = (DataAccess) Services.getDataAccess(dbName);
		result = dataAccess.getItemSequential(items);
		item = (Item) items.get(0);
		
		assertNull(result);
		assertNotNull(items);
		assertEquals(23, items.size());
		assertEquals(1, item.getItemID());
	}

	public static void testGetCollectionSequentialTypical() 
	{
		System.out.println("\nStarting DataAccessTest#testGetCollectionSequentialTypical (using stub)");
		
		Services.closeDataAccess();
		Services.createDataAccess((DataAccess) new DataAccessStub(dbName));
		
		getCollectionSequentialTypicalTest();
		
		System.out.println("\nFinished DataAccessTest#testGetCollectionSequentialTypical (using stub)");
	}

	public static void getCollectionSequentialTypicalTest()
	{
		DataAccess dataAccess;
		ArrayList<Collection> collections;
		Collection collection;
		String result;
		collections = new ArrayList<Collection>();
		dataAccess = (DataAccess) Services.getDataAccess(dbName);
		result = dataAccess.getCollectionSequential(collections);
		collection = (Collection) collections.get(1);

		assertNull(result);
		assertNotNull(collections);
		assertEquals(2, collections.size());
		assertEquals(12345, collection.getCollectionID());
	}

	public static void testGetItemElementsTypical() 
	{
		System.out.println("\nStarting DataAccessTest#testGetItemElementsTypical (using stub)");

		Services.closeDataAccess();
		Services.createDataAccess((DataAccess) new DataAccessStub(dbName));
		
		getItemElementsTypicalTest();
		
		System.out.println("\nFinished DataAccessTest#testGetItemElementsTypical (using stub)");
	}
	
	public static void getItemElementsTypicalTest()
	{
		DataAccess dataAccess;
		ArrayList<ItemCollection> itemsCollections;
		ItemCollection itemCollection;
		
		dataAccess = (DataAccess) Services.getDataAccess(dbName);
		itemsCollections = dataAccess.getItemElements(new ItemCollection(1, 12345));
		itemCollection = (ItemCollection) itemsCollections.get(0);
		
		assertNotNull(itemCollection);
		assertEquals(1, itemCollection.getItemID());
		assertEquals(12345, itemCollection.getCollectionID());
	}
	
	public static void testUpdateItemsTypical()
	{
		System.out.println("\nStarting DataAccessTest#testUpdateItemsTypical (using stub)");

		Services.closeDataAccess();
		Services.createDataAccess((DataAccess) new DataAccessStub(dbName));
		
		updateItemsTypicalTest();
		
		System.out.println("\nFinished DataAccessTest#testUpdateItemsTypical (using stub)");
	}
	
	public static void updateItemsTypicalTest()
	{
		DataAccess dataAccess;
		Item item1, item2;
		dataAccess = (DataAccess) Services.getDataAccess(dbName);
		item1 = dataAccess.getItemByID(1);
		item2 = new Item(item1.getItemID(), item1.getItemName(), item1.getItemDescription(), item1.getItemYear());
		item1.setItemName("A very modified name");
		dataAccess.updateItem(item1);
		item1 = null;
		item1 = dataAccess.getItemByID(1);
		
		assertFalse(item1.getItemName().equals(item2.getItemName()));
		
		dataAccess.updateItem(item2);
		
		item1 = dataAccess.getItemByID(1);
		
		assertEquals(item1.getItemName(),item2.getItemName());
	}
	
	public static void testDeleteItemsTypical()
	{
		System.out.println("\nStarting DataAccessTest#testDeleteItemsTypical (using stub)");

		Services.closeDataAccess();
		Services.createDataAccess((DataAccess) new DataAccessStub(dbName));
		
		deleteItemsTypicalTest();
		
		System.out.println("\nFinished DataAccessTest#testDeleteItemsTypical (using stub)");
	}
	
	public static void deleteItemsTypicalTest()
	{
		DataAccess dataAccess;
		Item item1, item2;
		long item1ID;
		
		dataAccess = (DataAccess) Services.getDataAccess(dbName);
		item1 = new Item(dataAccess.getNextID("item"));
		dataAccess.insertItem(item1,  1111);
		item1ID = item1.getItemID();
		
		item2 = dataAccess.getItemByID(item1.getItemID());
		
		assertNotNull(item2);
		
		dataAccess.deleteItem(item1);
		item2 = dataAccess.getItemByID(item1ID);
				
		assertNull(item2);
	}	

	public static void testUpdateCollectionsTypical()
	{
		System.out.println("\nStarting DataAccessTest#testUpdateCollectionsTypical (using stub)");

		Services.closeDataAccess();
		Services.createDataAccess((DataAccess) new DataAccessStub(dbName));
		
		updateCollectionsTypicalTest();
		
		System.out.println("\nFinished DataAccessTest#testUpdateCollectionsTypical (using stub)");
	}
	
	public static void updateCollectionsTypicalTest()
	{
		DataAccess dataAccess;
		Collection collection1, collection2;
		dataAccess = (DataAccess) Services.getDataAccess(dbName);
		collection1 = dataAccess.getCollectionByID(1111);
		collection2 = new Collection(collection1.getCollectionID(), collection1.getCollectionName(), collection1.getCollectionDescription());
		collection1.setCollectionName("A very modified name");
		dataAccess.updateCollection(collection1);
		collection1 = null;
		collection1 = dataAccess.getCollectionByID(1111);
		
		assertFalse(collection1.getCollectionName().equals(collection2.getCollectionName()));
		
		dataAccess.updateCollection(collection2);
		
		collection1 = dataAccess.getCollectionByID(1111);
		
		assertEquals(collection1.getCollectionName(),collection2.getCollectionName());
	}
	
	public static void testDeleteCollectionsTypical()
	{
		System.out.println("\nStarting DataAccessTest#testDeleteCollectionsTypical (using stub)");

		Services.closeDataAccess();
		Services.createDataAccess((DataAccess) new DataAccessStub(dbName));
		
		deleteCollectionsTypicalTest();
		
		System.out.println("\nFinished DataAccessTest#testDeleteCollectionsTypical (using stub)");
	}
	
	public static void deleteCollectionsTypicalTest()
	{
		DataAccess dataAccess;
		Collection collection1, collection2;
		long collection1ID;
		
		dataAccess = (DataAccess) Services.getDataAccess(dbName);
		collection1 = new Collection(dataAccess.getNextID("collection"));
		dataAccess.insertCollection(collection1);
		collection1ID = collection1.getCollectionID();
		
		collection2 = dataAccess.getCollectionByID(collection1.getCollectionID());
		
		assertNotNull(collection2);
		
		dataAccess.deleteCollection(collection1);
		collection2 = dataAccess.getCollectionByID(collection1ID);
				
		assertNull(collection2);
	}	
}