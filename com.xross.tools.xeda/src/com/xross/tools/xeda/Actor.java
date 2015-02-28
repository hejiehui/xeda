package com.xross.tools.xeda;

/**
 * TODO is actor a good name?
 * @author jhhe
 *
 */
public interface Actor extends Receiver, Replier, Manageable {
	/*
	 * Send a message to receiver
	 */
	<T> void send(Message<T> msg);
	
	/*
	 * Send a message and got reply
	 */
	<K, T> Message<K> request(Message<T> msg);
	
	<T> void request(Message<T> msg, Receiver receiver);
}
