package Question_2;

import java.util.List;

import java.util.ArrayList;

/**
 * PropertyManagement --- program to store a list of houses and have
 * 						  various function to alter this
 * 						  list, such as adding and
 * 					      removing houses and tenants.
 * @author    James Cocker
 */
public class PropertyManagement {
	// Defines the list of houses.
	private List<Property> properties;

	/**
	 * Constructs a PropertyManagement class.
	 */
	public PropertyManagement() {
		properties = new ArrayList<Property>();
	}

	/**
	 * Add a property to the properties array list.
	 * @param p property to add to list.
	 */
	public void addProperty(Property p) {
		if (p != null) {
			properties.add(p);
		} else {
			throw new NullPointerException();
		}
	}

	/**
	 * Adds a tenant to a specified house in a specified room.
	 * @param p property to add tenant to.
	 * @param r room to add tenant to.
	 * @param t tenant to add.
	 */
	public void addTenant(Property p, Room r, ITenant t) {
		if (p != null) {
			p.occupy(r, t);
		} else {
			throw new NullPointerException();
		}
	}

	/**
	 * Displays properties in nicely formatted string.
	 * @return output text of properties.
	 */
	public String displayProperties() {
		String output = "";
		for (Property p: properties) {
			if (p.isAvailable()) {
				output += p.toString() + "\n";
			} else {
				output += p.displayOccupiedProperty() + "\n";
			}
		}
		return output;
	}

	/**
	 * Returns the number of properties that match the given string type.
	 * @param type of property we are attempting to match.
	 * @return count of the number of matching property types.
	 */
	public int numberOfPropertyType(String type) {
		int count = 0;
		for (Property p: properties) {
			if (type.toUpperCase().matches(p.getClass()
				.getSimpleName().toUpperCase())) {
				count++;
			}
		}
		return count;
	}

	/**
	 * This method returns the percentage of properties that are exempt
	 * (i.e. only students live in the property) from council tax. It
	 * ensures that if its a house, its full, or if its a flat it has at
	 * least one tenant.
	 * @return percentage of properties exempt from tax.
	 */
	public double percentageCouncilTaxExemption() {
		double onlyStudents = 0.0;
		boolean professional = false;
		for (Property p: properties) {
			if ((p.rooms.size() == p.getNumberOfRooms()
			&& (p.getClass().getSimpleName().matches("House")
			|| p.getClass().getSimpleName().matches("Property")))
			|| (p.rooms.size() > 0
			&& p.getClass().getSimpleName().matches("Flat"))) {

				professional = false;
				for (Room key : p.rooms.keySet()) {
				    if (p.rooms.get(key).getType() == TenantType.PROFESSIONAL) {
				    	professional = true;
				    }
				}
				if (!professional) {
					onlyStudents++;
				}
			}
		}
		return ((onlyStudents/properties.size()) * 100.0);
	}

	/**
	 * Removes property specified.
	 * @param p property to be deleted.
	 */
	public void removeProperty(Property p) {
		if (properties.contains(p)) {
			properties.remove(p);
		} else {
			throw new IllegalArgumentException();
		}
	}
}
