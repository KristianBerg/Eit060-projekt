package Server;

import java.util.ArrayList;

public class Patient implements User{
	private String division;
	private String name;
	
	public ArrayList<MedRecord> getRecords() {
		return null;
	}
	
	public Patient(String name, String division){
		this.name = name;
		this.division = division;
				
	}

	public String toString() {
		return null;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDivision(){
		return division;
	}

}

