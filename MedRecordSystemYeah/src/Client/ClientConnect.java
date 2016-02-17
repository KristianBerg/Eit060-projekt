package Client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientConnect {
	private Socket socket;
	int port;
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
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
