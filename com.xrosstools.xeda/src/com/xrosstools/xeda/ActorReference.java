package com.xrosstools.xeda;

public class ActorReference {
	private ActorIdentifier actorId;
	
	public ActorReference(ActorIdentifier actorId) {
		this.actorId = actorId;
	}
	
	public ActorIdentifier getActorId() {
		return actorId;
	}

	public <T> void send(Message<T> msg) {
		
	}
}
