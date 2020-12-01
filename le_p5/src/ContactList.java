import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class ContactList {
    List<ContactItem> contactList;
    public ContactList(){
        contactList = new ArrayList<>();
    }

    public void newContactList(){
        contactList = new ArrayList<>();
    }

    public void storeContact(ContactItem contactInfo){
        contactList.add(contactInfo);
    }


    public void view(){
        System.out.println("Current Contacts");
        System.out.println("---------------");

        for(int i = 0; i < contactList.size();i++){
            ContactItem contact = contactList.get(i);
            System.out.println(i+") " + contact.toString());
        }
    }

    public boolean isNotEmpty(){
        return contactList.size() > 0;
    }

    public void editList(int index,String firstName,String lastName,String phoneNumber,String emailAddress){
        this.get(index).updateItem(firstName,lastName,phoneNumber,emailAddress);
    }

    public ContactItem get(int index){
        return contactList.get(index);
    }

    public int size(){
        return contactList.size();
    }

    public void remove(int index){
        contactList.remove(index);
    }
    public void save(String filename){
        try(Formatter output = new Formatter(filename)){
            output.format("contacts%n");
            for(ContactItem contact : contactList){
                output.format("%s%n", contact.getFirstName());
                output.format("%s%n", contact.getLastName());
                output.format("%s%n", contact.getEmailAddress());
                output.format("%s%n", contact.getPhoneNumber());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void load(String filename){
        List<ContactItem> backup = contactList;
        contactList = new ArrayList<>();

        try(Scanner input = new Scanner(Paths.get(filename))) {
            String header = input.nextLine();
            if(header.equalsIgnoreCase("contacts")) {
                while (input.hasNext()) {
                    String first = input.nextLine();
                    String last = input.nextLine();
                    String phone = input.nextLine();
                    String email = input.nextLine();

                    ContactItem contact = new ContactItem(first, last, phone, email);
                    contactList.add(contact);
                }
            }else{
                contactList = backup;
                throw new InputMismatchException("Invalid file");
            }
        } catch (FileNotFoundException e) {
            contactList = backup;
            throw new IllegalArgumentException("FIle not found");
        }catch (IOException e) {
            contactList = backup;
            throw new IllegalArgumentException("File cannot be loaded");
        }
    }

}
