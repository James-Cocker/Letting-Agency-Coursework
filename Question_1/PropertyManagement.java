package Question_1;

import java.util.List;
import java.util.ArrayList;

/**
 * PropertyManagement --- program to store a list of
 * 						  houses and have various
 * 						  function to alter this list,
 * 						  such as adding and removing
 * 						  houses and tenants.
 * @author    James Cocker
 */
public class PropertyManagement {
	// Defines the list of houses.
	private List<House> properties;

	/**
	 * Constructs a PropertyManagement class.
	 */
	public PropertyManagement() {
		properties = new ArrayList<House>();
	}

	/**
	 * Add a property to the properties array list.
	 * @param p property to add to list.
	 */
	public void addProperty(House p) {
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
	public void addTenant(House p, Room r, ITenant t) {
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
		for (House h: properties) {
			output += h.toString() + "\n";
		}
		return output;
	}

	/**
	 * Removes property specified.
	 * @param p property to be deleted.
	 */
	public void removeProperty(House p) {
		if (properties.contains(p)) {
			properties.remove(p);
		} else {
			throw new IllegalArgumentException();
		}
	}
}
