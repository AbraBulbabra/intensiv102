package org.example.my_list.list;

public interface MyList<T> {

    /**
     * Добавляет элемент в конец списка
     *
     * @param element элемент который вставим в лист
     */
    void add(T element);

    /**
     * Добавляет элемент по индексу
     *
     * @param index   - индекс а место которого будет вставлен элемент
     * @param element - элемент который будет вставлен
     */
    void add(int index, T element);

    /**
     * Удаляет элемент по индексу после чего, сдвигает матрицу влево.
     *
     * @param index - индекс элемента который нужно удалить
     */
    void remove(int index);

    /**
     * Возвращает элемент Т по индексу
     *
     * @param index - индекс элемента, который нужно вернуть
     * @return - возвращает элемент T по указанному индексу
     */
   T get(int index);

    /**
     * Заменяет элемент T по указанному индексу.
     *
     * @param index   - индекс элемента T, который нужно заменить
     * @param element - элемент, который будет вставлен по указанному индексу
     * @return - обновленный элемент T
     */
   <T> void set(int index, T element);

    /**
     * Удаляет все элементы из данного списка.
     */
    void clear();

    int size();

     <T2 extends Comparable<T2>> void sort();

      default boolean checkIndex(int index, int size) {
        return (index >= 0) && (index < size);
    }
}
