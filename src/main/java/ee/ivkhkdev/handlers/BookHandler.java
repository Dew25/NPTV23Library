package ee.ivkhkdev.handlers;

import ee.ivkhkdev.App;
import ee.ivkhkdev.interfaces.BookProvider;
import ee.ivkhkdev.interfaces.InputProvider;
import ee.ivkhkdev.model.Book;

public class BookHandler{
    private BookProvider bookProvider;
    private InputProvider inputProvider;
    public BookHandler(BookProvider bookProvider,InputProvider inputProvider) {
        this.bookProvider = bookProvider;
        this.inputProvider = inputProvider;
    }

    public void addBook() {
        Book book = bookProvider.newBook(inputProvider);
        for(int i = 0; i < App.books.length; i++){

        }
    }
}
