package ee.ivkhkdev.interfaces;

import java.util.Scanner;

public interface Input {
    default String getString(){
        return new Scanner(System.in).nextLine();
    }
}
