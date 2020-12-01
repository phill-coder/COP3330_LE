import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import static org.junit.jupiter.api.Assertions.*;
public class ContactListTest {
    private final ContactList list;

    public ContactListTest(){
        list = new ContactList();
    }

    @BeforeEach
    public void start(){
        ContactItem c = new ContactItem("John", "Doe", "727-123-3213", "johndoe@gmail.com");
        list.storeContact(c);
    }
    @Test
    public void addingItemsIncreasesSize() {
        assertEquals(1, list.size());
    }

    @Test
    public void editingItemsFailsWithAllBlankValues(){
        assertThrows(IllegalArgumentException.class, ()-> list.editList(0,"","","",""));
        }
    @Test
    public void editingItemsFailsWithInvalidIndex(){
        assertThrows(IndexOutOfBoundsException.class, ()-> list.editList(1,"","","",""));
    }
    @Test
    public void editingSucceedsWithBlankFirstName(){
        assertDoesNotThrow(()->list.editList(0,"", "Doe", "727-123-3213", "johndoe@gmail.com"));
    }

    @Test
    public void editingSucceedsWithBlankLastName(){
        assertDoesNotThrow(()->list.editList(0,"John", "", "727-123-3213", "johndoe@gmail.com"));
    }

    @Test
    public void editingSucceedsWithBlankPhone(){
        assertDoesNotThrow(()->list.editList(0,"John", "Doe", "", "johndoe@gmail.com"));
    }

    @Test
    public void editingSucceedsWithNonBlankValues(){
        assertDoesNotThrow(()->list.editList(0,"John", "Doe", "727-123-3213", "johndoe@gmail.com"));
    }

    @Test
    public void newListIsEmpty(){
        ContactList list = new ContactList();
        assertEquals(0,list.size());
    }

    @Test
    public void removingItemsDecreasesSize(){
        list.remove(0);
        assertEquals(0,list.size());
    }

    @Test
    public void removingItemsFailsWithInvalidIndex(){
        assertThrows(IndexOutOfBoundsException.class, ()-> list.remove(1));
    }

    @Test
    public void savedContactListCanBeLoaded(){
        list.save("test.txt");
        list.newContactList();
        list.load("test.txt");
        assertEquals(1,list.size());
    }
}
