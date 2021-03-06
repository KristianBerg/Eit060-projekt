package Server;

public class Nurse implements User {
	private String division;
	private String name;
	private String password;

	public Nurse(String name, String division, String password) {
		this.name = name;
		this.division = division;
		this.password = password;
	}

	public String toString() {
		return "u n " + this.name + " " + this.division + " " + password;
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
			if (mr.getNurse() == this) {
				return true;
			}
		}
		if (accessType.equals("read")) {
			if (this.division.equals(mr.getDivision())) {
				return true;
			} else if (mr.getNurse() == this){
				return true;
			}
		}
		return false;
	}

}
