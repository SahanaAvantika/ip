package Task;
import Parser.Parser;

import java.time.LocalDateTime;

public class Events extends Task {
    protected  LocalDateTime from;
    protected LocalDateTime to;

    public Events(String des, LocalDateTime from, LocalDateTime to){
        super(des);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + Parser.fromatToString(from) + " to: "
                + Parser.fromatToString(to) + ")";
    }
}
