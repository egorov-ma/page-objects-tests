package ru.egorovma.model;

import java.util.List;

public class StatusItem {
	private boolean premium;
	private boolean maintenance;
	private int configUpdatedTs;
	private List<AgentsItem> agents;

	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	public void setMaintenance(boolean maintenance) {
		this.maintenance = maintenance;
	}

	public void setConfigUpdatedTs(int configUpdatedTs) {
		this.configUpdatedTs = configUpdatedTs;
	}

	public void setAgents(List<AgentsItem> agents) {
		this.agents = agents;
	}

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