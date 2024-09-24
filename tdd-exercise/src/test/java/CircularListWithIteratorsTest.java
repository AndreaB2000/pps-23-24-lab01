import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tdditerators.CircularListWithIterators;
import tdditerators.CircularListWithIteratorsImpl;

public class CircularListWithIteratorsTest {

    private CircularListWithIterators circularList;
    private int numberOfElementsToAdd = 5;

    @BeforeEach
    public void beforeEach() {
        this.circularList = new CircularListWithIteratorsImpl();
    }
    
    @Test
    public void testInstantiation() {
        assertNotNull(this.circularList);
    }

    @Test
    public void testIsSizeZeroAfterInstantiation() {
        assertEquals(0, this.circularList.size());
    }

    @Test
    public void testIsEmptyAfterInstantiation() {
        assertTrue(this.circularList.isEmpty());
    }

    private void addOneElementToList() {
        this.circularList.add(0);
    }

    @Test
    public void testSizeAfterAddingOneElement() {
        this.addOneElementToList();
        assertEquals(1, this.circularList.size());
    }

    @Test
    public void testIsEmptyAfterAddingOneElement() {
        this.addOneElementToList();
        assertFalse(this.circularList.isEmpty());
    }

    private void addMultipleElementsToList(int amount) {
        for (int i = 0; i < this.numberOfElementsToAdd; i++) {
            this.addOneElementToList();
        }
    }

    @Test
    public void testSizeAfterAddingMultipleElements() {
        this.addMultipleElementsToList(this.numberOfElementsToAdd);
        assertEquals(this.numberOfElementsToAdd, this.circularList.size());
    }
    
    @Test
    public void testIsNotEmptyAfterAddingMultipleElements() {
        this.addMultipleElementsToList(this.numberOfElementsToAdd);
        assertFalse(this.circularList.isEmpty());
    }

    private void addIncrementalNumbersToList(int amount) {
        for (int i = 0; i < this.numberOfElementsToAdd; i++) {
            this.circularList.add(i);
        }
    }

    private List<Integer> buildIncrementalNumbersList(int amount) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            list.add(i);
        }
        return list;
    }

    private List<Integer> buildListFromCircularListIterator(int numberOfElements, Iterator<Integer> iterator) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < this.numberOfElementsToAdd; i++) {
            list.add(iterator.next());
        }
        return list;
    }

    @Test
    public void testForwardIteratorAfterInstantiation() {
        List<Integer> expectedList = this.buildIncrementalNumbersList(this.numberOfElementsToAdd);
        List<Integer> actualList = new ArrayList<>();
        this.addIncrementalNumbersToList(this.numberOfElementsToAdd);
        Iterator<Integer> forwardIterator = this.circularList.forwardIterator();
        actualList = buildListFromCircularListIterator(this.numberOfElementsToAdd, forwardIterator);
        assertArrayEquals(
            expectedList.toArray(),
            actualList.toArray()
        );
    }

    @Test
    public void testBackwardIteratorAfterInstantiation() {
        List<Integer> expectedList = this.buildIncrementalNumbersList(this.numberOfElementsToAdd);
        List<Integer> actualList = new ArrayList<>();
        this.addIncrementalNumbersToList(this.numberOfElementsToAdd);
        Iterator<Integer> backwardIterator = this.circularList.backwardIterator();
        actualList = buildListFromCircularListIterator(this.numberOfElementsToAdd, backwardIterator);
        Collections.reverse(expectedList);
        assertArrayEquals(
            expectedList.toArray(),
            actualList.toArray()
        );
    }

    // Test iterators circularity
}
