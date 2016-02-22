package Server;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class testFileIO {
	public static void main(String[] args) {
		new testFileIO();
	}

	public testFileIO() {
		
		AccessManager am = new AccessManager("records.txt");
//		am.dumpUsersAndRecords();
		
		//use one method /time 
		testCreateRecord(am);
		testCreateRecordDoctor(am);
		testCreateRecordPatient(am);
		testDelete(am);

		
//		am.saveToFile();
	
	}
	
	
	/** Testing login on a doctor user and goverment user
	 * tries deleteRecord() method on these users
	 * writes out if managed
	 * DO NOT save to file AFTER using this method
	 */
	private void testDelete(AccessManager am){
		am.login("arn", "Ur1gHa");
		boolean status = am.deleteRecord(0);
		System.out.println(status);
		am.logoff();
		am.login("soc", "pA95we");
		status = am.deleteRecord(0);	
		System.out.println(status);	
	}
	
	/** Testing CreateRecord() AND realAllRecords() methods
	 * goes as superuser with unlimited access
	 * expected additional record as output
	 * IF NOT already existing, then expected output "file is already existing"
	 */
	private void testCreateRecord(AccessManager am){
		am.login("fruktegott", "bananer");
	    String[] newRecord = new String[4];
	    newRecord[0]="gun";
	    newRecord[1]="per";
	    newRecord[2]="ann";
	    newRecord[3]="barn";
	    am.createRecord(newRecord);
		String recs = am.readAllRecords();
		System.out.println(recs);
	}
	
	/** Testing CreateRecord() AND realAllRecords() methods
	 * goes as Doctor with 2 records
	 * expected additional record as output, default 3
	 * IF NOT already existing, then expected output "file is already existing"
	 */
	private void testCreateRecordDoctor(AccessManager am){
		am.login("gun", "Ae88ha");
	    String[] newRecord = new String[4];
	    newRecord[0]="gun";
	    newRecord[1]="per";
	    newRecord[2]="ann";
	    newRecord[3]="barn";
	    am.createRecord(newRecord);
		String recs = am.readAllRecords();
		System.out.println(recs);
	}
	
	/** Testing CreateRecord() AND realAllRecords() methods
	 * goes as Patient
	 * CreateRecord should fail, expected output is this.recods
	 */
	private void testCreateRecordPatient(AccessManager am){
		am.login("ann", "pA92we");
	    String[] newRecord = new String[4];
	    newRecord[0]="gun";
	    newRecord[1]="per";
	    newRecord[2]="ann";
	    newRecord[3]="barn";
	    am.createRecord(newRecord);
		String recs = am.readAllRecords();
		System.out.println(recs);
	}
}
