package Server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Auditor {
	String filename;
	
	public Auditor(String filename){
		this.filename = filename;
	}
	
	public void log(String msg){
		PrintWriter out = null;
		try {
		    out = new PrintWriter(new BufferedWriter(new FileWriter("log.txt", true)));
		    out.println(getTimeStample()+" " + msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.close();
	}
	
	public String getTimeStample(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return (dateFormat.format(date));
	}
}
