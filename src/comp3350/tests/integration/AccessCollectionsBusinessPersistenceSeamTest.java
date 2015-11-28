package comp3350.tests.integration;

import java.util.ArrayList;

import junit.framework.TestCase;

import comp3350.organizr.application.Main;
import comp3350.organizr.application.Services;
import comp3350.organizr.business.AccessCollections;
import comp3350.organizr.objects.Collection;
import comp3350.tests.persistence.DataAccessStub;

public class AccessCollectionsBusinessPersistenceSeamTest extends TestCase
{
	public AccessCollectionsBusinessPersistenceSeamTest(String arg0)
	{
		super(arg0);
	}
	
	public void testAccessCollectionsCRUD()
	{		
		System.out.println("\nStarting AccessCollectionsBusinessPersistenceSeamTest#AccessCollectionsCRUD (using default DB)");
		Services.closeDataAccess();
		Services.createDataAccess(Main.dbName);
		Services.getDataAccess(Main.dbName);
		accessCollectionsCRUDTest();
		System.out.println("Finished AccessCollectionsBusinessPersistenceSeamTest#AccessCollectionsCRUD (using default DB)");
		
		System.out.println("\nStarting AccessCollectionsBusinessPersistenceSeamTest#AccessCollectionsCRUD (using stub)");
		Services.closeDataAccess();
		Services.createDataAccess(new DataAccessStub(Main.dbName));
		accessCollectionsCRUDTest();
		Services.closeDataAccess();
		System.out.println("Finished AccessCollectionsBusinessPersistenceSeamTest#AccessCollectionsCRUD (using stub)");
	}
	
	private void accessCollectionsCRUDTest()
	{
		AccessCollections ac;
		Collection collection;
		String result;
		long newCollectionID = 0;
		
		//Exercise getting a new ID, reading, and inserting
		ac = new AccessCollections();
		collection = ac.getCollectionByID(1111);
		
		assertNotNull(collection);
		assertTrue(1111 == collection.getCollectionID());
		
		newCollectionID = ac.getNextID();
		
		assertTrue(newCollectionID > 0);
		assertTrue(newCollectionID > collection.getCollectionID());
		
		collection = new Collection(newCollectionID);
		result = ac.insertCollection(collection);
		
		assertNull(result);
		
		collection = ac.getCollectionByID(newCollectionID);
		
		assertNotNull(collection);
		assertTrue(newCollectionID == collection.getCollectionID());
		
		collection.setCollectionName("Not an Elf Deck");
		result = ac.updateCollection(collection);
		collection = ac.getCollectionByID(newCollectionID);
		
		assertNotNull(collection);
		assertEquals(collection.getCollectionName(),"Not an Elf Deck");

		result = ac.deleteCollection(collection);
		
		assertNull(result);
		
		collection = ac.getCollectionByID(newCollectionID);
		
		assertNull(collection);
	}
	
	public void testAccessCollectionsCRUDInvalid()
	{		
		System.out.println("\nStarting AccessCollectionsBusinessPersistenceSeamTest#testAccessCollectionsCRUDInvalid (using default DB)");
		Services.closeDataAccess();
		Services.createDataAccess(Main.dbName);
		accessCollectionsCRUDInvalidTest();
		System.out.println("Finished AccessCollectionsBusinessPersistenceSeamTest#testAccessCollectionsCRUDInvalid (using default DB)");
		
		System.out.println("\nStarting AccessCollectionsBusinessPersistenceSeamTest#testAccessCollectionsCRUDInvalid (using stub)");
		Services.closeDataAccess();
		Services.createDataAccess(new DataAccessStub(Main.dbName));
		accessCollectionsCRUDInvalidTest();
		Services.closeDataAccess();
		System.out.println("Finished AccessCollectionsBusinessPersistenceSeamTest#testAccessCollectionsCRUDInvalid (using stub)");
	}
	
	private void accessCollectionsCRUDInvalidTest()
	{
		AccessCollections ac;
		Collection collection;
		String result;
		long newCollectionID = 0;
		
		//Exercise incorrectly getting a new ID, reading, and inserting
		ac = new AccessCollections();
		collection = ac.getCollectionByID(99);
		
		assertNull(collection);
		
		collection = new Collection(99);
		newCollectionID = ac.getNextID()-1;
		
		collection.setCollectionID(newCollectionID);
		result = ac.insertCollection(collection);
		
		assertNotNull(result);
		
		collection = ac.getCollectionByID(newCollectionID);
		
		assertNotNull(collection);
		
		collection = new Collection(99);
		result = ac.updateCollection(collection);
		
		assertNotNull(result);
		
		collection = ac.getCollectionByID(99);
		
		assertNull(collection);

		result = ac.deleteCollection(collection);
		
		assertNotNull(result);
		
		collection = ac.getCollectionByID(99);
		
		assertNull(collection);
	}
	
