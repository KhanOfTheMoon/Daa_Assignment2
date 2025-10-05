# Kadane’s Algorithm — Assignment 2

**Subject:** Design and Analysis of Algorithms
**Author:** Aibek Nurtay
**Group:** SE-2433

## Overview:
This project presents the implementation and analysis of Kadane’s Algorithm — a dynamic programming approach for finding the maximum sum of a contiguous subarray within a one-dimensional array.
The algorithm determines both the maximum sum and the starting and ending indices of the subarray.
An empirical study was conducted to measure runtime, comparisons, and array accesses for arrays of increasing size.
The observed results confirm Kadane’s theoretical efficiency: linear time complexity **O(n)** and constant space complexity **O(1)**.

## Algorithm Description:
Kadane’s Algorithm solves the Maximum Subarray Problem using a single linear pass through the array.
It maintains two values during iteration — the sum of the current subarray and the best (maximum) sum found so far.
Whenever the current sum becomes negative, it is reset, since extending a negative-sum subarray would only reduce the total.
By continuously updating the best sum and tracking the corresponding indices, the algorithm efficiently identifies the optimal segment.

For example, given the array `[-2, 1, -3, 4, -1, 2, 1, -5, 4]`,
the maximum subarray is `[4, -1, 2, 1]`, where
**Maximum Sum = 6**, **Start Index = 3**, **End Index = 6**.

## Theoretical Analysis:
Kadane’s Algorithm has a time complexity of Θ(n), as each element in the array is processed exactly once and every operation inside the loop takes constant time.
Its space complexity is O(1), since it uses only a few scalar variables to track sums and indices.
In the best case, when all elements are positive, the algorithm still performs a single pass through the array.
In the worst case, when all elements are negative, every element is still examined once.
Kadane’s Algorithm is therefore optimal for this problem, as any correct algorithm must inspect all elements.

## Experimental Results

| n       | time (ns) | comparisons | array accesses |
| ------- | --------- | ----------- | -------------- |
| 10      | 3,000     | 18          | 10             |
| 100     | 12,600    | 198         | 100            |
| 1,000   | 121,800   | 1,998       | 1,000          |
| 10,000  | 732,200   | 19,998      | 10,000         |
| 100,000 | 5,367,600 | 199,998     | 100,000        |

## Discussion:
Kadane’s Algorithm demonstrates how local optimization can lead directly to a global optimum.
At each iteration, it decides whether to extend the existing subarray or start a new one, based purely on the sign of the current cumulative sum.
This eliminates redundant checks and nested iterations required in naive quadratic algorithms.

Key strengths include:
* Execution in a single pass
* Constant auxiliary memory usage
* Absence of recursion or additional data structures
* Guaranteed optimal result
Because of these properties, Kadane’s Algorithm is widely regarded as a fundamental example in the study of dynamic programming and computational efficiency.

## Conclusion
The implementation and analysis confirm that Kadane’s Algorithm achieves Θ(n) runtime and O(1) space usage.
It accurately identifies the optimal subarray and its boundaries, exhibiting stable and scalable performance across input sizes.
Empirical data align with theoretical expectations, highlighting the algorithm’s efficiency, simplicity, and reliability in solving the Maximum Subarray Problem.
