import java.io.*;
import java.util.*;


public class Db{
    public Database database;
    
    
    public static void main(String[] args){
        Db dataBase = new Db();
        dataBase.run();
    }
    
    public void run(){
        
        testRecord();
        testTable();
        testDirectory();
    }
    /*
    public void read(){
        Console console = System.console();
        boolean quit = false;
        while(!quit){
            String line = console.readLine("Enter Command: ");
            if(line.equals("quit") || line.equals("QUIT")){
                quit = true;
            }else{
                Command input = new Command();
                databaseList = input.actionCommand(line, databaseList);
            }
        }
        for(Table temp : databaseList){
            temp.getFields();
        }
    }*/
    
    private static int numberOfTests;
    private static int testPassed;
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
        System.out.println(
            testPassed + " out of " + numberOfTests + 
            " Record tests passed.");    
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
        //Tests deleteRecord
        ArrayList<Record> testArray2 = new ArrayList<Record>();
        testArray2.add(checkRecord);
        test.deleteRecord("x");
        is(testArray2, test.getRecords());      //Test 6
        //Tests replaceRecord
        ArrayList<Record> testArray3 = new ArrayList<Record>();
        testArray3.add(checkRecord2);
        test.replaceRecord("data1", "x y z");
        is(testArray3, test.getRecords());      //Test 7
        //Test results
        System.out.println(
            testPassed + " out of " + numberOfTests + 
            " Table tests passed."); 
    }
    
    public static void testDirectory(){
        numberOfTests = 0;
        testPassed = 0;
        Directory testDirectory = new Directory("/Test");
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
        //Test deleteRecord function
        testDirectory.delete("test");
        ArrayList<Table> testArray2 = new ArrayList<Table>();
        is(testArray2, testDirectory.getAllFiles());        //Test 6
        
        
        //NEED TO ADD A DELETE DIRECTORY FUNCTION TO TEST CREATE PROPERLY
        //Test results
        System.out.println(
            testPassed + " out of " + numberOfTests + 
            " Directory tests passed.");
    }
    
    public static void testDatabase(){
        Database database = new Database("Directory");
        database.getFromDirectory("/Directory");
        database.printAvailableTables();
        Table current = database.getTable("People");
        if(current != null){
            current.print();
        }
        
        database = new Database("Created");
        database.getFromDirectory("/Created");
        database.createDirectory();
        database.getFromDirectory("/Created");
        Table newTable = new Table("Things", "Data1 Data2 Data3");
        newTable.addRecord("Item1 Item2 Item3");
        database.addTable(newTable);
        database.delete("Things");
        database.printAvailableTables();
        database.saveDatabase();
        
        
        current = database.getTable("Things");
        current.print();
    }
    
    static void is(Object x, Object y) {
        numberOfTests++;

        if (x == y){
            testPassed++;
        }else if (x != null && x.equals(y)){
            testPassed++;
          
        }else{
            System.out.println("Test number " + numberOfTests + " failed.");
            
        }
    }
}
