import java.util.ArrayList;
import java.util.List;

public class TaskList {
   List<TaskItem> itemList;

   public TaskList(){
       itemList = new ArrayList<>();
   }


    public void storeItem(TaskItem itemInfo){
       itemList.add(itemInfo);

    }
}
