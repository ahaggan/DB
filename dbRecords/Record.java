import java.util.*;

public class Record{
    private ArrayList<String> fields;
    private int numberOfFields;
    
    public Record(String inputFields){
        fields = new ArrayList<String>();
        String[] input = inputFields.split(" ");
        int count = 0;
        
        for(String word : input){
            fields.add(word);
            count++;
        }
        numberOfFields = count;
    }
    
    public void getFields(){
        for(String field : fields){
            System.out.println(field);
        }
    }
    
    public int getNoOfFields(){
        return numberOfFields;
    }
    
    public void setField(int position, String value){
        if(position < numberOfFields){
            fields.set(position, value);
        }else{
            System.out.println("Your record doen't contain a value at that position");
        }
    }
        
    public void addField(String newValue){
        String[] input = newValue.split(" ");
        int count = 0;
        
        for(String word : input){
            fields.add(word);
            count++;
        }
        numberOfFields += count;
    }
    
    public void removeField(String toRemove){
        ListIterator iterator = fields.listIterator();
        while(iterator.hasNext()){
            if(iterator.next().equals(toRemove)){
                iterator.remove();
            }
        }
    }
    
    /*
    public void add(String inputData){
        ArrayList<String> array = new ArrayList<String>();
        String[] input = inputData.split(" ");
        
        for(String word : input){
            array.add(word);
        }
        if(array.size() != numberOfFields){
            System.out.println("You have entered an incorrect number of fields.");
            System.out.println("You need to have the following:");
            this.getFields();
        }else{
            records.add(array);
            System.out.println("Record added successfully.");
        }
        
    }*/
    
}
