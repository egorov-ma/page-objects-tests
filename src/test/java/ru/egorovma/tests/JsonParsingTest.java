package ru.egorovma.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.egorovma.model.StatusItem;

import java.io.InputStream;

public class JsonParsingTest {

    private final ClassLoader cl = JsonParsingTest.class.getClassLoader();
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void jsonFileParsingStatusItemTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("sport-marafon.json")) {
            StatusItem statusItem = objectMapper.readValue(is, StatusItem.class);
            Assertions.assertEquals(statusItem.getTitle(), "top boots");
            Assertions.assertTrue(statusItem.isPremium());
            Assertions.assertEquals(statusItem.getConfigUpdatedTs(), 1693487433);
            Assertions.assertEquals(statusItem.getAgents().get(0).getAgentId(), 1773434);
            Assertions.assertEquals(statusItem.getAgents().get(0).getDisplayName(), "Ольга");
            Assertions.assertEquals(statusItem.getAgents().get(0).getTitle(), "Консультант");
        }

    }
}