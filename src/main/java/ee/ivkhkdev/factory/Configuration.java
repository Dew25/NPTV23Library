package ee.ivkhkdev.factory;

import java.util.Map;
import java.util.SimpleTimeZone;

public interface Configuration {
    Object getObject(String name);
}
