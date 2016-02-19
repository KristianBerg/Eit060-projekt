package Server;

public class testFileIO {
	public static void main(String[] args){
		new testFileIO();
	}
	public testFileIO(){
		AccessManager am = new AccessManager("records.txt");
		am.dumpUsersAndRecords();
		am.modifyRecord(0, 0, "gun");
		am.saveToFile();
	}
}
