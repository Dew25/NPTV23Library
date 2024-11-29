package ee.ivkhkdev.interfaces;

import java.util.List;
import java.util.Optional;

public interface AppHelper<T> {
    Optional<T> create();
    List<T> update(List<T> entities);
    boolean printList(List<T> elements);

}
