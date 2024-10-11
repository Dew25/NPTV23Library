package ee.ivkhkdev;

import ee.ivkhkdev.handlers.BookService;
import ee.ivkhkdev.interfaces.InputProvider;
import ee.ivkhkdev.model.Book;

public class App {

    public static Book[] books = new Book[100];

    private final BookService bookService;
    private final InputProvider inputProvider;

    public App(InputProvider inputProvider,BookService bookService) {
        this.bookService = bookService;
        this.inputProvider = inputProvider;
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
            System.out.print("Введите номер задачи: ");
            int task = Integer.parseInt(inputProvider.getInput());
            switch (task) {
                case 0:
                    repeat=false;
                    break;
                case 1:
                    System.out.println("----- Добавление книги -----");
                    bookService.addBbook(inputProvider);
                    break;
                case 2:
                    System.out.println("----- Список книг -----");
                    System.out.println(bookService.printListBooks());
                    break;
                default:
                    System.out.println("Выберите задачу из списка!");
            }
            System.out.println("--------------------------------------");
        }while(repeat);
        System.out.println("До свидания :)");
    }
}
