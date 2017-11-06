package client;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

public class MagicClientDriver {

	public static void main(String[] args) {
		
		InetAddress clientHost = null;
		String flag = ""; // Set to empty string so I can check if the user inputs a flag
		int port = 9876;
		
		
		if(args.length == 0 || args.length == 1){ // If the use enters in the wrong # of arguments
			System.out.println("Usage: \t<Protocol> <IP Address or Hostname> [<Port Number or Flag>] [<Flag>]");
			System.exit(1);
		} else {
			args[0] = args[0].toUpperCase(); // Makes the protocol enter go to Upper case
			if(!args[0].equals("TCP") && !args[0].equals("UDP")){
				System.out.println("Please enter in either TCP or UDP for <Protocol>\nUsage: \t<Protocol> <IP Address or Hostname> [<Port Number>] [<Flag>]");
				System.exit(1);
			} else {
				try {
					InetAddress hostAddress = InetAddress.getByName(args[1]);
					clientHost = hostAddress;
				} catch (UnknownHostException e) {
					System.out.println("Unknown Host Exception");
					System.exit(1);
				} 
				if (args.length == 3){ // If user enters in 3 CMA
					if(args[2].startsWith("-")){ // If the 3rd CMA is a flag i.s -A -L
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
						port = Integer.parseInt(args[2]);
					}
				}
					
				if(args.length == 4){ // If the user enters in 4 CMA
					port = Integer.parseInt(args[2]); // Port is assumed to be the 3rd CMA
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
		
		if (args[0].equals("TCP")){
			
			
		}
				
	}
}
