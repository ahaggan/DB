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
        
    }
    
}
