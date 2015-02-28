package com.xross.tools.xeda;

import java.io.Serializable;
import java.util.Date;

/**
 * Subclass must provide
 * @author jhhe
 *
 * @param <T>
 */
public class Message<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Date createTime;
	
	private ActorIdentifier sender;
	private ActorIdentifier receiver;
	private boolean durable;
	private T load;
	
	public ActorIdentifier getSender() {
		return sender;
	}
	public ActorIdentifier getReceiver() {
		return receiver;
	}
	public T getLoad() {
		return load;
	}
	
	
}
