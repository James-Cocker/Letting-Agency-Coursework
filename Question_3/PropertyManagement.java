package Question_3;

import java.util.List;
import java.util.Map;

import java.util.ArrayList;

/**
 * PropertyManagement --- program to store a list of houses and have
 * 						  various function to alter
 * 						  this list, such as adding
 *  					  and removing houses and
 *  					  tenants.
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

	public String displayInfographics() {
		int[] ages = {0, 0, 0, 0, 0};
		int professionals = 0;
		int students = 0;
		int tAge = 0;

		for (Property p : properties) {
			for (Map.Entry<Room, ITenant>
			entry : p.rooms.entrySet()) {
	            ITenant t = entry.getValue();
	            if (t.getType() == TenantType.PROFESSIONAL) {
	            	professionals++;
	            } else {
	            	students++;
	            }

	            tAge = t.getAge();
	            if (tAge <= 25) {
	            	ages[0]++;
	            } else if (tAge <= 35) {
	            	ages[1]++;
	            } else if (tAge <= 49) {
	            	ages[2]++;
	            } else if (tAge <= 60) {
	            	ages[3]++;
	            } else {
	            	ages[4]++;
	            }
	        }
		}
		return "Confirmed Tenant Summary:\n17-25:"
		+ ages[0] + "\n26-35:" + ages[1] + "\n36-49:"
		+ ages[2] + "\n50-60:" + ages[3] + "\n60+:"
		+ ages[4] + "\n*****\nProfessional:"
		+ professionals + "\nStudent:" + students;
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
				output +=
				p.displayOccupiedProperty()
				+ "\n";
			}
		}
		return output;
	}

	/**
	 * Finds the most problematic property.
	 * @return outputText the most problematic
	 * property's toString() method (address
	 * and availability).
	 */
	public String findProblematicProperty() {
		// The highest severity problem type in
		// all the properties. 3 highest, 1 lowest.
		int highestImpact = 0;

		// Same as above, but for the current
		// property in the loop.
		int currentImpact = 0;

		String outputText = "";

		for (Property p : properties) {
			currentImpact = p.calculateImpact();
			// If the highest problem severity is
			// greater than the greatest of the
			// previously severe property, or it
			// has the same severity but more
			// cases, then set it to the new
			// highest problem type.
			if (highestImpact < currentImpact) {
				highestImpact = currentImpact;
				outputText = p.toString();
			}
		}
		return outputText;
	}

	/**
	 * Returns the number of properties that
	 * match the given string type.
	 * @param type of property we are attempting
	 * to match.
	 * @return count of the number of matching
	 * property types.
	 */
	public int numberOfPropertyType(String type) {
		int count = 0;
		for (Property p: properties) {
			if (type.toUpperCase().matches(
			p.getClass().getSimpleName()
			.toUpperCase())) {
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
