package ee.ivkhkdev;

import ee.ivkhkdev.interfaces.UserProvider;
import ee.ivkhkdev.services.BookService;
import ee.ivkhkdev.interfaces.BookProvider;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.services.UserService;
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
    void testAppRun() {
        //Создаем заглушку
        Input inputMock = Mockito.mock(Input.class);
        when(inputMock.getString()).thenReturn("1","2","0");// Задаем значение ввода
        //Создаем заглушку
        BookProvider bookProviderMock = Mockito.mock(BookProvider.class);
        UserProvider userProviderMock = Mockito.mock(UserProvider.class);
        //Создаем массив авторов и инициируем его автором
        Author[] authors = new Author[1];
        Author author = new Author("Lev","Tolstoy");
        authors[0] = author;
        //Задаем значение, которое вставит bookProviderMock
        when(bookProviderMock.create(inputMock)).thenReturn(new Book("Voina i mir",authors,2000));
        when(bookProviderMock.getList()).thenReturn("1. Voina i mir. Lev, Tolstoy. 2000");

        BookService bookServiceMock = new BookService(bookProviderMock);
        UserService userServiceMock = new UserService(userProviderMock);
        App app = new App(inputMock,bookServiceMock,userServiceMock);
        app.run();
        System.setOut(new PrintStream(outDefault));
        System.out.println(outContent.toString());
        assertTrue(outContent.toString().contains("Книга добавлена"));
        assertTrue(outContent.toString().contains("Voina i mir"));
        assertTrue(outContent.toString().contains("До свидания :)"));
    }

}