import java.util.*;
import java.io.*;

public class Database{
    private ArrayList<Table> tables;
    private String title;
    private Directory location;
    
    public Database(String newTitle){
        tables = new ArrayList<Table>();
        title = newTitle;
    }
    
    public boolean getFromDirectory(String directory){
        String newDirectory = "/" + directory;
        location = new Directory(newDirectory);
        if(!location.exists()){
            return false;
        }
        tables = location.getAllFiles();
        return true;
    }
    
    public void createDirectory(){
        location.createDirectory();
	}      
    
    public void deleteDirectory(){
        location.deleteDirectory();
    }
    
    public void deleteTable(String name){
        
        try{
            for(Table temp: tables){
                if(temp.getTitle().equals(name)){
                    tables.remove(tables.indexOf(temp));
                }
            }
        }catch(Exception e){}
        location.delete(name);
    }
    
    public int numberOfTables(){
        return tables.size();
    }
    
    public void printAvailableTables(){
        System.out.println("There are " + tables.size() + " tables in theis database.");
        System.out.println("They are: ");
        for(Table temp : tables){
            System.out.println(temp.getTitle());
        }
    }
    
    public Table getTable(String requestedTitle){
        System.out.println("requested: " + requestedTitle);
        for(Table current : tables){
        System.out.println(current.getTitle());
            if(requestedTitle.equals(current.getTitle())){
                return current;
            }
        }
        System.out.println("There is no table by the name " + requestedTitle);
        return null;
    }
    
    public void addTable(Table newTable){
        tables.add(newTable);
    }
    
    public void saveDatabase(){
        for(Table temp : tables){
            location.saveToFile(temp);
        }
    }
    
    public void print(){
        System.out.println("Database: " + title);
        System.out.println("---------------------------------------------------------");
        for(Table temp : tables){
            temp.print();
             System.out.println("---------------------------------------------------------");
        }
        
    }
    
    public boolean equals(Object test){
        if(test == null){
            return false;
        }
        if(getClass() != test.getClass()){
            return false;
        }
        final Database testDatabase = (Database) test;
        
        if(!this.tables.equals(testDatabase.tables)){
            return false;
        }
        return true;
    }
}
