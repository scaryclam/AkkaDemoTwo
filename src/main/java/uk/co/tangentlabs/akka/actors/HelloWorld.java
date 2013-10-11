package uk.co.tangentlabs.akka.actors;

import java.util.HashMap;
import java.util.Map;

import uk.co.tangentlabs.akka.actors.Greeter.Msg;
import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;


public class HelloWorld extends UntypedActor {	
	public HelloWorld() {
		Props actorProperties = Props.create(Greeter.class);
	}
	
	@Override
	public void onReceive(Object msg) {
	    if (msg == Greeter.Msg.DONE) {
	        // when the greeter is done, stop this actor and with it the application
	        getContext().stop(getSelf());
	    } else unhandled(msg);
    }
}
