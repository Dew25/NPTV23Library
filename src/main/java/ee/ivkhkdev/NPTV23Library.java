package ee.ivkhkdev;

import ee.ivkhkdev.factory.Factory;
import ee.ivkhkdev.factory.JavaConfiguration;

public class NPTV23Library {

    public static void main(String[] args) {
        Factory factory = Factory.getInstance(new JavaConfiguration());
        App app = (App) factory.getObject("app");
        app.run();
    }

}