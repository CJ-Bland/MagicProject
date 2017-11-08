package server;

public class MagicServerDriver {

	public static void main(String[] args){
		
		int defaultPort = 6789;
		
		if (args.length > 2 || args.length == 0){
			System.out.println("Usage: \t<Protocol> [<Port Number>]");
			System.exit(1);
		}
		if (args.length == 1){
			args[0] = args[0].toUpperCase(); // Makes the protocol enter go to Upper case
			if(!args[0].equals("TCP") && !args[0].equals("UDP")){
				System.out.println("Please enter in either TCP or UDP for <Protocol>\nUsage: \t<Protocol> <IP Address or Hostname> [<Port Number>] [<Flag>]");
				System.exit(1);
			} 
		}
		if (args.length == 2){
			args[0] = args[0].toUpperCase(); // Makes the protocol enter go to Upper case
			if(!args[0].equals("TCP") && !args[0].equals("UDP")){
				System.out.println("Please enter in either TCP or UDP for <Protocol>\nUsage: \t<Protocol> <IP Address or Hostname> [<Port Number>] [<Flag>]");
				System.exit(1);
			} else {
				defaultPort = Integer.parseInt(args[1]);
			}
		}
		
		if (args[0].equals("TCP")){
			MagicServer ms = new TcpMagicServer(defaultPort);
		}
	}
}
