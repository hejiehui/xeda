package com.xross.tools.xeda;

public interface DeliveryListener<T> {
	/**
	 * Posted to receiver
	 * @param msg
	 */
	void success(Message<T> msg);
	
	/**
	 * No receiver
	 * @param msg
	 */
	 void fail(Message<T> msg);
}
