/**
 * This code is not used in the first iteration. It is provided as
 * an example of usage of HSQLDB (for iteration 2).
 */

package comp3350.organizr.persistence;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLWarning;
import java.util.ArrayList;
import java.util.List;

import comp3350.organizr.objects.Item;
import comp3350.organizr.objects.Collection;
import comp3350.organizr.objects.ItemCollection;

public class DataAccessObject implements DataAccess
{
	private Statement st1, st2, st3, st4, st5,st6;
	private Connection c1;
	private ResultSet rs2, rs4, rs5, rs6, rs7,rs8;

	private String dbName;
	private String dbType;

	private ArrayList<ItemCollection> ic;

	private String cmdString;
	private int updateCount;
	private String result;
	private static String EOF = "  ";

	public DataAccessObject(String dbName)
	{
		this.dbName = dbName;
	}

	public void open(String dbPath)
	{
		String url;
		try
		{
			// Setup for HSQL
			dbType = "HSQL";
			Class.forName("org.hsqldb.jdbcDriver").newInstance();
			url = "jdbc:hsqldb:file:" + dbPath; // stored on disk mode
			c1 = DriverManager.getConnection(url, "SA", "");
			st1 = c1.createStatement();
			st2 = c1.createStatement();
			st3 = c1.createStatement();
			st4 = c1.createStatement();
			st5 = c1.createStatement();
			st6 = c1.createStatement();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		System.out.println("Opened " +dbType +" database " +dbPath);
	}

	public void close()
	{
		try
		{	// commit all changes to the database
			cmdString = "shutdown compact";
			rs2 = st1.executeQuery(cmdString);
			c1.close();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		System.out.println("Closed " +dbType +" database " +dbName);
	}

	public String getItemSequential(List<Item> itemResult)
	{
		Item item;
		long myID;
		String myItemName, myItemDescription;
		int myItemYear;
		myID = -1;
		myItemName = EOF;
		myItemYear = 0;
		myItemDescription = EOF;

		result = null;
		try
		{
			cmdString = "Select * from Items";
			rs2 = st1.executeQuery(cmdString);
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		try
		{
			while (rs2.next())
			{
				myID = rs2.getLong("ItemID");
				myItemName = rs2.getString("Name");
				myItemYear = rs2.getInt("Year");
				myItemDescription = rs2.getString("Description");
				item = new Item(myID, myItemName, myItemDescription, myItemYear);
				itemResult.add(item);
			}
			rs2.close();
		}
		catch (Exception e)
		{
			result = processSQLError(e);
		}

		return result;
	}

	@Override
	public Item getItemByID(long id) {
		Item item = null;
		try 
		{
			cmdString = "Select * from Items where ItemID=" + id;
			rs2 = st1.executeQuery(cmdString);
			if (rs2.next())
			{
				long itemID = rs2.getLong("ItemID");
				String itemName = rs2.getString("Name");
				int itemYear = rs2.getInt("Year");
				String itemDescription = rs2.getString("Description");
				item = new Item(itemID, itemName, itemDescription, itemYear);
			}
			rs2.close();
		}
		catch (Exception e)
		{
			processSQLError(e);
			// It would be good to raise the error somehow, 
			// rather than just returning a null item.
			item = null;
		}
		return item;
	}

	public String insertItem(Item currentItem, long collectionID)
	{
		String values;

		result = null;
		try
		{
			values = "" + currentItem.getItemID()
			         +", '" +currentItem.getItemName()
			         +"', '" +currentItem.getItemDescription()
			         +"', " +currentItem.getItemYear();
			cmdString = "Insert into Items " +" Values(" +values +")";
			updateCount = st1.executeUpdate(cmdString);
			result = checkWarning(st1, updateCount);
			
			values = "" + currentItem.getItemID() + ", " + String.valueOf(collectionID) + "";
			cmdString = "INSERT into ITEMSCOLLECTIONS " + "Values("+ values + ")";
			updateCount = st1.executeUpdate(cmdString);
			result = checkWarning(st1, updateCount);
		}
		catch (Exception e)
		{
			result = processSQLError(e);
		}
		return result;
	}

	public String updateItem(Item currentItem)
	{
		String values;
		String where;

		result = null;
		try
		{
			// Should check for empty values and not update them
			values = "Name='" +currentItem.getItemName()
			         +"', Description='" +currentItem.getItemDescription()
			         +"', Year=" +currentItem.getItemYear();
			where = "where ItemID=" +currentItem.getItemID();
			cmdString = "Update Items " +" Set " +values +" " +where;
			updateCount = st1.executeUpdate(cmdString);
			result = checkWarning(st1, updateCount);
		}
		catch (Exception e)
		{
			result = processSQLError(e);
		}
		return result;
	}

	public String deleteItem(Item currentItem)
	{
		String values;

		result = null;
		try
		{
			values = "" + currentItem.getItemID();
			
			cmdString = "Delete from ItemsCollections where ItemID=" +values;
			updateCount = st1.executeUpdate(cmdString);
			result = checkWarning(st1, updateCount);
			
			cmdString = "Delete from Items where ItemID=" +values;
			updateCount = st1.executeUpdate(cmdString);
			result = checkWarning(st1, updateCount);
		}
		catch (Exception e)
		{
			result = processSQLError(e);
		}
		return result;
	}

	public String getCollectionSequential(List<Collection> collectionResult)
	{
		Collection collection;
		long myID;
		String myCollectionName, myCollectionDescription;
		myID = -1;
		myCollectionName = EOF;
		myCollectionDescription = EOF;

		result = null;
		try
		{
			cmdString = "Select * from Collections";
			rs5 = st3.executeQuery(cmdString);
			while (rs5.next())
			{
				myID = rs5.getLong("CollectionID");
				myCollectionName = rs5.getString("Name");
				myCollectionDescription = rs5.getString("Description");
				collection = new Collection(myID, myCollectionName, myCollectionDescription);
				collectionResult.add(collection);
			}
			rs5.close();
		}
		catch (Exception e)
		{
			result = processSQLError(e);
		}
		return result;
	}

	@Override
	public Collection getCollectionByID(long id) {
		Collection collection = null;
		try 
		{
			cmdString = "Select * from Collections where CollectionID=" + id;
			rs5 = st3.executeQuery(cmdString);
			if (rs5.next())
			{
				long collectionID = rs5.getLong("CollectionID");
				String collectionName = rs5.getString("Name");
				String collectionDescription = rs5.getString("Description");
				collection = new Collection(collectionID, collectionName, collectionDescription);
			}
			rs5.close();
		}
		catch (Exception e)
		{
			processSQLError(e);
			// It would be good to raise the error somehow, 
			// rather than just returning a null collection.
			collection = null;
		}
		return collection;
	}

	public String insertCollection(Collection currentCollection)
	{
		String values;

		result = null;
		try
		{
			values = currentCollection.getCollectionID()
			         +", '" +currentCollection.getCollectionName()
			         +"', '" +currentCollection.getCollectionDescription()
			         +"'";
			cmdString = "Insert into Collections " +" Values(" +values +")";
			updateCount = st1.executeUpdate(cmdString);
			result = checkWarning(st1, updateCount);
		}
		catch (Exception e)
		{
			result = processSQLError(e);
		}
		return result;
	}

	public String updateCollection(Collection currentCollection)
	{
		String values;
		String where;

		result = null;
		try
		{
			// Should check for empty values and not update them
			values = "Name='" +currentCollection.getCollectionName()
			         +"', Description='" +currentCollection.getCollectionDescription()
			         +"'";
			where = "where CollectionID=" +currentCollection.getCollectionID();
			cmdString = "Update Collections " +" Set " +values +" " +where;
			updateCount = st1.executeUpdate(cmdString);
			result = checkWarning(st1, updateCount);
		}
		catch (Exception e)
		{
			result = processSQLError(e);
		}
		return result;
	}

	public String deleteCollection(Collection currentCollection)
	{
		long values;

		result = null;
		try
		{
			values = currentCollection.getCollectionID();
			

//			cmdString = "Delete from ITEMCOLLECTIONS where CollectionID=" +values;
//			
//			updateCount = st1.executeUpdate(cmdString);
//			
			cmdString = "Delete from Collections where CollectionID=" +values;
			
			updateCount = st1.executeUpdate(cmdString);
			result = checkWarning(st1, updateCount);
		}
		catch (Exception e)
		{
			result = processSQLError(e);
		}
		return result;
	}

	public ArrayList<ItemCollection> getItemElements(ItemCollection newItemCollection)
	{
		Long myItemID, myCollectionID;
		//We only have a constructor for ItemCollection to handle ID's
		ItemCollection myItemCollection;

		myItemID = (long)-1;
		myCollectionID = (long)-1;
		ic = new ArrayList<ItemCollection>();
		
		try
		{
			cmdString = "Select * from Collections,ItemsCollections where Collections.CollectionID=ItemsCollections.CollectionID and ItemID=" + newItemCollection.getItemID();
			rs4 = st2.executeQuery(cmdString);
			while (rs4.next())
			{
				myItemID = rs4.getLong("ItemID");
				myCollectionID = rs4.getLong("CollectionID");
				myItemCollection = new ItemCollection(myItemID, myCollectionID);
				ic.add(myItemCollection);
			}
			rs4.close();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		return ic;
	}

	public ArrayList<ItemCollection> getCollectionElements(ItemCollection newItemCollection)
	{
		Long myItemID, myCollectionID;
		//We only have a constructor for ItemCollection to handle ID's
		//String myItemName, myItemDescription;
		ItemCollection myCollectionItem;

		myItemID = (long)-1;
		myCollectionID = (long)-1;
		ic = new ArrayList<ItemCollection>();
		
		try
		{
			cmdString = "Select * from Items,ItemsCollections where Items.ItemID=ItemsCollections.ItemID and CollectionID=" +newItemCollection.getCollectionID();
			rs4 = st2.executeQuery(cmdString);
			while (rs4.next())
			{
				myItemID = rs4.getLong("ItemID");
				myCollectionID = rs4.getLong("CollectionID");
				myCollectionItem = new ItemCollection(myItemID, myCollectionID);
				ic.add(myCollectionItem);
			}
			rs4.close();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		return ic;
	}

	public String checkWarning(Statement st, int updateCount)
	{
		String result;

		result = null;
		try
		{
			SQLWarning warning = st.getWarnings();
			if (warning != null)
			{
				result = warning.getMessage();
			}
		}
		catch (Exception e)
		{
			result = processSQLError(e);
		}
		if (updateCount != 1)
		{
			result = "Tuple not inserted correctly.";
		}
		return result;
	}

	public String processSQLError(Exception e)
	{
		String result = "*** SQL Error: " + e.getMessage();

		// Remember, this will NOT be seen by the user!
		e.printStackTrace();
		
		return result;
	}

	public long getNextID(String listType)
	{
		long nextID = -1;
		try 
		{
			if(listType.equals("collection"))
			{
				cmdString = "Select max(CollectionID) from Collections";
				rs6 = st4.executeQuery(cmdString);
				if (rs6.next()) 
				{
					nextID = rs6.getLong(1) + 1;
				}
			}
			else if (listType.equals("item"))
			{
				cmdString = "Select max(ItemID) from Items";
				rs7 = st5.executeQuery(cmdString);
				if (rs7.next()) 
				{
					nextID = rs7.getLong(1) + 1;
				}
			}
			return nextID;
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		return nextID;
	}
	
	public ArrayList<Item> getCollectionItems(long collectionID)
	{
		ArrayList<Item> items = new ArrayList<Item>();
		Item item;
		Long itemID;
		int itemYear;
		String itemName, itemDescription;
		itemID = (long)-1;
		
		try
		{
			cmdString = "Select * from Items,ItemsCollections where Items.ItemID=ItemsCollections.ItemID and CollectionID=" + collectionID;
			rs8 = st6.executeQuery(cmdString);
			
			while (rs8.next())
			{
				itemID = rs8.getLong("ItemID");
				itemName = rs8.getString("Name");
				itemYear = rs8.getInt("Year");
				itemDescription = rs8.getString("Description");
				
				item = new Item(itemID, itemName, itemDescription, itemYear);
				items.add(item);
			}
			rs8.close();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		
		return items;
	}
}
