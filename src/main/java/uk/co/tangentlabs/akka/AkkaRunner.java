package uk.co.tangentlabs.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import uk.co.tangentlabs.akka.actors.HelloWorld;
import uk.co.tangentlabs.akka.messages.*;


public class AkkaRunner {
	protected final ActorRef master;
	private ActorSystem system;
	
	public static void main(String[] args) {
		System.out.println("Hello Akka");
		AkkaRunner runner = new AkkaRunner();
	
		System.exit(0);
	}
	
	public AkkaRunner() {
		system = ActorSystem.create("HelloWorld");
		master = system.actorOf(Props.create(HelloWorld.class, "master"));
//		master.tell(new SimpleMessage("Hai", "me"), master);
	}
}
