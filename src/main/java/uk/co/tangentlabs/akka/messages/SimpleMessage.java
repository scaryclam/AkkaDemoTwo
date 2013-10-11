package uk.co.tangentlabs.akka.messages;

import java.util.Date;


public class SimpleMessage {
	protected final String message;
	protected final String caller;
	protected final Date timeStamp;
	
	public SimpleMessage(String caller, String logMessage){
		this.caller = caller;
		this.message = logMessage;
		this.timeStamp = new Date();
	}
	
	public String getLogMessage() {
		return message;
	}

	public String getCaller() {
		return caller;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}
}
