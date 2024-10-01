package ee.ivkhkdev;

import ee.ivkhkdev.handlers.BookHandler;
import ee.ivkhkdev.interfaces.BookProvider;
import ee.ivkhkdev.interfaces.InputProvider;
import ee.ivkhkdev.model.Book;


import java.util.Scanner;

public class App {
    public static Book[] books = new Book[10];
    private final BookHandler bookHandler;
    private final InputProvider inputProvider;
    private final BookProvider bookProvider;

    public App(BookHandler bookHandler,
               InputProvider inputProvider,
               BookProvider bookProvider) {
        this.bookHandler = bookHandler;
        this.inputProvider = inputProvider;
        this.bookProvider = bookProvider;
    }
    public void run() {
        System.out.println("---- Библиотека группы NPTV23 -----");
        System.out.println("-----------------------------------");
        boolean repeat=true;
        do{
            System.out.println("Список задач: ");
            System.out.println("0. Выйти из программы");
            System.out.println("1. Добавить книгу");
            System.out.print("Введите номер задачи: ");
            int task = Integer.parseInt(inputProvider.getInput());
            switch (task) {
                case 0:
                    repeat=false;
                    break;
                case 1:
                    bookHandler.addBook();
                    break;
                default:
                    System.out.println("Выберите задачу из списка!");
            }
            System.out.println("-----------------------------------");
        }while(repeat);
        System.out.println("До свидания! :)");
    }
}
