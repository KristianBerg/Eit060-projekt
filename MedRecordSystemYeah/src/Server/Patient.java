package Server;

import java.util.ArrayList;

public class Patient implements User{
	private String name;
	
	public ArrayList<MedRecord> getRecords() { 
		return null;
	}
	
	public Patient(String name){ //Removed division from patient, doesn't make sense for them to have one
		this.name = name;
				
	}

	public String toString() {
		return null;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDivision(){
		return null;
	}

	@Override
	public String getPass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasAccess(String accessType, MedRecord mr) {
		// TODO Auto-generated method stub
		return false;
	}

}

