import java.util.*;
public class Command{
    private ArrayList<Table> databases;
    
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
       Table temp = getDatabase(input[1]);
       if(temp == null){return;}
       String fields = createFields(input);
       temp.addRecord(fields);
       putDatabase(input[1], temp);
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
    
    private void putDatabase(String name, Table updated){
        for(Table current : databases){
            if(name.equals(current.getTitle())){
                current.set(updated);
            }
        }
    }
    
    public Table getDatabase(String name){
        for(Table current : databases){
            if(name.equals(current.getTitle())){
                return current;
            }
        }
        System.out.println("There is no database titled '" + name + "'.");
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
}
