package ee.ivkhkdev.handlers;

import ee.ivkhkdev.App;
import ee.ivkhkdev.interfaces.BookProvider;
import ee.ivkhkdev.interfaces.InputProvider;
import ee.ivkhkdev.model.Book;

public class BookHandler {

    private final BookProvider bookProvider;

    public BookHandler(BookProvider bookProvider) {
       this.bookProvider = bookProvider;
    }

    public void addBbook(InputProvider inputProvider) {
        Book book = bookProvider.createBook(inputProvider);
        for (int i = 0; i < App.books.length; i++) {
            if(i == 0 && App.books[i] == null) {
                App.books[i] = book;
                System.out.println("Книга добавлена");
                break;
            }else if(i > 0 && App.books[i] == null) {
                App.books[i] = book;
                System.out.println("Книга добавлена");
                break;
            }
        }
    }
}
