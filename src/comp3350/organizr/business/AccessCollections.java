package comp3350.organizr.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import comp3350.organizr.application.Main;
import comp3350.organizr.application.Services;
import comp3350.organizr.objects.Collection;
import comp3350.organizr.persistence.DataAccess;

public class AccessCollections
{
	private DataAccess dataAccess;

	public AccessCollections()
	{
		dataAccess = Services.getDataAccess(Main.dbName);
	}

    public String getCollections(List<Collection> collections)
    {
        collections.clear();
        return dataAccess.getCollectionSequential(collections);
    }
    
    public Collection getCollectionByID(long collectionID) 
    {
    	return dataAccess.getCollectionByID(collectionID);
    }
    
    public String searchCollection(List<Collection> collections, String query)
    {
    	List<Collection> temp = new ArrayList<>();
    	String result = dataAccess.getCollectionSequential(temp);
    	collections.clear();
    	
    	try
    	{
	    	if(result == null)
	    	{
	    		if(query == null || query.equals(""))
	    		{
	    			collections.addAll(temp);
	    		}
	    		else
	    		{
			    	for(int i = 0; i < temp.size(); i++)
			    	{
			    		if(temp.get(i).getCollectionName().toLowerCase(Locale.CANADA)
			    				.contains(query.toLowerCase()))
			    		{
			    			collections.add(temp.get(i));
			    		}
			    	}   
	    		}
	    	}
    	}
    	catch(Exception e)
    	{
    		result = e.getMessage();
    	}
    	
    	return result;
    }
    
   public String sortCollection(List<Collection> collections, String direction)
   {
    	String result = dataAccess.getCollectionSequential(collections);
    	Collection temp;
    	int j = 0;
    	
    	if(result == null && collections.size() > 1)
    	{
    		if(direction.equals("asc"))
    		{
	    		for(int i = 1; i < collections.size(); i++)
	    		{
	    			j = i;
	    			while((j > 0) 
	    					&& (collections.get(j).getCollectionName().toLowerCase(Locale.CANADA)
	    							.compareTo(collections.get(j-1).getCollectionName().toLowerCase(Locale.CANADA)) < 0))
	    			{
	    				temp = collections.get(j);
	    				collections.set(j, collections.get(j-1));
	    				collections.set(j-1, temp);
	    				j--;
	    			}
	    		}
    		}
    		else
    		{
    			for(int i = 1; i < collections.size(); i++)
    			{    					
	    			j = i;
	    			while((j > 0) 
	    					&& (collections.get(j).getCollectionName().toLowerCase(Locale.CANADA)
	    							.compareTo(collections.get(j-1).getCollectionName().toLowerCase(Locale.CANADA)) > 0))
	    			{
	    				temp = collections.get(j);
	    				collections.set(j, collections.get(j-1));
	    				collections.set(j-1, temp);
	    				j--;
	    			}
	    		}
    		}
    	}
    	
    	return result;
    }
	
	public String insertCollection(Collection currentCollection)
	{
		return dataAccess.insertCollection(currentCollection);
	}

	public String updateCollection(Collection currentCollection)
	{
		return dataAccess.updateCollection(currentCollection);
	}

	public String deleteCollection(Collection currentCollection)
	{
		return dataAccess.deleteCollection(currentCollection);
	}
	public long getNextID()
	{
		return dataAccess.getNextID("collection");
	}
	
}
