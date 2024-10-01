package ee.ivkhkdev.interfaces.impl;

import ee.ivkhkdev.interfaces.InputProvider;

import java.util.Scanner;

public class ConsoleHandler implements InputProvider {
    private Scanner scanner = new Scanner(System.in);
    @Override
    public String getInput() {
        return scanner.nextLine();
    }
}
