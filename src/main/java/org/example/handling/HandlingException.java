package org.example.handling;

import org.example.Work;
import org.example.craft.exception.CraftException;

public class HandlingException {
    public void errorHandlingAndFinish(boolean b) {
        try {
            Work.checkLogic(b);
            System.out.println("\nПрограмма продолжиться!");
        } catch (CraftException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public void errorHandling(boolean b) {
        try {
            Work.checkLogic(b);
            System.out.println("\nПрограмма продолжиться!");
        } catch (CraftException e) {
            System.out.printf(e.getMessage());
        }
    }

}
