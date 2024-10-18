package ee.ivkhkdev.interfaces.impl;

import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.interfaces.UserProvider;
import ee.ivkhkdev.model.User;

public class AppHelperUsers implements UserProvider {
    @Override
    public User create(Input input) {
        try {
            User user = new User();
            System.out.print("Имя: ");
            user.setFirstname(input.getString());
            System.out.print("Фамилия: ");
            user.setLastname(input.getString());
            System.out.print("Телефон: ");
            user.setPhone(input.getString());
            return user;
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public String getList() {
        return "";
    }


}
