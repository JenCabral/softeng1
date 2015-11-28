package comp3350.tests.persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350.organizr.application.Main;
import comp3350.organizr.objects.Collection;
import comp3350.organizr.objects.Item;
import comp3350.organizr.objects.ItemCollection;
import comp3350.organizr.persistence.DataAccess;

public class DataAccessStub implements DataAccess
{
	private String dbName;
	private String dbType = "stub";

	private ArrayList<Item> items;
	private ArrayList<Collection> collections;
	private ArrayList<ItemCollection> itemcollections;

	public DataAccessStub(String dbName)
	{
		this.dbName = dbName;
		items = new ArrayList<Item>();
		collections = new ArrayList<Collection>();
		itemcollections = new ArrayList<ItemCollection>();
	}

	public DataAccessStub()
	{
		this(Main.dbName);
	}

	public void open(String dbName)
	{
		Item item;
		Collection collection;
		ItemCollection myIC;

		items = new ArrayList<Item>();
		//item id, item name, item description
		item = new Item(1, "Jaga", "Thundercats", 1986);
		items.add(item);
		item = new Item(2, "Lion-o", "Thundercats", 1986);
		items.add(item);
		item = new Item(3, "Tygra", "Thundercats", 1986);
		items.add(item);
		item = new Item(4, "Cheetara", "Thundercats", 1986);
		items.add(item);
		item = new Item(5, "Panthro", "Thundercats", 1986);
		items.add(item);
		item = new Item(6, "Leonardo", "TMNT", 1987);
		items.add(item);
		item = new Item(7, "Donatello", "TMNT", 1987);
		items.add(item);
		item = new Item(8, "Michaelangelo", "TMNT", 1987);
		items.add(item);
		item = new Item(9, "Raphael", "TMNT", 1987);
		items.add(item);
		item = new Item(10, "Splinter", "TMNT", 1987);
		items.add(item);
		item = new Item(11, "Shredder", "TMNT", 1987);
		items.add(item);
		item = new Item(12, "Krang", "TMNT", 1987);
		items.add(item);
		item = new Item(13, "He-Man", "Masters of the Universe", 1984);
		items.add(item);
		item = new Item(14, "Man-At-Arms", "Masters of the Universe", 1984);
		items.add(item);
		item = new Item(15, "Battle Cat", "Masters of the Universe", 1984);
		items.add(item);
		item = new Item(16, "Skeletor", "Masters of the Universe", 1984);
		items.add(item);
		item = new Item(17, "Beast man", "Masters of the Universe", 1984);
		items.add(item);

		collections = new ArrayList<Collection>();
		//collection id, collection name, collection description
		collection = new Collection(12345, "80s Action figures","collection of action figures from the 1980s");
		collections.add(collection);
		
		//item id, collection id
		myIC = new ItemCollection(1, 12345);
		itemcollections.add(myIC);
		myIC = new ItemCollection(2, 12345);
		itemcollections.add(myIC);
		myIC = new ItemCollection(3, 12345);
		itemcollections.add(myIC);
		myIC = new ItemCollection(4, 12345);
		itemcollections.add(myIC);
		myIC = new ItemCollection(5, 12345);
		itemcollections.add(myIC);
		myIC = new ItemCollection(6, 12345);
		itemcollections.add(myIC);
		myIC = new ItemCollection(7, 12345);
		itemcollections.add(myIC);
		myIC = new ItemCollection(8, 12345);
		itemcollections.add(myIC);
		myIC = new ItemCollection(9, 12345);
		itemcollections.add(myIC);
		myIC = new ItemCollection(10, 12345);
		itemcollections.add(myIC);
		myIC = new ItemCollection(11, 12345);
		itemcollections.add(myIC);
		myIC = new ItemCollection(12, 12345);
		itemcollections.add(myIC);
		myIC = new ItemCollection(13, 12345);
		itemcollections.add(myIC);
		myIC = new ItemCollection(14, 12345);
		itemcollections.add(myIC);
		myIC = new ItemCollection(15, 12345);
		itemcollections.add(myIC);
		myIC = new ItemCollection(16, 12345);
		itemcollections.add(myIC);
		myIC = new ItemCollection(17, 12345);
		itemcollections.add(myIC);
		
		item = new Item(18, "Typhoid Rats", "Creature - Rat", 2012);
		items.add(item);
		item = new Item(19, "Ink-eyes Servant of Oni", "Creature - Rat", 2009);
		items.add(item);
		item = new Item(20, "Ogre Slumlord", "Creature - Ogre", 2005);
		items.add(item);
		item = new Item(21, "Trepanation Blade", "Artifact - Equipment", 2013);
		items.add(item);
		item = new Item(22, "Scrib Nibblers", "Creature - Rat", 2007);
		items.add(item);
		item = new Item(23, "Doom Blade", "Spell - Instant", 2009);
		items.add(item);
		
		collection = new Collection(1111, "Rat Deck","Magic the Gathering");
		collections.add(0,collection);
		
		myIC = new ItemCollection(18, 1111);
		itemcollections.add(myIC);
		myIC = new ItemCollection(19, 1111);
		itemcollections.add(myIC);
		myIC = new ItemCollection(20, 1111);
		itemcollections.add(myIC);
		myIC = new ItemCollection(21, 1111);
		itemcollections.add(myIC);
		myIC = new ItemCollection(22, 1111);
		itemcollections.add(myIC);
		myIC = new ItemCollection(23, 1111);
		itemcollections.add(myIC);

		System.out.println("Opened " +dbType +" database " +dbName);
	}

