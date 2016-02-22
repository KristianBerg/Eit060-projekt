package Server;

public class SuperDuperUser implements User{
	String name; 
	
	public SuperDuperUser(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getDivision() {
		return null;
	}

	public String getPass() {
		return "bananer";
	}

	public boolean hasAccess(String accessType, MedRecord mr) {
		return true;
	}
	
	public String toString(){
		return "u s " + name;
	}
}
