package ee.ivkhkdev.apphelpers;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.User;

import java.util.List;

public class UserAppHelper implements AppHelper<User>, Input {
    @Override
    public User create() {
        try {
            User user = new User();
            System.out.print("Имя читателя: ");
            user.setFirstname(getString());
            System.out.print("Фамилия читателя: ");
            user.setLastname(getString());
            return user;
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<User> update(List<User> users) {
        try {
            /*
             * 1. вывести список пользователей
             * 2. выбрать номер пользователя
             * 3. вывести имя пользователя
             * 4. получить от пользователя разрешения на замену (y/n)
             * 5. если y, получить новое имя и вызвать users.setFirstname(...)
             * 6. если n, вывести фамилию
             * 7. получить от пользователя разрешение на замену (y/n)
             * 8. если y, получить новую фамилию и вызвать users.setLastname(...)
             * 9. вывести телефон
             * 10. получить от пользователя разрешение на замену (y/n)
             * 11. если y, получить новый телефон и вызвать users.setPhone(...)
             * 12. вернуть список с измененным пользователем
             */
            this.printList(users);
            System.out.print("Выберите номер пользователя: ");
            int numberUser = Integer.parseInt(getString());
            System.out.print("Имя пользователя: "+ users.get(numberUser -1).getFirstname());
            System.out.print("Изменить (y/n): ");
            String change = getString();
            if(change.equals("y")){
                System.out.print("Новое имя пользователя: ");
                users.get(numberUser -1).setFirstname(getString());
            }
            System.out.print("Фамилия пользователя: "+ users.get(numberUser -1).getLastname());
            System.out.print("Изменить (y/n): ");
            change = getString();
            if(change.equals("y")){
                System.out.print("Новая фамилия пользователя: ");
                users.get(numberUser -1).setLastname(getString());
            }
            System.out.print("Телефон пользователя: "+ users.get(numberUser -1).getPhone());
            System.out.print("Изменить (y/n): ");
            change = getString();
            if(change.equals("y")){
                System.out.print("Новый телефон пользователя: ");
                users.get(numberUser -1).setPhone(getString());
            }
            return users;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean printList(List<User> users) {
        System.out.println("---------- Список читателей --------");
        for(int i=0;i<users.size();i++) {
            User user = users.get(i);
            System.out.printf("%d. %s %s. %s%n", i+1,user.getFirstname(),user.getLastname(), user.getPhone());
        }
        return false;
    }
}
