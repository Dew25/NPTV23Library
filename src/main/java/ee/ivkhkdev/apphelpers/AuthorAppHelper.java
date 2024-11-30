package ee.ivkhkdev.apphelpers;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AuthorAppHelper implements AppHelper<Author>{

    @Autowired private Input input;

    @Override
    public Optional<Author> create() {
        try {
            Author author = new Author();
            System.out.print("Имя автора: ");
            author.setAuthorName(input.getString());
            System.out.print("Фамилия автора: ");
            author.setAuthorSurname(input.getString());
            return Optional.of(author);
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Optional.empty();
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
            int numberAuthor = Integer.parseInt(input.getString());
            System.out.println("Имя автора: "+ authors.get(numberAuthor -1).getAuthorName());
            System.out.print("Изменить (y/n): ");
            String change = input.getString();
            if(change.equals("y")){
                System.out.print("Новое имя автора: ");
                authors.get(numberAuthor -1).setAuthorName(input.getString());
            }
            System.out.println("Фамилия автора: "+ authors.get(numberAuthor -1).getAuthorSurname());
            System.out.print("Изменить (y/n): ");
            change = input.getString();
            if(change.equals("y")){
                System.out.print("Новая фамилия автора: ");
                authors.get(numberAuthor -1).setAuthorSurname(input.getString());
            }
            return authors;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean printList(List<Author> authors) {
        try {
            if(authors.isEmpty()){
                System.out.println("Список авторов пуст");
                return false;
            }
            System.out.println("---------- Список авторов --------");
            for(int i=0;i<authors.size();i++) {
                Author author = authors.get(i);
                System.out.printf("%d. %s %s%n", i+1,author.getAuthorName(),author.getAuthorSurname());
            }
            return true;
        }catch (Exception e){
            System.out.println("Error authroAppHelper.printList(authors"+e.getMessage());
        }
        return false;
    }
}