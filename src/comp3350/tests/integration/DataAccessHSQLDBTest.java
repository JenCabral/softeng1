package comp3350.tests.integration;

import junit.framework.TestCase;
import comp3350.organizr.application.Services;
import comp3350.organizr.application.Main;
import comp3350.tests.persistence.DataAccessTest;

public class DataAccessHSQLDBTest extends TestCase
{
	private static String dbName = Main.dbName;
	
	public DataAccessHSQLDBTest(String arg0)
	{
		super(arg0);
	}
	
	public void testGetItemSequentialTypical()
	{
		System.out.println("\nStarting Integration test DataAccessTest#testGetItemSequentialTypical (using default DB)");
		
		Services.closeDataAccess();
		Services.createDataAccess(dbName);
		
		DataAccessTest.getItemSequentialTypicalTest();
		
		System.out.println("\nFinished Integration test DataAccessTest#testGetItemSequentialTypical (using default DB)");
	}

	public static void testGetCollectionSequentialTypical() 
	{
		System.out.println("\nStarting Integration test DataAccessTest#testGetCollectionSequentialTypical (using default DB)");
		
		Services.closeDataAccess();
		Services.createDataAccess(dbName);
		
		DataAccessTest.getCollectionSequentialTypicalTest();
		
		System.out.println("\nFinished Integration test DataAccessTest#testGetCollectionSequentialTypical (using default DB)");
	}

	public static void testGetItemElementsTypical() 
	{

		System.out.println("\nStarting Integration test DataAccessTest#testGetItemElementsTypical (using default DB)");

		Services.closeDataAccess();
		Services.createDataAccess(dbName);
		
		DataAccessTest.getItemElementsTypicalTest();
		
		System.out.println("\nFinished Integration test DataAccessTest#testGetItemElementsTypical (using default DB)");
	}
	
	public static void testupdateItemsTypical() 
	{
		System.out.println("\nStarting Integration test DataAccessTest#testUpdateItemsTypical (using default DB)");

		Services.closeDataAccess();
		Services.createDataAccess(dbName);
		
		DataAccessTest.updateItemsTypicalTest();
		
		System.out.println("\nFinished Integration test DataAccessTest#testItemsTypical (using default DB)");
	}
	
	public static void testDeleteItemsTypical() 
	{
		System.out.println("\nStarting Integration test DataAccessTest#testDeleteItemsTypical (using default DB)");

		Services.closeDataAccess();
		Services.createDataAccess(dbName);
		
		DataAccessTest.deleteItemsTypicalTest();
		
		System.out.println("\nFinished Integration test DataAccessTest#testDeleteItemsTypical (using default DB)");
	}
	
	public static void testupdateCollectionsTypical() 
	{
		System.out.println("\nStarting Integration test DataAccessTest#testUpdateCollectionsTypical (using default DB)");

		Services.closeDataAccess();
		Services.createDataAccess(dbName);
		
		DataAccessTest.updateCollectionsTypicalTest();
		
		System.out.println("\nFinished Integration test DataAccessTest#testCollectionsTypical (using default DB)");
	}
	
	public static void testDeleteCollectionsTypical() 
	{
		System.out.println("\nStarting Integration test DataAccessTest#testDeleteCollectionsTypical (using default DB)");

		Services.closeDataAccess();
		Services.createDataAccess(dbName);
		
		DataAccessTest.deleteCollectionsTypicalTest();
		
		System.out.println("\nFinished Integration test DataAccessTest#testDeleteCollectionsTypical (using default DB)");
	}
	
}