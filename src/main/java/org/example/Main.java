package org.example;

import org.example.craft.exception.CraftException;
import org.example.handling.HandlingException;

public class Main {
    public static void main(String[] args) {
        HandlingException handling = new HandlingException();
        handling.errorHandling(false);
        handling.errorHandling(true);

        handling.errorHandlingAndFinish(false);
        handling.errorHandlingAndFinish(true);
        handling.errorHandlingAndFinish(false);
    }
}