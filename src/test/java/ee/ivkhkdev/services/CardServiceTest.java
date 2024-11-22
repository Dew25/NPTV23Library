package ee.ivkhkdev.services;

import ee.ivkhkdev.apphelpers.CardAppHelper;
import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.FileRepository;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.Card;
import ee.ivkhkdev.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CardServiceTest {
    @Mock
    private AppHelper<Card> cardAppHelper;
    @Mock private Service<Book> bookService;
    @Mock private Service<User> userService;
    @Mock private FileRepository<Card> storage;
    private CardServiceImpl cardService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Инициализация моков
        cardService = new CardServiceImpl(cardAppHelper, bookService, userService, storage);
    }
    @Test
    void testAddCardSuccessfully() {
        // Подготовка данных
        Book book = new Book();
        book.setTitle("Test Book");

        User user = new User();
        user.setFirstname("John");
        user.setLastname("Doe");

        // Создание карты
        Card card = new Card();
        card.setBook(book);
        card.setUser(user);

        // Мокируем метод create() для cardAppHelper
        when(cardAppHelper.create()).thenReturn(card);

        // Мокируем работу с хранилищем
        doNothing().when(storage).save(card, "cards");

        // Проверка, что метод add возвращает true
        boolean result = cardService.add();

        assertTrue(result);
        verify(storage, times(1)).save(card, "cards"); // Проверяем, что save был вызван
    }
    @Test
    void testAddCardFailed() {
        // Мокируем, что create() вернет null
        when(cardAppHelper.create()).thenReturn(null);

        // Проверка, что метод add возвращает false
        boolean result = cardService.add();

        assertFalse(result);
        verify(storage, never()).save(any(), eq("cards")); // Проверяем, что save не был вызван
    }

    @Test
    void testReturnBookSuccessfully() {
        // Подготовка данных
        Book book = new Book();
        book.setTitle("Test Book");

        User user = new User();
        user.setFirstname("John");
        user.setLastname("Doe");

        Card card = new Card();
        card.setBook(book);
        card.setUser(user);
        card.setReturnedBookDate(null);

        List<Card> cards = List.of(card);

        // Мокируем загрузку списка карт из хранилища
        when(storage.load("cards")).thenReturn(cards);

        // Создаем шпион на CardAppHelper (реальный объект)
        CardAppHelper cardAppHelperSpy = spy(new CardAppHelper(bookService, userService));

        // Мокируем метод returnBook() в CardAppHelper через spy
        List<Card> modifiedCards = List.of(card);
        doReturn(modifiedCards).when(cardAppHelperSpy).returnBook(cards);

        // Мокируем storage
        doNothing().when(storage).saveAll(modifiedCards, "cards");

        // Передаем шпион в сервис
        cardService = new CardServiceImpl(cardAppHelperSpy, bookService, userService, storage);

        // Проверка, что возврат книги прошел успешно
        boolean result = cardService.returnBook();

        assertTrue(result);
        verify(storage, times(1)).saveAll(modifiedCards, "cards");
    }
}