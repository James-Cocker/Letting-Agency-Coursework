package Question_3;

import java.text.DecimalFormat;

public class House extends Property {

	public House(int houseNumber, String street,
			String city, String postCode, int numberOfRooms) {
		super(houseNumber, street, city, postCode, numberOfRooms);
	}

	@Override
	public double getPrice() {
		double totalCost = 0.00;
		for (Room key : rooms.keySet()) {
		    totalCost += key.getPrice();
		}
		return totalCost * 12;
	}

	@Override
	public boolean isAvailable() {
		return (getNumberOfRooms() > rooms.size());
	}

	@Override
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

	@Override
	public String displayOccupiedProperty() {
		final DecimalFormat df = new DecimalFormat("0.00");
		StringBuilder sb = new StringBuilder();
		sb.append(toString() + "\n");
		for (Room key : rooms.keySet()) {
		    sb.append("\tRoom: " + key.getPrice() + "\n");
		}
		sb.append("\tTotal: £" + df.format(getPrice())
		+ " (Council Tax: £" + councilTax + ")");
		return sb.toString();
	}

	@Override
	public String toString() {
		return super.toString() + " house :"
		+ super.getAvailableRooms() + " available)";
	}
}
