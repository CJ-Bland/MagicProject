package client;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

public class MagicClientDriver {

	public static void main(String[] args) {
		
		MagicClient magicClient = null;
		
		String stringHost = "";
		String protocol = ""; 
		int assumedPort = 0; //Check if valid in TCP Client
		String flag = ""; // Set to empty string so I can check if the user inputs a flag
		int port = 6789;
		
		
		if(args.length == 0 || args.length == 1){
			System.out.println("Usage: \t<Protocol> <IP Address or Hostname> [<Port Number or Flag>] [<Flag>]");
		} else {
			args[0] = args[0].toUpperCase();
			if(!args[0].equals("TWP") && !args[0].equals("UDP")){
				System.out.println("Please enter in either TCP or UDP for <Protocol>\nUsage: \t<Protocol> <IP Address or Hostname> [<Port Number>] [<Flag>]");
			} else {
				protocol = args[0]; // Has to equal UDP or TCP
				//Socket(String host, int port)
				stringHost = args[1]; // Assume they don't enter an IP Address
//				InetAddress hostIPAddress = InetAddress.getByName(args[1]);  
//				String hostAddress = hostIPAddress.getHostAddress();
//				System.out.println(hostAddress);
				if (args.length == 3){ // If user enters in 3 CMA
					if(args[2].startsWith("-")){
						args[2].toUpperCase();
						if(args[2].equals("-A") || args[2].equals("-L") || args[2].equals("-C")
							|| args[2].equals("-S") || args[2].equals("-LC") || args[2].equals("-CL") 
								|| args[2].equals("-SL") || args[2].equals("-SC") || args[2].equals("-CS")){
						flag = args[2];
						} else {
							System.out.println("Please enter a valid flag: -A, -L, -C, -S, -LC, -CL, -LS, -SL, -SC, -CS\n"
									+ "Usage: \t<Protocol> <IP Address or Hostname> [<Port Number or Flag>] [<Flag>]");
							System.exit(1);
						}
					} else { // If its not a flag then its a port.
						assumedPort = Integer.parseInt(args[2]);
						port = assumedPort; // If port isn't valid (sdjf) Client will handle in Socket.
					}
				}
					
				if(args.length == 4){ // If the user enters in 4 CMA
					assumedPort = Integer.parseInt(args[2]);
					port = assumedPort; // If port isn't valid (sdjf) Client will handle in Socket.
					if(args[3].equals("-A") || args[3].equals("-L") || args[3].equals("-C")
							|| args[3].equals("-S") || args[3].equals("-LC") || args[3].equals("-CL") 
									|| args[3].equals("-SL") || args[3].equals("-SC") || args[3].equals("-CS")){
						flag = args[3];
					} else {
						System.out.println("Please enter a valid flag: -A, -L, -C, -S, -LC, -CL, -LS, -SL, -SC, -CS\n"
								+ "Usage: \t<Protocol> <IP Address or Hostname> [<Port Number or Flag>] [<Flag>]");
						System.exit(1);
					}
				}
			}
		}
			// Sending Host, Port and Flag in that Order
			String stream = "" + stringHost + " " + port + " " +flag;
			PrintStream fancyStream = null;
			try {
				fancyStream = new PrintStream(stream);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			//magicClient.printToStream(fancyStream);
				
	}
}
