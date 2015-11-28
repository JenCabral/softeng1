package comp3350.organizr.business;

import java.util.List;

import comp3350.organizr.objects.ItemCollection;

public class Count
{
	public static int countItems(List<ItemCollection> elements)
	{
		int count = 0;
		long missing = -1;
		
		if (null != elements && 0 != elements.size()) 
		{
			for (int i = 0; i < elements.size(); i++) 
			{
				if (null == elements.get(i))
				{
					//don't count we have null list
				}
				else if (missing == elements.get(i).getItemID() || missing == elements.get(i).getCollectionID())
				{
					//don't count we have missing ids
				}
				else
				{
					count++;
				}
			}
		}
		
		return count;
	}
}
