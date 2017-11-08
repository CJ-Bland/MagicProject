package client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MagicClientDriver {

	public static void main(String[] args) {
		
		InetAddress clientHost = null;
		int defaultPort = 6789;
		boolean isFlag = false;
		
		
		
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
						String upper = args[2].toUpperCase();
						if(upper.equals("-A") || upper.equals("-L") || upper.equals("-C")
							|| upper.equals("-S") || upper.equals("-LC") || upper.equals("-CL") 
								|| upper.equals("-SL") || upper.equals("-SC") || upper.equals("-CS")){
						isFlag = true;
						} else {
							System.out.println("Please enter a valid flag: -A, -L, -C, -S, -LC, -CL, -LS, -SL, -SC, -CS\n"
									+ "Usage: \t<Protocol> <IP Address or Hostname> [<Port Number or Flag>] [<Flag>]");
							System.exit(1);
						}
				} else { // If its not a flag then its a port.
						defaultPort = Integer.parseInt(args[2]);
					}
				}
					
				if(args.length == 4){ // If the user enters in 4 CMA
					defaultPort = Integer.parseInt(args[2]); // Port is assumed to be the 3rd CMA
					String upper = args[3].toUpperCase();
					if(upper.equals("-A") || upper.equals("-L") || upper.equals("-C")
							|| upper.equals("-S") || upper.equals("-LC") || upper.equals("-CL") 
									|| upper.equals("-SL") || upper.equals("-SC") || upper.equals("-CS")){
					} else {
						System.out.println("Please enter a valid flag: -A, -L, -C, -S, -LC, -CL, -LS, -SL, -SC, -CS\n"
								+ "Usage: \t<Protocol> <IP Address or Hostname> [<Port Number or Flag>] [<Flag>]");
						System.exit(1);
					}
				}
			}
		}
		
		try {
			if (args[0].equals("TCP")){
				if (args.length == 2){
					MagicClient mc = new MagicTcpClient(clientHost, defaultPort, "-A");

				}
				if (args.length == 3){
					if (isFlag == true){ // If User enters in just a flag
						MagicClient mc = new MagicTcpClient(clientHost, defaultPort, args[2]);
					} else { // If User enters in just a port number
						int tmp = Integer.parseInt(args[2]);
					MagicClient mc = new MagicTcpClient(clientHost, tmp, "-A");
				}
				}
				if (args.length == 4){
					MagicClient mc = new MagicTcpClient(clientHost, defaultPort, args[3]);
				}
			}
		} catch (ClassNotFoundException e){
			System.out.println("Class Not Found Exception, Try Again");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("I/O Exception, Can not create Socket, Try Again");
			System.exit(1);
		}
				
	}
