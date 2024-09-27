package ee.ivkhkdev;

import ee.ivkhkdev.services.BookService;

import java.util.Scanner;

public class App {
    private final Scanner scanner;

    public App() {
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean repeat=true;
        do{
            System.out.println("Список задач: ");
            System.out.println("0. Выйти из программы");
            System.out.println("1. Добавить книгу");
            System.out.print("Введите номер задачи: ");
            int task = scanner.nextInt();  scanner.nextLine();
            switch (task) {
                case 0:
                    repeat=false;
                    break;
                case 1:
                    System.out.println("----- Добавление книги -----");
                    BookService bookService = new BookService();
                    break;
                default:
                    System.out.println("Выберите задачу из списка!");
            }
        }while(repeat);
        System.out.println("Досвидания :)");
    }
}
