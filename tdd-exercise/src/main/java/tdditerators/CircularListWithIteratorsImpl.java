package tdditerators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CircularListWithIteratorsImpl implements CircularListWithIterators {
    private int size = 0;
    private List<Integer> list = new ArrayList<>();

    @Override
    public void add(int element) {
        this.list.add(element);
        this.size++;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<Integer> forwardIterator() {
        return new Iterator<Integer>() {
            private int counter = 0;

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Integer next() {
                int elementToBeReturned = list.get(this.counter);
                this.counter = (this.counter + 1) % size;
                return elementToBeReturned;
            }
        };
    }

    @Override
    public Iterator<Integer> backwardIterator() {
        return new Iterator<Integer>() {
            private int counter = size - 1;

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Integer next() {
                int elementToBeReturned = list.get(this.counter);
                this.counter = (this.counter - 1 + size) % size;
                return elementToBeReturned;
            }
        };
    }
    
}
