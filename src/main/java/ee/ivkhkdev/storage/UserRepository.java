package ee.ivkhkdev.storage;

import ee.ivkhkdev.interfaces.AppRepository;
import ee.ivkhkdev.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements AppRepository<User> {
}
