package tdditerators;

import java.util.Iterator;

public interface CircularListWithIterators {
    void add(final int element);
    int size();
    boolean isEmpty();
    Iterator<Integer> forwardIterator();
    Iterator<Integer> backwardIterator();
}
