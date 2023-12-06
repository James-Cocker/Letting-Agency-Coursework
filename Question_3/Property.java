package Question_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Property {
	private int houseNumber;
	private String street;
	private String city;
	private String postCode;
	private int numberOfRooms;
	protected Map<Room, ITenant> rooms;
	protected double councilTax;
	private List<Complaint> complaints;

	public Property(int houseNumber, String street,
			String city, String postCode, int numberOfRooms) {
		if (street != null && city != null
				&& postCode != null) {
			if (houseNumber > 0
					&& numberOfRooms > 0
					&& validateCity(city)
					&& validatePostCode(postCode)) {
				this.houseNumber = houseNumber;
				this.street = street;
				this.city = city;
				this.postCode = postCode;
				this.numberOfRooms = numberOfRooms;
				this.rooms = new HashMap<Room, ITenant>();
				this.complaints = new ArrayList<Complaint>();
			} else {
				throw new IllegalArgumentException();
			}
		} else {
			throw new NullPointerException();
		}
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getPostCode() {
		return postCode;
	}

	public int getAvailableRooms() {
		return numberOfRooms - rooms.size();
	}

	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	public double getCouncilTax() {
		return councilTax;
	}

	public List<Complaint> getComplaints() {
		return complaints;
	}

	public Map<Room, ITenant> getRooms() {
		return rooms;
	}

	public void addComplaint(Complaint c) {
		if (c != null) {
			complaints.add(c);
		} else {
			throw new NullPointerException();
		}
	}

	public int calculateImpact() {
		int impact = 0;
		for (Complaint c : complaints) {
			impact += c.getSeverity().getWeight();
		}
		return impact;
	}

	public void setCouncilTax(double tax) {
		if (tax >= 0.0) {
			if (rooms.size() == numberOfRooms
			&& (this.getClass().getSimpleName().matches("House")
			|| this.getClass().getSimpleName().matches("Property"))) {
				int numOfProfessionals = 0;

				for (Room key : rooms.keySet()) {
				    if (rooms.get(key).getType()
				    	== TenantType.PROFESSIONAL) {
				    	numOfProfessionals++;
				    }
				}

				if (numOfProfessionals > 1) {
					this.councilTax = tax;
				} else if (numOfProfessionals == 1) {
					this.councilTax = tax * 0.75;
				} else {
					this.councilTax = 0.0;
				}
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	public String toString() {
		return houseNumber + " " + street
		+ ", " + city + " " + postCode
		+ " (" + numberOfRooms + " bedroom";
	}

	private boolean validateCity(String input) {
		return input.matches("Guildford");
	}

	private boolean validatePostCode(String input) {
		return input.matches("[A-Z]{2}[0-9][ ][0-9][A-Z]{2}|"
		+ "[A-Z]{2}[0-9]{2}[A-Z]{2}");
	}

	public abstract double getPrice();
	public abstract boolean isAvailable();
	public abstract void occupy(Room r, ITenant t);
	public abstract String displayOccupiedProperty();
}
