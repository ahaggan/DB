import java.io.*;
import java.util.*;
public class Db{
    public ArrayList<Table> databaseList;
    public Directory files;
    
    public static void main(String[] args){
        Db dataBase = new Db();
        dataBase.run();
    }
    
    public void run(){
        databaseList = new ArrayList<Table>();
        files = new Directory("/Directory");
        files.printFileNames();
        databaseList = files.getAllFiles();
        Table current = new Table("People", "Name age job");
        current.addRecord("Ashley 26 Student");
        current.addRecord("Christopher 100 Microbiologist");
        current.print();
        testRecord();
        testTable();
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
                databaseList = input.actionCommand(line, databaseList);
            }
        }
        for(Table temp : databaseList){
            temp.getFields();
        }
    }
    
    private static int numberOfTests;
    private static int testPassed;
    public static void testRecord(){
        numberOfTests = 0;
        testPassed = 0;
        //Test the Record constructor
        Record test1 = new Record("Ashley 26 Student");
        is("Ashley 26 Student",test1.getValues());
        //Test the number of fields function
        is(test1.getNoOfFields(), 3);
        //Tests the replace function
        Record checkRecord = new Record("Ashley 26 Pilot");
        test1.replaceField(2, "Pilot");
        is(test1.getValues(), checkRecord.getValues());
        //Test the add field function
        test1.addField("banana");
        is(test1.getValues(), "Ashley 26 Pilot banana");
        is(test1.getNoOfFields(), 4);
        //Test the remove field function
        test1.removeField("Pilot");
        is(test1.getValues(), "Ashley 26 banana");
        is(test1.getNoOfFields(), 3);
        //Test the getKey function
        is(test1.getKey(), "Ashley");
        
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
        is(test.getTitle(), "test");
        is(test.getNumberOfRecords(), 0);
        is(test.getFields(), "ID column1 column2 column3");
        //Test the addRecord and getRecord functions
        test.addRecord("data1 data2 data3");
        Record checkRecord = new Record("0 data1 data2 data3");
        is(test.getRecord("0"), checkRecord);
        //Tests the getRecords function
        ArrayList<Record> testArray = new ArrayList<Record>();
        Record checkRecord2 = new Record("1 x y z");
        testArray.add(checkRecord);
        testArray.add(checkRecord2);
        test.addRecord("x y z");
        is(testArray, test.getRecords());
        //Test results
        System.out.println(
            testPassed + " out of " + numberOfTests + 
            " Table tests passed."); 
    }
    
    static void is(Object x, Object y) {
        numberOfTests++;
        if(x == null){
            System.out.println("Object is null");
        }
        if (x == y){
            testPassed++;
        }else if (x != null && x.equals(y)){
            testPassed++;
          
        }else{
            System.out.println("Test number " + numberOfTests + " failed.");
            
        }
    }
}
