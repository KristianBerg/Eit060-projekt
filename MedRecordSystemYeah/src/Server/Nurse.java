package Server;

import java.util.ArrayList;

public class Nurse implements User {
	private String division;
	private String name;


	public Nurse(String name, String division){
		this.name = name;
		this.division = division;
	}
	
	
	
	public ArrayList<MedRecord> getRecords() {
		// TODO Auto-generated method stub
		return null;
	}



	public String toString() {
		return null;
	}



	@Override
	public String getName() {
		return name;
	}
	public String getDivision(){
		return division;
	}



	@Override
	public String getPass() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
