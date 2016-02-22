package Server;


public class Patient implements User{
	private String name;
	
	public Patient(String name){ //Removed division from patient, doesn't make sense for them to have one
		this.name = name;
				
	}

	public String toString() {
		return "u p " + name;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDivision(){
		return "-";
	}

	@Override
	public String getPass() {
		// TODO Auto-generated method stub
		return null;
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

