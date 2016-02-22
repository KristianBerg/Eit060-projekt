package Server;

import java.io.File;
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
		for (User u : users) {
			if (u.getName().equals(username)) {
				if (u.getPass().equals(pass)) {
					currentUser = u;
					return true;
				}
				break;
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
	 * @value 0 doctor, 1 nurse, 2 patient 3 division
	 */
	public boolean modifyRecord(int id, int field, String name) {
		for (int i = 0; i < records.size(); i++) {
			if (records.get(i).getId() == id) {
				switch (field) {
				case 0:
					records.get(i).setDoctor((Doctor) findByName(name));
					return true;
				case 1:
					records.get(i).setNurse((Nurse) findByName(name));
					return true;
				case 2:
					records.get(i).setPatient((Patient) findByName(name));
					return true;
				case 3:
					records.get(i).setDivision(name);
					return true;
				default:
					return false;
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
	 *            nurseDivision, patientName, division]
	 */
	public boolean createRecord(String[] userData) {
		if (userData.length != 6)
			return false;
		Doctor doctor = null;
		Nurse nurse = null;
		Patient patient = new Patient(userData[4]); // TODO what if the patient
													// already exists? also new
													// patients should be added
													// to users
		for (User u : users) {
			if (u.getName() == userData[0] && u.getDivision() == userData[1]) { // TODO
																				// not
																				// going
																				// to
																				// work,
																				// use
																				// .equals
																				// for
																				// String
																				// and
																				// not
																				// ==
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
			records.add(new MedRecord(doctor, nurse, patient, userData[5]));
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
		pw.println("c number of records");
		pw.println(MedRecord.recordNumber);
		pw.println("c users");
		for (User u : users) {
			System.out.println(u.toString());
			pw.println(u.toString());
		}
		pw.println("c records");
		for (MedRecord mr : records) {
			System.out.println(mr.toString());
			pw.println(mr.toString());
		}
		pw.close();
	}

	/**
	 * Prints all saved users and records to console.
	 */
	public void dumpUsersAndRecords() {
		System.out.println("Users");
		for (User u : users) {
			System.out
					.println("Role: " + u.getClass().getSimpleName()
							+ " Name: " + u.getName() + " Division: "
							+ u.getDivision());
		}
		for (MedRecord mr : records) {
			System.out.println("Doctor: " + mr.getDoctor().getName()
					+ " Nurse: " + mr.getNurse().getName() + " Patient: "
					+ mr.getPatient().getName() + " Division: "
					+ mr.getDivision());
		}
	}

	private void readFile(String filename) {
		Scanner scan = null;
		try {
			scan = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		scan.nextLine(); // TODO assumes first line of input
							// is comment, not pretty but
							// works
		//MedRecord.setRecordNumber(scan.nextInt());
		scan.nextLine();
		int currentRow = 3;
		while (scan.hasNext()) {
			char userOrRecord = scan.next().charAt(0);
			if (userOrRecord == 'u') { // read user
				switch (scan.next().charAt(0)) {
				case 'd':
					users.add(new Doctor(scan.next(), scan.next()));
					break;
				case 'n':
					users.add(new Nurse(scan.next(), scan.next()));
					break;
				case 'p':
					users.add(new Patient(scan.next()));
					break;
				case 'g':
					users.add(new Govt(scan.next()));
					break;
				default:
					System.out.println("not a valid user character");
				}
			} else if (userOrRecord == 'r') { // read record
				String doctorName = scan.next();
				String nurseName = scan.next();
				String patientName = scan.next();
				Doctor d = (Doctor) findByName(doctorName);
				Nurse n = (Nurse) findByName(nurseName);
				Patient p = (Patient) findByName(patientName);
				if (d == null || n == null || p == null) {
					System.out.println("part of record set to null at row: " + currentRow);
				}
				records.add(new MedRecord(d, n, p, scan.next()));
			} else if (userOrRecord == 'c') { // for comments
				//do nothing here
			} else {
				System.out
						.println("First character in each line should be c, u or r");
			}
			currentRow++;
			scan.nextLine();
		}
		scan.close();
	}
	
	/**
	 * finds user matching class type of user argument and name and puts it into user argument
	 * @param name
	 * @param user
	 * @return
	 */
	private User findByName(String name){
		for(User u: users){
			if(u.getName().equals(name)){
				return u;
			}
		}
		return null;
	}
}
