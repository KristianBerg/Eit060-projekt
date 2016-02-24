package Projekt1kod;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.security.KeyStore;

import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;
import javax.security.cert.X509Certificate;

import Server.AccessManager;

public class server implements Runnable {
    private ServerSocket serverSocket = null;
    private static int numConnectedClients = 0;

    public server(ServerSocket ss) throws IOException {
        serverSocket = ss;
        newListener();
    }

    public void run() {
        try {
            SSLSocket socket=(SSLSocket)serverSocket.accept();
            newListener();
            SSLSession session = socket.getSession();
            X509Certificate cert = (X509Certificate)session.getPeerCertificateChain()[0];
            String subject = cert.getSubjectDN().getName();
    	    numConnectedClients++;
            System.out.println("client connected");
            System.out.println("client name (cert subject DN field): " + subject);
            System.out.println(numConnectedClients + " concurrent connection(s)\n");

            PrintWriter out = null;
            BufferedReader in = null;
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String clientMsg = null;
            AccessManager am = new AccessManager("records.txt");
            while ((clientMsg = in.readLine()) != null) {
                System.out.println("received: \n" + clientMsg);
                String response = "";
                try{
                	response = parseCommand(clientMsg, am);
                } catch (Exception e) {
                	e.printStackTrace();
                	response = "Oops, something went wrong";
                }
                System.out.print("sending: \n" + response);
				out.println(response);
				out.flush();
                System.out.println("done\n");
			}
			in.close();
			out.close();
			socket.close();
    	    numConnectedClients--;
            System.out.println("client disconnected");
            System.out.println(numConnectedClients + " concurrent connection(s)\n");
		} catch (IOException e) {
            System.out.println("Client died: " + e.getMessage());
            e.printStackTrace();
            return;
        }
    }

    private void newListener() { (new Thread(this)).start(); } // calls run()

    public static void main(String args[]) {
        System.out.println("\nServer Started\n");
        int port = 2345;
        if (args.length >= 1) {
            port = Integer.parseInt(args[0]);
        }
        String type = "TLS";
        try {
            ServerSocketFactory ssf = getServerSocketFactory(type);
            ServerSocket ss = ssf.createServerSocket(port);
            ((SSLServerSocket)ss).setNeedClientAuth(true); // enables client authentication
            new server(ss);
        } catch (IOException e) {
            System.out.println("Unable to start Server: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static ServerSocketFactory getServerSocketFactory(String type) {
        if (type.equals("TLS")) {
            SSLServerSocketFactory ssf = null;
            try { // set up key manager to perform server authentication
                SSLContext ctx = SSLContext.getInstance("TLS");
                KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
                TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
                KeyStore ks = KeyStore.getInstance("JKS");
				KeyStore ts = KeyStore.getInstance("JKS");
                char[] password = "password".toCharArray();

                ks.load(new FileInputStream("serverkeystore"), password);  // keystore password (storepass)
                ts.load(new FileInputStream("servertruststore"), password); // truststore password (storepass)
                kmf.init(ks, password); // certificate password (keypass)
                tmf.init(ts);  // possible to use keystore as truststore here
                ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
                ssf = ctx.getServerSocketFactory();
                return ssf;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return ServerSocketFactory.getDefault();
        }
        return null;
    }
    
    private String parseCommand(String s, AccessManager am) throws Exception{
		String delims = "[ ]+";
		String[] tokens = s.split(delims);
		if (tokens == null || tokens.length == 0){
			return "Error: empty message";
		}
		switch (tokens[0]) {
		case "login":
			if(am.login(tokens[1], tokens[2])){
				return "login successful!";
			} else {
				return "login failed";
			}
		case "logoff":
			am.logoff();
			return "logged off";
		case "save":
			am.saveToFile();
			return "saved modifications";
		case "read":
			System.out.println("Reading records");
			return "Records:\n" + am.readAllRecords();
		case "create":
			System.out.println("creating record");
			if (tokens.length == 5 || tokens.length == 6) {
				String[] input = new String[tokens.length - 1];
				for (int i = 0; i < input.length; i++) {
					input[i] = tokens[i + 1];
				}
				if(am.createRecord(input)){
					return "Record created";
				} else {
					return "Error: Either you are not authorised or your input was incorrect";
				}
			} else {
				return "the create command string must contain 5 or 6 words.";
			}
		case "modify":
			System.out.println("Modifying record");
			if (tokens.length == 4) {
				if(am.modifyRecord(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), tokens[3])){
					return "Record modified";
				} else {
					return "Error: Either you are not authorised or your input was incorrect";
				}
			}
			break;
		case "delete":
			System.out.println("Deleting record");
			if (am.deleteRecord(Integer.parseInt(tokens[1]))){
				return "Record deleted";
			} else {
				return "Error: Either you are not authorised or your input was incorrect";
			}
		default:
			return "invalid command";
		}
		return "Input error";
	}

}
