package uk.co.tangentlabs.akka.actors;

import actions.XMLFileAction;
import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;
import akka.routing.SmallestMailboxRouter;


public class MasterActor extends UntypedActor {
	public static final int numWorkers = 3;
	private final ActorRef workerRouter;
	
	public MasterActor() {
		Props actorProperties = new Props(new UntypedActorFactory () {
			public Actor create() {
				return new PayloadActor(new XMLFileAction());
			}
		});
		workerRouter = this.getContext().actorOf(
				actorProperties.withRouter(new SmallestMailboxRouter(
						numWorkers)));
	}

	@Override
	public void onReceive(Object message) throws Exception {
		System.out.println("Hello");
		workerRouter.tell(message, getSelf());
		
	}
}
