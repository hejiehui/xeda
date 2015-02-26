package com.xross.tools.xeda;

public class Message<T> {
	private ActorIdentifier sender;
	private ActorIdentifier reciver;
	private T load;
	
	public ActorIdentifier getSender() {
		return sender;
	}
	public ActorIdentifier getReciver() {
		return reciver;
	}
	public T getLoad() {
		return load;
	}
	
	
}
