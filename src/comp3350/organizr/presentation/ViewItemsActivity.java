package comp3350.organizr.presentation;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import comp3350.organizr.R;
import comp3350.organizr.business.AccessCollections;
import comp3350.organizr.business.AccessItems;
import comp3350.organizr.objects.Collection;
import comp3350.organizr.objects.Item;

public class ViewItemsActivity extends Activity
{
	List<Object> objects;
	ItemAdapter adapter;
	long collectionId;
	TextView searchItemValue;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.view_items_layout);

        Intent intent = getIntent();
        
        ListView itemList = (ListView) findViewById(R.id.item_list);
        objects = new ArrayList<>();
        adapter = new ItemAdapter(this, objects);
        itemList.setAdapter(adapter);
        collectionId = intent.getLongExtra("id", 0);
        searchItemValue = (TextView) findViewById(R.id.editItemSearchValue);
        
        itemList.setOnItemClickListener(new ListView.OnItemClickListener() 
        {
            @Override
            public void onItemClick(AdapterView<?> listView, View clickedView, int position, long itemId) 
            {
                Object clickedObject = listView.getAdapter().getItem(position);

                if (clickedObject instanceof Item) 
                {
                	Intent intent = new Intent(ViewItemsActivity.this, EditItemActivity.class);
                	intent.putExtra("id", ((Item) clickedObject).getItemID());
                	startActivity(intent);
                }
                else
                {
                	Intent intent = new Intent(ViewItemsActivity.this, EditCollectionActivity.class);
                	intent.putExtra("id", ((Collection) clickedObject).getCollectionID());
                	startActivity(intent);
                }
            }
        });
		final Button button = (Button) findViewById(R.id.itemSearch);
        button.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {
            	searchItemButton(v);
            }
           
        });
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item_sort, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) 
    {
		AccessItems accessItems = new AccessItems();
		Collection collection;
		List<Item> items;

		switch (item.getItemId()) 
		{
            case R.id.sort_asc:
            	collection = (Collection)objects.get(0);
            	
            	items = new ArrayList<>();
            	for(int i = 1; i<objects.size(); i++)
            	{
            		items.add((Item)objects.get(i));
            	}
            	objects.clear();
            	objects.add(collection);
            	accessItems.sortItems(items, "asc","name");     
            	objects.addAll(items);
            	adapter.notifyDataSetChanged();
                return true;

            case R.id.sort_desc:
            	collection = (Collection)objects.get(0);
            	
            	items = new ArrayList<>();
            	for(int i = 1; i<objects.size(); i++)
            	{
            		items.add((Item)objects.get(i));
            	}
            	objects.clear();
            	objects.add(collection);
            	accessItems.sortItems(items, "desc","name");     
            	objects.addAll(items);
            	adapter.notifyDataSetChanged();
                return true;

            case R.id.sort_ascY:
            	collection = (Collection)objects.get(0);
            	
            	items = new ArrayList<>();
            	for(int i = 1; i<objects.size(); i++)
            	{
            		items.add((Item)objects.get(i));
            	}
            	objects.clear();
            	objects.add(collection);
            	accessItems.sortItems(items, "asc","year");     
            	objects.addAll(items);
            	adapter.notifyDataSetChanged();
                return true;

            case R.id.sort_descY:
            	collection = (Collection)objects.get(0);
            	
            	items = new ArrayList<>();
            	for(int i = 1; i<objects.size(); i++)
            	{
            		items.add((Item)objects.get(i));
            	}
            	objects.clear();
            	objects.add(collection);
            	accessItems.sortItems(items, "desc","year");     
            	objects.addAll(items);
            	adapter.notifyDataSetChanged();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    @Override
    protected void onResume() 
    {
    	super.onResume();

        List<Item> items = new ArrayList<>();
        AccessItems accessItems = new AccessItems();
        items.addAll(accessItems.getItemsinCollection(collectionId));

        List<Collection> collections = new ArrayList<>();
        AccessCollections accessCollections = new AccessCollections();
        collections.add(accessCollections.getCollectionByID(collectionId));
        
        // If the collection was deleted by EditCollectionActivity, we shouldn't
        // still be editing it. However, since multiple collections is still not
        // quite implemented, this !INCORRECT! but currently functional statement
        // act as a placeholder until it is.
        if (collections.size() == 0)
        {
        	finish();
        }

        objects.clear();
        objects.addAll(collections);
        objects.addAll(items);
        adapter.notifyDataSetChanged();
    }
    
    public void searchItemButton(View v)
	{
		AccessItems accessItems= new AccessItems();
        // Perform action on click
		Collection collection = (Collection)objects.get(0);
    	
    	List<Item> items = new ArrayList<>();
    	for(int i = 1; i<objects.size(); i++)
    	{
    		items.add((Item)objects.get(i));
    	}
    	objects.clear();
    	objects.add(collection);
    	accessItems.searchItem(collectionId,items,searchItemValue.getText().toString());     
    	objects.addAll(items);
    	adapter.notifyDataSetChanged();
	}
    
    public void buttonCreateItem(View v) 
    {
        Intent intent = new Intent(ViewItemsActivity.this, EditItemActivity.class);
        intent.putExtra("collectionID", collectionId);
        startActivity(intent);
    }

    private class ItemAdapter extends ArrayAdapter<Object> 
    {
        public ItemAdapter(Context context, List<Object> items) 
        {
            super(context, 0, items);
        }
        
        public View getView (int position, View convertView, ViewGroup parent) 
        {
            Object object = getItem(position);
            View view;
            if (object instanceof Item) 
            {
                Item item = (Item) object;
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_view, null);
                ((TextView) view.findViewById(R.id.name))
                        .setText(item.getItemName());
                ((TextView) view.findViewById(R.id.description))
                        .setText(item.getItemDescription());
                ((TextView) view.findViewById(R.id.year))
        				.setText("" + item.getItemYear());
            }
            else  
            {
                Collection collection = (Collection) object;
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.collection_view, null);
                ((TextView) view.findViewById(R.id.name))
                        .setText(collection.getCollectionName());
                ((TextView) view.findViewById(R.id.description))
                        .setText(collection.getCollectionDescription());
            }
            return view;
        }
    }
}
