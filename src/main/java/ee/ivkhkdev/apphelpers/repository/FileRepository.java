package ee.ivkhkdev.apphelpers.repository;

import ee.ivkhkdev.model.Book;

import java.util.List;

public interface FileRepository<T> {
    void save(T entity);
    List<T> load();
}
