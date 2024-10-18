package ee.ivkhkdev.services;

import ee.ivkhkdev.App;
import ee.ivkhkdev.interfaces.BookProvider;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Book;

public class BookService {

    private final BookProvider bookProvider;

    public BookService(BookProvider bookProvider) {
       this.bookProvider = bookProvider;
    }

    public boolean add(Input input, Book[] books) {
        try {
            Book book = bookProvider.create(input);
            if(book == null) return false;
            for (int i = 0; i < books.length; i++) {
                if( books[i] == null) {
                    books[i] = book;
                    System.out.println("Книга добавлена");
                    break;
                }

            }
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public String printList(Book[] books) {
        return bookProvider.getList(books);
    }
}
