package Client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientConnect {
	private Socket socket;
	int port;
	ObjectOutputStream out;
	String ip = "localhost";

	public static void main(String[] args) {
		new ClientConnect();
	}

	public ClientConnect() {
		port = 5678; // such random, wow
		try {
			System.out.println("Waiting for connection...");
			socket = new Socket(ip, port);
			System.out.println("Connected!");
			out = new ObjectOutputStream(socket.getOutputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendToClient(Object o){
		try {
			out.writeObject(o);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
