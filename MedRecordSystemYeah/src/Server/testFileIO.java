package Server;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class testFileIO {
	public static void main(String[] args) {
		new testFileIO();
	}

	public testFileIO() {
		
		AccessManager am = new AccessManager("records.txt");
		am.dumpUsersAndRecords();
		am.login("arn", "Ur1gHa");
		am.modifyRecord(0, 0, "arn");
		//am.login("arn", "Ur1gHa");
		//System.out.println(am.readAllRecords() + "\n");
		am.saveToFile();
	
	}
}
