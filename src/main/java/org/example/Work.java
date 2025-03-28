package org.example;

import org.example.craft.exception.CraftException;

public class Work {

    public static void checkLogic(boolean b) throws CraftException {
        if(b) {
            throw new CraftException("Messge exception in class Work");
        }
    }
}
