package ee.ivkhkdev.storage;

import ee.ivkhkdev.interfaces.AppRepository;
import ee.ivkhkdev.model.Cart;
import org.springframework.stereotype.Repository;

@Repository
public class CartRepository implements AppRepository<Cart> {
}
