package ee.ivkhkdev.services;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.AppService;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.interfaces.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService implements AppService<Author> {
    private final String fileName = "authors";
    @Autowired private AppHelper<Author> authorAppHelper;
    @Autowired private AppRepository<Author> authorAppRepository;

    public AuthorService(AppHelper<Author> authorAppHelper, AppRepository<Author> storageAuthor) {
         this.authorAppHelper = authorAppHelper;
         this.authorAppRepository = storageAuthor;
    }

    @Override
    public boolean add() {
        try {
            Optional<Author> author = authorAppHelper.create();
            if(author.isPresent()) {
                authorAppRepository.save(author.get(),fileName);
                return true;
            }
        }catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean edit() {
        try {
            List<Author> modifedAuthors = authorAppHelper.update(list());
            if(modifedAuthors.isEmpty()){
                return false;
            }
            authorAppRepository.saveAll(modifedAuthors,fileName);
            return true;
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean remove(Author entity) {
        return false;
    }

    @Override
    public boolean print() {
       return authorAppHelper.printList(this.list());
    }

    @Override
    public List<Author> list() {
        return authorAppRepository.load(fileName);
    }
}
