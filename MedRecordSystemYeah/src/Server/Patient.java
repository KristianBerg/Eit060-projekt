package Server;


public class Patient implements User{
	private String name;
	private String password;
	
	public Patient(String name, String password){ //Removed division from patient, doesn't make sense for them to have one
		this.name = name;
		this.password = password;
				
	}

	public String toString() {
		return "u p " + name + " " + password;
	}
	 public String printString(){
		 return " u p " + name;
	 }
	
	public String getName() {
		return name;
	}
	
	public String getDivision(){
		return "-";
	}

	@Override
	public String getPass() {
		return password;
	}

	@Override
	public boolean hasAccess(String accessType, MedRecord mr) {
		if(accessType.equals("read")){
			if(mr.getPatient()==this){
				return true;
			}
		}
		return false;
	}

}

