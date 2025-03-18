package org.example.my_list.my_linked_list;

import org.example.my_list.list.MyList;

public class MyLinkedList<T> implements MyList<T> {

    private int size = 0;


    private Node<T> first;
    private Node<T> last;

    private void linkFirst(T t) {
        final Node<T> f = first;
        final Node<T> newNode = new Node(t, null, f);

        first = newNode;

        if (f == null) {
            last = newNode;
        } else {
            f.before = newNode;
        }

        size++;
    }


    void linkLast(T t) {
        final Node<T> l = last;
        final Node<T> newNode = new Node<>(t, l, null);

        last = newNode;

        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }

        size++;
    }

    /**
     * @param oldElement NOT NULL
     */
    void linkBefore(T element, Node<T> oldElement) {
        final Node<T> before = oldElement.before;
        final Node<T> newNode = new Node(element, before, oldElement);

        oldElement.before = newNode;

        if (before == null) {
            first = newNode;
        } else {
            before.next = newNode;
        }

        size++;
    }


    @Override
    public void add(T element) {
        linkLast(element);
    }

    @Override
    public void add(int index, T element) {
        if (checkIndex(index, size)) {
            if (index == size) {
                linkLast(element);
            } else {
                linkBefore(element, nodeForIndex(index));
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void remove(int index) {
        if (checkIndex(index, size)) {
            removeLink(nodeForIndex(index));
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    private void removeLink(Node<T> oldNode) {
        Node<T> before = oldNode.before;
        Node<T> next = oldNode.next;

        if (before == null) {
            first = next;
        } else {
            before.next = next;
            oldNode.before = null;
        }

        if (next == null) {
            last = before;
        } else {
            next.before = before;
            oldNode.next = null;
        }

        oldNode.element = null;
        size--;
    }

    @Override
    public T get(int index) {
        if (checkIndex(index, size)) {
            return nodeForIndex(index).element;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public <T> void set(int index, T element) {
        if (checkIndex(index, size)) {
            Node<T> nodeWithMemory = (Node<T>) nodeForIndex(index);
            nodeWithMemory.element = element;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void clear() {
        for (Node<T> oldNode = first; oldNode != null; ) {
            Node<T> next = oldNode.next;
            oldNode.element = null;
            oldNode.next = null;
            oldNode.before = null;
            oldNode = next;
        }
        first = last = null;
        size = 0;
    }

    Node<T> nodeForIndex(int index) {
        if (index < (size >> 1)) {

            Node<T> desiredElement = first;

            for (int i = 0; i < index; i++) {
                desiredElement = desiredElement.next;
            }
            return desiredElement;

        } else {
            Node<T> desiredElement = last;

            for (int i = size - 1; i > index; i--) {
                desiredElement = desiredElement.before;
            }

            return desiredElement;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public <T2 extends Comparable<T2>> void sort() {

    }


    private static class Node<T> {
        T element;
        Node<T> next;
        Node<T> before;

        public Node(T element, Node<T> before, Node<T> next) {
            this.element = element;
            this.next = next;
            this.before = before;
        }
    }


}
