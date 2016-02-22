package Server;

import java.util.ArrayList;

public class Doctor implements User {
	private String division;
	private String name;
	private String password;

	public Doctor(String name, String division, String password) {
		this.division = division;
		this.name = name;
		this.password = password;
	}

	public String toString() {
		return "u d " + this.name + " " + this.division + " " + password;

	}

	@Override
	public String getName() {
		return name;
	}

	public String getDivision() {
		return division;
	}

	@Override
	public String getPass() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public boolean hasAccess(String accessType, MedRecord mr) {
		if (accessType.equals("write")) {
			if (mr.getDoctor() == this) {
				return true;
			}
		}
		if (accessType == "read") {
			if (this.equals("d")) {
				return true;
			}
		}
		return false;
	}
}
