package ee.ivkhkdev.services;

import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.AppRepository;
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

class BookServiceTest {

    @Mock
    private AppHelper<Book> bookAppHelper; // Мокируем зависимость AppHelper для книги

    @Mock
    private AppRepository<Book> bookAppRepository; // Мокируем зависимость AppRepository для книги

    @InjectMocks
    private BookService bookService; // Инъекция моков в тестируемый класс

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Инициализация моков перед каждым тестом
    }

    @Test
    void add_ShouldReturnTrue_WhenBookIsCreatedAndSaved() {
        // Подготовка мока
        Book newBook = new Book();
        newBook.setTitle("Effective Java");
        newBook.setAuthors(List.of(new Author("Joshuam"," Bloch")));
        Optional<Book> bookOptional = Optional.of(newBook);

        // Мокаем поведение метода create() для возврата новой книги
        when(bookAppHelper.create()).thenReturn(bookOptional);

        // Мокаем метод save() для репозитория
        doNothing().when(bookAppRepository).save(any(Book.class), eq("books"));

        // Вызов метода
        boolean result = bookService.add();

        // Проверка, что результат true (книга добавлена и сохранена)
        assertTrue(result);

        // Проверка, что метод save был вызван один раз с ожидаемой книгой
        verify(bookAppRepository, times(1)).save(eq(newBook), eq("books"));
    }

    @Test
    void add_ShouldReturnFalse_WhenNoBookIsCreated() {
        // Мокаем поведение метода create() для возврата пустого Optional
        when(bookAppHelper.create()).thenReturn(Optional.empty());

        // Вызов метода
        boolean result = bookService.add();

        // Проверка, что результат false (книга не создана)
        assertFalse(result);

        // Проверка, что метод save не был вызван
        verify(bookAppRepository, times(0)).save(any(), eq("books"));
    }

    @Test
    void edit_ShouldReturnTrue_WhenBooksAreUpdatedAndSaved() {
        // Подготовка мока
        List<Book> books = new ArrayList<>();
        Book book1 = new Book();
        book1.setTitle("Clean Code");
        book1.setAuthors(List.of(new Author("Robert", "Martin")));
        books.add(book1);

        List<Book> updatedBooks = new ArrayList<>();
        Book updatedBook = new Book();
        updatedBook.setTitle("Clean Architecture");
        updatedBook.setAuthors(List.of(new Author("Robert", "Martin")));
        updatedBooks.add(updatedBook);

        // Мокаем поведение метода update() для возврата обновленного списка
        when(bookAppHelper.update(books)).thenReturn(updatedBooks);

        // Мокаем метод saveAll() для репозитория
        doNothing().when(bookAppRepository).saveAll(updatedBooks, "books");

        // Вызов метода
        boolean result = bookService.edit();

        // Проверка, что результат true (книги обновлены и сохранены)
        assertTrue(result);

        // Проверка, что метод saveAll был вызван один раз
        verify(bookAppRepository, times(1)).saveAll(updatedBooks, "books");
    }

    @Test
    void edit_ShouldReturnFalse_WhenNoBooksAreUpdated() {
        // Подготовка мока
        List<Book> books = new ArrayList<>();
        Book book1 = new Book();
        book1.setTitle("Clean Code");
        book1.setAuthors(List.of(new Author("Robert", "Martin")));
        books.add(book1);

        // Мокаем поведение метода update() для возврата пустого списка
        when(bookAppHelper.update(books)).thenReturn(new ArrayList<>());

        // Вызов метода
        boolean result = bookService.edit();

        // Проверка, что результат false (книги не обновлены)
        assertFalse(result);

        // Проверка, что метод saveAll не был вызван
        verify(bookAppRepository, times(0)).saveAll(anyList(), eq("books"));
    }

    @Test
    void print_ShouldReturnTrue_WhenBooksArePrinted() {
        // Подготовка мока
        List<Book> books = new ArrayList<>();
        Book book1 = new Book();
        book1.setTitle("Design Patterns");
        book1.setAuthors(List.of(new Author("Robert", "Martin")));
        books.add(book1);

        // Мокаем метод printList() для возврата true
        when(bookAppHelper.printList(books)).thenReturn(true);
        whien(bookAppHelper.)
        // Вызов метода
        boolean result = bookService.print();

        // Проверка, что результат true (список книг напечатан)
        assertTrue(result);

        // Проверка, что метод printList был вызван
        verify(bookAppHelper, times(1)).printList(books);
    }

    @Test
    void print_ShouldReturnFalse_WhenNoBooksToPrint() {
        // Подготовка мока
        List<Book> books = new ArrayList<>();

        // Мокаем метод printList() для возврата false
        when(bookAppHelper.printList(books)).thenReturn(false);

        // Вызов метода
        boolean result = bookService.print();

        // Проверка, что результат false (список книг пуст)
        assertFalse(result);

        // Проверка, что метод printList был вызван
        verify(bookAppHelper, times(1)).printList(books);
    }

    @Test
    void list_ShouldReturnListOfBooks() {
        // Подготовка мока
        List<Book> books = new ArrayList<>();
        Book book1 = new Book();
        book1.setTitle("Refactoring");
        book1.setAuthors(List.of(new Author("Robert", "Martin")));
        books.add(book1);

        // Мокаем метод load() для возврата списка книг
        when(bookAppRepository.load("books")).thenReturn(books);

        // Вызов метода
        List<Book> result = bookService.list();

        // Проверка, что результат не пустой
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Refactoring", result.get(0).getTitle());
    }
}
