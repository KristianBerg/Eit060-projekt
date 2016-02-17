package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientConnect {
	private Socket socket;
	int port;
	ObjectOutputStream out;
	ObjectInputStream in;
	String ip = "localhost";

	public static void main(String[] args) {
		new ClientConnect();
	}

	public ClientConnect() {
		port = 5678; // such random, wow
		
		//connect to server
		try {
			System.out.println("Waiting for connection...");
			socket = new Socket(ip, port);
			System.out.println("Connected!");
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendToServer(Object o){
		try {
			out.writeObject(o);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
