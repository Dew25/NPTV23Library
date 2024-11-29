package ee.ivkhkdev.services;


import ee.ivkhkdev.interfaces.CartAppHelper;
import ee.ivkhkdev.interfaces.CartService;
import ee.ivkhkdev.interfaces.*;
import ee.ivkhkdev.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CartService {
    private final String fileName = "cards";
    @Autowired private Input input;
    @Autowired private CartAppHelper cardAppHelper;
    @Autowired private AppRepository<Cart> cartAppRepository;

    @Override
    public boolean add() {
        try {
            Optional<Cart> card = cardAppHelper.create();
            if (card.isPresent()) {
                cartAppRepository.save(card.get(), fileName);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean edit() {
        return false;
    }

    @Override
    public boolean makeReturn() {
        List<Cart> modifedCards = cardAppHelper.returnBook(cartAppRepository.load(fileName));
        if (modifedCards.isEmpty()) {
            return false;
        }
        cartAppRepository.saveAll(modifedCards, fileName);
        return true;
    }

    @Override
    public boolean remove(Cart entity) {
        return false;
    }

    @Override
    public boolean print() {
        return cardAppHelper.printList(this.list());
    }

    @Override
    public List<Cart> list() {
        return cartAppRepository.load(fileName);
    }
}


