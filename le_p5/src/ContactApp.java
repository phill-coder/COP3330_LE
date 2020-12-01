import java.util.InputMismatchException;
import java.util.Scanner;

public class ContactApp extends App{

    private static final Scanner input = new Scanner(System.in);
    private final ContactList contactList;

    public ContactApp(){

        contactList = new ContactList();

    }
    @Override
    public void mainMenu(){
        boolean choice = true;
        while(choice) {
            try {
                int option = showOptions();
                if (option == 1) {
                    contactList.newContactList();
                    contactMenu();
                }
                else if (option == 2) {
                    if(load()){
                        contactMenu();
                    }
                }
                else if (option == 3) {
                    contactList.newContactList();
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
        System.out.println("\nContact Menu");
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

    private void contactMenu(){
        boolean choice = true;
        while(choice){
            try{
                int operations = listOperation();
                if(operations == 1){
                    contactList.view();
                }
                else if(operations == 2){
                    input.nextLine();
                    addContact();
                }
                else if(operations == 3){
                    edit();
                }
                else if(operations == 4){
                    remove();
                }
                else if(operations == 5){
                    save();
                }
                else if(operations == 6){
                    choice = false;
                }
                else{
                    throw new InputMismatchException();
                }
            }
            catch(InputMismatchException ex){
                System.out.println("Must enter a number between 1 and 6");
            }

        }
    }
    private int listOperation(){
        System.out.println("\n"+"List Operation Menu");
        System.out.println("-------------------");
        System.out.println("1) View the list");
        System.out.println("2) add an item");
        System.out.println("3) edit an item");
        System.out.println("4) remove an item");
        System.out.println("5) save the current list");
        System.out.println("6) quit to the main menu");

        int num;
        if(input.hasNextInt()){
            num = input.nextInt();
            return num;
        } else{
            input.next();
            throw new InputMismatchException();
        }
    }

    private void addContact(){
        String first = getFirstName();
        String last = getLastName();
        String phone = getPhoneNumber();
        String email = getEmail();

        try{
            ContactItem contactInfo = new ContactItem(first, last, phone, email);
            contactList.storeContact(contactInfo);
        } catch(IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private String getFirstName(){
        System.out.println("First Name:");
        return input.nextLine();
    }
    private String getLastName(){
        System.out.println("Last Name:");
        return input.nextLine();
    }
    private String getPhoneNumber(){
        System.out.println("Phone Number xxx-xxx-xxxx:");
        return input.nextLine();
    }
    private String getEmail(){
        System.out.println("Email address (x@y.z)");
        return input.nextLine();
    }
    private void edit(){
        if(contactList.isNotEmpty()){
            int editIndex = getEditIndex();
            input.nextLine();
            if(editIndex < contactList.size()) {
                try {
                    String firstEdit = getFirstNameEdit(editIndex);
                    String lastEdit = getLastNameEdit(editIndex);
                    String phoneEdit = getPhoneNumberEdit(editIndex);
                    String emailEdit = getEmailEdit(editIndex);

                    contactList.editList(editIndex, firstEdit, lastEdit, phoneEdit, emailEdit);
                } catch (IllegalArgumentException ex) {
                    System.out.println(ex.getMessage());
                }
            }else{
                System.out.println("Invalid index");
            }
        }else{
            System.out.println("No current contact to edit");
        }
    }
    private int getEditIndex(){
        contactList.view();
        System.out.println("Which contact will you edit?");
        int num;
        if(input.hasNextInt()){
            num = input.nextInt();
            return num;
        } else{
            input.next();
            throw new IndexOutOfBoundsException();
        }
    }
    private String getFirstNameEdit(int taskNum){
        System.out.println("Enter a new First Name for contact "+taskNum);
        return input.nextLine();
    }
    private String getLastNameEdit(int taskNum){
        System.out.println("Enter a new Last Name for contact "+taskNum);
        return input.nextLine();
    }
    private String getPhoneNumberEdit(int taskNum){
        System.out.println("Enter a new Phone Number for contact "+taskNum);
        return input.nextLine();
    }
    private String getEmailEdit(int taskNum){
        System.out.println("Enter a new Email for contact "+taskNum);
        return input.nextLine();
    }

    public void remove(){
        if(contactList.isNotEmpty()) {
            try {
                int index = getRemoveIndex();
                contactList.remove(index);
            } catch(IndexOutOfBoundsException ex){
                System.out.println("Invalid Contact Number");
            }
        }
    }

    private int getRemoveIndex(){
        contactList.view();
        System.out.println("which contact will you remove?");
        int num;
        if(input.hasNextInt()){
            num = input.nextInt();
            return num;
        } else{
            input.next();
            throw new InputMismatchException();
        }
    }

    public void save(){
        if(contactList.isNotEmpty()) {
            input.nextLine();
            String name = getFileName();
            contactList.save(name);
        }else{
            System.out.println("Contact List empty");
        }
    }
    public String getFileName(){
        System.out.println("Enter Filename: ");
        return input.nextLine();
    }

    public boolean load(){
        input.nextLine();
        String name = getFileName();
        try {
            contactList.load(name);
        } catch(InputMismatchException e){
            System.out.println(e.getMessage());
            return false;
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }


}
