package comp3350.tests.objects;

import junit.framework.TestCase;
import comp3350.organizr.objects.Collection;

public class CollectionTest extends TestCase
{
	public CollectionTest(String arg0)
	{
		super(arg0);
	}

	public void testCollectionNameDescription()
	{
		Collection collection;

		System.out.println("\nStarting CollectionTest#testCollectionNameDescription");

		collection = new Collection(12345,"Stuffed Animals", "So soft, so fluffy.");
		assertNotNull(collection);
		assertEquals(12345, collection.getCollectionID());
		assertEquals("Stuffed Animals", collection.getCollectionName());
		assertEquals("So soft, so fluffy.", collection.getCollectionDescription());
		
		collection.setCollectionDescription("Ever so soft and fluffy");
		
		assertEquals("Ever so soft and fluffy", collection.getCollectionDescription());
		
		System.out.println("\nFinished CollectionTest#testCollectionNameDescription");
	}
	
	public void testCollectionNoNameNoDescription()
	{
		Collection collection;

		System.out.println("\nStarting CollectionTest#testCollectionNoNameNoDescription");

		collection = new Collection(12345, null, null);
		assertNotNull(collection);
		assertEquals(12345, collection.getCollectionID());
		assertEquals("Unnamed", collection.getCollectionName());
		assertEquals(null, collection.getCollectionDescription());
		
		System.out.println("\nFinished CollectionTest#testCollectionNoNameNoDescription");
	}
	
	public void testCollectionEquals()
	{
		Collection collection1, collection2;
		
		System.out.println("\nStarting CollectionTest#testCollectionEquals");
		
		collection1 = new Collection(123, "Stuffed Animals", "These are mine");
		collection2 = new Collection(123, "Not an animal", "Not even treasured");
		assertNotNull(collection1);
		assertNotNull(collection2);
		assertTrue(collection1.equals(collection2));
		
		System.out.println("Finished CollectionTest#testCollectionEquals");
	}
	
	public void testCollectionNotEquals()
	{
		Collection collection1, collection2;
		
		System.out.println("\nStarting CollectionTest#testCollectionNotEquals");
		
		collection1 = new Collection(1234567, "Stuffed Animals", "These are mine");
		collection2 = new Collection(123, "Stuffed Animals", "These are mine");
		assertNotNull(collection1);
		assertNotNull(collection2);
		assertFalse(collection1.equals(collection2));
		
		System.out.println("Finished CollectionTest#testCollectionNotEquals");
	}
	
	public void testToString()
	{
		long collectionID = 1;
		String collectionName = "Stuffed Animals";
		String collectionDescription = "These are mine";
		String toString;
		Collection collection;
		
		System.out.println("\nStarting CollectionTest#testToString");
		
		collection = new Collection(collectionID , collectionName, collectionDescription);
		toString = collection.toString();
		
		assertEquals(toString, "Collection: " +collectionID +" " +collectionName +" " +collectionDescription);
		
		System.out.println("\nFinished CollectionTest#testToString");
	}
	
	public void testEachUnnamed()
	{
		Collection collection1, collection2;
		
		System.out.println("\nStarting CollectionTest#testEachUnnamed");
		
		collection1 = new Collection(1234567, "", "These are mine");
		collection2 = new Collection(1234567, null, "These are mine");
		
		assertEquals(collection1.getCollectionName(),"Unnamed");
		assertEquals(collection2.getCollectionName(),"Unnamed");
		
		collection2.setCollectionName("");
		
		assertEquals(collection2.getCollectionName(),"Unnamed");
		
		System.out.println("\nFinished CollectionTest#testEachUnnamed");
	}
}

