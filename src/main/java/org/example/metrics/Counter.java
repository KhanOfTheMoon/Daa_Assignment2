package org.example.metrics;

public final class Counter {
    private long comparisons;
    private long arrayAccesses;
    private long allocations;
    private long elapsedNanos;
    private long startNanos;

    public void reset() {
        comparisons = 0;
        arrayAccesses = 0;
        allocations = 0;
        elapsedNanos = 0;
        startNanos = 0;
    }

    public void incComparisons(long k)  { comparisons += k; }
    public void incArrayAccesses(long k){ arrayAccesses += k; }
    public void incAllocations(long k)  { allocations += k; }

    public void tic() { startNanos = System.nanoTime(); }
    public void toc() { elapsedNanos = System.nanoTime() - startNanos; }

    public long getComparisons()   { return comparisons; }
    public long getArrayAccesses() { return arrayAccesses; }
    public long getAllocations()   { return allocations; }
    public long getElapsedNanos()  { return elapsedNanos; }
}