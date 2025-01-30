import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task{
    protected  LocalDateTime from;
    protected LocalDateTime to;

    public Events(String des, LocalDateTime from, LocalDateTime to){
        super(des);
        this.from = from;
        this.to = to;
    }

    public String fromatToString(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return dateTime.format(formatter);

    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + fromatToString(from) + " to: " + fromatToString(to) + ")";
    }
}
