package Question_1;

import java.util.HashMap;
import java.util.Map;

public class House {
	private int houseNumber;
	private String street;
	private String city;
	private String postCode;
	private int numberOfRooms;
	private Map<Room, ITenant> rooms;

	public House(int houseNumber, String street,
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
			} else {
				throw new IllegalArgumentException();
			}
		} else {
			throw new NullPointerException();
		}
	}

	public int getAvailableRooms() {
		return numberOfRooms - rooms.size();
	}

	public double getPrice() {
		double totalCost = 0.0;
		for (Room key : rooms.keySet()) {
		    totalCost += key.getPrice();
		}
		return totalCost;
	}

	public boolean isAvailable() {
		return (numberOfRooms > rooms.size());
	}

	public void occupy(Room r, ITenant t) {
		if (r != null && t != null) {
			if (isAvailable() && !rooms.containsKey(r)) {
				rooms.put(r, t);
			} else {
				throw new IllegalArgumentException();
			}
		} else {
			throw new NullPointerException();
		}
	}

	public String toString() {
		return houseNumber + " " + street + ", "
				+ city + " " + postCode + " ("
				+ numberOfRooms + " bedroom house :"
				+ getAvailableRooms() + " available)";
	}

	private boolean validateCity(String input) {
		return input.matches("Guildford");
	}

	private boolean validatePostCode(String input) {
		return input.matches("[A-Z]{2}[0-9][ ][0-9]"
				+ "[A-Z]{2}|[A-Z]{2}[0-9]{2}[A-Z]{2}");
	}
}
