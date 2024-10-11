package ee.ivkhkdev;

import ee.ivkhkdev.handlers.BookService;
import ee.ivkhkdev.interfaces.BookProvider;
import ee.ivkhkdev.interfaces.InputProvider;
import ee.ivkhkdev.interfaces.impl.ConsoleInput;
import ee.ivkhkdev.interfaces.impl.AppBookHalper;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class NPTV23Library {

    public static void main(String[] args) {
        InputProvider inputProvider = new ConsoleInput();
        BookProvider bookProvider = new AppBookHalper();
        BookService bookService = new BookService(bookProvider);
        App app = new App(inputProvider,bookService);
        app.run();
    }

}