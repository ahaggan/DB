import java.util.*;

public class Table{
    private ArrayList<Record> records;
    private Record fields;
    private int recordCount;
    private String title;
    
    public Table(String tableName, String inputFields){
        records = new ArrayList<Record>();
        title = tableName;
        fields = new Record("ID");
        fields.addField(inputFields);
        recordCount = 0;
    }
    
    public String getTitle(){ 
        return title;
    }
    
    public String getFields(){
        return fields.getValues();
    }
    
    public int getNumberOfRecords(){
        return recordCount;
    }
    
    public void addRecord(String inputData){
        Record input = new Record(Integer.toString(recordCount));
        input.addField(inputData);
        if(input.getNoOfFields() != fields.getNoOfFields()){
            System.out.println("You have entered an incorrect number of values.");
            System.out.print("You must enter: ");
            fields.getValues();
        }else{
            records.add(input);
            recordCount += 1;
        }
    }
    
     public Record getRecord(String key){
        boolean found = false;
        String temp;
        for(Record data: records){
            temp = data.getKey();
            if(key.equals(temp)){
                data.getValues();
                found = true;
                return data;
            }
        }
        System.out.println("");
        System.out.println("The " + fields.getKey() + " attribute is the key");
        System.out.println("There was no record with '" + key + "' as the key value.");
        System.out.println("");
        return null;
    }
    
    
    public void print(){
        fields.print();
        for(Record temp : records){
            temp.print();
        }
     
    }
    
    
    
    public void showRecords(){
        for(Record data : records){
            data.print();
        }
    }
    
   
    public ArrayList<Record> getRecords(){
        return records;
    }
    
    public void deleteRecord(String key){
        ListIterator<Record> iterator = records.listIterator();
        boolean found = false;
        while(iterator.hasNext()){
            Record temp = iterator.next();
            if(key.equals(temp.getKey())){
                iterator.remove();
                found = true;
            }
        }
        if(!found){
            System.out.println("");
            System.out.println("The " + fields.getKey() + " attribute is the key");
            System.out.println("There was no record with '" + key + "' as the key value.");
            System.out.println("");
        }
    }
    
    public void replaceRecord(String key, String newRecord){
        Record input = new Record(newRecord);
        if(input.getNoOfFields() != fields.getNoOfFields()){
            System.out.println("You have entered an incorrect number of values for your new record.");
            System.out.print("You must enter: ");
            fields.getValues();
            return;
        }
        ListIterator<Record> iterator = records.listIterator();
        boolean found = false;
        while(iterator.hasNext()){
            Record temp = iterator.next();
            if(key.equals(temp.getKey())){
                iterator.set(input);
                found = true;
            }
        }
        if(!found){
            System.out.println("");
            System.out.println("The " + fields.getKey() + " attribute is the key");
            System.out.println("There was no record with '" + key + "' as the key value.");
            System.out.println("");
        }
    }
    
}
    
