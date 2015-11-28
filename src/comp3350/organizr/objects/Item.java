package comp3350.organizr.objects;

public class Item
{
	private long itemID;
	private String itemName;
	private int itemYear;
	private String itemDescription;

	public Item(long newID)
	{
		itemID = newID;
		itemName = null;
		itemYear = 0;
		itemDescription = null;
	}

	public Item(long newID, String newItemName, String newItemDescription, int newItemYear)
	{
		itemID = newID;
		itemYear = newItemYear;
		itemDescription = newItemDescription;
		
		if(newItemName != null && newItemName != "")
		{
			itemName = newItemName;
		}
		else
		{
			itemName = "Unnamed";
		}
	}

	public long getItemID()
	{
		return (itemID);
	}

	public void setItemID(long id)
	{
		itemID = id;
	}

	public String getItemName()
	{
		return (itemName);
	}
	
	public void setItemName(String name)
	{
		if(name != null && name != "")
		{
			itemName = name;
		}
		else
		{
			itemName = "Unnamed";
		}
	}
	
	public void setItemYear(int year)
	{
		itemYear = year;
	}

	public String getItemDescription()
	{
		return itemDescription;
	}
	
	public int getItemYear()
	{
		return itemYear;
	}
	
	public void setItemDescription(String description)
	{
		itemDescription = description;
	}

	public String toString()
	{
		return "Item: " +itemID +" " +itemName +" " +itemDescription +" " +itemYear;
	}
	
	public boolean equals(Object object)
	{
		boolean result;
		Item s;
		
		result = false;
		
		if (object instanceof Item)
		{
			s = (Item) object;
			if (s.itemID == itemID)
			{
				result = true;
			}
		}
		return result;
	}
}