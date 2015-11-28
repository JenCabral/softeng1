package comp3350.tests.objects;

import junit.framework.TestCase;
import comp3350.organizr.objects.Item;

public class ItemTest extends TestCase
{
	public ItemTest(String arg0)
	{
		super(arg0);
	}

	public void testItemNameDescription()
	{
		Item item;
		
		System.out.println("\nStarting ItemTest#testItemNameDescription");
		
		item = new Item(123, "Peter Rabbit", "A treasured possession", 1957);
		assertNotNull(item);
		assertEquals(123, item.getItemID());
		assertEquals("Peter Rabbit", item.getItemName());
		assertEquals("A treasured possession", item.getItemDescription());
		
		System.out.println("Finished ItemTest#testItemNameDescription");
	}
	
	public void testItemNoNameNoDescription()
	{
		Item item;
		
		System.out.println("\nStarting ItemTest#testItemNoNameNoDescription");
		
		item = new Item(123, null, null, 1900);
		assertNotNull(item);
		assertEquals(123, item.getItemID());
		assertEquals("Unnamed", item.getItemName());
		assertEquals(null, item.getItemDescription());
		
		System.out.println("Finished ItemTest#testItemNoNameNoDescription");
	}
	
	public void testItemEquals()
	{
		Item item1, item2;
		
		System.out.println("\nStarting ItemTest#testItemEquals");
		
		item1 = new Item(123, "Peter Rabbit", "A treasured possession", 1957);
		item2 = new Item(123, "Not a rabbit", "Not even treasured", 0);
		assertNotNull(item1);
		assertNotNull(item2);
		assertTrue(item1.equals(item2));
		
		System.out.println("Finished ItemTest#testItemEquals");
	}
	
	public void testItemNotEquals()
	{
		Item item1, item2;;

		System.out.println("\nStarting ItemTest#testItemNotEquals");
		
		item1 = new Item(1234567, "Peter Rabbit", "A treasured possession", 1739);
		item2 = new Item(123, "Peter Rabbit", "A treasured possession", 200);
		assertNotNull(item1);
		assertNotNull(item2);
		assertFalse(item1.equals(item2));
		
		System.out.println("Finished ItemTest#testItemNotEquals");
	}
	
	public void testItemYear(){
		System.out.println("\nStarting ItemTest#testItemYear");
		
		Item item = new Item(9000, "Peter Rabbit", "A treasured possession", 2015);
		Item item2 = new Item(9001, "Vigilante Thief", "Thieves for days", 2013);
		
		assertNotNull(item);
		assertNotNull(item2);
		assertEquals(item.getItemYear(), 2015);
		assertEquals(item2.getItemYear(), 2013);
		assertFalse(item.getItemYear() == item2.getItemYear());
		
		System.out.println("Finished ItemTest#testItemYear");
	}
	
	public void testToString()
	{
		long itemID = 88;
		int year = 2015;
		String itemName = "Peter Rabbit";
		String itemDescription = "A treasured possession";
		String toString;
		Item item;
		
		System.out.println("\nStarting ItemTest#testToString");
		
		item = new Item(itemID , itemName, itemDescription, year);
		toString = item.toString();
		
		assertEquals(toString, "Item: " +itemID +" " +itemName +" " +itemDescription +" " +year);
		
		System.out.println("\nFinished ItemTest#testToString");
	}
	
	public void testEachUnnamed()
	{
		Item item1, item2;
		
		System.out.println("\nStarting ItemTest#testEachUnnamed");
		
		item1 = new Item(1234567, "", "A treasured possession", 2015);
		item2 = new Item(1234567, null, "A treasured possession", 2015);
		
		assertEquals(item1.getItemName(),"Unnamed");
		assertEquals(item2.getItemName(),"Unnamed");
		
		item2.setItemName("");
		
		assertEquals(item2.getItemName(),"Unnamed");
		
		System.out.println("\nFinished ItemTest#testEachUnnamed");
	}
}