package ee.ivkhkdev.apphelpers;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Author;

import java.util.List;


public class AuthorAppHelper implements AppHelper<Author>, Input {

    public AuthorAppHelper() {

    }


    @Override
    public Author create() {
        try {
            Author author = new Author();
            System.out.print("Имя автора: ");
            author.setAuthorName(getString());
            System.out.print("Фамилия автора: ");
            author.setAuthorSurname(getString());
            return author;
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }

    }

    @Override
    public List<Author> update(List<Author> authors) {
        try {
            /**
             * 1. вывести список авторов
             * 2. выбрать номер автора
             * 3. вывести имя автора
             * 4. получить от пользователя разрешения на замену (y/n)
             * 5. если y, получить новое имя и вызвать author.setFirstname(...)
             * 6. если n, вывести фамилию
             * 7. получить от пользователя разрешение на замену (y/n)
             * 8. если y, получить новую фамилию и вызвать autho.setLastname(...)
             * 9. вернуть список с измененным автором
             */
            this.printList(authors);
            System.out.print("Выберите номер автора: ");
            int numberAuthor = Integer.parseInt(getString());
            System.out.println("Имя автора: "+ authors.get(numberAuthor -1).getAuthorName());
            System.out.print("Изменить (y/n): ");
            String change = getString();
            if(change.equals("y")){
                System.out.print("Новое имя автора: ");
                authors.get(numberAuthor -1).setAuthorName(getString());
            }
            System.out.println("Фамилия автора: "+ authors.get(numberAuthor -1).getAuthorSurname());
            System.out.print("Изменить (y/n): ");
            change = getString();
            if(change.equals("y")){
                System.out.print("Новая фамилия автора: ");
                authors.get(numberAuthor -1).setAuthorSurname(getString());
            }
            return authors;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean printList(List<Author> authors) {
        System.out.println("---------- Список авторов --------");
        for(int i=0;i<authors.size();i++) {
            Author author = authors.get(i);
            System.out.printf("%d. %s %s%n", i+1,author.getAuthorName(),author.getAuthorSurname());
        }
        return false;
    }
}