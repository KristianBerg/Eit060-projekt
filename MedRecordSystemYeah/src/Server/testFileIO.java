package Server;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class testFileIO {
	public static void main(String[] args) {
		new testFileIO();
	}

	public testFileIO() {
		AccessManager am = new AccessManager("records.txt");
		//am.dumpUsersAndRecords();
		
		/** Testing login on a doctor user and goverment user
		 * tries deleteRecord() method on these users
		 * writes out if managed
		 * DO NOT save to file AFTER using this method
		*/
		//testDelete(am);

		
		/** describe expected output
		*/
		//am.modifyRecord(0, 0, "gun");
		//System.out.println(am.readAllRecords() + "\n");
		//am.saveToFile();
	
	}
	
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
