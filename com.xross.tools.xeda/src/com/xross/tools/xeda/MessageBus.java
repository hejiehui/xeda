package com.xross.tools.xeda;

/*
 * To avoid confusion, it is not allowed to dynamically subscribe message queue.
 */
public abstract class MessageBus {
	private String name;
	private boolean enabled;
	private ActorIdentifier[] getSenders;
	private ActorIdentifier[] getReceivers;
}
