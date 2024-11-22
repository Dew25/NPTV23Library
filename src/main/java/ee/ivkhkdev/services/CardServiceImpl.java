package ee.ivkhkdev.services;


import ee.ivkhkdev.interfaces.*;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.Card;
import ee.ivkhkdev.model.User;
import java.util.List;

public class CardServiceImpl implements CartService, Input {
    private final String fileName = "cards";
    private final CardAppHelper cardAppHelper;
    private final FileRepository<Card> storage;

    public CardServiceImpl(CardAppHelper cardAppHelper, Service<Book> bookService, Service<User> userService, FileRepository<Card> repository) {
        this.cardAppHelper = cardAppHelper;
        this.storage = repository;
    }

    @Override
    public boolean add() {
        try {
            Card card = cardAppHelper.create();
            if (card == null) {
                return false;
            }
            storage.save(card, fileName);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean edit() {
        return false;
    }

    @Override
    public boolean makeReturn() {
        List<Card> modifedCards = cardAppHelper.returnBook(storage.load(fileName));
        if (modifedCards == null) {
            return false;
        }
        storage.saveAll(modifedCards, fileName);
        return true;
    }

    @Override
    public boolean remove(Card entity) {
        return false;
    }

    @Override
    public boolean print() {
        return cardAppHelper.printList(storage.load(fileName));
    }

    @Override
    public List<Card> list() {
        return storage.load(fileName);
    }
}


