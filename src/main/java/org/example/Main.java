package org.example;

import org.example.my_list.list.MyList;
import org.example.my_list.my_array_list.MyArrayList;
import org.example.my_list.my_linked_list.MyLinkedList;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> intArr = new MyArrayList<>();
        intArr.add(9);
        intArr.add(2);
        intArr.add(6);
        intArr.add(11);
        intArr.add(7);

//        for (int i = 0; i < intArr.size; i++) {
//            System.out.println(intArr.get(i));
//        }
//
//        intArr.remove(1);
//        intArr.add(1,15);
//        intArr.set(0,10);
//
//        for (int i = 0; i < intArr.size; i++) {
//            System.out.println(intArr.get(i));
//        }

        for (int i = 0; i < intArr.size; i++) {
            System.out.println(intArr.get(i));
        }

        System.out.println("____________-------");
        intArr.sort();

        for (int i = 0; i < intArr.size; i++) {
            System.out.println(intArr.get(i));
        }

        System.out.println("--------MyLinkedList---------");

        MyList<Integer> strLinked = new MyLinkedList<>();
        strLinked.add(2);
        strLinked.add(6);
        strLinked.add(1);
        strLinked.add(5);

        for (int i = 0; i < strLinked.size(); i++) {
            System.out.println(strLinked.get(i));
        }

        System.out.println("-----------------");
        strLinked.sort();

        for (int i = 0; i < strLinked.size(); i++) {
            System.out.println(strLinked.get(i));
        }


//        strLinked.set(1, 20);
//
//        for (int i = 0; i < strLinked.size(); i++) {
//            System.out.println(strLinked.get(i));
//        }
//
//        System.out.println("-----------------");
//        strLinked.add(1,40);
//
//        for (int i = 0; i < strLinked.size(); i++) {
//            System.out.println(strLinked.get(i));
//        }
//
//        System.out.println("-----------------");
//        strLinked.remove(1);
//
//        for (int i = 0; i < strLinked.size(); i++) {
//            System.out.println(strLinked.get(i));
//        }
    }
}