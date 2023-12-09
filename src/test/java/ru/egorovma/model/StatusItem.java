package ru.egorovma.model;

import java.util.List;

public class StatusItem {
	private boolean premium;
	private boolean maintenance;
	private int configUpdatedTs;
	private List<AgentsItem> agents;

	public boolean isPremium(){
		return premium;
	}

	public boolean isMaintenance(){
		return maintenance;
	}

	public int getConfigUpdatedTs(){
		return configUpdatedTs;
	}

	public List<AgentsItem> getAgents(){
		return agents;
	}
}