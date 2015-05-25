
import java.net.*;
import java.io.*;
import java.util.*;


public class Client  {

	private BufferedReader input;
	private PrintWriter output;	
	private Socket socket;
	private String server, username;
	private int port;


	Client(String server, int port, String username) {
		this.server = server;
		this.port = port;
		this.username = username;
	}
	
	public boolean start() {

		// connect to the server
		try {
			socket = new Socket(server, port);
		} 
		catch(Exception ec) {
			display("Error connectiong to server:" + ec);
			return false;
		}
		
		String msg = "Connection accepted " + socket.getInetAddress() + ":" + socket.getPort();
		display(msg);
	
		try
		{
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(socket.getOutputStream(),true);
		}
		catch (IOException eIO) {
			display("Exception creating new Input/output Streams: " + eIO);
			return false;
		}
		// creates the Thread to listen from the server 
		new ListenFromServer().start();
		//send username
		output.println(username);
		return true;
	}

	private void display(String msg) {

		System.out.println(msg); 
	}
	
	/*
	 * send a message to the server
	 */
	void sendMessage(String msg) {
		output.println(msg);

	}
	private void disconnect() {
		try { 
			if(input != null) input.close();
		}
		catch(Exception e) {} 
		try {
			if(output != null) output.close();
		}
		catch(Exception e) {} 
        try{
			if(socket != null) socket.close();
		}
		catch(Exception e) {} 
		
			
	}
	/*
	 * Usage
	 * > java Client
	 * > java Client username
	 * > java Client username portNumber
	 * > java Client username portNumber serverAddress
	 * at the console prompt
	 * Default portNumber is 1500
	 * Default serverAddress is localhost
	 * Default serverAddress guest
	 */

	public static void main(String[] args) {
		// default values
		int portNumber = 1500;
		String serverAddress = "localhost";
		String userName = "guest";

		// analyze arguments
		switch(args.length) {
			// > javac Client username portNumber serverAddr
			case 3:
				serverAddress = args[2];
			// > javac Client username portNumber
			case 2:
				try {
					portNumber = Integer.parseInt(args[1]);
				}
				catch(Exception e) {
					System.out.println("Invalid port number.");
					System.out.println("Usage is: > java Client [username] [portNumber] [serverAddress]");
					return;
				}
			// > javac Client username
			case 1: 
				userName = args[0];
			// > java Client
			case 0:
				break;
			// invalid number of arguments
			default:
				System.out.println("Usage is: > java Client [username] [portNumber] {serverAddress]");
			return;
		}
		// create the Client object
		Client client = new Client(serverAddress, portNumber, userName);
		// test if we can start the connection to the Server
		// if it failed nothing we can do
		if(!client.start())
			return;
		
		// wait for messages from user
		Scanner scan = new Scanner(System.in);
		// loop forever for message from the user
		while(true) {
			System.out.print("> ");
			// read message from user
			String msg = scan.nextLine();
			 if(msg.equalsIgnoreCase("exit")) {
			 	client.sendMessage(msg);
			 	break;
			 }
			 else
			 	client.sendMessage(msg);
		}
		client.disconnect();	
	}

	/*
	 * a class that waits for the message from the server and append them to the JTextArea
	 * if we have a GUI or simply System.out.println() it in console mode
	 */
	class ListenFromServer extends Thread {

		public void run() {
			while(true) {
				try {

					String msg = input.readLine();
					if(msg != null) {
						System.out.println(msg);
						System.out.print("> ");
					}
				}
				catch(IOException e) {
                    display("Server closed the connection");
                    break;
                }
            }
		}
	}
}

