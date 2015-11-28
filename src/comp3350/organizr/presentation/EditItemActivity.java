package comp3350.organizr.presentation;

import comp3350.organizr.R;
import comp3350.organizr.business.AccessItems;
import comp3350.organizr.objects.Item;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EditItemActivity extends Activity 
{
	Item currItem;
	TextView itemName;
	TextView itemYear;
	TextView itemDescription;
	long collectionId;
	
	boolean updateExistingItem;
	
	AccessItems accessItems;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_item_activity);
		updateExistingItem = true;
		
		Intent intent = getIntent();
		
		collectionId = intent.getLongExtra("collectionID", 0);

		accessItems = new AccessItems();

		long itemID = getIntent().getLongExtra("id", -1);
		if (itemID == -1) 
		{
			// No ID was passed, so we must be creating a new item
			updateExistingItem = false;
			currItem = new Item(0);
		}
		else
		{
			currItem = accessItems.getItemByID(itemID);
		}
      
        itemName = (TextView) findViewById(R.id.editItemName);
        itemName.setText(currItem.getItemName());
        
        
        itemYear = (TextView) findViewById(R.id.editItemYear);
        itemYear.setText("" + currItem.getItemYear());
        
        itemDescription = (TextView) findViewById(R.id.editItemDescription);
        itemDescription.setText(currItem.getItemDescription());
	}
	
	public void saveItem(View v)
	{
		int year;
		currItem.setItemName(itemName.getText().toString());
		currItem.setItemDescription(itemDescription.getText().toString());
		
		try
		{
			year = Integer.parseInt(itemYear.getText().toString());
		}
		catch(Exception ignore)
		{
			year = 0;
		}
		currItem.setItemYear(year);

		if(!itemName.getText().toString().isEmpty()){
			if (updateExistingItem) 
			{
				accessItems.updateItem(currItem);
			}
			else
			{
				// Not updating, so insert a new item
				currItem.setItemID(accessItems.getNextID());
				accessItems.insertItem(currItem, collectionId);
			}
		}
		finish();
	}
	
	public void deleteItem(View v)
	{
		if (updateExistingItem)
		{
			accessItems.deleteItem(currItem);
		}
		finish();
	}
	
	public void cancelItemEdit(View v)
	{
		finish();
	}
}


