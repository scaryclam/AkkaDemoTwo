package uk.co.tangentlabs.akka;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;

import uk.co.tangentlabs.akka.actors.MasterActor;
import uk.co.tangentlabs.akka.messages.*;


public class AkkaRunner {
	protected final ActorRef master;
	private ActorSystem system;
	Logger logger = Logger.getLogger(AkkaRunner.class.getCanonicalName());
	
	public static void main(String[] args) {
		System.out.println("Hello Akka");
		AkkaRunner runner = new AkkaRunner();
		runner.runWorkers();
	
		System.exit(0);
	}
	
	public void runWorkers() {
		ExecutorService threadRunner = Executors.newFixedThreadPool(10);
		String[] filePaths = {
				"one.xml",
				"two.xml",
				"three.xml",
				"four.xml",
				"five.xml",
				"six.xml",
				"seven.xml",
				"eight.xml",
				"nine.xml",
				"ten.xml"
		};
		
		System.out.println("Starting the run");
		for (int i = 0; i < 10; i++) {
			threadRunner.submit(new XMLThread(master, filePaths[i]));
		}
		threadRunner.shutdown();
		try {
			threadRunner.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException err) {
			err.printStackTrace();
		}
	}
	
	public AkkaRunner() {
		system = ActorSystem.create("HelloWorld");
		master = system.actorOf(new Props(new UntypedActorFactory() {
			public UntypedActor create() {
				return new MasterActor();
			}
		}), "master");
		
		system.registerOnTermination(new Runnable() {
			public void run() {
				logger.info("System shutting down");
			}
		});
	}
	
	public static class XMLThread extends Thread {
		private ActorRef master;
		private String xmlFilePath;
		
		public XMLThread(ActorRef master, String xmlFilePath) {
			this.master = master;
			this.xmlFilePath = xmlFilePath;
		}
		
		public void run() {
			System.out.println("Thread run");
			XMLMessage message = new XMLMessage("master", this.xmlFilePath);
			try {
				master.tell(message, master);
			} catch (Exception err) {
				err.printStackTrace();
			}
		}
	}
	
}
