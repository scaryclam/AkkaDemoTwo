package uk.co.tangentlabs.akka.actors;

import actions.XMLFileAction;
import akka.actor.UntypedActor;


public class PayloadActor extends UntypedActor {	
	protected final XMLFileAction xmlFileAction;

	public PayloadActor(XMLFileAction xmlFileAction){
		this.xmlFileAction = xmlFileAction;
	}

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof uk.co.tangentlabs.akka.messages.XMLMessage){
			System.out.println("Hello XML Message");
		} else {
			unhandled(message);
		}
//		getSender().tell("Done");
		
	}
}
