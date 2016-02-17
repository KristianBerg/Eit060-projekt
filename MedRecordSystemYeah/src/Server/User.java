package Server;

import java.util.ArrayList;

public interface User {
	public ArrayList<MedRecord> getRecords();
	/**
	 * @return "u " + implementation specific character (eg. d for doctor) + " " 
	 * + attributes separated by spaces in same order as they are entered into the constructor
	 */
	public String toString();
}
