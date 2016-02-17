package Server;

public class MedRecord {
	private String doctor;
	private String nurse;
	private String patient;
	static int recordNumber = 0;
	private int recordId;

	public MedRecord(String d, String n, String p) {
		doctor = d;
		nurse = n;
		patient = p;
		recordId = recordNumber;
		recordNumber++;
	}
	
	public int getId(){
		return recordId;
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
	
	/**
	 * @return "r " + attributes separated by spaces in same order as they are enetered into the constructor
	 */
	public String toString() {
		//TODO
		return null;
	}
}
