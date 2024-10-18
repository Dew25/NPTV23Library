package ee.ivkhkdev;

import ee.ivkhkdev.model.User;
import ee.ivkhkdev.services.BookService;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.services.UserService;

public class App {

    public Book[] books;
    public User[] users;

    private final BookService bookService;
    private final UserService userService;
    private final Input input;

    public App(Input input, BookService bookService, UserService userService) {
        this.users = new User[100];
        this.books = new Book[100];
        this.bookService = bookService;
        this.input = input;
        this.userService = userService;
    }

    public void run() {
        System.out.println("------ Библиотека группы NPTV23 ------");
        System.out.println("--------------------------------------");
        boolean repeat=true;
        do{
            System.out.println("Список задач: ");
            System.out.println("0. Выйти из программы");
            System.out.println("1. Добавить книгу");
            System.out.println("2. Список книг");
            System.out.println("3. Добавить читателя");
            System.out.println("4. Список читателей");
            System.out.print("Введите номер задачи: ");
            int task = Integer.parseInt(input.getString());
            switch (task) {
                case 0:
                    repeat=false;
                    break;
                case 1:
                    System.out.println("----- Добавление книги -----");
                    if(bookService.add(input, books)){
                        System.out.println("Книга добавлена");
                    }else{
                        System.out.println("Книгу добавить не удолось");
                    }
                    break;
                case 2:
                    System.out.println("----- Список книг -----");
                    System.out.println(bookService.printList(books));
                    break;
                case 3:
                    System.out.println("----- Добавление читателя -----");
                    if(userService.add(input, users)){
                        System.out.println("Читатель добавлен");
                    }else{
                        System.out.println("Читателя добавить не удалось");
                    };
                    break;
                case 4:
                    System.out.println("----- Список читателей -----");
                    System.out.println(userService.printList(users));
                    break;
                default:
                    System.out.println("Выберите задачу из списка!");
            }
            System.out.println("--------------------------------------");
        }while(repeat);
        System.out.println("До свидания :)");
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }
}
