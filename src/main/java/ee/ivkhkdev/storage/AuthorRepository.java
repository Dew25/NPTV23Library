package ee.ivkhkdev.storage;

import ee.ivkhkdev.interfaces.AppRepository;
import ee.ivkhkdev.model.Author;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorRepository implements AppRepository<Author> {
}
