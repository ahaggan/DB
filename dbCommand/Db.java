import java.io.*;
import java.util.*;


public class Db{
    public Database database;
    
    
    public static void main(String[] args){
        Db dataBase = new Db();
        dataBase.run();
        
        testRecord();
        testTable();
        testDirectory();
        testDatabase();
        printResults();
    }
    
    public void run(){
       database = new Database("Current");
       read();
        
        
        
        
    }
    
    public static void printResults(){
        System.out.println("");
        System.out.println("--------------Test Results--------------");
        for(String output: testResults){
            System.out.println(output);
        }
    }
    
    
    public void read(){
        Console console = System.console();
        boolean quit = false;
        while(!quit){
            String line = console.readLine("Enter Command: ");
            if(line.equals("quit") || line.equals("QUIT")){
                quit = true;
            }else{
                Command input = new Command();
                database = input.actionCommand(line, database);
            }
        }
    }
    
    private static int numberOfTests;
    private static int testPassed;
    private static ArrayList<String> testResults = new ArrayList<String>();
    
    public static void testRecord(){
        numberOfTests = 0;
        testPassed = 0;
        //Test the Record constructor
        Record test1 = new Record("Ashley 26 Student");
        is("Ashley 26 Student",test1.getValues());      //Test 1
        //Test the number of fields function
        is(test1.getNoOfFields(), 3);       //Test 2
        //Tests the replace function
        Record checkRecord = new Record("Ashley 26 Pilot");
        test1.replaceField(2, "Pilot");
        is(test1.getValues(), checkRecord.getValues());     //Test 3
        //Test the add field function
        test1.addField("banana");
        is(test1.getValues(), "Ashley 26 Pilot banana");        ////Test 4
        is(test1.getNoOfFields(), 4);       //Test 5
        //Test the remove field function
        test1.removeField("Pilot");
        is(test1.getValues(), "Ashley 26 banana");      //Test 6
        is(test1.getNoOfFields(), 3);       //Test 7
        //Test the getKey function
        is(test1.getKey(), "Ashley");       //Test 8
        
        //Test results
        testResults.add(testPassed + " out of " + numberOfTests + " Record tests passed.");    
    }
    
    public static void testTable(){
        numberOfTests = 0;
        testPassed = 0;
        //Test constructor and return functions
        Table test = new Table("test", "column1 column2 column3");
        is(test.getTitle(), "test");        //Test 1
        is(test.getNumberOfRecords(), 0);       //Test 2
        is(test.getFields(), "column1 column2 column3");     //Test 3
        //Test the addRecord and getRecord functions
        test.addRecord("data1 data2 data3");
        Record checkRecord = new Record("data1 data2 data3");
        is(test.getRecord("data1"), checkRecord);       //Test 4
        //Tests the getRecords function
        ArrayList<Record> testArray = new ArrayList<Record>();
        Record checkRecord2 = new Record("x y z");
        testArray.add(checkRecord);
        testArray.add(checkRecord2);
        test.addRecord("x y z");
        is(testArray, test.getRecords());       //Test 5
        //Tests replaceRecord
        //First replace should fail because it tries to reaplce with an identical primary key
        test.replaceRecord("data1", "x 3 4");
        is(testArray, test.getRecords());       //Test 6
        test.replaceRecord("data1", "a b c");
        ArrayList<Record> testArray2 = new ArrayList<Record>();
        Record checkRecord3 = new Record("a b c");
        testArray2.add(checkRecord3);
        testArray2.add(checkRecord2);
        is(testArray2, test.getRecords());      //Test 7
        //Tests deleteRecord
        ArrayList<Record> testArray3 = new ArrayList<Record>();
        testArray3.add(checkRecord3);
        test.deleteRecord("x");
        is(testArray3, test.getRecords());      //Test 8
        
        //Test key uniqueness - I will add a recort with the same key
        //this shouldn't be added
        test.addRecord("a y z");
        is(testArray3, test.getRecords());      //Test 9
        //Test results
        testResults.add(testPassed + " out of " + numberOfTests + " Table tests passed."); 
    }
    
    public static void testDirectory(){
        numberOfTests = 0;
        testPassed = 0;
        Directory testDirectory = new Directory("/Test");
        testDirectory.createDirectory();
        //Test that the constructor has found the correct directory
        is(testDirectory.exists(), true);       //Test 1
        String filePath = new File("").getAbsolutePath();
        String testName = filePath + "/Test";
        is(testName, testDirectory.getName());  //Test 2
        //Test an empty directory
        ArrayList<Table> testArray = new ArrayList<Table>();
        is(testArray, testDirectory.getAllFiles());     //Test 3
        //Save a new table to the file
        Table testTable = new Table("test", "Col1 Col2 Col3");
        testTable.addRecord( "Data1 Data2 Data3");
        testArray.add(testTable);
        testDirectory.saveToFile(testTable);
        ArrayList<Table> directoryArray = testDirectory.getAllFiles();
        is(testArray, directoryArray);      //Test 4
        //Test getTable function
        is(testTable, testDirectory.getTable("test"));      //Test 5
        //Test deleteDirectory function - shouldn't work because there is a file in it
        testDirectory.deleteDirectory();
        is(testDirectory.exists(), true);       //Test 6
        //Test deleteRecord function
        testDirectory.delete("test");
        ArrayList<Table> testArray2 = new ArrayList<Table>();
        is(testArray2, testDirectory.getAllFiles());        //Test 7
        //Test delete directory - should now work because the directory is empty
        testDirectory.deleteDirectory();
        is(testDirectory.exists(), false);       //Test 8
        
        //Test results
        testResults.add(testPassed + " out of " + numberOfTests + " Directory tests passed.");
    }
    
    public static void testDatabase(){
        numberOfTests = 0;
        testPassed = 0;
        //Create a database that doesn't currently exist
        Database testDatabase = new Database("testDirectory");
        is(testDatabase.getFromDirectory("/testDirectory"), false);     //Test 1
        testDatabase.createDirectory();
        is(testDatabase.getFromDirectory("/testDirectory"), true);      //Test 2
        //Test add table function
        Table newTable1 = new Table("test1", "Col1 Col2 Col3");
        Table newTable2 = new Table("test2", "Col1 Col2 Col3");
        Table newTable3 = new Table("test3", "Col1 Col2 Col3");
        testDatabase.addTable(newTable1);
        testDatabase.addTable(newTable2);
        testDatabase.addTable(newTable3);
        is(testDatabase.numberOfTables(), 3);       //Test 3
        //Test getTable function
        is(testDatabase.getTable("test2"), newTable2);      //Test 4
        //Test saveDatabase - This will save the database to a file
        testDatabase.saveDatabase();
        Database testDatabase2 = new Database("testDirectory2");
        testDatabase2.getFromDirectory("/testDirectory");
        is(testDatabase, testDatabase2);        //Test 5
        //test deleteTable function
        testDatabase.deleteTable("test1");
        is(testDatabase.numberOfTables(), 2);       //Test 6
        //Test delete directory - need to remove rest of files first
        testDatabase.deleteTable("test2");
        testDatabase.deleteTable("test3");
        testDatabase.deleteDirectory();
        is(testDatabase.getFromDirectory("/testDirectory"), false);         //Test 7
        //Test results
        testResults.add(testPassed + " out of " + numberOfTests + " Database tests passed.");
        
        
    }
    
    static void is(Object x, Object y) {
        numberOfTests++;

        if (x == y){
            testPassed++;
        }else if (x != null && x.equals(y)){
            testPassed++;
          
        }else{
            testResults.add("Test number " + numberOfTests + " failed.");
            
        }
    }
}
