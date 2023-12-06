package Question_3;

public class Tenant implements ITenant {
	private String forename;
	private String surname;
	private int age;
	private TenantType type;

	public Tenant(String forename, String surname,
		int age, TenantType type) {
		if (forename == null
				|| surname == null
				|| type == null) {
			throw new NullPointerException();
		} else {
			if (age <= 16) {
				throw new IllegalArgumentException("Tenant "
				+ "cannot be 16 or under");
			} else {
				this.forename = forename;
				this.surname = surname;
				this.age = age;
				this.type = type;
			}
		}
	}

	@Override
	public int getAge() {
		return age;
	}

	@Override
	public TenantType getType() {
		return type;
	}

	@Override
	public String getName() {
		return forename + " " + surname;
	}
}
