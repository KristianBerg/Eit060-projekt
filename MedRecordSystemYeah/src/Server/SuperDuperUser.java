package Server;

public class SuperDuperUser implements User{
	String name;
	String password;
	
	public SuperDuperUser(String name, String password){
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getDivision() {
		return null;
	}

	public String getPass() {
		return password;
	}

	public boolean hasAccess(String accessType, MedRecord mr) {
		return true;
	}
	
	public String toString(){
		return "u s " + name + " " + password;
	}
}
