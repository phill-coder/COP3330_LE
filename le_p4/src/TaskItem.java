public class TaskItem {
    private String title;
    private String description;
    private String dueDate;

    public TaskItem(String tile, String description, String dueDate){
        if(titleOne(title)){
            this.title = title;
        } else{
            throw new IllegalArgumentException("Title is not valid. Longer than one");
        }
    }

    private boolean titleOne(String title){
        return title.length() > 0;
    }
}
