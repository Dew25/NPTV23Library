package ee.ivkhkdev.services;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.AppService;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.interfaces.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements AppService<Book> {
    private final String fileName="books";
    @Autowired private AppHelper<Book> bookAppHelper;
    @Autowired private AppRepository<Book> bookAppRepository;

    @Override
    public boolean add() {
        try {
            Optional<Book> book = bookAppHelper.create();
            if(book.isPresent()) {
                bookAppRepository.save(book.get(),fileName);
                return true;
            }
        }catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean edit() {
        try {
            List<Book> modifedBooks = bookAppHelper.update(list());
            if(modifedBooks.isEmpty()){
                return false;
            }
            bookAppRepository.saveAll(modifedBooks,fileName);
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
        return bookAppRepository.load(fileName);
    }
}
