
import java.nio.file.NoSuchFileException;
import java.io.FileNotFoundException;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;



public class TaskList {
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

    public int sizeOfList(){
        return itemList.size();
    }

    public TaskItem getItem(int index){
        return itemList.get(index);
    }

    public boolean isEmpty(){
        return itemList.size() == 0;
    }

    public void editTaskItemTitle(String title,int index) {
        if(indexCheck(index)){
           TaskItem item = itemList.get(index);
           item.setTitle(title);
        }
    }
    public void editTaskItemDescription(String description,int index) {
        if(indexCheck(index)){
            TaskItem item = itemList.get(index);
            item.setDescription(description);
        }
    }
    public void editTaskItemDueDate(String dueDate, int index) {
        if(indexCheck(index)){
            TaskItem item = itemList.get(index);
            item.setDueDate(dueDate);
        }
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

    public void removeTask(int index){
        if(indexCheck(index)){
            itemList.remove(index);
        } else{
            throw new IndexOutOfBoundsException();
        }
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
            for(int i = 0; i < itemList.size(); i++) {

                output.format("%s" + "\n" + "%s" + "\n" + "%s"+"\n",
                        getTitle(i), getDescription(i), getDueDate(i));
            }
            System.out.println("task list has been saved");
        }catch (FileNotFoundException ex) {
            System.out.println("Unable to save the file...");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void readFile(String name) throws Exception {
        try{
            Scanner input = new Scanner(Paths.get(name));
            while(input.hasNext()){

                String title = input.nextLine();
                String description = input.nextLine();
                String date = input.nextLine();

                TaskItem item = new TaskItem(title, description, date);
                itemList.add(item);
                System.out.println("task list has been loaded");
            }
        }catch (FileNotFoundException ex) {
        System.out.println("Unable to find the file...");
        } catch (NoSuchFileException ex) {
            System.out.println("Unable to find the file...");
            throw new Exception("load failed");
        } catch (Exception ex) {
        ex.printStackTrace();
        }
    }

    public void viewList(){
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
