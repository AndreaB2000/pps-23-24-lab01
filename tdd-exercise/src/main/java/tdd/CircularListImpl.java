package tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CircularListImpl implements CircularList {
    private boolean empty = true;
    private List<Integer> list = new ArrayList<>();
    private int cursor = 0;

    @Override
    public void add(int element) {
        this.empty = false;
        this.list.add(element);
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public boolean isEmpty() {
        return this.empty;
    }

    @Override
    public Optional<Integer> next() {
        if (this.size() == 0) {
            return Optional.empty();
        }
        int elementToBeReturned = this.list.get(this.cursor);
        this.cursor++;
        this.cursor = this.cursor % this.size();
        return Optional.of(elementToBeReturned);
    }

    @Override
    public Optional<Integer> previous() {
        if (this.size() == 0) {
            return Optional.empty();
        }
        this.cursor--;
        this.cursor = (this.cursor + this.size()) % this.size();
        int elementToBeReturned = this.list.get(this.cursor);
        return Optional.of(elementToBeReturned);
    }

    @Override
    public void reset() {
        this.cursor = 0;
    }
    
}
