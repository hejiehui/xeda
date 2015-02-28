package com.xross.tools.xeda;

public interface Receiver {
	/*
	 * Get a message
	 */
	<T> void recieve(Message<T> msg);
}
