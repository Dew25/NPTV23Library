package ee.ivkhkdev.apphelpers;

import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.User;
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
import static org.mockito.Mockito.when;

class UserAppHelperTest {

    @InjectMocks
    private UserAppHelper userAppHelper;

    @Mock
    Input input;

    @Mock
    private UserAppHelper inputMock;


    @BeforeEach
    void setUp() {

    }

    @Test
    void testCreate_UserCreationSuccess() {
        // Настроим поведение моков
        when(input.getString()).thenReturn("Иван", "Иванов");

        // Создаем пользователя
        User user = userAppHelper.create();

        // Проверяем, что созданный пользователь имеет ожидаемые значения
        assertNotNull(user);
        assertEquals("Иван", user.getFirstname());
        assertEquals("Иванов", user.getLastname());
    }
    @Test
    void testCreate_UserCreationFailure() {
        // Мокаем исключение, чтобы проверить обработку ошибок
        when(input.getString()).thenThrow(new RuntimeException("Ошибка ввода"));

        // Создаем пользователя
        User user = userAppHelper.create();

        // Проверяем, что метод вернул null при ошибке
        assertNull(user);
    }
    @Test
    public void testUpdateSuccessfull(){
        Input mockedInput = Mockito.mock(Input.class);

        Mockito.when(input.getString()).thenReturn(
                "1",
                "y",
                "NewName",
                "y",
                "NewSurname",
                "y",
                "123456"
        );
        List<User> users = List.of(new User("Ivan","Ivanov", "123456"));
        List<User> modifedUsers = userAppHelper.update(users);
        assertEquals("NewName", modifedUsers.get(0).getFirstname());
        assertEquals("NewSurname", modifedUsers.get(0).getLastname());
    }
    @Test
    void testPrintList() {
        // Создаем несколько пользователей
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setFirstname("Иван");
        user1.setLastname("Иванов");
        user1.setPhone("123456789");
        users.add(user1);

        User user2 = new User();
        user2.setFirstname("Петр");
        user2.setLastname("Петров");
        user2.setPhone("987654321");
        users.add(user2);

        // Перехватываем вывод в консоль
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalSystemOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Вызов метода
        userAppHelper.printList(users);

        // Восстанавливаем вывод в консоль
        System.setOut(originalSystemOut);

        // Проверяем, что вывод на консоль соответствует ожиданиям
        String output = outputStream.toString().trim();
        assertTrue(output.contains("1. Иван Иванов. 123456789"));
        assertTrue(output.contains("2. Петр Петров. 987654321"));
    }


    @AfterEach
    public void tearDown() {

    }
}