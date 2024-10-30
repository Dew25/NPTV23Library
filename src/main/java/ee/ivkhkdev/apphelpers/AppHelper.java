package ee.ivkhkdev.apphelpers;

import ee.ivkhkdev.apphelpers.repository.FileRepository;

import java.util.List;

public interface AppHelper<T> {
    T create();
    void printList(List<T> elements);
    FileRepository<T> getRepository();
}
