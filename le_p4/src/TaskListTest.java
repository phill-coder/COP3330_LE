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
        assertEquals(1,itemList.sizeOfList());
    }

    @Test
    public void editingTaskItemChangesStatus(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);

        itemList.editTaskItemDescription("new", 0);
        itemList.editTaskItemTitle("new title", 0);
        itemList.editTaskItemDueDate("1212-12-12", 0);

    }

    @Test
    public void editingTaskItemDescriptionChangesValue(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);

        itemList.editTaskItemDescription("new", 0);
        assertEquals("new", item.getDescription());
    }
    @Test
    public void editingTaskItemDescriptionFailsWithInvalidIndex(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);

        assertThrows(IndexOutOfBoundsException.class,() ->itemList.editTaskItemDescription("new", 1));

    }
    @Test
    public void editingTaskItemDueDateChangesValue(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);


        itemList.editTaskItemDueDate("1212-12-12", 0);
        assertEquals("1212-12-12", item.getDueDate());

    }
    @Test
    public void editingTaskItemDueDateFailsWithInvalidIndex(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);

        assertThrows(IndexOutOfBoundsException.class,() ->itemList.editTaskItemDueDate("1212-12-12", 1));

    }
    @Test
    public void editingTaskItemTitleChangesValue(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);


        itemList.editTaskItemTitle("new title", 0);
        assertEquals("new title", item.getTitle());
    }
    @Test
    public void editingTaskItemTitleFailsWithInvalidIndex(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);

        assertThrows(IndexOutOfBoundsException.class,() ->itemList.editTaskItemDueDate("new title", 1));

    }

    @Test
    public void gettingTaskItemDescriptionFailsWithInvalidIndex(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);

        assertThrows(IndexOutOfBoundsException.class, ()-> itemList.getDescription(1) );
    }
    @Test
    public void gettingTaskItemDescriptionSucceedsWithValidIndex(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);

        assertDoesNotThrow(()-> itemList.getDescription(0));
    }
    @Test
    public void gettingTaskItemDueDateFailsWithInvalidIndex(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);

        assertThrows(IndexOutOfBoundsException.class, ()-> itemList.getDueDate(1) );
    }
    @Test
    public void gettingTaskItemDueDateSucceedsWithValidIndex(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);

        assertDoesNotThrow(()-> itemList.getDueDate(0));
    }
    @Test
    public void gettingTaskItemTitleFailsWithInvalidIndex(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);

        assertThrows(IndexOutOfBoundsException.class, ()-> itemList.getTitle(1) );
    }
    @Test
    public void gettingTaskItemTitleSucceedsWithValidIndex(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);

        assertDoesNotThrow(()-> itemList.getTitle(0));
    }
    @Test
    public void newTaskListIsEmpty(){
        assertEquals(true, itemList.isEmpty());
    }
    @Test
    public void removingTaskItemsDecreasesSize() {
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);

        itemList.removeTask(0);
        assertEquals(0, itemList.sizeOfList());
    }
    @Test
    public void removingTaskItemsFailsWithInvalidIndex() {
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);

        assertThrows(IndexOutOfBoundsException.class, ()-> itemList.removeTask(1) );
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
        itemList.save("test.txt");
        itemList.newTaskList();
        itemList.readFile("test.txt");
        assertEquals(1, itemList.sizeOfList());
    }

    @Test
    public void newTaskListEmpty(){
        TaskItem item = new TaskItem("Title", "Description", "2012-12-12");
        itemList.storeItem(item);

        itemList.newTaskList();
        assertEquals(0,itemList.sizeOfList());
    }

}
