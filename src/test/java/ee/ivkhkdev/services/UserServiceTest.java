package ee.ivkhkdev.services;

import ee.ivkhkdev.App;
import ee.ivkhkdev.interfaces.impl.AppHelperUsers;
import ee.ivkhkdev.interfaces.impl.ConsoleInput;
import ee.ivkhkdev.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest {

    private ConsoleInput inputMock;
    private AppHelperUsers userProviderMock;
    private User userMock;
    User user;
    User[] users;
    private UserService userService;

    @BeforeEach
    void setUp() {
        this.inputMock = Mockito.mock(ConsoleInput.class);
        this.userProviderMock = Mockito.mock(AppHelperUsers.class);
        this.userMock = Mockito.mock(User.class);
        this.userService = new UserService(userProviderMock);
        user = new User("Ivan", "Ivanov", "56565656");
        users = new User[1];
        users[0] = user;
    }

    @AfterEach
    void tearDown() {
    }
    @Test
    void testUserServiceAdd(){
        when(userProviderMock.create(inputMock)).thenReturn(userMock);
        boolean result = userService.add(inputMock,users);
        verify(userProviderMock).create(inputMock);
        assertTrue(result);
    }
    @Test
    void testUserServiceList(){
        when(userProviderMock.getList(users)).thenReturn(user.toString());
        String result = userService.printList(users);
        assertTrue(result.contains("Ivan"));
    }
}