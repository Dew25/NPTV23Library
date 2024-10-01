package ee.ivkhkdev;

import ee.ivkhkdev.handlers.BookHandler;
import ee.ivkhkdev.interfaces.impl.ConsoleHandler;
import ee.ivkhkdev.interfaces.BookProvider;
import ee.ivkhkdev.interfaces.InputProvider;
import ee.ivkhkdev.interfaces.impl.InputBook;

public class NPTV23Library {

    public static void main(String[] args) {
        BookProvider bookProvider = new InputBook();
        InputProvider inputProvider = new ConsoleHandler();
        BookHandler bookHandler = new BookHandler(bookProvider,inputProvider);
        System.out.println("-------- NPTV23Library --------");
        App app = new App(bookHandler,inputProvider,bookProvider);
        app.run();
    }
}