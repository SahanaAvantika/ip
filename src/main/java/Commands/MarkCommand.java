package Commands;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class MarkCommand extends Commands {
    private int i;

    public MarkCommand(int i){
        this.i = i;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage){
        list.mark(i);
        ui.markDone(list, i);
    }

    @Override
    public boolean isExit(){
        return false;
    }

}
