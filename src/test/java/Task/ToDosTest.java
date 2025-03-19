package Task;

import org.junit.jupiter.api.Test;
import task.ToDos;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {

    @Test
    public void testToString() {
        ToDos todo = new ToDos("Read a book");
        assertEquals("[T][ ] Read a book", todo.toString(), "toString() does not match expected format.");
    }

}
