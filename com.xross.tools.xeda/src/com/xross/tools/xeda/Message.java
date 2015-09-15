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
	
//	private ActorIdentifier sender;
//	private ActorIdentifier receiver;
	private boolean durable;
	private int priority;
	private T load;
	
	public T getLoad() {
		return load;
	}
	
	
}
