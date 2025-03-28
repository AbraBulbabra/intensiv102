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
        intArr.add(9);
        intArr.add(2);
        intArr.add(6);
        intArr.add(11);
        intArr.add(7);

        System.out.println("____________-------");
        for (int i = 0; i < intArr.size; i++) {
            System.out.println(intArr.get(i));
        }

        intArr.remove(9);

        System.out.println("____________-------");
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

         class N{
            int i =1;

             public N(int i) {
                 this.i = i;
             }

             @Override
             public String toString() {
                 return "N{" +
                         "i=" + i +
                         '}';
             }
         }


        System.out.println("--------newN------");
        MyArrayList<N> newN = new MyArrayList<>();
        newN.add(new N(1));
        newN.add(new N(2));
        newN.add(new N(3));

//        newN.sort();

        for (int i = 0; i < newN.size(); i++) {
            System.out.println(newN.get(i));
        }

    }
}