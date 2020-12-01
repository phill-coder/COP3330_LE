public class ContactItem {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;

    public ContactItem(String firstName,String lastName,String phoneNumber,String emailAddress){

        if(firstName.length() == 0 && lastName.length() == 0 && phoneNumber.length() == 0 && emailAddress.length() == 0){
            throw new IllegalArgumentException("Contact creation FAILED. No first name, last name, number, or email");
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public void updateItem(String firstName,String lastName,String phoneNumber,String emailAddress){
        if(firstName.length() == 0 && lastName.length() == 0 && phoneNumber.length() == 0 && emailAddress.length() == 0){
            throw new IllegalArgumentException("No first name, last name, number, or email input");
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public String getEmailAddress(){
        return this.emailAddress;
    }

    @Override
    public String toString(){
        return "Name: "+ getFirstName() + " " + getLastName() +"\n" + "Phone: " + getPhoneNumber() + "\n" +"Email: " +getEmailAddress();
    }
}
