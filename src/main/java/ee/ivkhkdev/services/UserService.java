package ee.ivkhkdev.services;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.AppRepository;
import ee.ivkhkdev.interfaces.AppService;
import ee.ivkhkdev.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements AppService<User>{
    private final String fileName="users";
    @Autowired private AppHelper<User> userAppHelper;
    @Autowired private AppRepository<User> userAppRepository;

    @Override
    public boolean add() {
        try {
            Optional<User> user = userAppHelper.create();
            if(user.isPresent()) {
                userAppRepository.save(user.get(),fileName);
                return true;
            }
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        return false;
    }

    @Override
    public boolean edit() {
        try {
            List<User> modifedUsers = userAppHelper.update(list());
            if(modifedUsers.isEmpty()){
                return false;
            }
            userAppRepository.saveAll(modifedUsers,fileName);
            return true;
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean remove(User entity) {
        return false;
    }

    @Override
    public boolean print() {
        return userAppHelper.printList(this.list());
    }

    @Override
    public List<User> list() {
        return userAppRepository.load(fileName);
    }
}
