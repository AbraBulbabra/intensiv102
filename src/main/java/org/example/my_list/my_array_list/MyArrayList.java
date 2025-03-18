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
    @Override
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
        if (checkIndex(index, size)) {
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
        if (checkIndex(index, size)) {
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

    @Override
    public <T extends Comparable<T>> void sort() {
        MyArrayList<T> sort = (MyArrayList<T>) this;
        this.matrixElements = mergeSort(sort).matrixElements;
    }

    /**
     * Recursion method
     * <p>
     * *@param partition - index of the already sorted part of the array.
     * everything on the left will always be smaller
     * everything on the right is always bigger
     * *@param start - left border of the array
     * *@param end   - right border of the array
     */
    private <T extends Comparable<T>> void quickSort(int start, int end) {
        int partition = partition(start, end);

        if (partition - 1 > start) {
            quickSort(start, partition - 1);
        }
        if (partition + 1 < end) {
            quickSort(partition + 1, end);
        }
    }

    /**
     * Sorting method
     * <p>
     * *@param arr - sorting array
     * *@param pivot - element for comparison
     * *@param wall - everything to the left of which the indices are less than Pivot,
     * to the right are greater,
     * at the end at this point they become Pivot
     * *@param end   - right border of the array
     */
    private <T extends Comparable<T>> int partition(int wall, int end) {
        MyArrayList<T> arr = (MyArrayList<T>) this;
        T pivot = arr.get(end);

        for (int i = wall; i < end; i++) {
            if (arr.get(i).compareTo(pivot) < 0) {
                T temp = arr.get(wall);
                arr.set(wall, arr.get(i));
                arr.set(i, temp);
                wall++;
            }
        }


        T temp = arr.get(wall);
        arr.set(wall, pivot);
        arr.set(end, temp);

        return wall;
    }

    private <T extends Comparable<T>> MyArrayList<T> mergeSort(MyArrayList<T> myArrayList) {
        if (matrixElements == null) {
            throw new IllegalArgumentException();
        }

        int sizeMyList = myArrayList.size();

//        if (sizeMyList < 2) {
//            return myArrayList;
//        }

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

        for (int i = 0; i < arrayUnited.matrixElements.length; i++) {

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
