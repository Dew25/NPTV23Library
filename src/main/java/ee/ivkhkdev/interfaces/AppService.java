package ee.ivkhkdev.interfaces;

import java.util.List;

public interface AppService<T> {
    boolean add();
    boolean edit();
    boolean remove(T entity);
    boolean print();
    List<T> list();

}
