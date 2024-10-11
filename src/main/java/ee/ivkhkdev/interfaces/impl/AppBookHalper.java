package ee.ivkhkdev.interfaces.impl;

import ee.ivkhkdev.App;
import ee.ivkhkdev.interfaces.BookProvider;
import ee.ivkhkdev.interfaces.InputProvider;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;

import java.util.Arrays;

public class AppBookHalper implements BookProvider {
    @Override
    public Book createBook(InputProvider inputProvider) {
        Book book = new Book();
        System.out.print("Название книги: ");
        book.setTitle(inputProvider.getInput());
        System.out.print("Количество авторов: ");
        int countAuthors = Integer.parseInt(inputProvider.getInput());
        for (int i = 0; i < countAuthors; i++) {
            System.out.printf("%d. автор: %n",i+1);
            Author author = new Author();
            System.out.print("Имя автора: ");
            author.setAuthorName(inputProvider.getInput());
            System.out.print("Фамилия автора: ");
            author.setAuthorSurname(inputProvider.getInput());
            book.getAuthors()[i]=author;
            System.out.println();
        }
        System.out.print("Год издания: ");
        book.setPublishedYear(Integer.parseInt(inputProvider.getInput()));
        return book;
    }

    @Override
    public String getList() {
        StringBuilder sbBooks = new StringBuilder();
        for (int i = 0; i < App.books.length; i++) {
            Book book = App.books[i];
            if(book == null) {continue;}
            StringBuilder sbAuthorsBook = new StringBuilder();
            for (Author author : book.getAuthors()) {
                if (author != null) {
                    sbAuthorsBook.append(author.getAuthorName()).append(", ").append(author.getAuthorSurname());
                }
            }
            if (book != null) {
                sbBooks.append(String.format("%d. %s. %s. %d%n",
                                    i + 1,
                                    book.getTitle(),
                                    sbAuthorsBook.toString(),
                                    book.getPublishedYear()
                            )
                );
            }
        }
        return sbBooks.toString();
    }
}
