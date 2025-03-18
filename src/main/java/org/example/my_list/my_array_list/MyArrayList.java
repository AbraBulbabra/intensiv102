package org.example.my_list.my_array_list;

import org.example.my_list.list.MyList;

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
    public int size() {
        return size;
    }

    @Override
    public void add(int index, T element) {
        if (checkIndex(index,size)) {
            matrixElements = copyMatrixWithStepToRight(index);
            matrixElements[index] = element;
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
            newObject[newIndex] = matrixElements[i];
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
            return new Object[matrixElements.length];
        }
    }

    @Override
    public void remove(int index) {
        if (checkIndex(index,size)) {
            copyMatrixWithStepToLeft(index);
            size--;
        } else {
            System.err.printf("Out of bounds Check Index -> %d", index);
        }
    }

    private boolean isLimitSize() {
        return size == matrixElements.length;
    }

    /**
     * Копирует элементы матрицы с шагом влево, начиная с указанного индекса.
     *
     * @param index - индекс, с которого начинается сдвиг влево
     */
    private void copyMatrixWithStepToLeft(int index) {
        Object[] matrixElement = this.matrixElements;
        int oldSize = size;

        int add = 0;
        for (int i = index; i < size; i++) {
            if (i == index) {
                add++;
                int newIndex = i + add;
            }
            int newIndex = i + add;

            this.matrixElements[i] = matrixElements[newIndex];
        }
    }

    @Override
    public T get(int index) {
        return (T) matrixElements[index];
    }

    @Override
    public <T> void set(int index, T element) {
        matrixElements[index] = element;
    }


    @Override
    public void clear() {
        final Object[] es = matrixElements;
        for (int i = 0; i < size; i++) {
            es[i] = null;
        }
        size = 0;
    }
}
