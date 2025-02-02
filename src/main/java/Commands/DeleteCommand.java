package Commands;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;
import Task.*;

public class DeleteCommand extends Commands {
    private int i;

    public DeleteCommand(int i){
        this.i = i;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage){
        Task c = list.getTask(i);
        list.delete(i);
        ui.deleteTask(c, list);
    }

    @Override
    public boolean isExit(){
        return false;
    }

}
