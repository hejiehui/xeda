package com.xross.tools.xeda.def;

import java.util.Map;

public class ActorDef {
	private String name;
	private String description;
	private int quantity;
	
	private Map<String, String> properties;
	private String[] inboxes;
	private String[] outboxes;
}
