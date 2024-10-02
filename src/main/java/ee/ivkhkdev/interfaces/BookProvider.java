package ee.ivkhkdev.interfaces;

import ee.ivkhkdev.model.Book;

public interface BookProvider {
    Book createBook(InputProvider inputProvider);
}
