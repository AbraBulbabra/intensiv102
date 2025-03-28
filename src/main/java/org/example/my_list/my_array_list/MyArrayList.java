package org.example.my_list.my_array_list;

import org.example.my_list.list.MyList;

public class MyArrayList<T> implements MyList<T> {

    // Default size capacity my ArrayList.
    private static final int DEFAULT_CAPACITY = 10;

    //Default capacity for empty list.
    private static final Object[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = {};

    // The array buffer into which the elements of the ArrayList.
    private Object[] arrayElements;

    // Count element in matrixElements.
    public int size;

    public MyArrayList() {
        this.arrayElements = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.arrayElements = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.arrayElements = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    @Override
    public void add(T t) {
        growMatrix();
        arrayElements[size++] = t;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, T element) {
        if (checkIndex(index, size)) {
            arrayElements = copyMatrixWithStepToRight(index);
            arrayElements[index] = element;
            size++;
        } else {
            System.err.printf("Out of bounds Check Index -> %d", index);
        }
    }

    private void growMatrix() {
        if (isLimitSize()) {
            extensionMatrix();
        }
    }

    /**
     * Расширяет матрицу в зависимости от ее состояния.
     * Если матрица не пуста и имеет ненулевой размер,
     * создается копия существующих элементов матрицы.
     * В противном случае создается новая матрица с заданной длиной.
     */
    private void extensionMatrix() {
        if (isSizeMatrixElementsNotZeroOrEmpty()) {
            arrayElements = copyMatrixElement();
        } else {
            arrayElements = new Object[newLength()];
        }
    }

    /**
     * Создает копию текущей матрицы с увеличенным размером
     *
     * @return возвращает новый массив с копией элементов
     */
    private Object[] copyMatrixElement() {
        int oldLength = arrayElements.length;
        Object[] newMatrix = new Object[newLength()];

        for (int i = 0; i < size; i++) {
            newMatrix[i] = arrayElements[i];
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
        int oldLength = arrayElements.length;
        int minExtension = size + 1;
        int bestExtension = oldLength >> 1;

        if (isSizeMatrixElementsNotZeroOrEmpty()) {
            return Math.max((oldLength + minExtension), (oldLength + bestExtension));
        }
        return Math.max(DEFAULT_CAPACITY, minExtension);
    }

    private boolean isSizeMatrixElementsNotZeroOrEmpty() {
        return arrayElements.length > 0 || arrayElements != DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
    }

    /**
     * Создает копию текущей матрицы с шагом вправо, начиная с указанного индекса.
     * <p>
     * * @param index - индекс, с которого будет добавлен шаг вправо (сместит элементы)
     * * @return новый массив с элементами текущей матрицы, сдвинутыми вправо
     */
    private Object[] copyMatrixWithStepToRight(int index) {
        Object[] newObject = createObjectNewLength();

        int add = 0;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                newObject[i] = null;
                add++;
            }

            int newIndex = i + add;
            newObject[newIndex] = arrayElements[i];
        }

        return newObject;
    }

    /**
     * Создает новый массив для матрицы с учетом ограничения размера.
     * отлет из SVO 23.30 19.03.2025
     *
     * @return новый массив с длиной, соответствующей текущим ограничениям
     */
    private Object[] createObjectNewLength() {
        if (isLimitSize()) {
            return new Object[newLength()];
        } else {
            return new Object[arrayElements.length];
        }
    }

    @Override
    public void remove(int index) {
        if (checkIndex(index, size)) {
            copyMatrixWithStepToLeft(index);
            size--;
        } else {
            System.err.printf("Out of bounds Check Index -> %d", index);
        }
    }

    private boolean isLimitSize() {
        return size == arrayElements.length;
    }

    /**
     * Копирует элементы матрицы с шагом влево, начиная с указанного индекса.
     *
     * @param index - индекс, с которого начинается сдвиг влево
     */
    private void copyMatrixWithStepToLeft(int index) {

        int add = 0;
        for (int i = index; i < size - 1; i++) {
            if (i == index) {
                add++;
            }
            int newIndex = i + add;

            this.arrayElements[i] = arrayElements[newIndex];
        }
    }

    @Override
    public T get(int index) {
        return (T) arrayElements[index];
    }

    @Override
    public void set(int index, T element) {
        arrayElements[index] = element;
    }

    @Override
    public void clear() {
        final Object[] es = arrayElements;
        for (int i = 0; i < size; i++) {
            es[i] = null;
        }
        size = 0;
    }

    @Override
    public <T extends Comparable<T>> void sort() {
        MyArrayList<T> sort = (MyArrayList<T>) this;
        this.arrayElements = mergeSort(sort).arrayElements;
    }

    private <T extends Comparable<T>> MyArrayList<T> mergeSort(MyArrayList<T> myArrayList) {
        if (arrayElements == null) {
            throw new IllegalArgumentException();
        }

        int sizeMyList = myArrayList.size();

        if (sizeMyList > 2) {
            int medium = sizeMyList >> 1;

            MyArrayList arrayLeft = new MyArrayList();
            copy(0, medium, arrayLeft);

            MyArrayList arrayRight = new MyArrayList();
            copy(medium + 1, sizeMyList - 1, arrayRight);

            arrayLeft = mergeSort(arrayLeft);
            arrayRight = mergeSort(arrayRight);
            return mergeArray(arrayLeft, arrayRight);

        } else if (sizeMyList == 2) {
            MyArrayList arrayLeft = new MyArrayList();
            arrayLeft.add(myArrayList.get(0));

            MyArrayList arrayRight = new MyArrayList();
            arrayRight.add(myArrayList.get(sizeMyList - 1));
            return mergeArray(arrayLeft, arrayRight);

        } else {
            return myArrayList;
        }
    }

    private <T extends Comparable<T>> MyArrayList<T> mergeArray(MyArrayList<T> left, MyArrayList<T> right) {
        MyArrayList<T> arrayUnited = new MyArrayList<T>(left.size() + right.size());
        int indexArrayLeft = 0;
        int indexArrayRight = 0;

        for (int i = 0; i < arrayUnited.arrayElements.length; i++) {

            if (indexArrayLeft == left.size()) {
                arrayUnited.add(right.get(indexArrayRight));
                indexArrayRight++;

            } else if (indexArrayRight == right.size()) {
                arrayUnited.add(left.get(indexArrayLeft));
                indexArrayLeft++;

            } else if (left.get(indexArrayLeft).compareTo(right.get(indexArrayRight)) < 0) {
                arrayUnited.add(left.get(indexArrayLeft));
                indexArrayLeft++;

            } else {
                arrayUnited.add(right.get(indexArrayRight));
                indexArrayRight++;

            }
        }
        return arrayUnited;
    }

    private MyArrayList<T> copy(int start, int finish, MyArrayList<T> halfList) {
        for (int i = start; i <= finish; i++) {
            halfList.add(this.get(i));
        }
        return halfList;
    }
}