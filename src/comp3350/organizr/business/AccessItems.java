package comp3350.organizr.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import comp3350.organizr.application.Main;
import comp3350.organizr.application.Services;
import comp3350.organizr.objects.Item;
import comp3350.organizr.persistence.DataAccess;

public class AccessItems
{
    private DataAccess dataAccess;
    private List<Item> items;
    private Item item;
    private int currentItem;

    public AccessItems()
    {
        dataAccess = Services.getDataAccess(Main.dbName);
        items = null;
        item = null;
        currentItem = 0;
    }

    public String getItems(List<Item> items)
    {
        items.clear();
        return dataAccess.getItemSequential(items);
    }

    public Item getItemByID(long itemID) 
    {
        return dataAccess.getItemByID(itemID);
    }

    public ArrayList<Item> getItemsinCollection(long collectionId)
    {
        return dataAccess.getCollectionItems(collectionId);
    }

    public void searchItem(long collectionID, List<Item> items, String query)
    {    
        List<Item> temp = new ArrayList<Item>();
        items.clear();
        temp = dataAccess.getCollectionItems(collectionID);

        if(query.equals(""))
        {
            items.addAll(temp);
        }
        else
        {
            for(int i = 0; i < temp.size(); i++)
            {
                if(temp.get(i).getItemName().toLowerCase(Locale.CANADA).contains(query.toLowerCase()))
                {
                    items.add(temp.get(i));
                }
            }   
        }
    }

    public void sortItems(List<Item> items, String direction, String type)
    {
        int j = 0;
        Item temp;

        if(items.size() > 1)
        {
        	if(type.equals("name"))
        	{
	            if(direction.equals("asc"))
	            {
		        	for(int i = 1; i < items.size(); i++)
		            {
		                j = i;
		                while((j > 0) 
		                		&& (items.get(j).getItemName().toLowerCase(Locale.CANADA)
		                				.compareTo(items.get(j-1).getItemName().toLowerCase(Locale.CANADA)) < 0))
		                {
		                    temp = items.get(j);
		                    items.set(j, items.get(j-1));
		                    items.set(j-1, temp);
		
		                    j--;
		                }
		            }
	            }
	            else
	            {
	            	for(int i = 1; i < items.size(); i++)
		            {
		                j = i;
		                while((j > 0) 
		                		&& (items.get(j).getItemName().toLowerCase(Locale.CANADA)
		                				.compareTo(items.get(j-1).getItemName().toLowerCase(Locale.CANADA)) > 0))
		                {
		                    temp = items.get(j);
		                    items.set(j, items.get(j-1));
		                    items.set(j-1, temp);
		
		                    j--;
		                }
		            }
	            }
        	}
        	else
        	{
        		//sort on year
        		if(direction.equals("asc"))
        		{
		        	for(int i = 1; i < items.size(); i++)
		            {
		                j = i;
		                while((j > 0) && (items.get(j).getItemYear()<(items.get(j-1).getItemYear())))
		                {
		                    temp = items.get(j);
		                    items.set(j, items.get(j-1));
		                    items.set(j-1, temp);
		
		                    j--;
		                }
		            }
	            }
        		else
        		{
	            	for(int i = 1; i < items.size(); i++)
		            {
		                j = i;
		                while((j > 0) && (items.get(j).getItemYear()>(items.get(j-1).getItemYear())))
		                {
		                    temp = items.get(j);
		                    items.set(j, items.get(j-1));
		                    items.set(j-1, temp);
		
		                    j--;
		                }
		            }
	            }
        	}
        }
    }

    public Item getSequential()
    {
        if (items == null)
        {
            items = new ArrayList<Item>();
            currentItem = 0;
        }
        if (currentItem < items.size())
        {
            item = (Item) items.get(currentItem);
            currentItem++;
        }
        else
        {
            items = null;
            item = null;
            currentItem = 0;
        }
        return item;
    }

    public String insertItem(Item currentItem, long collectionID)
    {
        return dataAccess.insertItem(currentItem, collectionID);
    }

    public String updateItem(Item currentItem)
    {
        return dataAccess.updateItem(currentItem);
    }

    public String deleteItem(Item currentItem)
    {
        return dataAccess.deleteItem(currentItem);
    }

    public long getNextID()
    {
        return dataAccess.getNextID("item");
    }
}
