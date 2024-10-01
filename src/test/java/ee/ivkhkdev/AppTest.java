package ee.ivkhkdev;

import ee.ivkhkdev.handlers.BookHandler;
import ee.ivkhkdev.interfaces.BookProvider;
import ee.ivkhkdev.interfaces.InputProvider;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AppTest {
    PrintStream defaultOut;
    ByteArrayOutputStream out;
    InputProvider mockInputProvider;
    BookProvider mockBookProvider;
    BookHandler bookHandler;
    @BeforeEach
    void setUp() {
        mockInputProvider = mock(InputProvider.class);
        mockBookProvider = mock(BookProvider.class);
        bookHandler = new BookHandler(mockBookProvider,mockInputProvider);
        defaultOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void tearDown() {
    }
    @Test
    public void testRunExit() {
        when(mockInputProvider.getInput())
                .thenReturn("0");
        Author author = new Author("Лев","Толстой");
        Author[] authors = new Author[1];
        authors[0]=author;
        when(mockBookProvider.newBook(mockInputProvider)).thenReturn(new Book("Война и мир", authors,2000));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        App app = new App(bookHandler, mockInputProvider,mockBookProvider);
        app.run();
        String output = out.toString();
        assertTrue(output.contains("До свидания! :)"));
    }
}
