package ee.ivkhkdev.factory;

import java.util.HashMap;
import java.util.Map;

public class Factory {
    private static Factory instance = null;
    private static Configuration configuration;
    private Factory() {}
    public static Factory getInstance(Configuration configuration){
        Factory.configuration = configuration;
        if(instance == null){
            Factory.instance = new Factory();
        }
        return Factory.instance;
    }
    public Object getObject(String name){
        try {
            return configuration.getObject(name);
        }catch (Exception e){
            return null;
        }
    }
}
