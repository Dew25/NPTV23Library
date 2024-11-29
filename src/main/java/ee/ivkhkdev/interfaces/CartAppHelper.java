package ee.ivkhkdev.interfaces;

import ee.ivkhkdev.model.Cart;

import java.util.List;

public interface CartAppHelper extends AppHelper<Cart> {
    List<Cart> returnBook(List<Cart> carts);
}
 