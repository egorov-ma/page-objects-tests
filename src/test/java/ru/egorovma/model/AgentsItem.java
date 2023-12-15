package ru.egorovma.model;

public class AgentsItem{
	private int agentId;
	private String displayName;
	private String title;

	public int getAgentId(){
		return agentId;
	}

	public String getDisplayName(){
		return displayName;
	}

	public String getTitle(){
		return title;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
