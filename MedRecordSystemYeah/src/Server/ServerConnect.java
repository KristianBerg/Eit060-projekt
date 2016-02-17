package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnect {
	private Socket socket;
	int port;
	String ip = "130.235.136.24";
	
	public static void main(String[] args){
		new ServerConnect();
	}
	
	public ServerConnect(){
		port = 5678;
		try {
			System.out.println("Waiting for connection...");
			socket = new ServerSocket(port).accept();
			System.out.println("Connected!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
