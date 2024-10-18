package ee.ivkhkdev.services;

import ee.ivkhkdev.App;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.interfaces.UserProvider;
import ee.ivkhkdev.model.User;

public class UserService {
    private UserProvider userProvider;

    public UserService(UserProvider userProvider) {
        this.userProvider = userProvider;
    }

    public boolean add(Input input, User[] users) {
        try {
            User user = userProvider.create(input);
            if(user == null) return false;
            for (int i = 0; i < users.length; i++) {
                if (users[i] == null) {
                    users[i] = user;
                    break;
                }
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public String printList(User[] users) {
        return userProvider.getList(users);
    }
}
