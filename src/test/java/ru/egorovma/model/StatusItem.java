package ru.egorovma.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StatusItem {
    private String title;
    private boolean premium;
    @JsonProperty("config_updated_ts")
    private int configUpdatedTs;
    private List<AgentsItem> agents;

    public String getTitle() {
        return title;
    }

    public boolean isPremium() {
        return premium;
    }

    public int getConfigUpdatedTs() {
        return configUpdatedTs;
    }

    public List<AgentsItem> getAgents() {
        return agents;
    }
}