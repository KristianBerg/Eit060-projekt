package Server;

public class MedRecord {
	private Doctor doctor;
	private Nurse nurse;
	private Patient patient;
	private String division;
	static int recordNumber = 0;
	private int recordId;

	public MedRecord(Doctor d, Nurse n, Patient p, String division) {
		doctor = d;
		nurse = n;
		patient = p;
		this.division = division;
		recordId = recordNumber;
		recordNumber++;
	}

	public int getId() {
		return recordId;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public Nurse getNurse() {
		return nurse;
	}

	public Patient getPatient() {
		return patient;
	}

	public boolean setDoctor(Doctor newData) {
		if (newData != null) {
			doctor =  newData;
			return true;
		}
		return false;
	}
	
	public boolean setNurse(Nurse s) {
		if (s != null) {
			nurse = s;
			return true;
		}
		return false;
	}
	
	public boolean setPatient(Patient s) {
		if (s != null) {
			patient = s;
			return true;
		}
		return false;
	}

	public static void setRecordNumber(int i){
		recordNumber = i;
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
