package ee.ivkhkdev.apphelpers;

import ee.ivkhkdev.repository.Input;
import ee.ivkhkdev.model.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppHelperAuthorTest {

    private AppHelperAuthor appHelperAuthor;

    @BeforeEach
    void setUp() {
        // Создаем мок AppHelperAuthor для замены реального ввода
        appHelperAuthor = Mockito.spy(new AppHelperAuthor());
    }

    @Test
    void testCreateAuthor() {
        // Заменяем метод getString, чтобы вернуть заранее определенные значения
        doReturn("Имя", "Фамилия").when(appHelperAuthor).getString();

        // Выполняем метод create и проверяем результат
        Author author = appHelperAuthor.create();
        assertNotNull(author);
        assertEquals("Имя", author.getAuthorName());
        assertEquals("Фамилия", author.getAuthorSurname());
    }

    @Test
    void testPrintList() {
        // Создаем список авторов
        List<Author> authors = Arrays.asList(
                new Author("Имя1", "Фамилия1"),
                new Author("Имя2", "Фамилия2")
        );

        // Проверка, что printList не выбрасывает ошибок при выводе
        assertDoesNotThrow(() -> appHelperAuthor.printList(authors));
    }
}
