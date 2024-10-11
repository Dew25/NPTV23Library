package ee.ivkhkdev.handlers;

import ee.ivkhkdev.App;
import ee.ivkhkdev.interfaces.BookProvider;
import ee.ivkhkdev.interfaces.InputProvider;
import ee.ivkhkdev.model.Book;

public class BookService {

    private final BookProvider bookProvider;

    public BookService(BookProvider bookProvider) {
       this.bookProvider = bookProvider;
    }

    public void addBbook(InputProvider inputProvider) {
        Book book = bookProvider.createBook(inputProvider);
        for (int i = 0; i < App.books.length; i++) {
            if( App.books[i] == null) {
                App.books[i] = book;
                System.out.println("Книга добавлена");
                break;
            }
        }
    }

    public String  printListBooks() {
        return bookProvider.getList();
    }
}
