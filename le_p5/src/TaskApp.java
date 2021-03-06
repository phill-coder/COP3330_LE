import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskApp extends App {
    private final TaskList itemList;


    private static final Scanner input = new Scanner(System.in);

    public TaskApp(){
        itemList = new TaskList();
    }

    @Override
    public void mainMenu(){
        boolean choice = true;
        while(choice) {
            try {
                int option = showOptions();
                if (option == 1) {
                    itemList.newTaskList();
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
        System.out.println("\nTask Menu");
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
                    itemList.view();
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
            System.out.println(ex.getMessage());
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


    private void editTask(){
        if(itemList.isNotEmpty()){
            try{
                int editIndex = getEditIndex();
                input.nextLine();

                String newTitle = getEditTitle(editIndex);
                String newDescription = getEditDescription(editIndex);
                String newDueDate = getEditDueDate(editIndex);

                itemList.editList(editIndex,newTitle,newDescription,newDueDate);
            }catch(IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    private int getEditIndex(){
        itemList.view();
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

    private void toRemove(){
        if(itemList.isNotEmpty()) {
            try {
                int remove = getRemNum();
                itemList.remove(remove);
            } catch(IndexOutOfBoundsException ex){
                System.out.println("Invalid Task Number");
            }
        }
    }

    private int getRemNum(){
        itemList.view();
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
        itemList.unCompletedTaskPrint();
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
            System.out.println("No items to Uncomplete");
        }
    }
    private int getUnCompleteIndex(){
        itemList.completedTaskPrint();
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

    private void save(){
        if(itemList.isNotEmpty()) {
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

    public boolean load(){
        input.nextLine();
        String name = getLoadName();
        try {
            itemList.load(name);
        } catch(InputMismatchException e){
            System.out.println(e.getMessage());
            return false;
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    private String getLoadName(){
        System.out.println("Enter the filename to load: ");
        return input.nextLine();
    }
}
