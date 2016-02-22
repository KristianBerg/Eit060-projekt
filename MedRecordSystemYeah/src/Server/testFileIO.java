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
		am.modifyRecord(0, 0, "gun");
		//System.out.println(am.readAllRecords() + "\n");
		am.saveToFile();
	
	}
}
