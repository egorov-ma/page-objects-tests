package ru.egorovma.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AgentsItem {
    @JsonProperty("agent_id")
    private int agentId;
    @JsonProperty("display_name")
    private String displayName;
    private String title;

    public int getAgentId() {
        return agentId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getTitle() {
        return title;
    }

}
