package ee.ivkhkdev.services;

import ee.ivkhkdev.interfaces.BookProvider;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.interfaces.impl.AppHelperBooks;
import ee.ivkhkdev.interfaces.impl.AppHelperUsers;
import ee.ivkhkdev.interfaces.impl.ConsoleInput;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BookServiceTest {
    private Input inputMock;
    private Book[] books;
    private BookProvider bookProviderMock;
    private Book bookMock;
    private BookService bookService;
    private Author author;
    private Author[] authors;
    private Book book;

    @BeforeEach
    void setUp() {
        this.inputMock = Mockito.mock(ConsoleInput.class);
        this.bookProviderMock = Mockito.mock(AppHelperBooks.class);
        this.bookMock = Mockito.mock(Book.class);
        this.bookService = new BookService(bookProviderMock);
        this.authors = new Author[1];
        this.author = new Author("Lev", "Tolstoy");
        this.book = new Book("Voina i mir", authors, 2000);
        books = new Book[1];
        books[0] = book;
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void add() {
        when(bookProviderMock.create(inputMock)).thenReturn(bookMock);
        boolean result = bookService.add(inputMock,books);
        verify(bookProviderMock).create(inputMock);
        assertTrue(result);
    }

    @Test
    void printList() {
        when(bookProviderMock.getList(books)).thenReturn(book.toString());
        String result = bookService.printList(books);
        assertTrue(result.contains("Voina i mir"));
    }
}