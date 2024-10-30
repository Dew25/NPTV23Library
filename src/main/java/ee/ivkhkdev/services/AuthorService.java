package ee.ivkhkdev.services;

import ee.ivkhkdev.repository.AppHelper;
import ee.ivkhkdev.repository.Repository;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.repository.Service;

import java.util.List;

public class AuthorService implements Service<Author>, Repository<Author> {

    private final AppHelper<Author> appHelperAuthor;
    private final String fileName = "authors";


    public AuthorService( AppHelper<Author>  appHelperAuthor) {
        this.appHelperAuthor = appHelperAuthor;

    }

    @Override
    public boolean add() {
        try {
            Author author = appHelperAuthor.create();
            if(author == null) {return false;}
            //authors.add(author);
            save(author,fileName);
            return true;
        }catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean edit(Author entity) {
        return false;
    }

    @Override
    public boolean remove(Author entity) {
        return false;
    }

    @Override
    public void print() {
        appHelperAuthor.printList(this.list());
    }

    @Override
    public List<Author> list() {
        return load(fileName);
    }
}
