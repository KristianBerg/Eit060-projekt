package Server;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class AccessManager {
	private ArrayList<MedRecord> records;
	private ArrayList<User> users;
	private User currentUser;
	String filename;

	public AccessManager(String filename) {
		records = new ArrayList<MedRecord>();
		users = new ArrayList<User>();
		readFile(filename);
		this.filename = filename;
	}

	public boolean login(String username, String pass) {
		// set currentUser attribute here
		for(User u: users){
			if(u.getName().equals(username)){
				if(u.getPass().equals(pass)){
					currentUser = u;
				} 
			}
		}
		return false;
	}

	public void logoff() {
		currentUser = null;
	}

	// TODO All 4 record reading and modifying methods
	public ArrayList<MedRecord> readAllRecords() {
		return null;
	}

	/**
	 * @param field
	 *            decides the attribute to modify.
	 * @value 0 doctor, 1 nurse, 2 patient
	 */
	public boolean modifyRecord(int id, int field, Object newData) {
		for (int i = 0; i < records.size(); i++) {
			if (records.get(i).getId() == id) {
				switch (field) {
				case 0:
					records.get(i).setDoctor((Doctor) newData);
					return true;
				case 1:
					records.get(i).setNurse((Nurse) newData);
					return true;
				case 2:
					records.get(i).setPatient((Patient) newData);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @param id
	 *            indicates which record to delete
	 * @return true if successful
	 */
	public boolean deleteRecord(int id) {
		for (int i = 0; i < records.size(); i++) {
			if (records.get(i).getId() == id) {
				records.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Ensures that the doctor and nurse are registered as users. If patient is
	 * registered, adds record with corresponding patient. If patient does not
	 * exist, creates new patient.
	 * 
	 * @param String
	 *            array in the format [doctorName, doctorDivision, nurseName,
	 *            nurseDivision, patientName, patientDivision]
	 */
	public boolean createRecord(String[] userData) {
		if (userData.length != 6)
			return false;
		Doctor doctor = null;
		Nurse nurse = null;
		Patient patient = new Patient(userData[4], userData[5]);
		for (User u : users) {
			if (u.getName() == userData[0] && u.getDivision() == userData[1]) {
				doctor = (Doctor) u;
			}
			if (u.getName() == userData[2] && u.getDivision() == userData[3]) {
				nurse = (Nurse) u;
			}
			if (u.getName() == userData[4] && u.getDivision() == userData[5]) {
				patient = (Patient) u;
			}
		}
		if (doctor != null && nurse != null) {
			records.add(new MedRecord(doctor, nurse, patient));
		}
		;
		return false;
	}

	public void saveToFile() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		pw.println(MedRecord.recordNumber);
		for (User u : users) {
			pw.println(u.toString());
		}
		for (MedRecord mr : records) {
			pw.println(mr.toString());
		}
	}

	private void readFile(String filename) {
		Scanner scan = new Scanner(filename);
		while (scan.hasNext()) {
			String userOrRecord = scan.next();
			if (userOrRecord.equals("u")) {
				switch (scan.next().charAt(0)) {
				case 'd':
					users.add(new Doctor(scan.next(), scan.next()));
				case 'n':
					users.add(new Nurse(scan.next(), scan.next()));
				case 'p':
					users.add(new Patient(scan.next(), scan.next()));
				case 'g':
					users.add(new Govt());
				default:
					System.out.println("not a valid user character");
				}
			} else if (userOrRecord.equals("r")) {
				records.add(new MedRecord(new Doctor(scan.next(), scan.next()),
						new Nurse(scan.next(), scan.next()), new Patient(scan
								.next(), scan.next())));
			} else {
				System.out
						.println("First character in each line should be u or r");
			}
			scan.nextLine();
		}
		scan.close();
	}

}
