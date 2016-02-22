package Server;

import java.util.ArrayList;

public interface User {
	/**
	 * @return "u " + implementation specific character (eg. d for doctor) + " " 
	 * + attributes separated by spaces in same order as they are entered into the constructor
	 */
	public String toString();
	public String getName();
	public String getDivision();
	public String getPass();
	public boolean hasAccess(String accessType, MedRecord mr);
}

