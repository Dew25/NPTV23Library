package ee.ivkhkdev.apphelpers;

import ee.ivkhkdev.interfaces.CartAppHelper;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.interfaces.AppService;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.Cart;
import ee.ivkhkdev.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CartAppHelperImpl implements CartAppHelper {

    @Autowired private AppService<Book> bookAppService;
    @Autowired private AppService<User> userAppService;
    @Autowired private Input input;

    @Override
    public Optional<Cart> create() {
        try {
            Cart cart = new Cart();
            bookAppService.print();
            System.out.print("Выберите номер книги: ");
            int numberBook = Integer.parseInt(input.getString());
            Book book = bookAppService.list().get(numberBook-1);
            cart.setBook(book);
            userAppService.print();
            System.out.print("Выберите номер пользователя: ");
            int numberUser = Integer.parseInt(input.getString());
            User user = userAppService.list().get(numberUser-1);
            cart.setUser(user);
            cart.setBorrowedBookDate(LocalDate.now());
            return Optional.of(cart);
        }catch (Exception e){
            return Optional.empty();
        }

    }

    @Override
    public List<Cart> update(List<Cart> entities) {
        return List.of();
    }

    @Override
    public boolean printList(List<Cart> cards) {
        try {
            if(cards.isEmpty()){
                System.out.println("Нет выданных книг");
                return false;
            }
            System.out.println("--------- Список выданных книг --------");
            for(int i = 0; i < cards.size(); i++) {
                Cart card = cards.get(i);
                if (card.getReturnedBookDate() == null) {
                    System.out.printf("%d. %s. читает: %s %s%n",
                            i + 1,
                            card.getBook().getTitle(),
                            card.getUser().getFirstname(),
                            card.getUser().getLastname()
                    );
                }
            }
            System.out.println("--------- Конец списка --------");
            return true;
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        return false;
    }

    /*
     *  список выданных книг через карты
     *  выбираем номер карты с нужной книгой
     *  добавляем в карте дату возврата
     *  возвращаем измененный список карт
     */
    @Override
    public List<Cart> returnBook(List<Cart> carts) {
        try {
            if(carts.isEmpty()){
                System.out.println("Нет выданных книг");
                new ArrayList<>();
            }
            this.printList(carts);
            System.out.print("Выберите номер возвращаемой книги: ");
            int numberCard = Integer.parseInt(input.getString());
            carts.get(numberCard-1).setReturnedBookDate(LocalDate.now());
            return carts;
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        return new ArrayList<>();
    }
}
