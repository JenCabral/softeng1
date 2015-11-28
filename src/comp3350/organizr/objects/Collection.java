package comp3350.organizr.objects;

public class Collection
{
	private long collectionID;
	private String collectionName;
	private String collectionDescription;

	public Collection(long newID)
	{
		collectionID = newID;
		collectionName = null;
	}

	public Collection(long newCollectionID, String newCollectionName, String newCollectionDescription)
	{
		collectionID = newCollectionID;
		collectionDescription = newCollectionDescription;
		
		if(newCollectionName != null && newCollectionName != "")
		{
			collectionName = newCollectionName;
		}
		else
		{
			collectionName = "Unnamed";
		}
	}

	public long getCollectionID()
	{
		return (collectionID);
	}

	public void setCollectionID(long id)
	{
		collectionID = id;
	}

	public String getCollectionName()
	{
		return (collectionName);
	}
	
	public void setCollectionName(String name)
	{
		if(name != null && name != "")
		{
			collectionName = name;
		}
		else
		{
			collectionName = "Unnamed";
		}
	}
	
	public String getCollectionDescription()
	{
		return (collectionDescription);
	}
	
	public void setCollectionDescription(String description)
	{
		collectionDescription = description;
	}

	public String toString()
	{
		return "Collection: " +collectionID +" " +collectionName +" " +collectionDescription;
	}
	
	public boolean equals(Object object)
	{
		boolean result;
		Collection c;
		
		result = false;
		
		if (object instanceof Collection)
		{
			c = (Collection) object;
			if (c.collectionID == collectionID)
			{
				result = true;
			}
		}
		return result;
	}
}