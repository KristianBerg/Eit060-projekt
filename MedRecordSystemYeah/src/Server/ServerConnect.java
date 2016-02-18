package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerConnect {
	private Socket socket;
	private int port;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private AccessManager am;
	String dbFile = "records.txt";

	public static void main(String[] args) {
		new ServerConnect();
	}
	public ServerConnect() {
		am = new AccessManager(dbFile); // TEMPORARY
		port = 5678;
		
		// connect to client
		try {
			System.out.println("Waiting for connection...");
			socket = new ServerSocket(port).accept();
			System.out.println("Connected!");
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		// listen for client input
		while (!socket.isClosed()) {
			try {
				Object o = in.readObject();
				if (o != null) {
					System.out.println((String) o);
					handleRequest(o);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void sendToClient(Object o) {
		try {
			out.writeObject(o);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void handleRequest(Object o) {

	}
}
