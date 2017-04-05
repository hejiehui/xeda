package com.xrosstools.xeda;

import java.io.Serializable;
import java.net.URI;
import java.util.Date;

/**
 * Subclass must provide
 * @author jhhe
 *
 * @param <T>
 */
public class Message<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private MessageType type;
	private URI messageSource;
	private String name;
	private Date createTime;
	
//	private ActorIdentifier sender;
//	private ActorIdentifier receiver;
	private boolean durable;
	private int priority;
	private T load;
	
	// TODO do we need additional message metadata?
	
	public T getLoad() {
		return load;
	}
	
	
}
