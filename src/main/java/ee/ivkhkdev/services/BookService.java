package ee.ivkhkdev.services;

import ee.ivkhkdev.repository.AppHelper;
import ee.ivkhkdev.repository.Repository;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.repository.Service;

import java.util.List;

public class BookService implements Service<Book>, Repository<Book> {

    private final AppHelper<Book> appHelperBook;
    private final String fileName = "books";

    public BookService(AppHelper<Book> appHelperBook) {
        this.appHelperBook = appHelperBook;
    }

    @Override
    public boolean add() {
        try {
            Book book = appHelperBook.create();
            if(book == null) {return false;}
            save(book,fileName);
            return true;
        }catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean edit(Book book) {
        return false;
    }

    @Override
    public boolean remove(Book book) {
        return false;
    }

    @Override
    public void print() {
        appHelperBook.printList(load(fileName));
    }

    @Override
    public List<Book> list() {
        return load(fileName);
    }
}
