package org.example.cli;

import org.example.algos.Kadane;
import org.example.metrics.Counter;
import org.example.util.CsvWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Benchmark {
    public static void main(String[] args) {
        int[] sizes = {10, 100, 1000, 10000, 100000};
        int trials = 1;
        String out = "results.csv";
        long seed = 42L;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--sizes" -> sizes = Arrays.stream(args[++i].split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
                case "--trials" -> trials = Integer.parseInt(args[++i]);
                case "--out" -> out = args[++i];
                case "--seed" -> seed = Long.parseLong(args[++i]);
            }
        }

        try (CsvWriter csv = new CsvWriter(out)) {
            csv.header("n","majority","time_ns","comparisons","Array Accesses");
            Counter t = new Counter();
            Random rng = new Random(seed);

            for (int n : sizes) {
                for (int trial = 1; trial <= trials; trial++) {
                    int[] a = randomArray(n, rng);
                    Kadane.kadaneTracked(a, t);
                    csv.row(n, 1, t.getElapsedNanos(), t.getComparisons(), t.getArrayAccesses());
                }
            }
            System.out.println("CSV written: " + out);
        } catch (IOException e) {
            System.err.println("CSV error: " + e.getMessage());
            System.exit(1);
        }
    }

    static int[] randomArray(int n, Random rng) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = rng.nextInt(2001) - 1000;
        return a;
    }
}
