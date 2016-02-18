package Server;

import java.util.ArrayList;

public class Govt implements User{
	private String name;

	public Govt(String name){
		this.name = name;
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
