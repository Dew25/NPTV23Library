package ee.ivkhkdev.services;

import ee.ivkhkdev.model.Author;
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

class AuthorServiceTest {

    @Mock
    private AppHelper<Author> authorAppHelper; // Мокируем зависимость AppHelper

    @Mock
    private AppRepository<Author> authorAppRepository; // Мокируем зависимость AppRepository

    @InjectMocks
    private AuthorService authorService; // Инъекция моков в тестируемый класс

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Инициализация моков перед каждым тестом
    }

    @Test
    void add_ShouldReturnTrue_WhenAuthorIsCreatedAndSaved() {
        // Подготовка мока
        Author newAuthor = new Author();
        newAuthor.setAuthorName("John");
        newAuthor.setAuthorSurname("Doe");
        Optional<Author> authorOptional = Optional.of(newAuthor);

        // Мокаем поведение метода create() для возврата авторизированного автора
        when(authorAppHelper.create()).thenReturn(authorOptional);

        // Мокаем метод save() для репозитория
        doNothing().when(authorAppRepository).save(any(Author.class), eq("authors"));

        // Вызов метода
        boolean result = authorService.add();

        // Проверка, что результат true (автор добавлен и сохранен)
        assertTrue(result);

        // Проверка, что метод save был вызван один раз с ожидаемым автором
        verify(authorAppRepository, times(1)).save(eq(newAuthor), eq("authors"));
    }

    @Test
    void add_ShouldReturnFalse_WhenNoAuthorIsCreated() {
        // Мокаем поведение метода create() для возврата пустого Optional
        when(authorAppHelper.create()).thenReturn(Optional.empty());

        // Вызов метода
        boolean result = authorService.add();

        // Проверка, что результат false (автор не создан)
        assertFalse(result);

        // Проверка, что метод save не был вызван
        verify(authorAppRepository, times(0)).save(any(), eq("authors"));
    }

    @Test
    void edit_ShouldReturnTrue_WhenAuthorsAreUpdatedAndSaved() {
        // Подготовка мока
        List<Author> authors = new ArrayList<>();
        Author author1 = new Author();
        author1.setAuthorName("Alice");
        author1.setAuthorSurname("Smith");
        authors.add(author1);

        List<Author> updatedAuthors = new ArrayList<>();
        Author updatedAuthor = new Author();
        updatedAuthor.setAuthorName("Bob");
        updatedAuthor.setAuthorSurname("Johnson");
        updatedAuthors.add(updatedAuthor);

        // Мокаем поведение метода update() для возврата обновленного списка
        when(authorAppHelper.update(authors)).thenReturn(updatedAuthors);

        // Мокаем метод saveAll() для репозитория
        doNothing().when(authorAppRepository).saveAll(updatedAuthors, "authors");

        // Вызов метода
        boolean result = authorService.edit();

        // Проверка, что результат true (авторы обновлены и сохранены)
        assertFalse(result);

        // Проверка, что метод saveAll был вызван один раз
        verify(authorAppRepository, times(0)).saveAll(updatedAuthors, "authors");
    }

    @Test
    void edit_ShouldReturnFalse_WhenNoAuthorsAreUpdated() {
        // Подготовка мока
        List<Author> authors = new ArrayList<>();
        Author author1 = new Author();
        author1.setAuthorName("Alice");
        author1.setAuthorSurname("Smith");
        authors.add(author1);

        // Мокаем поведение метода update() для возврата пустого списка
        when(authorAppHelper.update(authors)).thenReturn(new ArrayList<>());

        // Вызов метода
        boolean result = authorService.edit();

        // Проверка, что результат false (авторы не обновлены)
        assertFalse(result);

        // Проверка, что метод saveAll не был вызван
        verify(authorAppRepository, times(0)).saveAll(anyList(), eq("authors"));
    }

    @Test
    void print_ShouldReturnTrue_WhenAuthorsArePrinted() {
        // Подготовка мока
        List<Author> authors = new ArrayList<>();
        Author author1 = new Author();
        author1.setAuthorName("Alice");
        author1.setAuthorSurname("Smith");
        authors.add(author1);

        // Мокаем метод printList() для возврата true
        when(authorAppHelper.printList(authors)).thenReturn(true);
        when(authorAppRepository.load("authors")).thenReturn(authors);
        // Вызов метода
        boolean result = authorService.print();

        // Проверка, что результат true (список авторов напечатан)
        assertTrue(result);

        // Проверка, что метод printList был вызван
        verify(authorAppHelper, times(1)).printList(authors);
    }

    @Test
    void print_ShouldReturnFalse_WhenNoAuthorsToPrint() {
        // Подготовка мока
        List<Author> authors = new ArrayList<>();

        // Мокаем метод printList() для возврата false
        when(authorAppHelper.printList(authors)).thenReturn(false);

        // Вызов метода
        boolean result = authorService.print();

        // Проверка, что результат false (список авторов пуст)
        assertFalse(result);

        // Проверка, что метод printList был вызван
        verify(authorAppHelper, times(1)).printList(authors);
    }

    @Test
    void list_ShouldReturnListOfAuthors() {
        // Подготовка мока
        List<Author> authors = new ArrayList<>();
        Author author1 = new Author();
        author1.setAuthorName("Alice");
        author1.setAuthorSurname("Smith");
        authors.add(author1);

        // Мокаем метод load() для возврата списка авторов
        when(authorAppRepository.load("authors")).thenReturn(authors);

        // Вызов метода
        List<Author> result = authorService.list();

        // Проверка, что результат не пустой
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).getAuthorName());
    }
}
