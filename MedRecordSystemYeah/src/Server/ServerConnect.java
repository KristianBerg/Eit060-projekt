package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
	/*
	 * Parses string and calls the appropriate method from accessManager. String
	 * commands should be phrased as follows: read: "read" create:
	 * "create doctorName nurseName patientName division patientPassword*"
	 * patientPassword only if patient doesn't already exist. modify:
	 * "modify recordId field newData" delete: "delete recordId"
	 */

	public boolean parseCommand(String s, AccessManager am) {
		// am = this.am; //temporary. Depends on where method is moved
		String delims = "[ ]+";
		String[] tokens = s.split(delims);
		if (tokens.equals(null))
			return false;
		switch (tokens[0]) {
		case "read":
			System.out.println("Reading records");
			if (am.readAllRecords() != null) {
				return true;
			} else
				break;
		case "create":
			System.out.println("creating record");
			if (tokens.length == 5 || tokens.length == 6) {
				String[] input = new String[tokens.length - 1];
				for (int i = 0; i < input.length; i++) {
					input[i] = tokens[i + 1];
				}
				return (am.createRecord(input));
			} else {
				System.out.println("the create command string must contain 5 or 6 words.");
				break;
			}
		case "modify":
			System.out.println("Modifying record");
			if (tokens.length == 4) {
				return (am.modifyRecord(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), tokens[3]));
			}
			break;
		case "delete":
			System.out.println("Deleting record");
			return (am.deleteRecord(Integer.parseInt(tokens[1])));
		default:
			System.out.println("invalid command");
			break;
		}
		return false;
	}

}
