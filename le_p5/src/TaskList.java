import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class TaskList{

    List<TaskItem> itemList;

    public TaskList(){
        itemList = new ArrayList<>();
    }
    public void newTaskList(){
        itemList = new ArrayList<>();
    }

    public void storeItem(TaskItem itemInfo){
        itemList.add(itemInfo);
    }

    public TaskItem getItem(int index){
        return itemList.get(index);
    }

    public boolean isNotEmpty(){
        return itemList.size() > 0;
    }

    public int size(){
        return itemList.size();
    }

    public boolean indexCheck(int index){
        if(index < itemList.size() || index > 0){
            return true;
        } else{
            throw new IndexOutOfBoundsException();
        }

    }

    public String getTitle(int index){
        TaskItem item = itemList.get(index);
        return item.getTitle();
    }
    public String getDescription(int index){
        TaskItem item = itemList.get(index);
        return item.getDescription();
    }
    public String getDueDate(int index){
        TaskItem item = itemList.get(index);
        return item.getDueDate();
    }
    public boolean getCompletion(int index){
        TaskItem item = itemList.get(index);
        return item.getCompletion();
    }

    public void remove(int index){
        if(indexCheck(index)){
            itemList.remove(index);
        } else{
            throw new IndexOutOfBoundsException();
        }
    }
    public void editList(int index, String title, String description, String dueDate){
        this.getItem(index).updateList(title,description,dueDate);
    }

    public boolean isThereUnCompletedTask(){
        for(int i = 0; i < itemList.size();i++) {
            if(!getCompletion(i)){
                return true;
            }
        }
        return false;
    }

    public void markComplete(int index){
        if (indexCheck(index)) {
            if (!getCompletion(index)) {
                TaskItem item = getItem(index);
                item.setCompletionTrue();
            }else{
                throw new IndexOutOfBoundsException();
            }
        }else{
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean isThereCompletedTask(){
        for(int i = 0; i < itemList.size();i++) {
            if(getCompletion(i)){
                return true;
            }
        }
        return false;
    }

    public void unMarkComplete(int index){
        if (indexCheck(index)) {
            if (getCompletion(index)) {
                TaskItem item = getItem(index);
                item.setCompletionFalse();
            }else{
                throw new IndexOutOfBoundsException();
            }
        }else{
            throw new IndexOutOfBoundsException();
        }
    }

    public void save(String name) {
        try(Formatter output = new Formatter(name)) {
            output.format("tasks%n");
            for(int i = 0; i < itemList.size(); i++) {

                output.format("%s" + "\n" + "%s" + "\n" + "%s"+"\n"+ "%s"+"\n",
                        getTitle(i), getDescription(i), getDueDate(i),getCompletion(i));
            }
            System.out.println("task list has been saved");
        }catch (FileNotFoundException ex) {
            System.out.println("Unable to save the file...");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void load(String filename){
        List<TaskItem> backup = itemList;
        itemList = new ArrayList<>();

        try(Scanner input = new Scanner(Paths.get(filename))) {
            String header = input.nextLine();
            if(header.equalsIgnoreCase("tasks")) {
                while (input.hasNext()) {
                    String title = input.nextLine();
                    String description = input.nextLine();
                    String date = input.nextLine();
                    String complete = input.nextLine();

                    TaskItem item = new TaskItem(title, description, date);
                    if(complete.equalsIgnoreCase("true")){
                        item.setCompletionTrue();
                    } else{
                        item.setCompletionFalse();
                    }
                    itemList.add(item);
                }
            }else{
                itemList = backup;
                throw new InputMismatchException("Invalid file name");
            }
        } catch (FileNotFoundException e) {
            itemList = backup;
            throw new IllegalArgumentException("FIle not found");
        }catch (IOException e) {
            itemList = backup;
            throw new IllegalArgumentException("File cannot be loaded");
        }
    }

    public void view(){
        System.out.println("Current Tasks");
        System.out.println("---------------");

        for(int i = 0; i < itemList.size();i++){
            System.out.println(i+") " +checkPrint(i)+"[" + getDueDate(i) + "] " + getTitle(i) + ": " + getDescription(i));
        }
    }
    private String checkPrint(int index){
        if(getCompletion(index)){
            return " *** ";
        }
        return "";
    }


    public void completedTaskPrint(){
        System.out.println("Completed Tasks");
        System.out.println("------------------");

        for(int i = 0; i < itemList.size();i++){
            if(getCompletion(i)) {
                System.out.println(i + ") " + "[" + getDueDate(i) + "] " + getTitle(i) + ": " + getDescription(i));
            }
        }
    }

    public void unCompletedTaskPrint(){
        System.out.println("Uncompleted Tasks");
        System.out.println("------------------");

        for(int i = 0; i < itemList.size();i++){
            if(!getCompletion(i)) {
                System.out.println(i + ") " + "[" + getDueDate(i) + "] " + getTitle(i) + ": " + getDescription(i));
            }
        }
    }


}
