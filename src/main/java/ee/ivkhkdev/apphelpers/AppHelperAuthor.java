package ee.ivkhkdev.apphelpers;

import ee.ivkhkdev.repository.AppHelper;
import ee.ivkhkdev.repository.Input;
import ee.ivkhkdev.model.Author;

import java.util.List;


public class AppHelperAuthor implements AppHelper<Author>,Input{


    public AppHelperAuthor() {

    }

    @Override
    public Author create() {
        try {
            Author author = new Author();
            System.out.print("Имя автора: ");
            author.setAuthorName(getString());
            System.out.println("Фамилия автора: ");
            author.setAuthorSurname(getString());
            return author;
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }

    }

    @Override
    public void printList(List<Author> authors) {
        System.out.println("---------- Список авторов --------");
        for(int i=0;i<authors.size();i++) {
            Author author = authors.get(i);
            System.out.printf("%d. %s %s%n", i+1,author.getAuthorName(),author.getAuthorSurname());
        }
    }
}
