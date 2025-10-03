package org.example.algos;

import org.example.metrics.Counter;

public class Kadane {
    public static final class Result {
        public final int maxSum;
        public final int start;
        public final int end;
        public Result(int maxSum, int start, int end) { this.maxSum = maxSum; this.start = start; this.end = end; }
        @Override public String toString(){ return "maxSum=" + maxSum + ", [" + start + "," + end + "]"; }
    }

    public static Result kadane(int[] a) {
        if (a == null || a.length == 0) return new Result(0, -1, -1);
        int bestSum = a[0], bestL = 0, bestR = 0;
        int curSum  = a[0], curL  = 0;
        for (int i = 1; i < a.length; i++) {
            int extend = curSum + a[i];
            if (a[i] > extend) { curSum = a[i]; curL = i; }
            else               { curSum = extend; }
            if (curSum > bestSum) { bestSum = curSum; bestL = curL; bestR = i; }
        }
        return new Result(bestSum, bestL, bestR);
    }

    public static Result kadaneTracked(int[] a, Counter t) {
        if (t == null) t = new Counter();
        t.reset();
        if (a == null || a.length == 0) return new Result(0, -1, -1);
        t.tic();
        int bestSum = a[0], bestL = 0, bestR = 0;
        int curSum  = a[0], curL  = 0;
        t.incArrayAccesses(1);
        for (int i = 1; i < a.length; i++) {
            t.incArrayAccesses(2);
            int extend = curSum + a[i];
            t.incComparisons(1);
            if (a[i] > extend) { curSum = a[i]; curL = i; }
            else               { curSum = extend; }
            t.incComparisons(1);
            if (curSum > bestSum) { bestSum = curSum; bestL = curL; bestR = i; }
        }
        t.toc();
        return new Result(bestSum, bestL, bestR);
    }
}