	public void close()
	{
		System.out.println("Closed " +dbType +" database " +dbName);
	}

	public String getItemSequential(List<Item> itemResult)
	{
        itemResult.addAll(items);
		return null;
	}

	@Override
	public Item getItemByID(long id) 
	{
		Item result = null;
		for (int i = 0; i < items.size(); i++)
		{
			if (items.get(i).getItemID() == id) 
			{
				result = items.get(i);
				break;
			}
		}
		return result;
	}

	public String insertItem(Item currentItem, long collectionID)
	{
		String result = null;
		ItemCollection newIC;
		boolean duplicate = false;

		for (int i = 0; i < items.size(); i++)
		{
			if (items.get(i).equals(currentItem))
			{
				duplicate = true;
				break;
			}
		}
		if (duplicate == false)
		{
			items.add(currentItem);
			if (getCollectionByID(collectionID) != null)
			{
				
				newIC = new ItemCollection(currentItem.getItemID(),collectionID);
				itemcollections.add(newIC);
			} 
			else
			{
				result = "No collection with this ID exists, failed to create ItemCollection";
			}
		} 
		else 
		{
			result = "There already is an Item with this ID";
		}
		return result;
	}

	public String updateItem(Item currentItem)
	{
		int index;
		String result = null;
		
		index = items.indexOf(currentItem);
		if (index >= 0)
		{
			items.set(index, currentItem);
		}
		else
		{
			result = "Item is not in Items";
		}
		return result;
	}

	public String deleteItem(Item currentItem)
	{
		boolean deletedFromItems = false;
		boolean deletedFromItemsCollections = false;
		String result = "An error occured deleting the item";

		if(currentItem != null)
		{
			for(int i = 0; i < items.size(); i++)
			{
				if(currentItem.equals(items.get(i)))
				{
					items.remove(i);
					deletedFromItems = true;
				}
			}

			for(int i = 0; i < itemcollections.size(); i++)
			{
				if(currentItem.getItemID() == itemcollections.get(i).getItemID())
				{
					itemcollections.remove(i);
					deletedFromItemsCollections = true;
				}
			}
			if(deletedFromItems && deletedFromItemsCollections)
			{
				result = null;
			}
		}
		return result;
	}

