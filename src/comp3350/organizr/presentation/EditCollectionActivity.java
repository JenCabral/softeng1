package comp3350.organizr.presentation;

import java.util.ArrayList;
import java.util.List;

import comp3350.organizr.business.AccessCollections;
import comp3350.organizr.business.AccessItems;
import comp3350.organizr.objects.Collection;
import comp3350.organizr.objects.Item;
import android.view.View;
import android.widget.TextView;
import comp3350.organizr.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class EditCollectionActivity extends Activity 
{
    Collection currCollection;
    TextView collectionName;
    TextView collectionDescription;
    
    boolean updateExisting;

    AccessCollections accessCollections;

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_collection_activity);
        updateExisting = true;

        accessCollections = new AccessCollections();

        long collectionID = getIntent().getLongExtra("id", -1);
        if (collectionID == -1) 
        {
            updateExisting = false;
            currCollection = new Collection(0);
        }
        else
        {
            currCollection = accessCollections.getCollectionByID(collectionID);
        }
        
        if(currCollection != null){       	        
	        collectionName = (TextView) findViewById(R.id.editCollectionName);
	        collectionName.setText(currCollection.getCollectionName());
	        
	        collectionDescription = (TextView) findViewById(R.id.editCollectionDescription);
	        collectionDescription.setText(currCollection.getCollectionDescription());
        }
    }

    public void saveCollection(View v)
    {
        currCollection.setCollectionName(collectionName.getText().toString());
        currCollection.setCollectionDescription(collectionDescription.getText().toString());
        if(!collectionName.getText().toString().isEmpty())
        {
	        if (updateExisting) 
	        {
	            accessCollections.updateCollection(currCollection);
	        }
	        else
	        {
	        	currCollection.setCollectionID(accessCollections.getNextID());
	            accessCollections.insertCollection(currCollection);
	        }
        }
        finish();
            
    }
    
    public void deleteCollection(View v)
    {
        if (updateExisting) 
        {
        	// Must delete all of the dangling items for when we delete the collection
            AccessItems accessItems = new AccessItems();
            List<Item> items = new ArrayList<>();
            items.addAll(accessItems.getItemsinCollection(currCollection.getCollectionID()));
            for (int i = 0; i < items.size(); i++)
            {
            	accessItems.deleteItem(items.get(i));
            }

            accessCollections.deleteCollection(currCollection);
  
            Intent intent = new Intent(EditCollectionActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        finish();
    }
    
    public void cancelCollectionEdit(View v)
    {
        finish();
    }
}


