package server;

import java.io.IOException;
import java.util.ArrayList;

import common.CardType;
import common.CardType.Type;

public abstract class AbstractMagicServer implements MagicServer{

	private int port;
	private CardSource source;
	private ArrayList<CardType.Type> types = new ArrayList<Type>();
	private int numItems;
	
	public AbstractMagicServer(int port){
		this.port = port;
	}

	protected int getPort(){
		return port;
	}

	protected CardSource getSource(){
		return source;
	}
	
	protected ArrayList<CardType.Type> getTypes(){
		return types;
	}

	protected void changeSource(CardSource source){
		this.source = source;
	}

	protected void changeItemsToSend(int numItems){
		this.numItems = numItems;
	}

	protected int getItemToSend(){
		return numItems;
	}

	protected void setCardsReturned(String command){
		String flag = command.toUpperCase();
		if(types.size()!= 0){
			types.clear();
		}
		if(flag.equals("-A")){
			numItems = 60;
			//source.setCardType(null);
			types.add(CardType.Type.ARTIFACT);
		}
		else if(flag.equals("-L")){
			numItems = 20;	
			types.add(CardType.Type.LAND);
			//source.setCardType(CardType.Type.LAND);
		}
		else if (flag.equals("-S")){
			numItems = 20;	
			types.add(CardType.Type.SPELL);
			//source.setCardType(CardType.Type.SPELL);
		}
		else if (flag.equals("-C")){
			numItems = 20;	
			types.add(CardType.Type.CREATURE);
			//source.setCardType(CardType.Type.CREATURE);
		}
		else if(flag.equals("-LS") || flag.equals("-SL")){
			numItems = 40;
			types.add(CardType.Type.LAND);
			types.add(CardType.Type.SPELL);
		}
		else if(flag.equals("-CL") || flag.equals("-LC")){
			numItems = 40;
			types.add(CardType.Type.CREATURE);
			types.add(CardType.Type.LAND);
		}
		else if(flag.equals("-CS") || flag.equals("-SC")){		
			numItems = 40;
			types.add(CardType.Type.CREATURE);
			types.add(CardType.Type.SPELL);
		}

	}

	public void listen() throws IOException{

	}
}
