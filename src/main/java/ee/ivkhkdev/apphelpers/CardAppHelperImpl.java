package ee.ivkhkdev.apphelpers;

import ee.ivkhkdev.interfaces.CardAppHelper;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.Card;
import ee.ivkhkdev.model.User;

import java.time.LocalDate;
import java.util.List;

public class CardAppHelperImpl implements CardAppHelper {
    private final Service<Book> bookService;
    private final Service<User> userService;
    private final Input input;

    public CardAppHelperImpl(Input input,Service<Book> bookService, Service<User>userService) {
        this.input = input;
        this.bookService = bookService;
        this.userService = userService;

    }
    @Override
    public Card create() {
        try {
            Card card = new Card();
            bookService.print();
            System.out.print("Выберите номер книги: ");
            int numberBook = Integer.parseInt(input.getString());
            Book book = bookService.list().get(numberBook-1);
            card.setBook(book);
            userService.print();
            System.out.print("Выберите номер пользователя: ");
            int numberUser = Integer.parseInt(input.getString());
            User user = userService.list().get(numberUser-1);
            card.setUser(user);
            card.setBorrowedBookDate(LocalDate.now());
            return card;
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public List<Card> update(List<Card> entities) {
        return List.of();
    }

    @Override
    public boolean printList(List<Card> cards) {
        int counter = 0;
        System.out.println("--------- Список выданных книг --------");
        for(int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            if (card.getReturnedBookDate() == null) {
                System.out.printf("%d. %s. читает: %s %s%n",
                        i + 1,
                        card.getBook().getTitle(),
                        card.getUser().getFirstname(),
                        card.getUser().getLastname()
                );
                counter++;
            }
        }
        System.out.println("--------- Конец списка --------");
        if(counter == 0){
            return false;
        }
        return true;
    }

    /*
     *  список выданных книг через карты
     *  выбираем номер карты с нужной книгой
     *  добавляем в карту дату возврата
     *  возвращаем измененный список карт
     */
    @Override
    public List<Card> returnBook(List<Card> cards) {
        try {
            this.printList(cards);
            System.out.print("Выберите номер возвращаемой книги: ");
            int numberCard = Integer.parseInt(input.getString());
            cards.get(numberCard-1).setReturnedBookDate(LocalDate.now());
            return cards;
        }catch (Exception e){
            return null;
        }

    }
}
