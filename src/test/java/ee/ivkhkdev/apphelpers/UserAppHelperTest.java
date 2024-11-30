import ee.ivkhkdev.apphelpers.UserAppHelper;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.User;
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

class UserAppHelperTest {

    @Mock
    private Input input; // Мокируем зависимость

    @InjectMocks
    private UserAppHelper userAppHelper; // Внедряем мок в объект для тестирования

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Инициализируем моки перед каждым тестом
    }

    @Test
    void create_ShouldReturnUser_WhenValidInput() {
        // Подготовка моков
        when(input.getString()).thenReturn("John").thenReturn("Doe");

        // Вызов метода
        Optional<User> userOptional = userAppHelper.create();

        // Проверка результатов
        assertTrue(userOptional.isPresent());
        User user = userOptional.get();
        assertEquals("John", user.getFirstname());
        assertEquals("Doe", user.getLastname());
    }

    @Test
    void create_ShouldReturnEmpty_WhenExceptionOccurs() {
        // Мокаем исключение при получении ввода
        when(input.getString()).thenThrow(new RuntimeException("Test Exception"));

        // Вызов метода
        Optional<User> userOptional = userAppHelper.create();

        // Проверка, что результат пустой
        assertTrue(userOptional.isEmpty());
    }

    @Test
    void update_ShouldUpdateUser_WhenValidInput() {
        // Подготовка списка пользователей
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setFirstname("Alice");
        user1.setLastname("Smith");
        user1.setPhone("12345");
        users.add(user1);

        // Подготовка моков
        when(input.getString()).thenReturn("1")  // Выбор пользователя
                .thenReturn("y")  // Изменить имя
                .thenReturn("Bob")  // Новое имя
                .thenReturn("y")  // Изменить фамилию
                .thenReturn("Johnson")  // Новая фамилия
                .thenReturn("y")  // Изменить телефон
                .thenReturn("67890");  // Новый телефон

        // Вызов метода
        List<User> updatedUsers = userAppHelper.update(users);

        // Проверка, что изменения были применены
        User updatedUser = updatedUsers.get(0);
        assertEquals("Bob", updatedUser.getFirstname());
        assertEquals("Johnson", updatedUser.getLastname());
        assertEquals("67890", updatedUser.getPhone());
    }

    @Test
    void update_ShouldReturnEmptyList_WhenExceptionOccurs() {
        // Подготовка списка пользователей
        List<User> users = new ArrayList<>();
        users.add(new User());

        // Мокаем исключение при получении ввода
        when(input.getString()).thenThrow(new RuntimeException("Test Exception"));

        // Вызов метода
        List<User> updatedUsers = userAppHelper.update(users);

        // Проверка, что список пустой
        assertTrue(updatedUsers.isEmpty());
    }

    @Test
    void printList_ShouldReturnTrue_WhenListIsNotEmpty() {
        // Подготовка списка пользователей
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setFirstname("Alice");
        user1.setLastname("Smith");
        user1.setPhone("12345");
        users.add(user1);

        // Вызов метода
        boolean result = userAppHelper.printList(users);

        // Проверка результата
        assertTrue(result);
    }

    @Test
    void printList_ShouldReturnFalse_WhenListIsEmpty() {
        // Подготовка пустого списка пользователей
        List<User> users = new ArrayList<>();

        // Вызов метода
        boolean result = userAppHelper.printList(users);

        // Проверка результата
        assertFalse(result);
    }
}
