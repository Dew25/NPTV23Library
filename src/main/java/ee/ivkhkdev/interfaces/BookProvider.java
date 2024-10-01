package ee.ivkhkdev.interfaces;

import ee.ivkhkdev.model.Book;

public interface BookProvider {
    Book newBook(InputProvider inputProvider);
}
