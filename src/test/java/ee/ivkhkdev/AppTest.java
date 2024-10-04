package ee.ivkhkdev;

import ee.ivkhkdev.handlers.BookHandler;
import ee.ivkhkdev.interfaces.BookProvider;
import ee.ivkhkdev.interfaces.InputProvider;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class AppTest {
    PrintStream outDefault;
    ByteArrayOutputStream outContent;
    @BeforeEach
    void setUp() {
        outDefault = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(new PrintStream(outDefault));
    }
    @Test
    void testAppRunExit() {
        InputProvider inputMock = Mockito.mock(InputProvider.class);
        when(inputMock.getInput()).thenReturn("0");
        BookProvider bookProviderMock = Mockito.mock(BookProvider.class);
        Author[] authors = new Author[1];
        Author author = new Author("Lev","Tolstoy");
        authors[0] = author;
        when(bookProviderMock.createBook(inputMock)).thenReturn(new Book("Voina i mir",authors,2000));
        BookHandler bookHandler = new BookHandler(inputMock,bookProviderMock);
        App app = new App(bookHandler,inputMock);
        app.run();
//        String outContentString = outContent.toString();
//        System.setOut(new PrintStream(outDefault));
//        System.out.println(outContentString);
        assertTrue(outContent.toString().contains("До свидания :)"));
    }
}