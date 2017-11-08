
package client;

import java.io.PrintStream;
import java.net.InetAddress;

public abstract class AbstractMagicClient implements MagicClient {

	private InetAddress host;
	private int port;
	private String flag;
	
	public AbstractMagicClient(InetAddress host, int port, String flag) {
		this.host = host;
		this.port = port;
		this.flag = flag;
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
	
	public void printToStream(PrintStream out){
		
	}
}
