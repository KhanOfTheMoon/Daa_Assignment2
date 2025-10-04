package org.example.algos;

import org.example.metrics.Counter;
import org.example.util.CsvWriter;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Random;

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

    @Disabled("run manually to generate CSV")
    @Test
    void perfSmoke() throws IOException {
        int[] sizes = {100, 1000, 10000, 100000};
        int trials = 5;
        String out = "kadane_results.csv";
        long seed = 42L;

        try (CsvWriter csv = new CsvWriter(out)) {
            csv.header("algo","n","trial","ns","comparisons","arrayAccesses","allocations","maxSum","start","end");
            Counter t = new Counter();
            Random rng = new Random(seed);
            for (int n : sizes) {
                if (n <= 0) continue;
                for (int trial = 1; trial <= trials; trial++) {
                    int[] a = randomArray(n, rng);
                    var r = Kadane.kadaneTracked(a, t);
                    csv.row("Kadane", n, trial, t.getElapsedNanos(), t.getComparisons(), t.getArrayAccesses(), t.getAllocations(), r.maxSum, r.start, r.end);
                }
            }
        }
    }

    private static int[] randomArray(int n, Random rng) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = rng.nextInt(2001) - 1000;
        return a;
    }
}
