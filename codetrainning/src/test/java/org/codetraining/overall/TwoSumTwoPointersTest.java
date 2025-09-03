package org.codetraining.overall;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TwoSumTwoPointersTest {
    @Test
    void testSimpleCase() {
        TwoSumTwoPointers solver = new TwoSumTwoPointers();
        int[] nums = {2, 7, 11, 15};
        int[] result = solver.twoSum(nums, 9);
        assertArrayEquals(new int[]{0, 1}, result);
    }

    @Test
    void testAnotherCase() {
        TwoSumTwoPointers solver = new TwoSumTwoPointers();
        int[] nums = {3, 2, 4};
        int[] result = solver.twoSum(nums, 6);
        assertArrayEquals(new int[]{1, 2}, result);
    }

    @Test
    void testWithNegativeNumbers() {
        TwoSumTwoPointers solver = new TwoSumTwoPointers();
        int[] nums = {-3, 4, 3, 90};
        int[] result = solver.twoSum(nums, 0);
        assertArrayEquals(new int[]{0, 2}, result);
    }

    @Test
    void testNoSolutionThrows() {
        TwoSumTwoPointers solver = new TwoSumTwoPointers();
        int[] nums = {1, 2, 3};
        assertThrows(IllegalArgumentException.class, () -> solver.twoSum(nums, 10));
    }

}
