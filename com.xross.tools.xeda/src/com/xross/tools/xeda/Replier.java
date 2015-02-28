package com.xross.tools.xeda;

public interface Replier {
	/*
	 * Being asked to reply
	 */
	<K, T> Message<K> reply(Message<T> msg);
}
