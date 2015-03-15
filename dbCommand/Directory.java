import java.io.File;
import java.io.PrintWriter;
import java.io.FilenameFilter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Directory {
    private File dir;
    private boolean current;
    private String directoryName;
    
    public Directory(String inputName){
        String filePath = new File("").getAbsolutePath();
        directoryName = filePath + inputName;
        File tempFile = new File(directoryName);
        if(tempFile.isDirectory()){
            current = true;
        }else{
            current = false;
        }
        dir = tempFile;
    }
    
    public String getName(){
        return directoryName;
    }
    
    public void createDirectory(){
        File file = new File(directoryName);
	    if (!file.exists()) {
		    if (file.mkdir()) {
		    	System.out.println("Directory is created!");
		    	current = true;
		    } else {
		    	System.out.println("Failed to create directory!");
		    }
	    }
    }
    
    public void deleteDirectory(){
        File dir = new File(directoryName);
        //Wont delete a directory if it contains files.
        // I kept this to prevent unwanted data loss
        if(dir.isDirectory()){
            dir.delete();           
        }
    }    
    
    
    
    public boolean exists(){ 
        File temp = new File(directoryName);
        if(temp.exists() && temp.isDirectory()){
            return true;
        }
        return false;
    }
    
    public File[] finder(){
    	return dir.listFiles(new FilenameFilter() { 
    	         public boolean accept(File dir, String filename)
    	              { return filename.endsWith(".txt"); }
    	} );
    }
    
    public ArrayList<Table> getAllFiles(){
        ArrayList<Table> tables = new ArrayList<Table>();
        File[] files = finder();
        try{
            for (File temp : files){
                tables.add(makeTable(temp.getName()));
            }
        }catch(Exception e){System.out.println("There are currently no files in your directory.");}
        return tables;
    }
    
    
    
    public void printFileNames(){
        File[] files = finder();
        System.out.println("Current files in directory:");
        try{
            for (File temp : files){
                System.out.println(temp.getName());
            }
        }catch(Exception e){System.out.println("There are currently no files in your directory.");}
    }
    
    public Table getTable(String name){
        File[] files = finder();
        String fileName = name + ".txt";
        try{
            for (File temp : files){
                if(fileName.equals(temp.getName())){
                    return makeTable(fileName);
                }
            }
        }catch(Exception e){System.out.println("There are currently no files called" + name);}
        return null;
    }
    
    private Table makeTable(String file){
        Table temp;
        String path = dir.getName() + "/" + file;
        System.out.println(path);
        try{
            ArrayList<String> lines = new ArrayList<String>();
            lines.addAll(Files.readAllLines(Paths.get(path), Charset.defaultCharset()));
            temp = new Table(lines.get(0), lines.get(1));
            for(String line : lines){
                if(!line.equals(lines.get(0)) && !line.equals(lines.get(1))){
                    temp.addRecord(line);
                }
            }
        }catch(Exception e){ 
            System.out.println("Cant read file");
            return null;
        }
        return temp;
    }
    
    public void saveToFile(Table table){
        String fileName = dir.getName() + "/" + table.getTitle() + ".txt";
        try{
            PrintWriter out  = new PrintWriter(fileName);
            ArrayList<Record> tableRecords = table.getRecords();
            out.println(table.getTitle());
            out.println(table.getFields());
            for(Record record : tableRecords){
                out.println(record.getValues());
            }
            out.close();
        }catch(Exception e){ System.out.println("Unable to save to file.");}
    }
    
    public void delete(String name){
        File[] files = finder();
        boolean check = false;
        String fileName = name + ".txt";
        try{
            for (File temp : files){
                if(fileName.equals(temp.getName())){
                    temp.setWritable(true);
                    check = temp.delete();
                }
            }
            System.out.println("File deleted: " + check);
        }catch(Exception e){System.out.println("There are currently no files called" + name);}
    }   
}
