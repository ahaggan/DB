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
        location = new Directory(directory);
        if(!location.exists()){
            System.out.println("There is currently no directory in your specified location.");
       
            return false;
        }
        tables = location.getAllFiles();
        
        return true;
    }
    
    public void createDirectory(){
        location.create();
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
        //ListIterator it = tables.listIterator();
        /*while(it.hasNext()){
            Table temp = it.next();
            System.out.println(current.getTitle());
            if(requestedTitle.equals(temp.getTitle())){
                return temp;
            }*/
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
}
