package ee.ivkhkdev;



import ee.ivkhkdev.apphelpers.CardAppHelperImpl;
import ee.ivkhkdev.apphelpers.UserAppHelper;
import ee.ivkhkdev.input.ConsoleInput;
import ee.ivkhkdev.interfaces.*;
import ee.ivkhkdev.apphelpers.AuthorAppHelper;
import ee.ivkhkdev.apphelpers.BookAppHelper;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.Card;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.services.AuthorService;
import ee.ivkhkdev.services.BookService;
import ee.ivkhkdev.services.CardServiceImpl;
import ee.ivkhkdev.services.UserService;
import ee.ivkhkdev.storage.Storage;


public class NPTV23Library {

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        AppHelper<Author> authorAppHelper = new AuthorAppHelper(input);
        AppHelper<User> userAppHelper = new UserAppHelper(input);
        FileRepository<Author>authorStorage = new Storage<Author>();
        FileRepository<Book>bookStorage = new Storage<Book>();
        FileRepository<User> userStorage = new Storage<User>();
        FileRepository<Card> cardStorage = new Storage<Card>();
        Service<User> userService = new UserService (userAppHelper,userStorage);
        Service<Author> authorService = new AuthorService(authorAppHelper,authorStorage);
        AppHelper<Book> bookAppHelper = new BookAppHelper(input,authorService);
        Service<Book> bookService = new BookService(bookAppHelper,bookStorage);
        CardAppHelper cardAppHelper = new CardAppHelperImpl(input, bookService,userService);
        CartService cardService = new CardServiceImpl(cardAppHelper, bookService, userService, cardStorage);
        App app = new App(input, bookService, authorService,userService,cardService);
        app.run();
    }

}