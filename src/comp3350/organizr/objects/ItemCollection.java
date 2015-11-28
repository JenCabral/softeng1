package comp3350.organizr.objects;

public class ItemCollection
{
	private long itemID;
	private long collectionID;

	public ItemCollection(long newItemID, long newCollectionID)
	{
		itemID = newItemID;
		collectionID = newCollectionID;
	}

	public long getItemID()
	{
		return (itemID);
	}

	public long getCollectionID()
	{
		return (collectionID);
	}

	public String toString()
	{
		return "ItemCollection: " +itemID +" " +collectionID;
	}
	
	public boolean equals(Object object)
	{
		boolean result;
		ItemCollection itemCollection;
		
		result = false;
		
		if (object instanceof ItemCollection)
		{
			itemCollection = (ItemCollection) object;
			if ((itemCollection.itemID == itemID) &&
					(itemCollection.collectionID == collectionID))
			{
				result = true;
			}
		}
		return result;
	}
}
