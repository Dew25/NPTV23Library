package ee.ivkhkdev.services;

import ee.ivkhkdev.interfaces.CartAppHelper;
import ee.ivkhkdev.interfaces.AppRepository;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Cart;
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

class CardServiceImplTest {

    @Mock
    private Input input;

    @Mock
    private CartAppHelper cartAppHelper;

    @Mock
    private AppRepository<Cart> cartAppRepository;

    @InjectMocks
    private CardServiceImpl cardService;

    private final String fileName = "cards";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void add_shouldAddCartSuccessfully() {
        // Arrange
        Cart cart = new Cart();
        when(cartAppHelper.create()).thenReturn(Optional.of(cart));

        // Act
        boolean result = cardService.add();

        // Assert
        assertTrue(result);
        verify(cartAppRepository, times(1)).save(cart, fileName);
    }

    @Test
    void add_shouldReturnFalseWhenCartNotCreated() {
        // Arrange
        when(cartAppHelper.create()).thenReturn(Optional.empty());

        // Act
        boolean result = cardService.add();

        // Assert
        assertFalse(result);
        verify(cartAppRepository, never()).save(any(Cart.class), anyString());
    }

    @Test
    void makeReturn_shouldModifyAndSaveCarts() {
        // Arrange
        List<Cart> carts = List.of(new Cart());
        List<Cart> modifiedCarts = List.of(new Cart());
        when(cartAppRepository.load(fileName)).thenReturn(carts);
        when(cartAppHelper.returnBook(carts)).thenReturn(modifiedCarts);

        // Act
        boolean result = cardService.makeReturn();

        // Assert
        assertTrue(result);
        verify(cartAppRepository, times(1)).saveAll(modifiedCarts, fileName);
    }

    @Test
    void makeReturn_shouldReturnFalseWhenNoModifiedCarts() {
        // Arrange
        List<Cart> carts = List.of(new Cart());
        when(cartAppRepository.load(fileName)).thenReturn(carts);
        when(cartAppHelper.returnBook(carts)).thenReturn(new ArrayList<>());

        // Act
        boolean result = cardService.makeReturn();

        // Assert
        assertFalse(result);
        verify(cartAppRepository, never()).saveAll(anyList(), anyString());
    }

    @Test
    void print_shouldPrintCartListSuccessfully() {
        // Arrange
        List<Cart> carts = List.of(new Cart());
        when(cartAppRepository.load(fileName)).thenReturn(carts);
        when(cartAppHelper.printList(carts)).thenReturn(true);

        // Act
        boolean result = cardService.print();

        // Assert
        assertTrue(result);
        verify(cartAppHelper, times(1)).printList(carts);
    }

    @Test
    void print_shouldReturnFalseWhenCartListIsEmpty() {
        // Arrange
        List<Cart> emptyCarts = new ArrayList<>();
        when(cartAppRepository.load(fileName)).thenReturn(emptyCarts);
        when(cartAppHelper.printList(emptyCarts)).thenReturn(false);

        // Act
        boolean result = cardService.print();

        // Assert
        assertFalse(result);
        verify(cartAppHelper, times(1)).printList(emptyCarts);
    }

    @Test
    void list_shouldLoadCartsFromRepository() {
        // Arrange
        List<Cart> carts = List.of(new Cart());
        when(cartAppRepository.load(fileName)).thenReturn(carts);

        // Act
        List<Cart> result = cardService.list();

        // Assert
        assertEquals(carts, result);
        verify(cartAppRepository, times(1)).load(fileName);
    }
}
