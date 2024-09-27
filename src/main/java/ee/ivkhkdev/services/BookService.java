package ee.ivkhkdev.services;


import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;

public class BookService {
    public Book createBook() {
        Author author = new Author("Лев","Толстой");
        Book book = new Book();
        book.setTitle("Война и мир");
        book.setPublishedYear(2000);
        book.getAuthors()[0] = author;
        return book;
    }
}
