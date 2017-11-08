package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import common.Card;

public class TcpMagicServer extends AbstractMagicServer{

	/** Listen for clients knocking at the door (port) */
	ServerSocket welcomeSocket;
	

	/**
	 * Create a Server with a listening socket to accept client connections
	 *
	 * @param port port where the server is listening
	 *
	 * @throws IOException if we cannot create a socket.
	 */
	public TcpMagicServer(int port) {
		super(port);
		// socket listens for incoming connections.
		try {
			welcomeSocket = new ServerSocket(getPort());	
			listen();
		} catch (IllegalArgumentException e) {
			System.out.println("IllegalArgumentException, method was passed an illegal or ina"
					+ "ppropriate argument, Try Again");
			System.exit(1);
		} catch (IOException e){
			
		}
	
	}

	/**
	 * Listens to requests from clients
	 * @throws IOException 
	 */
	@SuppressWarnings("resource")
	public void listen() throws IOException {

		welcomeSocket.setSoTimeout(60000); // Timeout in 1 min
		try {
			while(welcomeSocket.getSoTimeout()>0){
//				System.out.println("Waiting for client on port " +
//					welcomeSocket.getLocalPort() + "...");
			// Accept a connection, and create a new 'direct' socket
			// This socket has the same port as the welcome socket.
				Socket connectionSocket = welcomeSocket.accept();

				ObjectOutputStream outToClient = new ObjectOutputStream(connectionSocket.getOutputStream());
				Scanner clientIn = new Scanner(connectionSocket.getInputStream());
				String clientLine = clientIn.nextLine();
				//System.out.println(clientLine);

				CardSource test = new CardSource();
				ArrayList<Card> deck = null;
			
				setCardsReturned(clientLine);
				deck = test.makeDeck(getItemToSend(), getTypes());            
				
				outToClient.writeObject(deck);

				DataOutputStream clientOut = new DataOutputStream(connectionSocket.getOutputStream());
				clientOut.writeBytes("\n");
			}

		} catch (InterruptedIOException e){
			System.out.println("Server Terminated due to Inactivity");	
		}

	}
}
