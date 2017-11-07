package client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import common.Card;


public class MagicTcpClient extends AbstractMagicClient{
	/** Socket will be our connection to a server */
	Socket clientSocket;

	/**
	 * Creates client with a socket connected to a waiting server
	 *
	 * @param clientHost hostname where the server lives.
	 * @param port port where the server is listening
	 *
	 * @throws IOException if we cannot create a socket.
	 * @throws ClassNotFoundException 
	 */
	public MagicTcpClient(InetAddress clientHost, int clientPort, String clientFlag) throws IOException, ClassNotFoundException {
		
		this.host = clientHost;
		this.flag = clientFlag;
		this.port = clientPort;
	
		clientSocket = new Socket(clientHost, port);
		go();
	}

	protected InetAddress getHost(){
		return host;
	}
	
	protected int getPort(){
		return port;
	}
	
	protected String getFlag(){
		return flag;
	}
	
	/**
	 * Get some information from the user, send it to a server, get a response, and then
	 * print out the response. Make many assumptions.  Example purposes only!
	 *
	 * @throws IOException if something goes wrong with out socket.
	 * @throws ClassNotFoundException 
	 */
	public void go() throws IOException, ClassNotFoundException {
		
		 ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
		 DataOutputStream toServer = new DataOutputStream(clientSocket.getOutputStream());
		 toServer.writeBytes(flag + "\n");       // write to the server
			


		 ArrayList<Card> testCard = null;
		 testCard = (ArrayList<Card>) inFromServer.readObject();
		 
		 //System.out.println(testCard.toString());
		 for(int i = 0; i<testCard.size(); i++){
			 System.out.println(testCard.get(i));
		 }
		 
		 inFromServer.close();
		 clientSocket.close();
		 
		/*
		// Create a 'stream' connected to the keyboard for user input.
		Scanner scanIn = new Scanner(System.in);
		// Create a 'stream' connected to the server to write data.
		DataOutputStream toServer = new DataOutputStream(clientSocket.getOutputStream());
		// Create a 'stream' connected to the server to read data.
		Scanner clientIn = new Scanner(clientSocket.getInputStream());
		
		//while(!sendLine.equals("end")){
			System.out.print("Enter a sentence to send to the server >");
			String sendLine = scanIn.nextLine();        // data from user
			// Why am I sending a newline?
			toServer.writeBytes(sendLine + "\n");       // write to the server
			String recdLine = clientIn.nextLine();      // read from server
			System.out.println("The server sent back:");
			System.out.println("\t" + recdLine);
		//}
		clientSocket.close();                       // close the TCP connection.
		clientIn.close();
		*/
	}



}
