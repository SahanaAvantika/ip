public class DeleteCommand extends Commands{
    int i;

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
