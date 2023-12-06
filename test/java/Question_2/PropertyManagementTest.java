package Question_2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class PropertyManagementTest {

    @Test
    public void testPropertyManagement() {
	PropertyManagement pm = new PropertyManagement();
	Property house = new House(1, "Percy Road", "Guildford", "GU2 7XH", 2);

	pm.addProperty(house);

	Property flat = new Flat(1, "The Chase", "Guildford", "GU2 7UB", 5, 2);

	pm.addProperty(flat);

	pm.removeProperty(flat);

	assertEquals("1 Percy Road, Guildford GU2 7XH (2 bedroom house :2 available)\n", pm.displayProperties());

    }

    @Test
    public void testAddTenant() {
	PropertyManagement pm = new PropertyManagement();

	Property house1 = new House(1, "Percy Road", "Guildford", "GU2 7XH", 2);
	Property house2 = new House(1, "The Chase", "Guildford", "GU2 7UB", 5);

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
    public void testNumberOfPropertyType() {
	PropertyManagement pm = new PropertyManagement();
	Property house = new House(1, "Percy Road", "Guildford", "GU2 7XH", 2);

	pm.addProperty(house);

	Property flat = new Flat(1, "The Chase", "Guildford", "GU2 7UB", 5, 2);

	pm.addProperty(flat);

	pm.removeProperty(flat);

	assertEquals(1, pm.numberOfPropertyType("house"));
    }

    @Test
    public void testMixCouncilTax() {
	PropertyManagement pm = new PropertyManagement();

	Room room1 = new Room(800);
	Room room2 = new Room(900);

	Tenant tenant1 = new Tenant("Alice", "Wonderland", 18, TenantType.STUDENT);
	Tenant tenant2 = new Tenant("Bob", "Ross", 53, TenantType.PROFESSIONAL);

	Property house = new House(10, "The Chase", "Guildford", "GU2 7UB", 2);

	pm.addProperty(house);

	pm.addTenant(house, room1, tenant1);
	pm.addTenant(house, room2, tenant2);

	house.setCouncilTax(1500);

	String output[] = {
		"10 The Chase, Guildford GU2 7UB (2 bedroom house :0 available)\n\tRoom: 900.0\n\tRoom: 800.0\n	Total: £20400.00 (Council Tax: £1125.0)\n",
		"10 The Chase, Guildford GU2 7UB (2 bedroom house :0 available)\n\tRoom: 800.0\n\tRoom: 900.0\n	Total: £20400.00 (Council Tax: £1125.0)\n" };
	List<String> expectedTitlesList = Arrays.asList(output);
	assertTrue(expectedTitlesList.contains((pm.displayProperties())));
    }

    @Test
    public void testNoCouncilTax() {
	PropertyManagement pm = new PropertyManagement();

	Property house2 = new House(1, "Percy Road", "Guildford", "GU2 7XH", 2);
	Room room3 = new Room(900);
	Room room4 = new Room(950);

	Tenant tenant3 = new Tenant("Amaan", "Khalid", 19, TenantType.STUDENT);
	Tenant tenant4 = new Tenant("Joe", "Bloggs", 18, TenantType.STUDENT);
	pm.addProperty(house2);

	pm.addTenant(house2, room3, tenant3);
	pm.addTenant(house2, room4, tenant4);

	house2.setCouncilTax(1600);

	String output[] = {
		"1 Percy Road, Guildford GU2 7XH (2 bedroom house :0 available)\n\tRoom: 950.0\n\tRoom: 900.0\n\tTotal: £22200.00 (Council Tax: £0.0)\n",
		"1 Percy Road, Guildford GU2 7XH (2 bedroom house :0 available)\n\tRoom: 900.0\n\tRoom: 950.0\n\tTotal: £22200.00 (Council Tax: £0.0)\n" };
	List<String> expectedTitlesList = Arrays.asList(output);
	assertTrue(expectedTitlesList.contains((pm.displayProperties())));
    }

    @Test
    public void testMissingCouncilTax() {
	PropertyManagement pm = new PropertyManagement();
	Property house = new House(10, "The Chase", "Guildford", "GU2 7UB", 2);

	Property house2 = new House(1, "Percy Road", "Guildford", "GU2 7XH", 2);
	Room room3 = new Room(900);
	Room room4 = new Room(950);

	Tenant tenant3 = new Tenant("Amaan", "Khalid", 19, TenantType.STUDENT);
	Tenant tenant4 = new Tenant("Joe", "Bloggs", 18, TenantType.STUDENT);
	pm.addProperty(house2);
	pm.addProperty(house);

	pm.addTenant(house2, room3, tenant3);
	pm.addTenant(house2, room4, tenant4);

	house.setCouncilTax(2000);
	house2.setCouncilTax(1600);

	String output[] = {
		"10 The Chase, Guildford GU2 7UB (2 bedroom house :2 available)\n1 Percy Road, Guildford GU2 7XH (2 bedroom house :0 available)\n\tRoom: 950.0\n\tRoom: 900.0\n\tTotal: £22200.00 (Council Tax: £0.0)\n",
		"10 The Chase, Guildford GU2 7UB (2 bedroom house :2 available)\n1 Percy Road, Guildford GU2 7XH (2 bedroom house :0 available)\n\tRoom: 900.0\n\tRoom: 950.0\n\tTotal: £22200.00 (Council Tax: £0.0)\n",
		"1 Percy Road, Guildford GU2 7XH (2 bedroom house :0 available)\n\tRoom: 950.0\n\tRoom: 900.0\n\tTotal: £22200.00 (Council Tax: £0.0)\n10 The Chase, Guildford GU2 7UB (2 bedroom house :2 available)\n",
		"1 Percy Road, Guildford GU2 7XH (2 bedroom house :0 available)\n\tRoom: 900.0\n\tRoom: 950.0\n\tTotal: £22200.00 (Council Tax: £0.0)\n10 The Chase, Guildford GU2 7UB (2 bedroom house :2 available)\n" };
	List<String> expectedTitlesList = Arrays.asList(output);
	assertTrue(expectedTitlesList.contains((pm.displayProperties())));
	assertEquals(50.0, pm.percentageCouncilTaxExemption(), 0);
    }

}
