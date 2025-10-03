package org.example.algos;

import org.example.metrics.Counter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class KadaneTest {
    @Test
    void classic() {
        int[] a = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        var r = Kadane.kadane(a);
        assertEquals(6, r.maxSum);
        assertEquals(3, r.start);
        assertEquals(6, r.end);
    }

    @Test
    void allNegative() {
        int[] a = {-8, -3, -6, -2, -5, -4};
        var r = Kadane.kadane(a);
        assertEquals(-2, r.maxSum);
        assertEquals(3, r.start);
        assertEquals(3, r.end);
    }

    @Test
    void allPositive() {
        int[] a = {1, 2, 3, 4};
        var r = Kadane.kadane(a);
        assertEquals(10, r.maxSum);
        assertEquals(0, r.start);
        assertEquals(3, r.end);
    }

    @Test
    void singleElement() {
        int[] a = {7};
        var r = Kadane.kadane(a);
        assertEquals(7, r.maxSum);
        assertEquals(0, r.start);
        assertEquals(0, r.end);
    }

    @Test
    void emptyArray() {
        int[] a = {};
        var r = Kadane.kadane(a);
        assertEquals(0, r.maxSum);
        assertEquals(-1, r.start);
        assertEquals(-1, r.end);
    }

    @Test
    void trackedHasMetrics() {
        int[] a = {-1, 3, -2, 5, -1};
        var t = new Counter();
        var r = Kadane.kadaneTracked(a, t);
        assertEquals(6, r.maxSum);
        assertTrue(t.getComparisons() > 0);
        assertTrue(t.getArrayAccesses() > 0);
        assertEquals(0, t.getAllocations());
        assertTrue(t.getElapsedNanos() > 0);
    }
}
