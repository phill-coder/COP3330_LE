import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args){
        App a = new App();
        a.mainMenu();
    }

    private void mainMenu(){
        while(true) {
            try {
                int option = showOptions();
                if (option == 1) {
                    taskMenu();
                }
                else if (option == 2) {
                    System.out.println("Two");
                }
                else if (option == 3) {
                    break;
                }
                else{
                    throw new InputMismatchException();
                }
                break;

            } catch (InputMismatchException ex) {
                System.out.println("Must enter a number between 1 and 3");
            }
            finally {
                //input.nextLine();
            }
        }
    }

   private static int showOptions(){
        System.out.println("Main Menu");
        System.out.println("---------------");
        System.out.println("1) Create a new list");
        System.out.println("2) Load an existing list");
        System.out.println("3) Quit");

       return input.nextInt();
   }

    public void taskMenu(){
        while(true){
            try{
                int operations = listOperation();
                if(operations == 1){
                    System.out.println("1");
                }
                else if(operations == 2){
                    addItem();
                }
                else if(operations == 3){
                    System.out.println("3");
                }
                else if(operations == 4){
                    System.out.println("1");
                }
                else if(operations == 5){
                    System.out.println("1");
                }
                else if(operations == 6){
                    System.out.println("1");
                }
                else if(operations == 7){
                    System.out.println("1");
                }
                else if(operations == 8){
                    mainMenu();
                }
                else{
                    throw new InputMismatchException();
                }
                break;

            }
            catch(InputMismatchException ex){
                System.out.println("Must enter a number between 1 and 8");
            } finally {
                //input.nextLine();
            }
        }
    }
    private static int listOperation(){
        System.out.println("List Operation Menu");
        System.out.println("-------------------");
        System.out.println("1) View the list");
        System.out.println("2) add an item");
        System.out.println("3) edit an item");
        System.out.println("4) remove an item as completed");
        System.out.println("5) mark an item as completed");
        System.out.println("6) unmark an item as completed");
        System.out.println("7) save the current list");
        System.out.println("8) quit to the main menu");

        return input.nextInt();
    }
/*
    private static void addItem(){
        TaskItem t = new TaskItem();
        while(true){
            try{
                String title = t.getTitle();

                break;
            } catch(IllegalArgumentException ex){
                System.out.println("Invalid Title");
            }
        }

    }
*/
}
