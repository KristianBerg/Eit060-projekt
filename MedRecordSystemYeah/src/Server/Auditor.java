package Server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Auditor {
	String filename;
	
	public Auditor(String filename){
		this.filename = filename;
	}
	
	public void log(String msg){
		PrintWriter out = null;
		try {
		    out = new PrintWriter(new BufferedWriter(new FileWriter("myfile.txt", true)));
		    out.println(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.close();
	}
}
