package comp3350.tests.objects;

import junit.framework.TestCase;
import comp3350.organizr.objects.Collection;
import comp3350.organizr.objects.ItemCollection;

public class ItemCollectionTest extends TestCase
{
	public ItemCollectionTest(String arg0)
	{
		super(arg0);
	}

	public void testItemCollection()
	{
		ItemCollection itemCollection;

		System.out.println("\nStarting ItemCollectionTest#testItemCollection");

		itemCollection = new ItemCollection(123, 12345);
		assertNotNull(itemCollection);
		assertEquals(123, itemCollection.getItemID());
		assertEquals(12345, itemCollection.getCollectionID());

		System.out.println("Finished ItemCollectionTest#testItemCollection");
	}
	
	public void testItemCollectionEquals()
	{
		ItemCollection itemCollection1;
		ItemCollection itemCollection2;

		System.out.println("\nStarting ItemCollectionTest#testItemCollectionEquals");

		itemCollection1 = new ItemCollection(123, 12345);
		itemCollection2 = new ItemCollection(123, 12345);
		assertNotNull(itemCollection1);
		assertNotNull(itemCollection2);
		assertTrue(itemCollection1.equals(itemCollection2));

		System.out.println("Finished ItemCollectionTest#testItemCollectionEquals");
	}
	
	public void testItemCollectionNotEquals()
	{
		ItemCollection itemCollection1;
		ItemCollection itemCollection2;

		System.out.println("\nStarting ItemCollectionTest#testItemCollectionNotEquals");

		itemCollection1 = new ItemCollection(1234567, 1234567899);
		itemCollection2 = new ItemCollection(123, 12345);
		assertNotNull(itemCollection1);
		assertNotNull(itemCollection2);
		assertFalse(itemCollection1.equals(itemCollection2));

		System.out.println("Finished ItemCollectionTest#testItemCollectionNotEquals");
	}
	
	public void testToString()
	{
		long collectionID = 88;
		long itemID = 99;
		String toString;
		ItemCollection itemCollection;
		
		System.out.println("\nStarting ItemCollectionTest#testToString");
		
		itemCollection = new ItemCollection(itemID, collectionID);
		toString = itemCollection.toString();
		
		assertEquals(toString,"ItemCollection: " +itemID +" " +collectionID);
		
		System.out.println("\nFinished ItemCollectionTest#testToString");
	}
}