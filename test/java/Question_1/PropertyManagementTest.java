package Question_1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class PropertyManagementTest {

    @Test
    public void testAddProperty() {
	PropertyManagement pm = new PropertyManagement();
	House house = new House(1, "Percy Road", "Guildford", "GU2 7XH", 2);

	pm.addProperty(house);

	assertEquals("1 Percy Road, Guildford GU2 7XH (2 bedroom house :2 available)\n", pm.displayProperties());

    }

    @Test
    public void testAddTenant() {
	PropertyManagement pm = new PropertyManagement();

	House house1 = new House(1, "Percy Road", "Guildford", "GU2 7XH", 2);
	House house2 = new House(1, "The Chase", "Guildford", "GU2 7UB", 5);

	Room room1 = new Room(900);

	Tenant tenant1 = new Tenant("Alice", "Wonderland", 18, TenantType.STUDENT);

	pm.addProperty(house1);
	pm.addProperty(house2);

	pm.addTenant(house1, room1, tenant1);

	assertEquals(
		"1 Percy Road, Guildford GU2 7XH (2 bedroom house :1 available)\n"
			+ "1 The Chase, Guildford GU2 7UB (5 bedroom house :5 available)\n" + "",
		pm.displayProperties());
    }

    @Test
    public void testRemoveInvalidProperty() {
	PropertyManagement pm = new PropertyManagement();

	House house1 = new House(1, "Percy Road", "Guildford", "GU2 7XH", 2);
	try {
	    pm.removeProperty(house1);
	    fail("Expected IllegalArgumentException not thrown");
	} catch (IllegalArgumentException e) {

	}
    }

    @Test
    public void testRemoveProperty() {
	PropertyManagement pm = new PropertyManagement();

	House house2 = new House(1, "The Chase", "Guildford", "GU2 7UB", 5);

	pm.addProperty(house2);

	pm.removeProperty(house2);

	assertEquals("", pm.displayProperties());
    }
}
