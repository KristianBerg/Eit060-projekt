package Server;

import java.util.ArrayList;

public class RecordManager {
	ArrayList<MedRecord> records; 

	public RecordManager(){
		records = new ArrayList<MedRecord>();
	}
	
	public boolean addRecord(){
		return false;
	}
	public MedRecord getRecord(String recordNumber){
		return null;
	}
	
	public boolean deleteRecord(String recordNumber){
		return false;
	}
}


