package ee.ivkhkdev.interfaces;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public interface AppRepository<T> {
    default void save(T entity,String fileName){
        List<T> list = this.load(fileName);
        if(list == null){
            list = new ArrayList<>();
        }
        list.add(entity);
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        try {
            fileOutputStream = new FileOutputStream(fileName);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
            objectOutputStream.flush();
        } catch (FileNotFoundException e) {
            System.out.println("Нет файла с именем "+fileName);
        } catch (IOException e) {
            System.out.println("Ошибка ввода информации");
        }
    };
    default void saveAll(List<T> entities,String fileName){
        if(entities == null){entities = new ArrayList<>();}
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        try {
            fileOutputStream = new FileOutputStream(fileName);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(entities);
            objectOutputStream.flush();
        } catch (FileNotFoundException e) {
            System.out.println("Нет файла с именем "+fileName);
        } catch (IOException e) {
            System.out.println("Ошибка ввода информации");
        }
    };
    default List<T> load(String fileName){
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        try {
            fileInputStream = new FileInputStream(fileName);
            objectInputStream = new ObjectInputStream(fileInputStream);
            return (List<T>) objectInputStream.readObject();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            System.out.println("Нет файла с именем "+fileName);
        } catch (IOException e) {
            System.out.println("Ошибка вывода информации");
        }
        return new ArrayList<>();
    }
}
