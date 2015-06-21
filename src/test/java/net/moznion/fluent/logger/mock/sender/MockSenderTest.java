package net.moznion.fluent.logger.mock.sender;

import net.moznion.fluent.logger.mock.sender.MockSender.FluentLog;
import org.fluentd.logger.Config;
import org.fluentd.logger.FluentLogger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MockSenderTest {
    @Test
    public void basicTest() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Properties props = System.getProperties();
        props.setProperty(Config.FLUENT_SENDER_CLASS, MockSender.class.getName());

        FluentLogger logger = FluentLogger.getLogger("test");

        Map<String, Object> map1 = new HashMap<>();
        map1.put("foo", "bar");
        map1.put("buz", 123);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("qux", new ArrayList<String>());

        logger.log("app", map1, 1430294276);
        logger.log("db", map2, 1430294277);

        MockSender sender = (MockSender) logger.getSender();

        assertEquals(sender.getFluentLogs(), Arrays.asList(
                new FluentLog("test.app", 1430294276, map1),
                new FluentLog("test.db", 1430294277, map2)
        ));

        sender.clearFluentLogs();

        assertTrue(sender.getFluentLogs().isEmpty());
    }
}
