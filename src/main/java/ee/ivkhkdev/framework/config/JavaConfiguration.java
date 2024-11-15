package ee.ivkhkdev.framework.config;

import ee.ivkhkdev.App;
import ee.ivkhkdev.apphelpers.AuthorAppHelper;
import ee.ivkhkdev.apphelpers.BookAppHelper;
import ee.ivkhkdev.apphelpers.CardAppHelper;
import ee.ivkhkdev.apphelpers.UserAppHelper;
import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.FileRepository;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.Card;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.services.AuthorService;
import ee.ivkhkdev.services.BookService;
import ee.ivkhkdev.services.CardService;
import ee.ivkhkdev.services.UserService;
import ee.ivkhkdev.storage.Storage;

import java.util.HashMap;
import java.util.Map;

public class JavaConfiguration implements Configuration {
    private Map<String,Object> map = new HashMap<>();

    public JavaConfiguration() {
        init();
    }
    private void init(){
        map.put("authorAppHelper",new AuthorAppHelper ());
        map.put("userAppHelper",new UserAppHelper());
        map.put("authorStorage",new Storage<Author>());
        map.put("bookStorage",new Storage<Book> ());
        map.put("userStorage",new Storage<User> ());
        map.put("cardStorage",new Storage<Card> ());
        map.put("userService",new UserService((AppHelper<User>) map.get("userAppHelper"), (FileRepository<User>) map.get("userStorage")));
        map.put("authorService",new AuthorService((AppHelper<Author>) map.get("authorAppHelper"), (FileRepository<Author>) map.get("authorStorage")));
        map.put("bookAppHelper",new BookAppHelper((Service<Author>) map.get("authorService")));
        map.put("bookService",new BookService((AppHelper<Book>) map.get("bookAppHelper"), (FileRepository<Book>) map.get("bookStorage")));
        map.put("cardAppHelper",new CardAppHelper((Service<Book>) map.get("bookService"), (Service<User>) map.get("userService")));
        map.put("cardService",new CardService((AppHelper<Card>) map.get("cardAppHelper"), (Service<Book>) map.get("bookService"), (Service<User>) map.get("userService"), (FileRepository<Card>) map.get("cardStorage")));
        map.put("app",new App((Service<Book>) map.get("bookService"), (Service<Author>) map.get("authorService"), (Service<User>) map.get("userService"), (Service<Card>) map.get("cardService")));
    }

    @Override
    public Object getObject(String name) {
        return  map.get(name);
    }
}
