import java.time.DateTimeException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    private TaskList itemList;


    private static Scanner input = new Scanner(System.in);

    public App(){
       itemList = new TaskList();
    }
    public static void main(String[] args){
        App a = new App();
        a.mainMenu();
    }

    private void mainMenu(){
        boolean choice = true;
        while(choice) {
            try {
                int option = showOptions();
                if (option == 1) {
                    System.out.println("New task list created");
                    taskMenu();
                }
                else if (option == 2) {

                    if(load()) {
                        taskMenu();
                    }
                }
                else if (option == 3) {
                    choice = false;
                }
                else{
                    throw new InputMismatchException();
                }


            } catch (InputMismatchException ex) {
                System.out.println("Must enter a number between 1 and 3");
            }

        }
    }

    private static int showOptions(){
        System.out.println("Main Menu");
        System.out.println("---------------");
        System.out.println("1) Create a new list");
        System.out.println("2) Load an existing list");
        System.out.println("3) Quit");
        int num;
        if(input.hasNextInt()){
            num = input.nextInt();
            return num;
        } else{
            input.next();
            throw new InputMismatchException();
        }
    }
    public void taskMenu(){
        boolean choice = true;
        while(choice){
            try{
                int operations = listOperation();
                if(operations == 1){
                    viewList();
                }
                else if(operations == 2){
                    input.nextLine();
                    addItem();
                }
                else if(operations == 3){
                    editTask();
                }
                else if(operations == 4){
                    toRemove();
                }
                else if(operations == 5){
                    completeItem();
                }
                else if(operations == 6){
                    unComplete();
                }
                else if(operations == 7){
                    save();
                }
                else if(operations == 8){
                    itemList.newTaskList();
                    choice = false;
                }
                else{
                    throw new InputMismatchException();
                }
            }
            catch(InputMismatchException ex){
                System.out.println("Must enter a number between 1 and 8");
            }

        }
    }

    private static int listOperation(){
        System.out.println("\n"+"List Operation Menu");
        System.out.println("-------------------");
        System.out.println("1) View the list");
        System.out.println("2) add an item");
        System.out.println("3) edit an item");
        System.out.println("4) remove an item");
        System.out.println("5) mark an item as completed");
        System.out.println("6) unmark an item as completed");
        System.out.println("7) save the current list");
        System.out.println("8) quit to the main menu");

        int num;
        if(input.hasNextInt()){
            num = input.nextInt();
            return num;
        } else{
            input.next();
            throw new InputMismatchException();
        }
    }

    private void addItem(){
        String title = createTitle();
        String description = createDescription();
        String dueDate = createDueDate();

        try{
            TaskItem item = new TaskItem(title, description, dueDate);
            addToList(item);
        } catch(IllegalArgumentException ex) {
            System.out.println("Invalid Title");
        } catch (DateTimeException ex){
            System.out.println("Invalid Due date");
        }
    }
    private void addToList(TaskItem item) {
        itemList.storeItem(item);
    }

    private String createTitle(){
        System.out.println("Task title:");
        return input.nextLine();
    }
    private String createDescription(){
        System.out.println("Task description:");
        return input.nextLine();
    }
    private String createDueDate(){
        System.out.println("Task Due date (YYYY-MM-DD)");
        return input.nextLine();
    }

    private void viewList(){
        System.out.println("Current Tasks");
        System.out.println("---------------");

        for(int i = 0; i < itemList.sizeOfList();i++){
            System.out.println(i+") " +checkPrint(i)+"[" + itemList.getDueDate(i) + "] " + itemList.getTitle(i) + ": " + itemList.getDescription(i));
        }
    }
    private String checkPrint(int index){
        if(itemList.getCompletion(index)){
            return " *** ";
        }
        return "";
    }

    private void editTask(){
        if(isNotEmpty()){
            try{
                int editIndex = getEditIndex();
                input.nextLine();

                String newTitle = getEditTitle(editIndex);
                String newDescription = getEditDescription(editIndex);
                String newDueDate = getEditDueDate(editIndex);

                itemList.editTaskItemTitle(newTitle, editIndex);
                itemList.editTaskItemDescription(newDescription, editIndex);
                itemList.editTaskItemDueDate(newDueDate, editIndex);
            }catch (IndexOutOfBoundsException ex){
                System.out.println("Invalid Task number");
            }catch (DateTimeException ex){
                System.out.println("Invalid Due date");
            }catch(IllegalArgumentException ex) {
                System.out.println("Invalid Title");
            }
        }
    }
    private int getEditIndex(){
        viewList();
        System.out.println("Which task will you edit?");
        int num;
        if(input.hasNextInt()){
            num = input.nextInt();
            return num;
        } else{
            input.next();
            throw new IndexOutOfBoundsException();
        }
    }
    private String getEditTitle(int taskNum){
        System.out.println("Enter a new title for task "+taskNum);
        return input.nextLine();
    }
    private String getEditDescription(int taskNum){
        System.out.println("Enter a new description for task "+ taskNum);
        return input.nextLine();
    }
    private String getEditDueDate(int taskNum){
        System.out.println("Enter a new task due date (YYYY-MM-DD) for task "+taskNum);
        return input.nextLine();
    }
    private boolean isNotEmpty(){
        if(itemList.isEmpty()){
            return false;
        }
        return true;
    }

    private void toRemove(){
        if(isNotEmpty()) {
            try {
                int remove = getRemNum();
                itemList.removeTask(remove);
            } catch(IndexOutOfBoundsException ex){
                System.out.println("Invalid Task Number");
            }
        }
    }

    private int getRemNum(){
        viewList();
        System.out.println("which task will you remove?");
        int num;
        if(input.hasNextInt()){
            num = input.nextInt();
            return num;
        } else{
            input.next();
            throw new InputMismatchException();
        }
    }

    private void completeItem(){
        if(itemList.isThereUnCompletedTask()) {
            try {
                int index = getCompleteIndex();

                itemList.markComplete(index);

            } catch(InputMismatchException ex){
                System.out.println("Input must be integer");
            } catch(IndexOutOfBoundsException ex){
                System.out.println("Invalid Task");
            }
        } else {
            System.out.println("No items to complete");
        }
    }
    private int getCompleteIndex(){
        unCompletedTaskPrint();
        System.out.println("Which task will you mark as complete?");
        int num;
        if(input.hasNextInt()){
            num = input.nextInt();
            return num;
        } else{
            input.next();
            throw new InputMismatchException();
        }
    }
    private void unCompletedTaskPrint(){
        System.out.println("Uncompleted Tasks");
        System.out.println("------------------");

        for(int i = 0; i < itemList.sizeOfList();i++){
            if(!itemList.getCompletion(i)) {
                System.out.println(i + ") " + "[" + itemList.getDueDate(i) + "] " + itemList.getTitle(i) + ": " + itemList.getDescription(i));
            }
        }
    }

    private void unComplete(){
        if(itemList.isThereCompletedTask()) {
            try {
                int index = getUnCompleteIndex();

                itemList.unMarkComplete(index);

            } catch(InputMismatchException ex){
                System.out.println("Input must be integer");
            } catch(IndexOutOfBoundsException ex){
                System.out.println("Invalid Task");
            }
        } else {
            System.out.println("No items to complete");
        }
    }
    private int getUnCompleteIndex(){
        completedTaskPrint();
        System.out.println("Which task will you unmark as completed?");
        int num;
        if(input.hasNextInt()){
            num = input.nextInt();
            return num;
        } else{
            input.next();
            throw new InputMismatchException();
        }
    }
    private void completedTaskPrint(){
        System.out.println("Completed Tasks");
        System.out.println("------------------");

        for(int i = 0; i < itemList.sizeOfList();i++){
            if(itemList.getCompletion(i)) {
                System.out.println(i + ") " + "[" + itemList.getDueDate(i) + "] " + itemList.getTitle(i) + ": " + itemList.getDescription(i));
            }
        }
    }
    private void save(){
        if(isNotEmpty()) {
            input.nextLine();
            String name = getSaveName();
            itemList.save(name);
        } else{
            System.out.println("List Empty");
        }

    }
    private String getSaveName(){
        System.out.println("Enter the filename to save as: ");
        return input.nextLine();
    }

    private boolean load(){
        input.nextLine();
        String name = getLoadName();
        try {
            itemList.readFile(name);
        } catch (Exception ex){
            return false;
        }
        return true;
    }

    private String getLoadName(){
        System.out.println("Enter the filename to load: ");
        return input.nextLine();
    }
}
