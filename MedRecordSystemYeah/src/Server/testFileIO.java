package Server;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class testFileIO {
	public static void main(String[] args) {
		new testFileIO();
	}

	public testFileIO() {
		
		AccessManager am = new AccessManager("records.txt");
//		am.dumpUsersAndRecords();
		//am.modifyRecord(0, 0, "arn");
		//am.dumpUsersAndRecords();

		//testDelete(am);
		
		/** creates record
		*/
		am.login("fruktegott", "bananer");
	    String[] newRecord = new String[4];
	    newRecord[0]="gun";
	    newRecord[1]="per";
	    newRecord[2]="ann";
	    newRecord[3]="barn";
//	    newRecord[4]="pA92we";
	    am.createRecord(newRecord);
		String recs = am.readAllRecords();
		System.out.println(recs);
		
//		am.login("per", "Er9Raa");
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
}
