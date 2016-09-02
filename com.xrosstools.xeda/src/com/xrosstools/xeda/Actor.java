package com.xrosstools.xeda;

import java.util.List;

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
	
	/**
	 * Newly added from 2015 
	 */
	List<MessageType> getReciveMessageTypes();
	List<MessageType> getSendMessageTypes();
	
}
