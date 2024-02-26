import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tdd.CircularList;
import tdd.CircularListImpl;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    private CircularList circularList;
    private final int numberOfElementsAdded = 5;
    private final int circularityTestOffset = 2;

    @BeforeEach
    public void beforeEach() {
        this.circularList = new CircularListImpl();
    }

    @Test
    public void testInstantiation() {
        assertNotNull(this.circularList);
    }

    @Test
    public void testIsEmptyAfterInstantiation() {
        assertTrue(this.circularList.isEmpty());
    }

    @Test
    public void testIsSizeZeroAfterInstantiation() {
        assertEquals(0, this.circularList.size());
    }

    private void addItemToList(int amount) {
        for (int i = 0; i < amount; i++) {
            this.circularList.add(0);
        }
    }

    @Test
    public void testAddIsNotEmpty() {
        this.addItemToList(1);
        assertFalse(this.circularList.isEmpty());
    }

    @Test
    public void testAddIsSizeOne() {
        this.addItemToList(1);
        assertEquals(1, this.circularList.size());
    }

    @Test
    public void testAddMultipleIsNotEmpty() {
        this.addItemToList(this.numberOfElementsAdded);
        assertFalse(this.circularList.isEmpty());
    }

    @Test
    public void testAddMultipleIsSizeCorrect() {
        this.addItemToList(this.numberOfElementsAdded);
        assertEquals(this.numberOfElementsAdded, this.circularList.size());
    }

    @Test
    public void testNextOnEmptyList() {
        assertEquals(Optional.empty(), this.circularList.next());
    }

    @Test
    public void testPreviousOnEmptyList() {
        assertEquals(Optional.empty(), this.circularList.previous());
    }

    @Test
    public void testNextAfterAddingOne() {
        this.circularList.add(1);
        assertEquals(Optional.of(1), this.circularList.next());
    }

    @Test
    public void testPreviousAfterAddingOne() {
        this.circularList.add(1);
        assertEquals(Optional.of(1), this.circularList.previous());
    }

    private void addIncrementalNumbersToList(int amount) {
        for (int i = 0; i < amount; i++) {
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

    private List<Integer> iterateNext(int amount) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            list.add(this.circularList.next().get());
        }
        return list;
    }

    private List<Integer> iteratePrevious(int amount) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            list.add(this.circularList.previous().get());
        }
        return list;
    }

    @Test
    public void testNextAfterAddingMultipleElements() {
        List<Integer> expectedOutput;
        List<Integer> actualOutput = new ArrayList<>();
        this.addIncrementalNumbersToList(this.numberOfElementsAdded);
        expectedOutput = this.buildIncrementalNumbersList(this.numberOfElementsAdded);
        actualOutput = this.iterateNext(this.numberOfElementsAdded);
        assertArrayEquals(
            expectedOutput.stream().toArray(),
            actualOutput.stream().toArray()
        );
    }

    @Test
    public void testPreviousAfterAddingMultipleElements() {
        List<Integer> expectedOutput;
        List<Integer> actualOutput = new ArrayList<>();
        this.addIncrementalNumbersToList(this.numberOfElementsAdded);
        expectedOutput = this.buildIncrementalNumbersList(this.numberOfElementsAdded);
        Collections.reverse(expectedOutput);
        actualOutput = iteratePrevious(this.numberOfElementsAdded);
        assertArrayEquals(
            expectedOutput.stream().toArray(),
            actualOutput.stream().toArray()
        );
    }

    @Test
    public void testNextCircularity() {
        List<Integer> nextValuesList = new ArrayList<>();
        this.addIncrementalNumbersToList(this.numberOfElementsAdded);
        nextValuesList = this.iterateNext(this.numberOfElementsAdded + this.circularityTestOffset);
        int expectedNumber = this.circularityTestOffset - 1;
        assertEquals(expectedNumber, nextValuesList.get(nextValuesList.size() - 1));
    }

    @Test
    public void testPreviousCircularity() {
        List<Integer> previousValuesList = new ArrayList<>();
        this.addIncrementalNumbersToList(this.numberOfElementsAdded);
        previousValuesList = this.iteratePrevious(this.numberOfElementsAdded + this.circularityTestOffset);
        int expectedNumber = this.numberOfElementsAdded - this.circularityTestOffset;
        assertEquals(expectedNumber, previousValuesList.get(previousValuesList.size() - 1));
    }

    @Test
    public void testReset() {
        this.addIncrementalNumbersToList(this.numberOfElementsAdded);
        int nextBeforeReset = this.circularList.next().get();
        this.circularList.reset();
        int nextAfterReset = this.circularList.next().get();
        assertEquals(nextBeforeReset, nextAfterReset);
    }
}