	public void testGetCollections()
	{
		System.out.println("\nStarting AccessCollectionsBusinessPersistenceSeamTest#testGetCollections (using default DB)");
		Services.closeDataAccess();
		Services.createDataAccess(Main.dbName);
		getCollectionsTest();
		System.out.println("Finished AccessCollectionsBusinessPersistenceSeamTest#testGetCollections (using default DB)");
		
		System.out.println("\nStarting AccessCollectionsBusinessPersistenceSeamTest#testGetCollections (using stub)");
		Services.closeDataAccess();
		Services.createDataAccess(new DataAccessStub(Main.dbName));
		getCollectionsTest();
		Services.closeDataAccess();
		System.out.println("Finished AccessCollectionsBusinessPersistenceSeamTest#testGetCollections (using stub)");
	}
	
	private void getCollectionsTest()
	{
		AccessCollections ac;
		Collection collection1, collection2;
		ArrayList<Collection> collections;
		
		ac = new AccessCollections();
		collections = new ArrayList<Collection>();
		collection1 = new Collection(1111, "Rat Deck","Magic the Gathering");
		collections.add(collection1);
		collection2 = new Collection(12345, "80s Action figures","collection of action figures from the 1980s");
		collections.add(collection2);
		ac.getCollections(collections);
		
		assertTrue(collections.get(0).equals(collection1));
		assertTrue(collections.get(1).equals(collection2));
	}
	
	public void testGetCollectionsInvalid()
	{
		System.out.println("\nStarting AccessCollectionsBusinessPersistenceSeamTest#testGetCollectionsInvalid (using default DB)");
		Services.closeDataAccess();
		Services.createDataAccess(Main.dbName);
		getCollectionsInvalidTest();
		System.out.println("Finished AccessCollectionsBusinessPersistenceSeamTest#testGetCollectionsInvalid (using default DB)");
		
		System.out.println("\nStarting AccessCollectionsBusinessPersistenceSeamTest#testGetCollectionsInvalid (using stub)");
		Services.closeDataAccess();
		Services.createDataAccess(new DataAccessStub(Main.dbName));
		getCollectionsInvalidTest();
		Services.closeDataAccess();
		System.out.println("Finished AccessCollectionsBusinessPersistenceSeamTest#testGetCollectionsInvalid (using stub)");
	}
	
	private void getCollectionsInvalidTest()
	{
		AccessCollections ac;
		Collection collection;
		ArrayList<Collection> collections;
		
		ac = new AccessCollections();
		collections = new ArrayList<Collection>();
		collection = new Collection(12345, "10s Action figures","collection of action figures from the 1910s");
		collections.add(collection);
		ac.getCollections(collections);
		
		assertFalse(collections.get(0).equals(collection));
	}
	
	public void testSearchCollection()
	{
		System.out.println("\nStarting AccessCollectionsBusinessPersistenceSeamTest#testSearchCollection (using default DB)");
		Services.closeDataAccess();
		Services.createDataAccess(Main.dbName);
		searchCollectionTest();
		System.out.println("Finished AccessCollectionsBusinessPersistenceSeamTest#testSearchCollection (using default DB)");
		
		System.out.println("\nStarting AccessCollectionsBusinessPersistenceSeamTest#testSearchCollection (using stub)");
		Services.closeDataAccess();
		Services.createDataAccess(new DataAccessStub(Main.dbName));
		searchCollectionTest();
		Services.closeDataAccess();
		System.out.println("Finished AccessCollectionsBusinessPersistenceSeamTest#testSearchCollection (using stub)");
	}
	
	private void searchCollectionTest()
	{
		AccessCollections ac;
		ArrayList<Collection> collections;
		
		ac = new AccessCollections();
		collections = new ArrayList<Collection>();
		ac.searchCollection(collections, "Rat Deck");
		
		assertEquals(collections.size(), 1);
		
		collections.clear();
		ac.searchCollection(collections, "");
		
		assertEquals(collections.size(), 2);
	}
}