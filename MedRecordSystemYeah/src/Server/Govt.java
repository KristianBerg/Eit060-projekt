package Server;

public class Govt implements User {
	private String name;
	private String password;

	public Govt(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public String toString() {
		return "u g " + name + " " + password;
	}

	@Override
	public String getName() {
		return name;
	}

	public String getDivision() {
		return null;
	}

	@Override
	public String getPass() {
		return password;
	}

	@Override
	public boolean hasAccess(String accessType, MedRecord mr) {
		if (accessType.equals("read") || accessType.equals("delete")) {
			return true;
		}
		return false;
	}
}
