package Question_1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RoomTest {

    @Test
    public void testExpensivePrice() {
	Room room = new Room(1200);
	assertEquals(1200, room.getPrice(), 0);
    }

}
