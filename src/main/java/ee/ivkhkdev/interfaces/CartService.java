package ee.ivkhkdev.interfaces;

import ee.ivkhkdev.model.Cart;

public interface CartService extends AppService<Cart> {
    boolean makeReturn();
}
