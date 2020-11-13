import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskItem {
    private String title;
    private String description;
    private String dueDate;

    public TaskItem(String title, String description, String dueDate){
        if(titleOne(title)){
            this.title = title;
        } else{
            throw new IllegalArgumentException("Title is not valid. Longer than one");
        }
        this.description = description;

        if(dateValid(dueDate)){
            this.dueDate = dueDate;
        } else {
            throw new DateTimeException("Date not valid");
        }
    }

    private boolean titleOne(String title){
        return title.length() > 0;
    }

    private boolean dateValid(String dueDate){
        try {
            DateTimeFormatter yearMonthDay = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(dueDate, yearMonthDay);
            return true;
        }catch(DateTimeException ex){}
        return false;
    }
}
