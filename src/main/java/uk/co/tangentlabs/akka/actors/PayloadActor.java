package uk.co.tangentlabs.akka.actors;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.logging.Logger;

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
			String filePath = "/home/scaryclam/temp_xml/";
			Logger log = Logger.getLogger("log_file");
			FileWriter fileWriter = new FileWriter(message.xmlFilePath);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			try {
				bufferedWriter.write("Hello World");
			} catch (Exception err) {
				log.warning("oops");
			}
			bufferedWriter.close();
			
		} else {
			unhandled(message);
		}
		getSender().tell("Done");
		
	}
}
