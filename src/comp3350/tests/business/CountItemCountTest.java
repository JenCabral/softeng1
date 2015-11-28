package comp3350.tests.business;

import junit.framework.TestCase;
import comp3350.organizr.business.Count;
import comp3350.organizr.objects.ItemCollection;

import java.util.List;
import java.util.ArrayList;

public class CountItemCountTest extends TestCase
{
	List <ItemCollection>list;
	ItemCollection itemCollection;
	int resultItemCount;
	//because we only allow a single collection for now, we'll only worry about one collection ID
	final long collectionID = 123;
	final long missingID = -1;

	public CountItemCountTest(String arg0)
	{
		super(arg0);
	}

	public void testNullList()
	{
		System.out.println("\nStarting ItemCountTest#testNullList");

		resultItemCount = 0;
		resultItemCount = Count.countItems(null);

		assertNotNull(resultItemCount);
		assertEquals(0 , resultItemCount);

		System.out.println("Finished ItemCountTest#testNullList");
	}

	public void testEmptyList()
	{
		System.out.println("\nStarting ItemCountTest#testEmptyList");

		resultItemCount = 0;
		list = new ArrayList<ItemCollection>();
		resultItemCount = Count.countItems(list);

		assertNotNull(resultItemCount);
		assertEquals(0, resultItemCount);

		System.out.println("Finished ItemCountTest#testEmptyList");
	}

	public void testNullObject()
	{	
		List <ItemCollection>list;
		
		System.out.println("\nStarting ItemCountTest#testNullObject");

		resultItemCount = 0;
		list = new ArrayList<ItemCollection>();
		list.add(null);
		resultItemCount = Count.countItems(list);

		assertNotNull(resultItemCount);
		assertEquals(0, resultItemCount);

		System.out.println("Finished ItemCountTest#testNullObject");
	}

	public void testMissingItemIds()
	{
		System.out.println("\nStarting ItemCountTest#testMissingItemIds");

		resultItemCount = 0;
		list = new ArrayList<ItemCollection>();
		itemCollection = new ItemCollection(missingID, collectionID);
		list.add(itemCollection);
		itemCollection = new ItemCollection(missingID, collectionID);
		list.add(itemCollection);
		resultItemCount = Count.countItems(list);

		assertNotNull(resultItemCount);
		assertEquals(0, resultItemCount);

		System.out.println("Finished ItemCountTest#testMissingItemIds");
	}

	public void testMissingCollectionIds()
	{
		System.out.println("\nStarting ItemCountTest#testMissingCollectionIds");

		resultItemCount = 0;
		list = new ArrayList<ItemCollection>();
		itemCollection = new ItemCollection(123, missingID);
		list.add(itemCollection);
		itemCollection = new ItemCollection(456, missingID);
		list.add(itemCollection);
		resultItemCount = Count.countItems(list);

		assertNotNull(resultItemCount);
		assertEquals(0, resultItemCount);

		System.out.println("Finished ItemCountTest#testMissingCollectionIds");
	}

	public void testMissingItemAndCollectionIds()
	{
		System.out.println("\nStarting ItemCountTest#testMissingItemAndCollectionIds");

		resultItemCount = 0;
		list = new ArrayList<ItemCollection>();
		itemCollection = new ItemCollection(missingID, collectionID);
		list.add(itemCollection);
		itemCollection = new ItemCollection(456, missingID);
		list.add(itemCollection);
		resultItemCount = Count.countItems(list);

		assertNotNull(resultItemCount);
		assertEquals(0, resultItemCount);

		System.out.println("Finished ItemCountTest#testMissingItemAndCollectionIds");
	}

	public void testSomeMissingItemIds()
	{
		System.out.println("\nStarting ItemCountTest#testSomeMissingItemIds");

		resultItemCount = 0;
		list = new ArrayList<ItemCollection>();
		itemCollection = new ItemCollection(123, collectionID);
		list.add(itemCollection);
		itemCollection = new ItemCollection(missingID, collectionID);
		list.add(itemCollection);
		itemCollection = new ItemCollection(456, collectionID);
		list.add(itemCollection);
		resultItemCount = Count.countItems(list);

		assertNotNull(resultItemCount);
		assertEquals(2, resultItemCount);

		System.out.println("Finished ItemCountTest#testSomeMissingItemIds");
	}	

	public void testSomeMissingCollectionIds()
	{
		System.out.println("\nStarting ItemCountTest#testSomeMissingCollectionIds");

		resultItemCount = 0;
		list = new ArrayList<ItemCollection>();
		itemCollection = new ItemCollection(123, missingID);
		list.add(itemCollection);
		itemCollection = new ItemCollection(124, collectionID);
		list.add(itemCollection);
		itemCollection = new ItemCollection(456, collectionID);
		list.add(itemCollection);
		resultItemCount = Count.countItems(list);

		assertNotNull(resultItemCount);
		assertEquals(2, resultItemCount);

		System.out.println("Finished ItemCountTest#testSomeMissingCollectionIds");
	}

	public void testSomeMissingItemAndCollectionIds()
	{
		System.out.println("\nStarting ItemCountTest#testSomeMissingItemAndCollectionIds");

		resultItemCount = 0;
		list = new ArrayList<ItemCollection>();
		itemCollection = new ItemCollection(123, missingID);
		list.add(itemCollection);
		itemCollection = new ItemCollection(124, collectionID);
		list.add(itemCollection);
		itemCollection = new ItemCollection(missingID, collectionID);
		list.add(itemCollection);
		itemCollection = new ItemCollection(457, collectionID);
		list.add(itemCollection);
		resultItemCount = Count.countItems(list);

		assertNotNull(resultItemCount);
		assertEquals(2, resultItemCount);

		System.out.println("Finished ItemCountTest#testSomeMissingItemAndCollectionIds");
	}
	
	public void testOneItem()
	{
		System.out.println("\nStarting ItemCountTest#testOneItem");
		
		resultItemCount = 0;
		list = new ArrayList<ItemCollection>();
		itemCollection = new ItemCollection(123, collectionID);
		list.add(itemCollection);
		resultItemCount = Count.countItems(list);
		
		assertNotNull(resultItemCount);
		assertEquals(1, resultItemCount);
		
		System.out.println("Finished testItemCount: one item");
	}
	
	public void testMultipleItems()
	{
		System.out.println("\nStarting ItemCountTest#testMultipleItems");
		
		resultItemCount = 0;
		list = new ArrayList<ItemCollection>();
		itemCollection = new ItemCollection(123, collectionID);
		list.add(itemCollection);
		itemCollection = new ItemCollection(124, collectionID);
		list.add(itemCollection);
		itemCollection = new ItemCollection(125, collectionID);
		list.add(itemCollection);
		itemCollection = new ItemCollection(126, collectionID);
		list.add(itemCollection);
		resultItemCount = Count.countItems(list);
		
		assertNotNull(resultItemCount);
		assertEquals(4, resultItemCount);
		
		System.out.println("Finished ItemCountTest#testMultipleItems");
	}

}