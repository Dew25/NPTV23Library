package ee.ivkhkdev;


import ee.ivkhkdev.apphelpers.CardAppHelper;
import ee.ivkhkdev.apphelpers.UserAppHelper;
import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.apphelpers.AuthorAppHelper;
import ee.ivkhkdev.apphelpers.BookAppHelper;
import ee.ivkhkdev.interfaces.FileRepository;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.Card;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.services.AuthorService;
import ee.ivkhkdev.services.BookService;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.services.CardService;
import ee.ivkhkdev.services.UserService;
import ee.ivkhkdev.storage.Storage;


public class NPTV23Library {

    public static void main(String[] args) {
        AppHelper<Author> authorAppHelper = new AuthorAppHelper();
        AppHelper<User> userAppHelper = new UserAppHelper();
        FileRepository<Author>authorStorage = new Storage<Author>();
        FileRepository<Book>bookStorage = new Storage<Book>();
        FileRepository<User> userStorage = new Storage<User>();
        FileRepository<Card> cardStorage = new Storage<Card>();
        Service<User> userService = new UserService (userAppHelper,userStorage);
        Service<Author> authorService = new AuthorService(authorAppHelper,authorStorage);
        AppHelper<Book> bookAppHelper = new BookAppHelper(authorService);
        Service<Book> bookService = new BookService(bookAppHelper,bookStorage);
        AppHelper<Card> cardAppHelper = new CardAppHelper(bookService,userService);
        Service<Card> cardService = new CardService(cardAppHelper, bookService, userService, cardStorage);
        App app = new App(bookService, authorService,userService,cardService);
        app.run();
    }

}