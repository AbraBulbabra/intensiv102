package org.example;

import org.example.craft.exception.CraftException;

public class Main {
    public static void main(String[] args) {

        errorHendling(false);
        errorHendling(true);

        errorHendlingAndFinish(false);
        errorHendlingAndFinish(true);
        errorHendlingAndFinish(false);
    }


    private static void errorHendlingAndFinish(boolean b) {
        try {
            Work.checkLogic(b);
            System.out.print("\nПрограмма продолжиться!");
        } catch (CraftException e) {
            System.out.printf(
                    "\n Мы словили ошибку \n->%s\nпрограма закончена!", e.toString()
            );
            throw new RuntimeException(e);
        }

    }

    private static void errorHendling(boolean b) {
        try {
            Work.checkLogic(b);
            System.out.print("\nПрограмма продолжиться!");
        } catch (CraftException e) {
            System.out.printf(
                    "\n------- Мы словили ошибку \n-> %s\n!!!но програму не остановили!", e.toString()
            );
        }

    }
}