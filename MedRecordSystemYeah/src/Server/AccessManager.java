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
	private Auditor auditor;

	public AccessManager(String filename) {
		records = new ArrayList<MedRecord>();
		users = new ArrayList<User>();
		readFile(filename);
		this.filename = filename;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public boolean login(String username, String pass) {
		for (User u : users) {
			if (u.getName().equals(username)) {
				if (u.getPass().equals(pass)) {
					currentUser = u;
//					auditor.log("user " + u.getName() + " logged in");
					System.out.println(currentUser);
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
	public String readAllRecords() {
//		auditor.log("user " + currentUser.getName() + " asked for reading access");
		String s = "";
		for (MedRecord r : records) {
			if (currentUser.hasAccess("read", r)) {
				s += r.idString() + "\n";
			}
		}
		s += "end\n";

		return s;
	}

	/**
	 * @param field
	 *            decides the attribute to modify.
	 * @value 0 doctor, 1 nurse, 2 patient 3 division
	 */
	public boolean modifyRecord(int id, int field, String name) {
//		auditor.log("user " + currentUser.getName() + " asked for writing access");
		for (int i = 0; i < records.size(); i++) {
			if (records.get(i).getId() == id) {
				if (currentUser.hasAccess("write", records.get(i))) {
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

				} else {
				}
			}
		}
		return false;
	}

    public boolean deleteRecord(int id) {
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getId() == id) {
                if (currentUser.hasAccess("delete", records.get(i))) {
                    records.remove(i);
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
	public boolean createRecord(String[] userData) {
        //Input has to be 4 or 5 words to be valid.
        if (userData.length < 4 || userData.length > 5)
            return false;
        boolean patientRegistered = false;
        Doctor doctor = null;
        Nurse nurse = null;
        Patient patient = null;
        //Checks if doctor, nurse and patient exists.
        for (User u : users) {
            if (u.getName().equals(userData[0])) {
                doctor = (Doctor) u;
            }
            if (u.getName().equals(userData[1])) {
                nurse = (Nurse) u;
            }
            if (u.getName().equals(userData[2])) {
                patient = (Patient) u;
                patientRegistered = true;
            }
        }
        if (!patientRegistered && userData.length == 5) {
            //Patient not registered, creating new patient
            patient = new Patient(userData[2], userData[4]);
            users.add(patient);
        } else if (!patientRegistered){
            //Patient not registered and no password for new patient in input
            System.out.println("wtf");
            return false;
        }
        if (doctor != null && nurse != null) {
            //Checks if the current user matches the doctor name in userData
//          if (currentUser.getName().equals(userData[0])) {
                records.add(new MedRecord(doctor, nurse, patient, userData[3]));
//          }
        }
 
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
			// System.out.println(u.toString());
			pw.println(u.toString());
		}
		pw.println("c records");
		for (MedRecord mr : records) {
			// System.out.println(mr.toString());
			pw.println(mr.toString());
		}
		pw.close();
//		auditor.log("user " + currentUser.getName() + " saved changes to file");

	}

	/**
	 * Prints all saved users and records to console.
	 */
	public void dumpUsersAndRecords() {
		System.out.println("Users\n");
		for (User u : users) {
			System.out.println("Role: " + u.getClass().getSimpleName() + " Name: " + u.getName() + " Division: "
					+ u.getDivision());
		}
		System.out.println("\nRecords\n");
		for (MedRecord mr : records) {
			System.out.println("Doctor: " + mr.getDoctor().getName() + " Nurse: " + mr.getNurse().getName()
					+ " Patient: " + mr.getPatient().getName() + " Division: " + mr.getDivision());
		}
		System.out.println("\n");
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
		// MedRecord.setRecordNumber(scan.nextInt());
		scan.nextLine();
		int currentRow = 3;
		while (scan.hasNext()) {
			char userOrRecord = scan.next().charAt(0);
			if (userOrRecord == 'u') { // read user
				switch (scan.next().charAt(0)) {
				case 'd':
					users.add(new Doctor(scan.next(), scan.next(), scan.next()));
					break;
				case 'n':
					users.add(new Nurse(scan.next(), scan.next(), scan.next()));
					break;
				case 'p':
					users.add(new Patient(scan.next(), scan.next()));
					break;
				case 'g':
					users.add(new Govt(scan.next(), scan.next()));
					break;
				case 's':
					users.add(new SuperDuperUser(scan.next(), scan.next()));
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
				// do nothing here
			} else {
				System.out.println("First character in each line should be c, u or r");
			}
			currentRow++;
			scan.nextLine();
		}
		scan.close();
	}

	/**
	 * finds user matching class type of user argument and name and puts it into
	 * user argument
	 * 
	 * @param name
	 * @param user
	 * @return
	 */
	private User findByName(String name) {
		for (User u : users) {
			if (u.getName().equals(name)) {
				return u;
			}
		}
		return null;
	}

}
