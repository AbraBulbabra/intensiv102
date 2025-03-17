package org.example.my_list.my_array_list;

import org.example.my_list.my_array_list.list.MyList;

public class MyArrayList<T> implements MyList<T> {

    // Default size capacity my ArrayList.
    private static final int DEFAULT_CAPACITY = 10;

    //Default capacity for empty list.
    private static final Object[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = {};

    // The array buffer into which the elements of the ArrayList.
    private Object[] matrixElements;

    // Count element in matrixElements.
    public int size;

    public MyArrayList() {
        this.matrixElements = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.matrixElements = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.matrixElements = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    //Appends the specified element to the end of this list.
    public void add(T t) {
        growMatrix();
        matrixElements[size++] = t;
    }


    @Override
    public void add(int index, T element) {

    }

    @Override
    public void remove(int index) {

    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void clear() {

    }

    private void growMatrix() {
        if (isLimitSize()) {
            extensionMatrix();
        }
    }

    private boolean isLimitSize() {
        return size == matrixElements.length;
    }

    /**
     * Расширяет матрицу в зависимости от ее состояния.
     * Если матрица не пуста и имеет ненулевой размер,
     * создается копия существующих элементов матрицы.
     * В противном случае создается новая матрица с заданной длиной.
     */
    private void extensionMatrix() {
        if (isSizeMatrixElementsNotZeroOrEmpty()) {
            matrixElements = copyMatrixElement();
        } else {
            matrixElements = new Object[newLength()];
        }
    }

    /**
     * Создает копию текущей матрицы с увеличенным размером
     *
     * @return возвращает новый массив с копией элементов
     */
    private Object[] copyMatrixElement() {
        int oldLength = matrixElements.length;
        Object[] newMatrix = new Object[newLength()];

        for (int i = 0; i < size; i++) {
            newMatrix[i] = matrixElements[i];
        }

        return newMatrix;
    }

    /**
     * Вычисляет новую длину для массива элементов матрицы.
     * <p>
     * - Если массив не пуст и имеет ненулевой размер,
     * возвращает максимальное значение между минимальным и оптимальным расширением.
     * - Если массив пуст, возвращает максимальное значение между стандартной емкостью и минимальным расширением.
     *
     * @return - новое рассчитанное значение длины массива
     */
    private int newLength() {
        int oldLength = matrixElements.length;
        int minExtension = size + 1;
        int bestExtension = oldLength >> 1;

        if (isSizeMatrixElementsNotZeroOrEmpty()) {
            return Math.max((oldLength + minExtension), (oldLength + bestExtension));
        }
        return Math.max(DEFAULT_CAPACITY, minExtension);
    }

    private boolean isSizeMatrixElementsNotZeroOrEmpty() {
        return matrixElements.length > 0 || matrixElements != DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
    }
}
