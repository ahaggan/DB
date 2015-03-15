import java.util.*;
import java.io.*;
import java.nio.*;
public class Command{
    private Database currentDatabase;
    
    public File[] showDirectories(){
        String filePath = new File("").getAbsolutePath();
        File tempFile = new File(filePath);
        File[] directories = tempFile.listFiles(File::isDirectory);
        
        System.out.println("");
        System.out.println("Available Directories:");
        for(File temp : directories){
            System.out.println("Directory: " + temp.getName());
        }
        System.out.println("");
        return directories;
    }
    
    public Database actionCommand(String userInput, Database database){
        currentDatabase = database;
        String[] input = userInput.split(" ");
        if(input.length < 2){
            System.out.println("ERROR: Your command needs to have at least 2 words in it.");
        }
        else if(input[0].equals("SHOW")){
            show(input);
        }
        else if(input[0].equals("USE")){
            use(input);
        }
        else if(input[0].equals("USE")){
            use(input);
        }
        else if(input[0].equals("USE")){
            use(input);
        }
        else if(input[0].equals("USE")){
            use(input);
        }
        else if(input[0].equals("USE")){
            use(input);
        }
        else if(input[0].equals("USE")){
            use(input);
        }
        else{
            System.out.println("The command " + input[0] + " is not valid here.");
        }
        return currentDatabase;
    }
    
    private void show(String[] input){
        if(input[1].equals("all")){
            showDirectories();
        }
        else if(input[1].equals("tables")){
            if(currentDatabase.numberOfTables() < 1){
                System.out.println("There are currently no tables in the database selected.");
                System.out.println("Try the 'USE' command to select one or create a new directory.");
            }
            else{
                currentDatabase.printAvailableTables();
            }
        }
        else{
            System.out.println("Your first word was SHOW, this should be followed with:");
            System.out.println("'all' - To display all available directories");
        }
    }    
    
    private void use(String[] input){
        if(!currentDatabase.getFromDirectory(input[1])){
            System.out.println("There is currently no directory in the location '" + input[1] + "'.");
            System.out.println("Would you like to create a new diretory in this location?");
            boolean answered = false;
            while(!answered){
                Console console = System.console();
                String line = console.readLine("Type 'Y' for yes or 'N' for no: ");
                if(line.equals("Y")){
                    answered = true;
                    currentDatabase.createDirectory();
                }else if(line.equals("N")){
                    answered = true;
                }
            }
        }
    }
    
}

    
 /*   
    
    
    
    
    
    
    
    
    
    
    
    
    public ArrayList<Table> actionCommand(String userInput, ArrayList<Table> databases){
        this.databases = databases;
        System.out.println("You entered: " + userInput);
        String[] input = userInput.split(" ");
        if(input.length == 3){
            if(input[0].equals("CREATE")){
                try{
                    create(input);
                }catch(Exception e){
                    helpSecond(input[0]);
                }
            }else if(input[0].equals("ADD")){
                try{
                    add(input);
                }catch(Exception e){
                    helpSecond(input[0]);
                }
            }else{
                helpFirst();
            }
        }else{
            helpFirst();
        }
        
        
        return databases;
    }
    
    public void create(String[] input){
        String fields = createFields(input);
        Table current = new Table(input[1], fields);
        databases.add(current);
    }
    
    public void add(String[] input){
       Table temp = getTable(input[1]);
       if(temp == null){return;}
       String fields = createFields(input);
       temp.addRecord(fields);
       updateTableInDatabase(input[1], temp);
    }
        
    public String createFields(String[] input){
        StringBuilder temp = new StringBuilder();
        for(String word : input){
            if(!word.equals(input[0]) && !word.equals(input[1])){
                temp.append(word);
                temp.append(" ");
            }
        }   
        String fields = temp.toString();
        return fields;
    }
    
    private void updateTableInDatabase(String name, Table updated){
        int i = 0;
        for(Table current : databases){
            if(name.equals(current.getTitle())){
                databases.set(i, updated);
            }
            i++;
        }
    }
 
    public Table getTable(String name){
        for(Table current : databases){
            if(name.equals(current.getTitle())){
                return current;
            }
        }
        System.out.println("There is no Table titled '" + name + "'.");
        return null;
    }
    
    public void helpSecond(String command){
        System.out.println("The " + command + " action should be followed by:");
        System.out.println("'name_of_table' 'names of fields'"); 
    }
    
    public void helpFirst(){
        System.out.println("");
        System.out.println("The command you entered was incorrect.");
        System.out.println("The following commands are allowed:");
        System.out.println("CREATE");
        System.out.println("");
    }
}*/
