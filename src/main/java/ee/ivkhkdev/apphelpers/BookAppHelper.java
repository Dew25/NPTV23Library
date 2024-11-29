package ee.ivkhkdev.apphelpers;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.interfaces.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BookAppHelper implements AppHelper<Book>{

    @Autowired private AppService<Author> authorService;
    @Autowired private Input input;

    @Override
    public Optional<Book> create() {
        try {
            Book book = new Book();
            System.out.print("Название книги: ");
            book.setTitle(input.getString());
            List<Author> bookAuthors = this.insertAuthors();
            if(bookAuthors.isEmpty()){
                return Optional.empty();
            };
            book.setAuthors(bookAuthors);
            System.out.print("Год издания книги: ");
            book.setPublishedYear(numberInput());
            return Optional.of(book);
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        return Optional.empty();
    }
    private List<Author> insertAuthors(){
        try {
            if(!authorService.print()){
                System.out.println("Список авторов пуст");
                return new ArrayList<>();
            };
            System.out.print("Если в списке нет нужных авторов, нажмите \"y\", чтобы прервать процес добавления книги.");
            System.out.println("Для продолжения нажмите любой другой символ: ");
            String answer = input.getString();
            if(answer.equalsIgnoreCase("y")) {
                return new ArrayList<>();
            }
            List<Author> bookAuthors = new ArrayList<>();
            System.out.print("Укажите количество авторов книги: ");
            int countAuthors = numberInput();
            for (int i = 0; i < countAuthors; i++) {
                System.out.printf("Выбери номер автора из списка (%d из %d): ", i + 1, countAuthors);
                int numberAuthor = Integer.parseInt(input.getString());
                bookAuthors.add(authorService.list().get(numberAuthor-1));
            }
            return bookAuthors;
        }catch (Exception e){
            System.out.println("Error insertAuthors():"+e.getMessage());
        }
        return new ArrayList<>();
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
         *
         * Используем шаблон извлечение метода, т.е разбиваем метод на
         * несколько простых методов.
         * Это позволяет легко понять логику изменения книги
         */
        try {
            if(!this.printList(books)){
                return new ArrayList<>();
            }
            System.out.print("Выберите номер книги: ");
            int numberBook = numberInput();
            Book book = books.get(numberBook-1);
            book.setTitle(modifyTitle(book.getTitle()));
            List<Author>newListBookAuthors = modifyAuthors(book.getAuthors());
            if(!newListBookAuthors.isEmpty()){
                book.setAuthors(newListBookAuthors);
            }
            book.setPublishedYear(modifyPublishedYear(book.getPublishedYear()));
            return books;
        }catch (Exception e){
            return new ArrayList<>();
        }

    }
    private String modifyTitle(String bookTitle){
        System.out.println("Название книги: "+ bookTitle);
        System.out.print("Изменить (y/n): ");
        String change = input.getString();
        if(change.equalsIgnoreCase("y")){
            System.out.print("Новое название: ");
            bookTitle=input.getString();
        }
        return bookTitle;
    }
    private List<Author> modifyAuthors(List<Author>oldBookAuthors){
        if(!this.printListBookAuthors(oldBookAuthors)){
            System.out.println("Нет списка авторов книги");
            return new ArrayList<>();
        }
        System.out.print("Изменить (y/n): ");
        String yesModify = input.getString();
        if(yesModify.equals("y")){
            List<Author> modifyBookAuthors = new ArrayList<>();
            authorService.print();
            System.out.print("Новое количество авторов: ");
            int numberAuthors = Integer.parseInt(input.getString());
            for (int i = 0; i < numberAuthors; i++) {
                System.out.printf("Введите номер автора (%d из %d): ", i + 1, numberAuthors);
                int numberAuthor = Integer.parseInt(input.getString());
                modifyBookAuthors.add(authorService.list().get(numberAuthor-1));
            }
            return modifyBookAuthors;
        }
        return oldBookAuthors;
    }
    private boolean printListBookAuthors(List<Author>bookAuthors){
        try {
            System.out.println("Список авторов книги");
            for(int i = 0; i < bookAuthors.size(); i++){
                Author author = bookAuthors.get(i);
                System.out.printf("%d. %s %s%n",
                        i+1,
                        author.getAuthorName(),
                        author.getAuthorSurname()
                );
            }
            return true;
        }catch (Exception e){
            System.out.println("Error printBookAuthors(): "+e.getMessage());
        }
        return false;
    }
    private int modifyPublishedYear(int oldPublishedYear){
        System.out.printf("Год издания книги: %d%n", oldPublishedYear);
        System.out.print("Изменить (y/n): ");
        String yesModify = input.getString();
        if(yesModify.equals("y")){
            System.out.print("Другой год издания: ");
            return numberInput();
        }
        return oldPublishedYear;
    }
    @Override
    public boolean printList(List<Book> books) {
        try {
            if(books.isEmpty()){
                System.out.println("Список книг пуст");
                return false;
            }
            for (int i = 0; i < books.size(); i++) {
                String stringListBookAuthors = convertListBookAuthorsToString(books.get(i).getAuthors());
                System.out.printf("%d. %s. %s. %d%n",
                        i + 1,
                        books.get(i).getTitle(),
                        stringListBookAuthors,
                        books.get(i).getPublishedYear()
                );
            }
            return true;
        }catch (Exception e){
            System.out.println("Error printList(books):"+e.getMessage());
        }
        return false;
    }
    private String convertListBookAuthorsToString(List<Author> bookAuthors){
        StringBuilder sbAuthorsBook = new StringBuilder();
        for (Author author : bookAuthors) {
            if (author != null) {
                sbAuthorsBook.append(author.getAuthorName())
                             .append(" ")
                             .append(author.getAuthorSurname());
            }
        }
        return sbAuthorsBook.toString();
    }
    private int numberInput(){
        do{
            try {
                int number = Integer.parseInt(input.getString());
                return number;
            }catch (Exception e){
                System.out.print("Введите номер цифрами: ");
            }
        }while (true);
    }
}