	public String getCollectionSequential(List<Collection> collectionResult)
	{
        for(Collection collection : collections)
        {
        	collectionResult.add(collection);
        }
		return null;
	}

	@Override
	public Collection getCollectionByID(long id) 
	{
		Collection result = null;
		for (Collection collection : collections)
		{
			if (collection.getCollectionID() == id) 
			{
				result = collection;
				break;
			}
		}
		return result;
	}

	public String insertCollection(Collection currentCollection)
	{
		String result = null;
		boolean duplicate = false;
		
		for (int i = 0; i < collections.size(); i++)
		{
			if (collections.get(i).equals(currentCollection))
			{
				duplicate = true;
				break;
			}
		}
		if (duplicate == false)
		{
			collections.add(currentCollection);
		}
		else 
		{
			result = "There already is a Collection with this ID";
		}
		return result;
	}

	public String updateCollection(Collection currentCollection)
	{
		int index;
		String result = null;
		
		index = collections.indexOf(currentCollection);
		if (index >= 0)
		{
			collections.set(index, currentCollection);
		}
		else
		{
			result = "Collection is not in Collections";
		}
		return result;
	}
	
	public String deleteCollection(Collection currentCollection)
	{
		String result = "An error occured deleting the collection";
		
		for (int index = 0; index < collections.size(); index++)
		{
			if(collections.get(index).equals(currentCollection))
			{
				collections.remove(index);
				result = null;
			}
		}
		return result;
	}

	public ArrayList<ItemCollection> getItemElements(ItemCollection currentIC)
	{
		ArrayList<ItemCollection> newItemCollections;
		ItemCollection itemCollection;
		int counter;
		
		// get the ItemCollection objects with the same itemID as currentItemCollection
		newItemCollections = new ArrayList<ItemCollection>();
		for (counter=0; counter<itemcollections.size(); counter++)
		{
			itemCollection = itemcollections.get(counter);
			if (itemCollection.getItemID() == currentIC.getItemID())
			{
				newItemCollections.add(itemcollections.get(counter));
			}
		}
		return newItemCollections;
	}

	public ArrayList<ItemCollection> getCollectionElements(ItemCollection currentIC)
	{
		ArrayList<ItemCollection> newItemCollections;
		ItemCollection ci;
		int counter;
		
		// get the ItemCollection objects with the same collectionID as currentItemCollection
		newItemCollections = new ArrayList<ItemCollection>();
		for (counter=0; counter<itemcollections.size(); counter++)
		{
			ci = itemcollections.get(counter);
		
			if (ci.getCollectionID() == currentIC.getCollectionID())
			{				
				newItemCollections.add(itemcollections.get(counter));
			}
		}
		return newItemCollections;
	}
	
	public long getNextID(String listType)
	{
		long nextID = 0;
		if(listType.equals("collection"))
		{
			if (collections.size() > 0)
			{
				for(int i = 0; i < collections.size(); i++)
				{
					if(collections.get(i).getCollectionID() > nextID)
					{
						nextID = collections.get(i).getCollectionID();
					}
				}
				nextID++;
			}
		}
		else if (listType.equals("item"))
		{
			if (items.size() > 0)
			{
				for(int i = 0; i < items.size(); i++)
				{
					if(items.get(i).getItemID() > nextID)
					{
						nextID = items.get(i).getItemID();
					}
				}
				nextID++;
			}
		}
		return nextID;
	}

	@Override
	public ArrayList<Item> getCollectionItems(long collectionID) 
	{
		ItemCollection ic = new ItemCollection(0, collectionID);
		ArrayList<ItemCollection> matches = this.getCollectionElements(ic);
		
		ArrayList<Item> items = new ArrayList<Item>();
		
		for (int i = 0; i < matches.size(); i++)
		{
			items.add(this.getItemByID(matches.get(i).getItemID()));
		}
		
		return items;
	}
}
