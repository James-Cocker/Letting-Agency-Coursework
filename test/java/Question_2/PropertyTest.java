package Question_2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class PropertyTest {

    @Test
    public void testFlat() {
	Property flat = new Flat(1, "Percy Road", "Guildford", "GU2 7XH", 2, 1);
	assertEquals("1 Percy Road, Guildford GU2 7XH (2 bedroom flat on 1 floor :2 available)", flat.toString());

    }

    @Test
    public void testHouse() {
	Property house = new House(1, "Percy Road", "Guildford", "GU2 7XH", 3);
	assertEquals("1 Percy Road, Guildford GU2 7XH (3 bedroom house :3 available)", house.toString());

    }

    @Test
    public void testFlatGetAvailableRooms() {
	Property flat = new Flat(1, "Percy Road", "Guildford", "GU2 7XH", 2, 1);
	assertEquals(2, flat.getAvailableRooms());
    }

    @Test
    public void testFlatCouncilTax() {
	Room room1 = new Room(950);

	Tenant tenant = new Tenant("Bob", "Ross", 53, TenantType.PROFESSIONAL);

	Property flat = new Flat(10, "Percy Road", "Guildford", "GU2 7XH", 1, 2);

	flat.occupy(room1, tenant);

	flat.setCouncilTax(1000);
	assertEquals("10 Percy Road, Guildford GU2 7XH (1 bedroom flat on 2 floor :0 available)", flat.toString());

    }

    @Test
    public void testFlatIsAvailable() {
	Property flat = new Flat(1, "Percy Road", "Guildford", "GU2 7XH", 2, 1);
	Tenant tenant = new Tenant("Humpty", "Dumpty", 52, TenantType.PROFESSIONAL);

	Room room = new Room(1000);
	flat.occupy(room, tenant);

	assertFalse(flat.isAvailable());
    }
}
