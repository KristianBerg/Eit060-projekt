package Server;

import java.util.ArrayList;

public class Doctor implements User {
	private String division;
	private String name;
	private ArrayList<MedRecord> medRecords;

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
		return null;
	}

	public String toString() {
		return null;
	}
}
