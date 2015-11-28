package comp3350.organizr.presentation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import comp3350.organizr.R;
import comp3350.organizr.application.Main;
import comp3350.organizr.business.AccessCollections;
import comp3350.organizr.objects.Collection;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
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

public class HomeActivity extends Activity 
{
	List<Collection> collections;
	CollectionAdapter adapter;
	TextView searchValue;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		copyDatabaseToDevice();
		
		setContentView(R.layout.home_layout);

		Main.startUp();

		collections = new ArrayList<>();

		ListView collectionList = (ListView) findViewById(R.id.collection_list);
		adapter = new CollectionAdapter(this, collections);
		collectionList.setAdapter(adapter);
		
		searchValue = (TextView) findViewById(R.id.editSearchValue);

		collectionList.setOnItemClickListener(new ListView.OnItemClickListener() 
		{
			@Override
			public void onItemClick(AdapterView<?> listView,
					View clickedView, int position, long itemId) 
			{
				// Get the id of the collection that was clicked
				long collectionID = ((Collection) listView.getAdapter().getItem(position)).getCollectionID();

				Intent intent = new Intent(HomeActivity.this, ViewItemsActivity.class);
				intent.putExtra("id", collectionID);
				startActivity(intent);
			}
		});
		final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {
            	searchButton(v);
            }
           
        });
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
	    // Inflate the menu items for use in the action bar
		//SubMenu sortMenu = menu.addSubMenu("Sort");
		//sortMenu.add(0, Menu.FIRST, 0, "Ascending");
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_activity_actions, menu);
	    //MenuItem myMenuItem = menu.findItem(R.id.action_sort);
	   // getMenuInflater().inflate(R.menu.menu_sub_sort,myMenuItem.getSubMenu());
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) 
	{
		AccessCollections accessCollections = new AccessCollections();
		ListView collectionList;
        switch (item.getItemId()) 
        {
            case R.id.sort_asc:
            	collections.clear();     		
        		accessCollections.sortCollection(collections,"asc");      		
        		collectionList = (ListView) findViewById(R.id.collection_list);
        		adapter = new CollectionAdapter(this, collections);
        		collectionList.setAdapter(adapter);
                return true;

            case R.id.sort_desc:
            	collections.clear();     		
        		accessCollections.sortCollection(collections,"desc");      		
        		collectionList = (ListView) findViewById(R.id.collection_list);
        		adapter = new CollectionAdapter(this, collections);
        		collectionList.setAdapter(adapter);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
	
	@Override
	protected void onResume() 
	{
		super.onResume();
		collections.clear();
		
		AccessCollections accessCollections = new AccessCollections();
		accessCollections.getCollections(collections);
		
		adapter.notifyDataSetChanged();
	}

	@Override
	protected void onDestroy() 
	{
		super.onDestroy();
		Main.shutDown();
	}

	public void buttonCreateCollection(View v) 
	{
		// Starting either of the edit activities without putting extra "id" tells
		// them to create a new object.
		Intent intent = new Intent(HomeActivity.this, EditCollectionActivity.class);
		startActivity(intent);
	}
	
	public void searchButton(View v)
	{
		AccessCollections accessCollections = new AccessCollections();
        // Perform action on click
    	collections.clear();     		
		accessCollections.searchCollection(collections, searchValue.getText().toString());      		
		ListView collectionList = (ListView) findViewById(R.id.collection_list);
		adapter = new CollectionAdapter(this, collections);
		collectionList.setAdapter(adapter);
	}

    private void copyDatabaseToDevice() 
    {
    	final String DB_PATH = "db";

    	String[] assetNames;
    	Context context = getApplicationContext();
    	File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
    	AssetManager assetManager = getAssets();
    	
    	try 
    	{
    		assetNames = assetManager.list(DB_PATH);
    		for (int i = 0; i < assetNames.length; i++) 
    		{
    			assetNames[i] = DB_PATH + "/" + assetNames[i];
    		}

    		copyAssetsToDirectory(assetNames, dataDirectory);
    		
    		Main.setDBPathName(dataDirectory.toString() + "/" + Main.dbName);
    	} 
    	catch (IOException ioe) 
    	{
    		ioe.printStackTrace(); // Not seen by users
    	}
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException 
    {
    	AssetManager assetManager = getAssets();

    	for (String asset : assets) 
    	{
    		String[] components = asset.split("/");
    		String copyPath = directory.toString() + "/" + components[components.length - 1];
    		char[] buffer = new char[1024];
    		int count;
    		
    		File outFile = new File(copyPath);
    		
    		if (!outFile.exists()) 
    		{
    			InputStreamReader in = new InputStreamReader(assetManager.open(asset));
	    		FileWriter out = new FileWriter(outFile);
	    		
	    		count = in.read(buffer);
	    		while (count != -1) 
	    		{
	    			out.write(buffer, 0, count);
	        		count = in.read(buffer);
	    		}
	    		
	    		out.close();
	    		in.close();
    		}
    	}
	}

	private class CollectionAdapter extends ArrayAdapter<Collection> 
	{
		public CollectionAdapter(Context context, List<Collection> collections) 
		{
			super(context, 0, collections);
		}

		public View getView(int position, View convertView, ViewGroup parent) 
		{
			// convertView is a view that has already been inflated by this method
			// earlier in the ListView, and is being reused for performance's sake.
			if (convertView == null) 
			{
				// Inflating with null causes a warning, but trying to use the
				// provided parent causes a crash. Feel free to look into the reason,
				// but passing null here seems to be correct from what I've seen.
				convertView = LayoutInflater.from(parent.getContext())
						.inflate(R.layout.collection_view, null);
			}
			Collection collection = getItem(position);
			((TextView) convertView.findViewById(R.id.name)).setText(collection
					.getCollectionName());
			((TextView) convertView.findViewById(R.id.description))
					.setText(collection.getCollectionDescription());
			return convertView;
		}
	}
}
