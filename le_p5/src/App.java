import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args){
        App m = new App();
        m.mainMenu();
    }

    public void mainMenu(){
        TaskApp task = new TaskApp();
        ContactApp contact = new ContactApp();
        boolean choice = true;
        while(choice) {
            try {
                int appChoice = application();
                if (appChoice == 1) {
                    task.mainMenu();
                }
                else if (appChoice == 2) {
                    contact.mainMenu();
                }
                else if(appChoice == 3){
                    choice = false;
                }
                else{
                    throw new IllegalArgumentException();
                }
            }catch(IllegalArgumentException ex){
                System.out.println("Choose integer 1-3");
            }catch(InputMismatchException ex){
                System.out.println("Choose integer 1-3");
            }
        }
    }

    public int application(){
        System.out.println("\nSelect your application");
        System.out.println("------------------------");
        System.out.println("1) task list");
        System.out.println("2) contact list");
        System.out.println("3) quit");

        int num;
        if(input.hasNextInt()){
            num = input.nextInt();
            return num;
        } else{
            input.next();
            throw new InputMismatchException();
        }
    }
}
