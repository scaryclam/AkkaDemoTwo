package uk.co.tangentlabs.akka.actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.SmallestMailboxRouter;


public class MasterActor extends UntypedActor {
	public static final int numWorkers = 3;
	private final ActorRef workerRouter;
	
	public MasterActor() {
		Props actorProperties = Props.create(PayloadActor.class);
		workerRouter = this.getContext().actorOf(
				actorProperties.withRouter(new SmallestMailboxRouter(
						numWorkers)));
	}

	@Override
	public void onReceive(Object arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
