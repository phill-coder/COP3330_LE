import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TaskItemTest {

    @Test
    public void constructorFailsWithInvalidDueDate() {
        assertThrows(IllegalArgumentException.class, ()-> new TaskItem("Hello", "Description", "20-12-12"));

    }
    @Test
    public void constructorFailsWithInvalidTitle() {
        assertThrows(IllegalArgumentException.class, ()-> new TaskItem("", "Description", "2012-12-12"));

    }
    @Test
    public void constructorSucceedsWithValidDueDate() {
        assertDoesNotThrow(()-> new TaskItem("Hello", "Description", "2012-12-12"));

    }
    @Test
    public void constructorSucceedsWithValidTitle() {
        assertDoesNotThrow(()-> new TaskItem("Hello", "Description", "2012-12-12"));
    }


    @Test
    public void editingDueDateFailsWithInvalidDateFormat() {
        TaskItem item = new TaskItem("Hello", "Description", "2012-12-12");

        assertThrows(IllegalArgumentException.class, ()-> item.updateList(item.getTitle(),item.getDescription(),""));
        assertEquals("Hello",item.getTitle());
        assertEquals("Description",item.getDescription());
        assertEquals("2012-12-12",item.getDueDate());

    }
    @Test
    public void editingDueDateFailsWithInvalidYYYMMDD() {
        TaskItem item = new TaskItem("Hello", "Description", "2012-12-12");

        assertThrows(IllegalArgumentException.class, ()-> item.updateList(item.getTitle(),item.getDescription(),"1212-13-20"));
        assertEquals("Hello",item.getTitle());
        assertEquals("Description",item.getDescription());
        assertEquals("2012-12-12",item.getDueDate());

    }

    @Test
    public void editingDueDateSucceedsWithExpectedValue() {
        TaskItem item = new TaskItem("Hello", "Description", "2012-12-12");

        assertDoesNotThrow(()-> item.updateList(item.getTitle(),item.getDescription(),"2020-12-20"));
        assertEquals("Hello",item.getTitle());
        assertEquals("Description",item.getDescription());
        assertEquals("2020-12-20",item.getDueDate());

    }
    @Test
    public void editingTitleFailsWithEmptyString() {
        TaskItem item = new TaskItem("Hello", "Description", "2012-12-12");

        assertThrows(IllegalArgumentException.class, ()-> item.updateList("",item.getDescription(),item.getDueDate()));
        assertEquals("Hello",item.getTitle());
        assertEquals("Description",item.getDescription());
        assertEquals("2012-12-12",item.getDueDate());

    }
    @Test
    public void editingTitleSucceedsWithExpectedValue(){
        TaskItem item = new TaskItem("Hello", "Description", "2012-12-12");

        assertDoesNotThrow(()-> item.updateList("Title",item.getDescription(),item.getDueDate()));
        assertEquals("Title",item.getTitle());
        assertEquals("Description",item.getDescription());
        assertEquals("2012-12-12",item.getDueDate());

    }
}
