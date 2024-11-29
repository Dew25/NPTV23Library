package ee.ivkhkdev.storage;

import ee.ivkhkdev.interfaces.AppRepository;
import ee.ivkhkdev.model.Book;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository implements AppRepository<Book> {
}
