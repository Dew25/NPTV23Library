package ee.ivkhkdev.apphelpers;

import ee.ivkhkdev.interfaces.AppService;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookAppHelperTest {

    @Mock
    private AppService<Author> authorService;
    @Mock
    private AuthorAppHelper authorAppHelper;
    @Mock
    private Input input;

    @InjectMocks
    private BookAppHelper bookAppHelper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_shouldCreateBookSuccessfully() {
        // Arrange
        List<Author> authors = List.of(new Author("John", "Doe"));
        when(authorService.print()).thenReturn(true);
        when(authorAppHelper.printList(authors)).thenReturn(true);
        when(authorService.list()).thenReturn(authors);
        when(input.getString()).thenReturn("Book Title", "n","1","1", "2000");

        // Act
        Optional<Book> result = bookAppHelper.create();

        // Assert
        assertTrue(result.isPresent());
        Book book = result.get();
        assertEquals("Book Title", book.getTitle());
        assertEquals(2000, book.getPublishedYear());
        assertEquals(authors, book.getAuthors());
    }

    @Test
    void create_shouldReturnEmptyOptionalWhenNoAuthors() {
        // Arrange
        when(authorService.print()).thenReturn(false);

        // Act
        Optional<Book> result = bookAppHelper.create();

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void create_shouldHandleExceptionGracefully() {
        // Arrange
        when(input.getString()).thenThrow(new RuntimeException("Input error"));

        // Act
        Optional<Book> result = bookAppHelper.create();

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void update_shouldUpdateBookFields() {
        // Arrange
        Author author1 = new Author("John", "Doe");
        Author author2 = new Author("Jane", "Smith");
        List<Author> authors = List.of(author1, author2);

        Book book = new Book("Old Title", authors, 1990);
        List<Book> books = new ArrayList<>();
        books.add(book);

        when(input.getString())
                .thenReturn("1", "y", "New Title", "y", "1","1","y","2000");
        when(authorService.print()).thenReturn(true);
        when(authorService.list()).thenReturn(authors);

        // Act
        List<Book> updatedBooks = bookAppHelper.update(books);

        // Assert
        assertEquals(1, updatedBooks.size());
        Book updatedBook = updatedBooks.get(0);
        assertEquals("New Title", updatedBook.getTitle());
        assertEquals(2000, updatedBook.getPublishedYear());
        assertEquals(List.of(author1), updatedBook.getAuthors());
    }

    @Test
    void update_shouldSkipFieldsIfNotModified() {
        // Arrange
        Author author = new Author("John", "Doe");
        List<Author> authors = List.of(author);

        Book book = new Book("Original Title", authors, 1990);
        List<Book> books = List.of(book);

        when(input.getString()).thenReturn("1", "n", "n", "n");

        // Act
        List<Book> updatedBooks = bookAppHelper.update(books);

        // Assert
        assertEquals(1, updatedBooks.size());
        Book updatedBook = updatedBooks.get(0);
        assertEquals("Original Title", updatedBook.getTitle());
        assertEquals(1990, updatedBook.getPublishedYear());
        assertEquals(authors, updatedBook.getAuthors());
    }

    @Test
    void update_shouldReturnEmptyListWhenNoBooks() {
        // Act
        List<Book> updatedBooks = bookAppHelper.update(new ArrayList<>());

        // Assert
        assertTrue(updatedBooks.isEmpty());
    }

    @Test
    void printList_shouldPrintBooks() {
        // Arrange
        Author author = new Author("John", "Doe");
        List<Author> authors = List.of(author);

        Book book = new Book("Book Title", authors, 2000);
        List<Book> books = List.of(book);

        // Act
        boolean result = bookAppHelper.printList(books);

        // Assert
        assertTrue(result);
    }

    @Test
    void printList_shouldReturnFalseForEmptyList() {
        // Act
        boolean result = bookAppHelper.printList(new ArrayList<>());

        // Assert
        assertFalse(result);
    }
}
