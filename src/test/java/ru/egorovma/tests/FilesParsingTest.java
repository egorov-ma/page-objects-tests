package ru.egorovma.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.egorovma.model.Glossary;
import ru.egorovma.model.StatusItem;

import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;

import static org.hamcrest.MatcherAssert.assertThat;

public class FilesParsingTest {

    private ClassLoader cl = FilesParsingTest.class.getClassLoader();
    ObjectMapper objectMapper = new ObjectMapper();
//    private static final Gson gson = new Gson();
//    StatusItem emp = JacksonObjectMapperExample.createEmployee();
//    StatusItem employee = objectMapper.readValue(file, Employee.class);

    @Test
    void jsonFileParsingImprovedTest() throws Exception {
        try (Reader reader = new InputStreamReader(
                cl.getResourceAsStream("glossary.json")
        )) {
            Glossary actual = gson.fromJson(reader, Glossary.class);

            Assertions.assertEquals("example glossary", actual.getTitle());
            Assertions.assertEquals(234234, actual.getID());
            Assertions.assertEquals("SGML", actual.getGlossary().getSortAs());
            Assertions.assertEquals("Standard Generalized Markup Language", actual.getGlossary().getGlossTerm());
        }
    }

    @Test
    void jsonFileParsingStatusItemTest() throws Exception {
        File file = new File("sport-marafon.json");
        StatusItem statusItem = objectMapper.readValue(file, StatusItem.class);

        assertThat(statusItem.getConfigUpdatedTs().(1693487433));
        assertThat(statusItem.getLastName()).isEqualTo("Simpson");
        assertThat(statusItem.getFirstName()).isEqualTo("Homer");

    }
}

{
//        "premium": true,
//        "maintenance": false,
//        "config_updated_ts": 1693487433,
//        "agents": [
//        {
//        "agent_id": 1773434,
//        "display_name": "Ольга",
//        "title": "Консультант"
//        },
//        {
//        "agent_id": 1773491,
//        "display_name": "Даниил",
//        "title": "Консультант"
//        },
//        {
//        "agent_id": 1773495,
//        "display_name": "Ника",
//        "title": "Консультант"
//        },
//        {
//        "agent_id": 1773496,
//        "display_name": "Светлана",
//        "title": "Консультант"
//        },
//        {
//        "agent_id": 1773497,
//        "display_name": "Максим",
//        "title": "Консультант"
//        }
//        ]
//        }