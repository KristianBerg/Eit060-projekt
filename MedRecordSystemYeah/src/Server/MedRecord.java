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

	public int getId() {
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

	public boolean setDoctor(String s) {
		if (s != null) {
			doctor = s;
			return true;
		}
		return false;
	}
	
	public boolean setNurse(String s) {
		if (s != null) {
			nurse = s;
			return true;
		}
		return false;
	}
	
	public boolean setPatient(String s) {
		if (s != null) {
			patient = s;
			return true;
		}
		return false;
	}

	/**
	 * @return "r " + attributes separated by spaces in same order as they are
	 *         enetered into the constructor
	 */
	public String toString() {
		// TODO
		return null;
	}
}
