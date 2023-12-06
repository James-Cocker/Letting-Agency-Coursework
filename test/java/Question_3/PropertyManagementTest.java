package Question_3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PropertyManagementTest {

    @Test
    public void testFindProblematicProperty() {
	PropertyManagement pm = new PropertyManagement();
	Property house = new House(1, "Percy Road", "Guildford", "GU2 7XH", 2);
	house.addComplaint(new Complaint("Broken Window", Severity.MEDIUM));
	house.addComplaint(new Complaint("Missing front door", Severity.HIGH));

	pm.addProperty(house);

	Property flat = new Flat(1, "The Chase", "Guildford", "GU2 7UB", 5, 2);

	pm.addProperty(flat);

	pm.removeProperty(flat);

	assertEquals(house.toString(), pm.findProblematicProperty());
    }

    @Test
    public void testDisplayInfographics() {
	PropertyManagement pm = new PropertyManagement();
	Property house = new House(1, "Percy Road", "Guildford", "GU2 7XH", 2);

	Room room1 = new Room(900);
	Room room2 = new Room(1000);

	Tenant tenant1 = new Tenant("Alice", "Wonderland", 18, TenantType.STUDENT);
	Tenant tenant2 = new Tenant("Cheshire", "Cat", 20, TenantType.STUDENT);

	pm.addProperty(house);
	pm.addTenant(house, room1, tenant1);
	pm.addTenant(house, room2, tenant2);

	Property flat = new Flat(1, "The Chase", "Guildford", "GU2 7UB", 2, 2);

	Room room3 = new Room(1000);

	Tenant tenant3 = new Tenant("Humpty", "Dumpty", 52, TenantType.STUDENT);

	pm.addProperty(flat);
	pm.addTenant(flat, room3, tenant3);

	assertEquals(
		"Confirmed Tenant Summary:\n17-25:2\n26-35:0\n36-49:0\n50-60:0\n60+:0\n*****\nProfessional:0\nStudent:2",
		pm.displayInfographics());
    }

}
