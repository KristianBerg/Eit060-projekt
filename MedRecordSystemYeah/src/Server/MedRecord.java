package Server;

public class MedRecord {
	String doctor;
	String nurse;
	String patient;
	String recordNumber;

	public MedRecord(String d, String n, String p, String r) {
		doctor = d;
		nurse = n;
		patient = p;
		recordNumber = r;
	}
	public String getDoctor() {
		return doctor;
	}

	public String getNurse() {
		return nurse;
	}

	public String getPatient() {
		return patient;
	}

	public String recordNumber() {
		return recordNumber;
	}
	
	/**
	 * @return "r " + attributes separated by spaces in same order as they are enetered into the constructor
	 */
	public String toString() {
		//TODO
		return null;
	}

//	public boolean setNurse(String n) {
//		if (n != null) {
//			nurse = n;
//			return true;
//		}
//	return false;
//	}
}
