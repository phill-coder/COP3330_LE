import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactItemTest {
    @Test
    public void creationFailsWithAllBlankValues(){
        assertThrows(IllegalArgumentException.class, ()-> new ContactItem("","","",""));
    }

    @Test
    public void creationSucceedsWithBlankEmail(){
        assertDoesNotThrow(()-> new ContactItem("John","Doe","727-123-3213",""));
    }

    @Test
    public void creationSucceedsWithBlankFirstName(){
        assertDoesNotThrow(()-> new ContactItem("","Doe","727-123-3213","johndoe@gmail.com"));
    }

    @Test
    public void creationSucceedsWithBlankLastName(){
        assertDoesNotThrow(()-> new ContactItem("John","","727-123-3213","johndoe@gmail.com"));
    }

    @Test
    public void creationSucceedsWithBlankPhone(){
        assertDoesNotThrow(()-> new ContactItem("John","Doe","","johndoe@gmail.com"));
    }

    @Test
    public void creationSucceedsWithNonBlankValues(){
        assertDoesNotThrow(()-> new ContactItem("John","Doe","727-123-3213","johndoe@gmail.com"));
    }

    @Test
    public void editingFailsWithAllBlankValues(){
        ContactItem contact = new ContactItem("John","Doe","727-123-3213","johndoe@gmail.com");
        assertThrows(IllegalArgumentException.class, ()-> contact.updateItem("","","",""));
    }

    @Test
    public void editingSucceedsWithBlankEmail(){
        ContactItem contact = new ContactItem("John","Doe","727-123-3213","johndoe@gmail.com");
        assertDoesNotThrow(()-> contact.updateItem("Dohn","Joe","727-542-3245",""));
    }

    @Test
    public void editingSucceedsWithBlankFirstName(){
        ContactItem contact = new ContactItem("John","Doe","727-123-3213","johndoe@gmail.com");
        assertDoesNotThrow( ()-> contact.updateItem("","Joe","727-542-3245","johndoe@gmail.com"));
    }

    @Test
    public void editingSucceedsWithBlankLastName(){
        ContactItem contact = new ContactItem("John","Doe","727-123-3213","johndoe@gmail.com");
        assertDoesNotThrow(()-> contact.updateItem("Dohn","","727-542-3245","johndoe@gmail.com"));
    }

    @Test
    public void editingSucceedsWithBlankPhone(){
        ContactItem contact = new ContactItem("John","Doe","727-123-3213","johndoe@gmail.com");
        assertDoesNotThrow(()-> contact.updateItem("Dohn","Joe","","johndoe@gmail.com"));
    }

    @Test
    public void editingSucceedsWithNonBlankValues(){
        ContactItem contact = new ContactItem("John","Doe","727-123-3213","johndoe@gmail.com");
        assertDoesNotThrow(()-> contact.updateItem("Dohn","Joe","727-542-3245","johndoe@gmail.com"));
    }
    @Test
    public void testToString(){
        ContactItem contact = new ContactItem("John","Doe","727-123-3213","johndoe@gmail.com");
        assertEquals("Name: John Doe\nPhone: 727-123-3213\nEmail: johndoe@gmail.com",contact.toString());
    }
}
