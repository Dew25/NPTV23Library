package ee.ivkhkdev.apphelpers;

import ee.ivkhkdev.apphelpers.repository.FileRepository;
import ee.ivkhkdev.input.Input;
import ee.ivkhkdev.model.Author;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppHelperAuthorTest {

    @Mock
    private Input input;

    @Mock
    private FileRepository<Author> authorRepository;

    @InjectMocks
    private AppHelperAuthor appHelperAuthor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAuthorSuccess() {
        // Симуляция ввода имени и фамилии автора
        when(input.getString()).thenReturn("John", "Doe");

        // Вызов метода create для создания нового автора
        Author author = appHelperAuthor.create();

        // Проверка, что объект автора был создан с корректными значениями
        assertNotNull(author);
        assertEquals("John", author.getAuthorName());
        assertEquals("Doe", author.getAuthorSurname());

        // Проверка количества вызовов input.getString() для имени и фамилии
        verify(input, times(2)).getString();
    }

    @Test
    void testCreateAuthorWithException() {
        // Симуляция исключения при вызове input.getString()
        when(input.getString()).thenThrow(new RuntimeException("Input error"));

        // Вызов метода create должен вернуть null при исключении
        Author author = appHelperAuthor.create();

        assertNull(author);
        verify(input, times(1)).getString();  // Проверка, что input.getString() вызвался один раз до исключения
    }

    @Test
    void testPrintList() {
        // Создание списка авторов для печати
        List<Author> authors = new ArrayList<>();
        Author author1 = new Author();
        author1.setAuthorName("John");
        author1.setAuthorSurname("Doe");

        Author author2 = new Author();
        author2.setAuthorName("Jane");
        author2.setAuthorSurname("Smith");

        authors.add(author1);
        authors.add(author2);

        // Вызов метода printList для печати списка авторов
        appHelperAuthor.printList(authors);

        // Проверка, что input.getString() не вызывался в printList
        verify(input, never()).getString();
    }
}