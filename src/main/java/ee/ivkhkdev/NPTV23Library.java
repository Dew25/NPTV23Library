package ee.ivkhkdev;

import ee.ivkhkdev.framework.Factory;
import ee.ivkhkdev.framework.config.JavaConfiguration;

public class NPTV23Library {

    public static void main(String[] args) {
        Factory factory = Factory.getInstance(new JavaConfiguration());
        App app = (App) factory.getObject("app");
        app.run();
    }

}