package Question_2;

import java.text.DecimalFormat;

public class Flat extends Property {
	private static final double MAINTENANCE_COSTS = 500;
	private int floor;

	public Flat(int houseNumber, String street,
			String city, String postCode,
			int numberOfRooms, int floor) {
		super(houseNumber, street, city, postCode, numberOfRooms);
		if (floor >= 0) {
			this.floor = floor;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public double getPrice() {
		double totalCost = 0.00;
		for (Room key : rooms.keySet()) {
		    totalCost = key.getPrice() * 12 * getNumberOfRooms();
		    break;
		}
		return totalCost + MAINTENANCE_COSTS;
	}

	@Override
	public boolean isAvailable() {
		return (rooms.size() == 0);
	}

	@Override
	public void occupy(Room r, ITenant t) {
		if (r != null && t != null) {
			if (isAvailable() && !rooms.containsKey(r)
					&& t.getType()
					== TenantType.PROFESSIONAL) {
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
		return super.toString() + " flat on " + floor
		+ " floor :" + getAvailableRooms() + " available)";
	}
}
