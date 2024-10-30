package ee.ivkhkdev.apphelpers.repository;

import ee.ivkhkdev.model.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage<T> implements FileRepository<T>{
    private String fileName;

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(T entity) {
        List<T> list = this.load();
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
            System.out.println("Нет файла с именем books");
        } catch (IOException e) {
            System.out.println("Ошибка ввода информации");
        }
    }

    @Override
    public List<T> load() {
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        try {
            fileInputStream = new FileInputStream(fileName);
            objectInputStream = new ObjectInputStream(fileInputStream);
            return (List<T>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Нет файла с именем books");
        } catch (IOException e) {
            System.out.println("Ошибка вывода информации");
        } catch (ClassNotFoundException e) {
            System.out.println("Нет файла с именем books");
        }
        return new ArrayList<>();
    }
}
