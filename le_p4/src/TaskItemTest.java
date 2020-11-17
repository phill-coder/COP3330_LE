import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskItemTest {

    @Test
    public void creatingTaskItemFailsWithInvalidDueDate() {
           assertThrows(DateTimeException.class, ()-> new TaskItem("Hello", "Description", "20-12-12"));

    }
    @Test
    public void creatingTaskItemFailsWithInvalidTitle() {
        assertThrows(IllegalArgumentException.class, ()-> new TaskItem("", "Description", "2012-12-12"));

    }
    @Test
    public void creatingTaskItemSucceedsWithValidDueDate() {
        assertDoesNotThrow(()-> new TaskItem("Hello", "Description", "2012-12-12"));

    }
    @Test
    public void creatingTaskItemSucceedsWithValidTitle() {
        assertDoesNotThrow(()-> new TaskItem("Hello", "Description", "2012-12-12"));
    }


    @Test
    public void settingTaskItemFailsWithInvalidDueDate() {
        TaskItem item = new TaskItem("Hello", "Description", "2012-12-12");
        assertThrows(DateTimeException.class, ()-> item.setDueDate("12-12-12") );

    }
    @Test
    public void settingTaskItemFailsWithInvalidTitle() {
        TaskItem item = new TaskItem("Hello", "Description", "2012-12-12");
        assertThrows(IllegalArgumentException.class, ()-> item.setTitle("") );
    }
    @Test
    public void settingTaskItemSucceedsWithValidDueDate() {
        TaskItem item = new TaskItem("Hello", "Description", "2012-12-12");
        assertDoesNotThrow(()-> item.setDueDate("2012-12-12"));

    }
    @Test
    public void settingTaskItemSucceedsWithValidTitle() {
        TaskItem item = new TaskItem("Hello", "Description", "2012-12-12");
        assertDoesNotThrow(()-> item.setTitle("Title"));
    }

}
