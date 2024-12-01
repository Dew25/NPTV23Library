package ee.ivkhkdev.services;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.AppRepository;
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

class UserServiceTest {

    @Mock
    private AppHelper<User> userAppHelper;

    @Mock
    private AppRepository<User> userAppRepository;

    @InjectMocks
    private UserService userService;

    private final String fileName = "users";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void add_shouldAddUserSuccessfully() {
        // Arrange
        User user = new User();
        when(userAppHelper.create()).thenReturn(Optional.of(user));

        // Act
        boolean result = userService.add();

        // Assert
        assertTrue(result);
        verify(userAppRepository, times(1)).save(user, fileName);
    }

    @Test
    void add_shouldReturnFalseWhenUserNotCreated() {
        // Arrange
        when(userAppHelper.create()).thenReturn(Optional.empty());

        // Act
        boolean result = userService.add();

        // Assert
        assertFalse(result);
        verify(userAppRepository, never()).save(any(User.class), anyString());
    }

    @Test
    void edit_shouldModifyAndSaveUsers() {
        // Arrange
        List<User> users = List.of(new User());
        List<User> modifiedUsers = List.of(new User());
        when(userAppRepository.load(fileName)).thenReturn(users);
        when(userAppHelper.update(users)).thenReturn(modifiedUsers);

        // Act
        boolean result = userService.edit();

        // Assert
        assertTrue(result);
        verify(userAppRepository, times(1)).saveAll(modifiedUsers, fileName);
    }

    @Test
    void edit_shouldReturnFalseWhenNoModifiedUsers() {
        // Arrange
        List<User> users = List.of(new User());
        when(userAppRepository.load(fileName)).thenReturn(users);
        when(userAppHelper.update(users)).thenReturn(new ArrayList<>());

        // Act
        boolean result = userService.edit();

        // Assert
        assertFalse(result);
        verify(userAppRepository, never()).saveAll(anyList(), anyString());
    }

    @Test
    void print_shouldPrintUserListSuccessfully() {
        // Arrange
        List<User> users = List.of(new User());
        when(userAppRepository.load(fileName)).thenReturn(users);
        when(userAppHelper.printList(users)).thenReturn(true);

        // Act
        boolean result = userService.print();

        // Assert
        assertTrue(result);
        verify(userAppHelper, times(1)).printList(users);
    }

    @Test
    void print_shouldReturnFalseWhenUserListIsEmpty() {
        // Arrange
        List<User> emptyUsers = new ArrayList<>();
        when(userAppRepository.load(fileName)).thenReturn(emptyUsers);
        when(userAppHelper.printList(emptyUsers)).thenReturn(false);

        // Act
        boolean result = userService.print();

        // Assert
        assertFalse(result);
        verify(userAppHelper, times(1)).printList(emptyUsers);
    }

    @Test
    void list_shouldLoadUsersFromRepository() {
        // Arrange
        List<User> users = List.of(new User());
        when(userAppRepository.load(fileName)).thenReturn(users);

        // Act
        List<User> result = userService.list();

        // Assert
        assertEquals(users, result);
        verify(userAppRepository, times(1)).load(fileName);
    }
}
