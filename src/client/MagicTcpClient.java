package client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
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
		super(clientHost, clientPort, clientFlag);
		
		clientSocket = new Socket(clientHost, getPort());
		go();
	}
	
	/**
	 * Get some information from the user, send it to a server, get a response, and then
	 * print out the response. Make many assumptions.  Example purposes only!
	 *
	 * @throws IOException if something goes wrong with out socket.
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public void go() throws IOException, ClassNotFoundException {
		
		 ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
		 DataOutputStream toServer = new DataOutputStream(clientSocket.getOutputStream());
		 toServer.writeBytes(getFlag() + "\n");       // write to the server
			


		 ArrayList<Card> testCard = null;
		 testCard = (ArrayList<Card>) inFromServer.readObject();
		 
		 for(int i = 0; i<testCard.size(); i++){
			 System.out.println(testCard.get(i));
		 }
		 
		 inFromServer.close();
		 clientSocket.close();
	
	}

}
