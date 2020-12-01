import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskItem {
    private String title;
    private String description;
    private String dueDate;
    private boolean completion;

    public TaskItem(String title,String description,String dueDate){
        if(checkTitle(title)) {
            throw new IllegalArgumentException("Invalid Title");
        }

        if(checkDueDate(dueDate)) {
            throw new IllegalArgumentException("Invalid Date");
        }

        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.completion = false;
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
        return title.length() == 0;
    }
    private boolean checkDueDate(String dueDate){
        try {
            DateTimeFormatter yearMonthDay = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(dueDate, yearMonthDay);
            return false;
        }catch(DateTimeException ex){}
        return true;
    }

    public void updateList(String title,String description,String dueDate){
        if(checkTitle(title)) {
            throw new IllegalArgumentException("Invalid Title");
        }

        if(checkDueDate(dueDate)) {
            throw new IllegalArgumentException("Invalid Date");
        }

        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.completion = false;
    }
}
