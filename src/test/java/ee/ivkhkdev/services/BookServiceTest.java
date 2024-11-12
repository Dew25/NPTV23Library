package ee.ivkhkdev.services;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.FileRepository;
import ee.ivkhkdev.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private AppHelper<Book> appHelperBook;

    @Mock
    private FileRepository<Book> storage;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBookSuccessfully() {
        Book book = new Book(); // Создаем тестовый экземпляр книги
        when(appHelperBook.create()).thenReturn(book); // Мокаем создание книги
        doNothing().when(storage).save(book, "books"); // Мокаем сохранение книги

        boolean result = bookService.add();

        assertTrue(result);
        verify(appHelperBook, times(1)).create();
        verify(storage, times(1)).save(book, "books");
    }

    @Test
    void testAddBookFailure() {
        when(appHelperBook.create()).thenReturn(null); // Мокаем создание книги, возвращая null

        boolean result = bookService.add();

        assertFalse(result);
        verify(appHelperBook, times(1)).create();
        verify(storage, never()).save(any(), eq("books"));
    }
    @Test
    void testEditBookSuccessfully() {


    }
    @Test
    void testListBooks() {
        List<Book> books = Collections.singletonList(new Book()); // Создаем список с одной книгой
        when(storage.load("books")).thenReturn(books); // Мокаем загрузку списка

        List<Book> result = bookService.list();

        verify(storage, times(1)).load("books");
        assertTrue(result.containsAll(books));
    }
}