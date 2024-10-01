package ee.ivkhkdev.interfaces.impl;

import ee.ivkhkdev.interfaces.BookProvider;
import ee.ivkhkdev.interfaces.InputProvider;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;

public class InputBook implements BookProvider {
    @Override
    public Book newBook(InputProvider inputProvider) {
        Book book = new Book();
        System.out.print("Название книги: ");
        book.setTitle(inputProvider.getInput());
        System.out.print("Авторы: ");
        System.out.print("Количество авторов: ");
        int countAuthors = Integer.parseInt(inputProvider.getInput());
        for(int i = 0; i < countAuthors; i++){
            Author author = new Author();
            author.setAuthorName(inputProvider.getInput());
            author.setAuthorSurname(inputProvider.getInput());
            book.getAuthors()[i]=author;
        }
        book.setPublishedYear(Integer.parseInt(inputProvider.getInput()));
        return book;
    }
}
