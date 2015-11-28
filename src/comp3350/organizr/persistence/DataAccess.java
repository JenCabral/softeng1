package comp3350.organizr.persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350.organizr.objects.Item;
import comp3350.organizr.objects.Collection;
import comp3350.organizr.objects.ItemCollection;

public interface DataAccess
{
	public void open(String string);

	public void close();

	public String getItemSequential(List<Item> itemResult);
	
	public Item getItemByID(long id);

	public String insertItem(Item item, long collectionID);

	public String updateItem(Item item);

	public String deleteItem(Item item);

	public String getCollectionSequential(List<Collection> collectionResult);

	public String insertCollection(Collection collection);

	public Collection getCollectionByID(long id);

	public String updateCollection(Collection collection);

	public String deleteCollection(Collection collection);
	
	public ArrayList<ItemCollection> getCollectionElements(ItemCollection currentIC);
	
	public ArrayList<ItemCollection> getItemElements(ItemCollection currentIC);
	
	public ArrayList<Item> getCollectionItems(long collectionID);

	public long getNextID(String string);
}
