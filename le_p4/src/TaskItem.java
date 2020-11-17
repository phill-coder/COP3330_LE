import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskItem {

    private String title;
    private String description;
    private String dueDate;
    private boolean completion = false;

    public TaskItem(String title,String description,String dueDate){
        if(checkTitle(title)) {
            this.title = title;
        } else{
            throw new IllegalArgumentException("Invalid Title");
        }

        this.description = description;

        if(checkDueDate(dueDate)) {
            this.dueDate = dueDate;
        } else{
            throw new DateTimeException("Invalid Date");
        }
    }

    public void setTitle(String title){
        if(checkTitle(title)) {
            this.title = title;
        } else{
            throw new IllegalArgumentException("Invalid Title");
        }
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setDueDate(String dueDate){
        if(checkDueDate(dueDate)) {
            this.dueDate = dueDate;
        } else{
            throw new DateTimeException("Invalid Date");
        }
    }
    public void setCompletionTrue(){
        this.completion = true;
    }
    public void setCompletionFalse(){
        this.completion = false;
    }


    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public String getDueDate(){
        return dueDate;
    }
    public boolean getCompletion(){
        return completion;
    }

    public boolean checkTitle(String title){
        return title.length() > 0;
    }
    private boolean checkDueDate(String dueDate){
        try {
            DateTimeFormatter yearMonthDay = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(dueDate, yearMonthDay);
            return true;
        }catch(DateTimeException ex){}
        return false;
    }
}
