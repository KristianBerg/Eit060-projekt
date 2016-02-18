package Server;

public class MedRecord {
	private Doctor doctor;
	private Nurse nurse;
	private Patient patient;
	static int recordNumber = 0;
	private int recordId;

	public MedRecord(User d, User n, User p) {
		doctor = (Doctor)d;
		nurse = (Nurse)n;
		patient = (Patient)p;
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

	/**
	 * @return "r " + attributes separated by spaces in same order as they are
	 *         enetered into the constructor
	 */
	public String toString() {
		// TODO
		return null;
	}
}
