package ee.ivkhkdev;


import ee.ivkhkdev.interfaces.CartService;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.interfaces.AppService;
import ee.ivkhkdev.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App implements CommandLineRunner {
    @Autowired private Input input;
    @Autowired private AppService<Book> bookService;
    @Autowired private AppService<Author> authorService;
    @Autowired private AppService<User> userService;
    @Autowired private CartService cardService;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        System.out.println("------ Библиотека группы NPTV23 ------");
        System.out.println("--------------------------------------");
        boolean repeat=true;
        do{
            System.out.println("Список задач: ");
            System.out.println("0. Выйти из программы");
            System.out.println("1. Добавить книгу");
            System.out.println("2. Изменить книгу");
            System.out.println("3. Список книг");
            System.out.println("4. Добавить автора");
            System.out.println("5. Изменить автора");
            System.out.println("6. Добавить читателя");
            System.out.println("7. Список читателей");
            System.out.println("8. Изменить читателя");
            System.out.println("9. Выдать книгу");
            System.out.println("10. Вернуть книгу");

            System.out.print("Введите номер задачи: ");
            int task = Integer.parseInt(input.getString());
            switch (task) {
                case 0:
                    repeat = false;
                    break;
                case 1:
                    System.out.println("----- Добавление книги -----");

                    if (bookService.add()) {
                        System.out.println("Книга добавлена");
                    } else {
                        System.out.println("Книгу добавить не удалось");
                    }
                    break;
                case 2:
                    System.out.println("----- Изменение книги -----");
                    if (bookService.edit()) {
                        System.out.println("Книга изменена");
                    } else {
                        System.out.println("Книгу изменить не удалось");
                    }
                    break;
                case 3:
                    System.out.println("----- Список книг -----");
                    bookService.print();
                    break;
                case 4:
                    System.out.println("----- Добавление автора -----");
                    if (authorService.add()) {
                        System.out.println("Автор добавлен");
                    } else {
                        System.out.println("Автора добавить не удалось");
                    }
                    break;
                case 5:
                    System.out.println("----- Изменение автора -----");
                    if (authorService.edit()) {
                        System.out.println("Автор изменен");
                    } else {
                        System.out.println("Автора изменить не удалось");
                    }
                    break;
                case 6:
                    System.out.println("----- Добавление читателя -----");
                    if (userService.add()) {
                        System.out.println("Читатель добавлен");
                    } else {
                        System.out.println("Читателя добавить не удалось");
                    }
                    break;
                case 7:
                   System.out.println("----- Список читателей -----");
                   userService.print();
                   break;
                case 8:
                    System.out.println("----- Изменение читателя -----");
                    if (userService.edit()) {
                        System.out.println("Читатель изменен");
                    } else {
                        System.out.println("Читателя изменить не удалось");
                    }
                    break;
                case 9:
                    System.out.println("------Выдача книги------");
                    if (cardService.add()) {
                        System.out.println("Книга выдана");
                    }else {
                        System.out.println("Книгу выдать не удалось");
                    }
                    break;
                case 10:
                    System.out.println("------Возварат книги------");
                    if (cardService.makeReturn()) {
                        System.out.println("Книга возвращена");
                    }else {
                    System.out.println("Книгу возвратить не удалось");
                    }
                    break;
                default:
                    System.out.println("Выберите задачу из списка!");
            }
            System.out.println("--------------------------------------");
        }while(repeat);
        System.out.println("До свидания :)");
    }



}
