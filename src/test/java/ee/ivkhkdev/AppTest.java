package ee.ivkhkdev;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {

    @Test
    public void testRunExit() {
        String input = "0\n"; // Ввод для выхода из программы
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        App app = new App();
        app.run(); // Запуск метода

        // Здесь можно добавить дополнительные проверки, если необходимо
        String output = outContent.toString();
        assertTrue(output.contains("Досвидания :)")); // Проверка на наличие текста

    }

    @Test
    public void testRunInvalidTask() {
        String input = "2\n0\n"; // Ввод для неверной задачи и выхода
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        App app = new App();
        app.run(); // Запуск метода

        // Здесь можно добавить дополнительные проверки, если необходимо
        String output = outContent.toString();
        assertTrue(output.contains("Выберите задачу из списка!")); // Проверка на наличие текста
        assertTrue(output.contains("Досвидания :)")); // Проверка на наличие текста

    }@Test
    public void testRunValidTask() {
        String input = "1\n0\n"; // Ввод для неверной задачи и выхода
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        App app = new App();
        app.run(); // Запуск метода

        // Здесь можно добавить дополнительные проверки, если необходимо
        String output = outContent.toString();
        assertTrue(output.contains("----- Добавление книги -----")); // Проверка на наличие текста
        assertTrue(output.contains("Досвидания :)")); // Проверка на наличие текста

    }
}
