package org.example;

import org.example.my_list.list.MyList;
import org.example.my_list.my_array_list.MyArrayList;
import org.example.my_list.my_linked_list.MyLinkedList;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> intArr = new MyArrayList<>();
        intArr.add(4);
        intArr.add(5);
        intArr.add(6);

        for (int i = 0; i < intArr.size; i++) {
            System.out.println(intArr.get(i));
        }

        intArr.remove(1);
        intArr.add(1,15);
        intArr.set(0,10);

        for (int i = 0; i < intArr.size; i++) {
            System.out.println(intArr.get(i));
        }


        MyList<String> strLinked = new MyLinkedList<>();
        strLinked.add("one");
        strLinked.add("два");
        strLinked.add("trua");

        for (int i = 0; i < strLinked.size(); i++) {
            System.out.println(strLinked.get(i));
        }

        System.out.println("-----------------");
        strLinked.set(1, "two");

        for (int i = 0; i < strLinked.size(); i++) {
            System.out.println(strLinked.get(i));
        }

        System.out.println("-----------------");
        strLinked.add(1,"in");

        for (int i = 0; i < strLinked.size(); i++) {
            System.out.println(strLinked.get(i));
        }

        System.out.println("-----------------");
        strLinked.remove(1);

        for (int i = 0; i < strLinked.size(); i++) {
            System.out.println(strLinked.get(i));
        }
    }
}