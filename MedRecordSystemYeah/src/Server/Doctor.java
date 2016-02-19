package Server;

import java.util.ArrayList;

public class Doctor implements User {
	private String division;
	private String name;
	private ArrayList<MedRecord> medRecords;
	private String password;
	
	public Doctor(String name, String division) {
		this.division = division;
		this.name = name;
	}

	public boolean addRecord(MedRecord m) {
		if (m != null) {
			medRecords.add(m);
			return true;
		}
	return false;
	}

	@Override
	public ArrayList<MedRecord> getRecords() {
		return medRecords;
	}

	public String toString() {
		return "u d " + this.name +" " + this.division;
				 
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
		return password;
	}

	@Override
	public boolean hasAccess(String accessType, MedRecord mr) {
		if(accessType.equals("write")){
		if(mr.getDoctor()==this){
			return true;
		}}
		if(accessType=="read"){
			if(this.equals("d")){
				return true;
			}}
		return false;
	}
}
