package ee.ivkhkdev.interfaces;

import ee.ivkhkdev.model.Card;

public interface CartService extends Service<Card>{
    boolean makeReturn();
}
