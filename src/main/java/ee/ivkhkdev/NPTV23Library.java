package ee.ivkhkdev;


import ee.ivkhkdev.repository.AppHelper;
import ee.ivkhkdev.apphelpers.AppHelperAuthor;
import ee.ivkhkdev.repository.Repository;
import ee.ivkhkdev.repository.Input;
import ee.ivkhkdev.apphelpers.AppHelperBook;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.services.AuthorService;
import ee.ivkhkdev.services.BookService;
import ee.ivkhkdev.repository.Service;

import java.util.ArrayList;
import java.util.List;


public class NPTV23Library {

    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        List<Author> authors = new ArrayList<>();


        AppHelper<Author> appHelperAuthor = new AppHelperAuthor();
        Service<Author> authorService = new AuthorService(appHelperAuthor);
        AppHelper<Book> appHelperBook = new AppHelperBook(authorService);
        Service<Book> bookService = new BookService(appHelperBook);
        App app = new App(bookService, authorService);
        app.run();
    }

}