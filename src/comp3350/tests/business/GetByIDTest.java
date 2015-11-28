package comp3350.tests.business;

import comp3350.organizr.application.Main;
import comp3350.organizr.application.Services;
import comp3350.organizr.business.AccessCollections;
import comp3350.organizr.business.AccessItems;
import comp3350.organizr.objects.Collection;
import comp3350.organizr.objects.Item;
import comp3350.organizr.persistence.DataAccess;
import comp3350.tests.persistence.DataAccessStub;
import junit.framework.TestCase;

public class GetByIDTest extends TestCase 
{
	AccessCollections ac;
	AccessItems ai;
	
	private static String dbName = Main.dbName;

	public GetByIDTest(String arg0)
	{
		super(arg0);
	}
	
	public void setUp()
	{
		Services.closeDataAccess();
		Services.createDataAccess((DataAccess) new DataAccessStub(dbName));
		ac = new AccessCollections();
		ai = new AccessItems();
	}
	
	public void tearDown()
	{
		Services.closeDataAccess();
	}
	
	public void testInvalidID()
	{
		assertNull(ac.getCollectionByID(-1));
		assertNull(ai.getItemByID(-1));
		
		assertNull(ac.getCollectionByID(50));
		assertNull(ai.getItemByID(50));
	}
	
	public void testValidID()
	{
		Item itemWithID_1 = ai.getItemByID(1);
		Collection CollectionWithID_12345 = ac.getCollectionByID(12345);

		assertNotNull(itemWithID_1);
		assertNotNull(CollectionWithID_12345);

		assertEquals(1, itemWithID_1.getItemID());
		assertEquals(12345, CollectionWithID_12345.getCollectionID());

		assertEquals("Jaga", itemWithID_1.getItemName());
		assertEquals("80s Action figures", CollectionWithID_12345.getCollectionName());
	}
}
