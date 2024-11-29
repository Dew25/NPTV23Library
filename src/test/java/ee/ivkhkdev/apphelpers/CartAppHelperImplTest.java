package ee.ivkhkdev.apphelpers;

import ee.ivkhkdev.interfaces.AppService;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.Cart;
import ee.ivkhkdev.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartAppHelperImplTest {
    @Mock
    private AppService<Book> bookAppService;

    @Mock
    private AppService<User> userAppService;

    @Mock
    private Input input;

    @InjectMocks
    private CartAppHelperImpl cartAppHelper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_shouldCreateCartSuccessfully() {
        // Arrange
        Book book = new Book();
        book.setTitle("Test Book");

        User user = new User();
        user.setFirstname("John");
        user.setLastname("Doe");

        when(input.getString()).thenReturn("1", "1");
        when(bookAppService.list()).thenReturn(List.of(book));
        when(userAppService.list()).thenReturn(List.of(user));

        // Act
        Optional<Cart> result = cartAppHelper.create();

        // Assert
        assertTrue(result.isPresent());
        Cart cart = result.get();
        assertEquals("Test Book", cart.getBook().getTitle());
        assertEquals("John", cart.getUser().getFirstname());
        assertEquals("Doe", cart.getUser().getLastname());
        assertEquals(LocalDate.now(), cart.getBorrowedBookDate());

        verify(bookAppService, times(1)).print();
        verify(userAppService, times(1)).print();
    }

    @Test
    void create_shouldReturnEmptyOptionalWhenExceptionOccurs() {
        // Arrange
        when(input.getString()).thenThrow(new RuntimeException("Input error"));

        // Act
        Optional<Cart> result = cartAppHelper.create();

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void printList_shouldReturnFalseWhenListIsEmpty() {
        // Act
        boolean result = cartAppHelper.printList(List.of());

        // Assert
        assertFalse(result);
    }

    @Test
    void printList_shouldPrintAllUnreturnedBooks() {
        // Arrange
        Book book = new Book();
        book.setTitle("Test Book");

        User user = new User();
        user.setFirstname("John");
        user.setLastname("Doe");

        Cart cart = new Cart();
        cart.setBook(book);
        cart.setUser(user);

        // Act
        boolean result = cartAppHelper.printList(List.of(cart));

        // Assert
        assertTrue(result);
    }

    @Test
    void returnBook_shouldUpdateReturnedBookDate() {
        // Arrange
        Book book = new Book();
        book.setTitle("Test Book");

        User user = new User();
        user.setFirstname("John");
        user.setLastname("Doe");

        Cart cart = new Cart();
        cart.setBook(book);
        cart.setUser(user);

        when(input.getString()).thenReturn("1");

        // Act
        List<Cart> updatedCarts = cartAppHelper.returnBook(List.of(cart));

        // Assert
        assertNotNull(updatedCarts);
        assertEquals(1, updatedCarts.size());
        assertNotNull(updatedCarts.get(0).getReturnedBookDate());
        assertEquals(LocalDate.now(), updatedCarts.get(0).getReturnedBookDate());
    }

    @Test
    void returnBook_shouldHandleEmptyList() {
        // Act
        List<Cart> updatedCarts = cartAppHelper.returnBook(List.of());

        // Assert
        assertNotNull(updatedCarts);
        assertTrue(updatedCarts.isEmpty());
    }


}