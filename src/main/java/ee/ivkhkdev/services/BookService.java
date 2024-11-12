package ee.ivkhkdev.services;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.interfaces.FileRepository;
import ee.ivkhkdev.storage.Storage;

import java.io.File;
import java.util.List;

public class BookService implements Service<Book> {

    private final AppHelper<Book> bookAppHelper;
    private final String fileName="books";
    private final FileRepository<Book>  storage;

    public BookService(AppHelper<Book> bookAppHelper, FileRepository<Book> storage) {
        this.bookAppHelper = bookAppHelper;
        this.storage = storage;
    }

    @Override
    public boolean add() {
        try {
            Book book = bookAppHelper.create();
            if(book == null) {return false;}
            storage.save(book,fileName);
            return true;
        }catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean edit() {
        try {
            List<Book> modifedBooks = bookAppHelper.update(list());
            if(modifedBooks == null && modifedBooks.isEmpty()){
                return false;
            }
            storage.saveAll(modifedBooks,fileName);
            return true;
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }

        return false;
    }


    @Override
    public boolean remove(Book book) {
        return false;
    }

    @Override
    public boolean print() {
       return bookAppHelper.printList(this.list());
    }

    @Override
    public List<Book> list() {
        return storage.load(fileName);
    }
}
