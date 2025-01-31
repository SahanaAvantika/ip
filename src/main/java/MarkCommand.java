public class MarkCommand extends Commands {
    int i;

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
