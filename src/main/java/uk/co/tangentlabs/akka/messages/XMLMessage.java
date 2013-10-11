package uk.co.tangentlabs.akka.messages;

import java.util.Date;

public class XMLMessage {
	protected final String caller;
	protected final String xmlFilePath;
	protected final Date timeStamp;
	
	public XMLMessage(String caller, String xmlFilePath){
		this.caller = caller;
		this.xmlFilePath = xmlFilePath;
		this.timeStamp = new Date();
	}
	
	public String getXmlFilePath() {
		return xmlFilePath;
	}

	public String getCaller() {
		return caller;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}
}
