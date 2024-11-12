package ee.ivkhkdev.apphelpers;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.interfaces.Service;

import java.util.ArrayList;
import java.util.List;

public class BookAppHelper implements AppHelper<Book>, Input{

    private final Service<Author> authorService;

    public BookAppHelper(Service<Author> authorService) {
        this.authorService = authorService;
    }

    @Override
    public Book create() {
        Book book = new Book();
        System.out.print("Название книги: ");
        book.setTitle(getString());
        authorService.print();
        System.out.print("Добавить нового автора (y/n): ");
        String answer = getString();
        if(answer.equalsIgnoreCase("y")) {return null;}
        System.out.print("Укажите количество авторов книги: ");
        int countAuthors = Integer.parseInt(getString());

        for (int i = 0; i < countAuthors; i++) {
            System.out.printf("Выбери номер автора из списка (%d из %d): ", i + 1, countAuthors);
            int numberAuthor = Integer.parseInt(getString());
            book.getAuthors().add(authorService.list().get(numberAuthor-1));
        }

        System.out.print("Год издания книги: ");
        book.setPublishedYear(Integer.parseInt(getString()));
        return book;
    }

    @Override
    public List<Book> update(List<Book> books) {
        /*
         * 1. вывести список книг
         * 2. выбрать номер книги
         * 3. вывести название книги
         * 4. получить от пользователя разрешения на замену (y/n)
         * 5. если y, получить новое имя и вызвать book.setTitle(...)
         * 6. вывести список авторов книгм
         * 7. получить от пользователя разрешение на замену (y/n)
         * 8. если y,
         * 9.   спросить у пользователя количество авторов в книге
         * 10.  вывести список авторов и в цикле попросить пользователя выбрать номер автора для книги
         *          и вставить в книгу авторов с помощью метода book.getAuthors.add(...)
         * 11. вывести год публикации книги
         * 12. получить от пользователя разрешение на замену (y/n)
         * 13. если y, получить новый год и вызвать book.setPublishedYear(...)
         * 14. вернуть список с измененными книгами
         */
        try {
            this.printList(books);
            System.out.print("Выберите номер книги: ");
            int numberBook = Integer.parseInt(getString());
            System.out.println("Название книги: "+ books.get(numberBook -1).getTitle());
            System.out.print("Изменить (y/n): ");
            String change = getString();
            if(change.equals("y")){
                System.out.print("Новое название: ");
                books.get(numberBook -1).setTitle(getString());
            }
            System.out.println("Список авторов книги");
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < books.get(numberBook-1).getAuthors().size(); i++){
                Author author = books.get(numberBook-1).getAuthors().get(i);
                System.out.printf("%d. %s %s%n", i+1, author.getAuthorName(),author.getAuthorSurname());
            }
            System.out.print("Изменить (y/n): ");
            change = getString();
            if(change.equals("y")){
                authorService.print();
                List<Author> bookAuthors = new ArrayList<>();
                System.out.print("Количество авторов: ");
                int numberAuthors = Integer.parseInt(getString());
                for (int i = 0; i < numberAuthors; i++) {
                    System.out.printf("Введите номер автора (%d из %d): ", i + 1, numberAuthors);
                    int numberAuthor = Integer.parseInt(getString());
                    bookAuthors.add(authorService.list().get(numberAuthor-1));
                }
                books.get(numberBook -1).setAuthors(bookAuthors);
            }
            System.out.println("Год издания книги: "+ books.get(numberBook -1).getPublishedYear());
            System.out.print("Изменить (y/n): ");
            change = getString();
            if(change.equals("y")){
                System.out.print("Новый год издания: ");
                books.get(numberBook -1).setPublishedYear(Integer.parseInt(getString()));
            }
            return books;
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public boolean printList(List<Book> books) {
        StringBuilder sbBooks = new StringBuilder();
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if(book == null) {continue;}
            StringBuilder sbAuthorsBook = new StringBuilder();
            for (Author author : book.getAuthors()) {
                if (author != null) {
                    sbAuthorsBook.append(author.getAuthorName()).append(" ").append(author.getAuthorSurname());
                }
            }
            sbBooks.append(String.format("%d. %s. %s. %d%n",
                                i + 1,
                                book.getTitle(),
                                sbAuthorsBook.toString(),
                                book.getPublishedYear()
                        )
            );
            System.out.println(sbBooks.toString());

        }
        return false;
    }
}
