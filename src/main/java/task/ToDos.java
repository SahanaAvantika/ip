package task;

public class ToDos extends Task {

    public ToDos(String description){
        super(description);
    }

    @Override
    public ToDos clone() {
        return new ToDos(this.description);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
