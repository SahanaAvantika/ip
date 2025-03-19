package Task;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import task.Deadlines;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlinesTest {

    @Test
    public void testMark() {
        Deadlines d = new Deadlines("Submit report", LocalDateTime.parse("2025-03-15T23:59:00"));
        d.markAsDone();
        assertEquals("[D][X] Submit report (by: Mar 15 2025 11:59 pm)", d.toString(),
                "toString() does not match expected format.");
    }
}
