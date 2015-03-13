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
    
    public String getValues(){
        StringBuilder allValues = new StringBuilder();
        for(String field : fields){
            allValues.append(field + " ");
        }
        allValues.setLength(allValues.length() - 1);
        return allValues.toString();
    }
    
    public void print(){
        StringBuilder allValues = new StringBuilder();
        for(String field : fields){
            System.out.printf("%-15s ", field);
        }
        System.out.println();   
    }
    
    public int getNoOfFields(){
        return numberOfFields;
    }
    
    public void replaceField(int position, String value){
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
                numberOfFields--;
            }
        }
    }
       
    public String getKey(){
        return fields.get(0);
    }
    
   
    
    public boolean equals(Object test){
        if(test == null){
            return false;
        }
        if(getClass() != test.getClass()){
            return false;
        }
        final Record testRecord = (Record) test;
        
        if(!this.fields.equals(testRecord.fields)){
            return false;
        }
        if(this.numberOfFields != testRecord.numberOfFields){
            return false;
        }
        return true;
        
    }
            
    
}
