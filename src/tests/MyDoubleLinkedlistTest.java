package tests;

import Lists.MyDoubleLinkedlist;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyDoubleLinkedlistTest {

    List<Integer> list;

    /*
    * Tests:
    * size, isEmpty, contains, iterator, toArray, add,
    * clear, get, set, add(i), remove(i)
    *
    * */
    @org.junit.jupiter.api.Test
    void testMyDoubleLinkedlist() {
        list = new MyDoubleLinkedlist<>();
        // Case of none
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertFalse(list.contains(1));
        Iterator<Integer> iter = list.iterator();
        assertFalse(iter.hasNext());
        Object[] tempArr = list.toArray();
        Integer[] arr = Arrays.copyOf(tempArr, tempArr.length, Integer[].class);
        assertEquals(0, arr.length);

        caseOfOne();

        caseOfMany();
    }

    void caseOfOne() {
        assertTrue(list.add(1));
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertTrue(list.contains(1));
        Iterator<Integer> iter = list.iterator();
        assertTrue(iter.hasNext());
        assertEquals(1, iter.next());
        assertFalse(iter.hasNext());
        Object[] tempArr = list.toArray();
        Integer[] arr = Arrays.copyOf(tempArr, tempArr.length, Integer[].class);
        assertEquals(1, arr.length);
        assertEquals(arr[0], list.get(0));
        assertEquals(1, list.set(0, 2));

        assertEquals(2, list.remove(0));
        assertFalse(list.contains(1));
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    void caseOfMany() {
        for (int i = 0; i < 10; i++) {
            assertTrue(list.add(i));
        }
        assertEquals(10, list.size());
        assertFalse(list.isEmpty());
        for (int i = 0; i < 10; i++) {
            assertTrue(list.contains(i));
        }
        Iterator<Integer> iter = list.iterator();
        for (int i = 0; i < 10; i++) {
            assertTrue(iter.hasNext());
            assertEquals(i, iter.next());
        }
        assertFalse(iter.hasNext());
        Object[] tempArr = list.toArray();
        Integer[] arr = Arrays.copyOf(tempArr, tempArr.length, Integer[].class);
        assertEquals(10, arr.length);
        for (int i = 0; i < 10; i++) {
            assertEquals(arr[i], list.get(i));
        }
        for (int i = 10; i < 20; i++) {
            assertEquals(i - 10, list.set(i - 10, i));
        }

        for (int i = 10; i < 15; i++) {
            assertEquals(i, list.remove(0));
        }
        for (int i = 19; i > 14; i--) {
            assertEquals(i, list.remove(list.size() - 1));
        }
        assertFalse(list.contains(1));
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }
}