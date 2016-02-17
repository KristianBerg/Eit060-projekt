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
	
	public boolean login(String username, String pass){
		//set currentUser attribute here
		return false;
	}
	
	public void logoff(){
		currentUser = null;
	}
	
	//TODO All 4 record reading and modifying methods
	public ArrayList<MedRecord> readAllRecords(){
		return null;
	}
	
	/**
	 * @param field decides the attribute to modify
	 */
	public boolean modifyRecord(int id, int field, String newData){
		return false;
	}
	/** 
	 * @param id indicates which record to delete
	 * @return true if successful
	 */
	public boolean deleteRecord(int id){
		for(int i = 0; i < records.size(); i++){
			if(records.get(i).getId() == id){
				records.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public boolean createRecord(){
		return false;
	}
	
	public void saveToFile(){
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for(User u: users){
			pw.print(u.toString());
		}
		for(MedRecord mr: records){
			pw.print(mr.toString());
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
					users.add(new Patient());
				case 'g':
					users.add(new Govt());
				default:
					System.out.println("not a valid user character");
				}
			} else if (userOrRecord.equals("r")) {
				records.add(new MedRecord(scan.next(), scan.next(),
						scan.next()));
			} else {
				System.out.println("First character in each line should be u or r");
			}
			scan.nextLine();
		}
		scan.close();
	}
}
