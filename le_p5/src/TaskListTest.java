import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    private TaskList itemList;
    @BeforeEach
    public void initArray(){
        itemList = new TaskList();

    }


    @Test
    public void addingTaskItemsIncreasesSize(){
        TaskItem item = new TaskItem("Title new", "Description new", "2000-12-12");
        itemList.storeItem(item);
        assertEquals(1,itemList.size());
    }


    @Test
    public void editingItemDescriptionSucceedsWithExpectedValue(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);

        itemList.editList(0, item.getTitle(), "new", item.getDueDate());
        assertEquals("new", item.getDescription());
    }

    @Test
    public void editingItemDescriptionFailsWithInvalidIndex(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);

        assertThrows(IndexOutOfBoundsException.class,()->itemList.editList(1, item.getTitle(), "new", item.getDueDate()));
        assertEquals("Description", item.getDescription());
    }

    @Test
    public void editingItemDueDateSucceedsWithExpectedValue(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);

        assertDoesNotThrow(()->itemList.editList(0, item.getTitle(), item.getDescription(), "2000-10-12"));
        assertEquals("2000-10-12", item.getDueDate());
    }


    @Test
    public void editingItemTitleSucceedsWithExpectedValue(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);


        assertDoesNotThrow(()->itemList.editList(0, "NEW TITLE", item.getDescription(), "2000-10-12"));
        assertEquals("NEW TITLE", item.getTitle());
    }
    @Test
    public void editingItemTitleFailsWithEmptyString(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);

        assertThrows(IllegalArgumentException.class,()->itemList.editList(0,"", item.getDescription(), item.getDueDate()));
        assertEquals("Title", item.getTitle());
    }

    @Test
    public void editingItemTitleFailsWithInvalidIndex(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);

        assertThrows(IndexOutOfBoundsException.class,()->itemList.editList(1,"NewTitle", item.getDescription(), item.getDueDate()));
        assertEquals("Title", item.getTitle());
    }

    @Test
    public void editingTaskItemDueDateFailsWithInvalidDateFormat(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);

        assertThrows(IllegalArgumentException.class,()->itemList.editList(0,item.getTitle(), item.getDescription(), "12-10-12"));
        assertEquals("2012-12-12", item.getDueDate());
    }

    @Test
    public void editingTaskItemDueDateFailsWithInvalidIndex(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);

        assertThrows(IndexOutOfBoundsException.class,()->itemList.editList(1,item.getTitle(), item.getDescription(), "12-10-12"));
        assertEquals("2012-12-12", item.getDueDate());
    }

    @Test
    public void editingTaskItemDueDateFailsWithInvalidYYYMMDD(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);

        assertThrows(IllegalArgumentException.class,()->itemList.editList(0,item.getTitle(), item.getDescription(), "1212-30-12"));
        assertEquals("2012-12-12", item.getDueDate());
    }


    @Test
    public void gettingItemDescriptionFailsWithInvalidIndex(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);

        assertThrows(IndexOutOfBoundsException.class, ()-> itemList.getDescription(1) );
    }
    @Test
    public void gettingItemDescriptionSucceedsWithValidIndex(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);

        assertDoesNotThrow(()-> itemList.getDescription(0));
    }
    @Test
    public void gettingItemDueDateFailsWithInvalidIndex(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);

        assertThrows(IndexOutOfBoundsException.class, ()-> itemList.getDueDate(1) );
    }
    @Test
    public void gettingItemDueDateSucceedsWithValidIndex(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);

        assertDoesNotThrow(()-> itemList.getDueDate(0));
    }
    @Test
    public void gettingItemTitleFailsWithInvalidIndex(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);

        assertThrows(IndexOutOfBoundsException.class, ()-> itemList.getTitle(1) );
    }
    @Test
    public void gettingItemTitleSucceedsWithValidIndex(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);

        assertDoesNotThrow(()-> itemList.getTitle(0));
    }


    @Test
    public void newTaskListIsEmpty(){
        assertEquals(false, itemList.isNotEmpty());
    }

    @Test
    public void removingTaskItemsDecreasesSize() {
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);

        itemList.remove(0);
        assertEquals(0, itemList.size());
    }
    @Test
    public void removingTaskItemsFailsWithInvalidIndex() {
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);

        assertThrows(IndexOutOfBoundsException.class, ()-> itemList.remove(1) );
    }


    @Test
    public void completingTaskItemChangesStatus(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);

        itemList.markComplete(0);
        assertEquals(true, itemList.getCompletion(0));
    }
    @Test
    public void completingTaskItemFailsWithInvalidIndex(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);

        assertThrows(IndexOutOfBoundsException.class, ()-> itemList.markComplete(1));
    }
    @Test
    public void uncompletingTaskItemChangesStatus(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);
        itemList.markComplete(0);

        itemList.unMarkComplete(0);
        assertEquals(false, itemList.getCompletion(0));
    }
    @Test
    public void uncompletingTaskItemFailsWithInvalidIndex(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);
        itemList.markComplete(0);

        assertThrows(IndexOutOfBoundsException.class, ()-> itemList.unMarkComplete(1));
    }

    @Test
    public void loadTaskList() throws Exception {
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);
        TaskItem items = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(items);
        itemList.save("test.txt");

        itemList.newTaskList();
        itemList.load("test.txt");
        assertEquals(2, itemList.size());
    }

}
