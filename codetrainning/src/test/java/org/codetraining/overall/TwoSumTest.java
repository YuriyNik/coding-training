package org.codetraining.overall;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TwoSumTest {

    @Test
    void testSimpleCase() {
        TwoSum solver = new TwoSum();
        int[] nums = {2, 7, 11, 15};
        int[] result = solver.twoSum(nums, 9);
        assertArrayEquals(new int[]{0, 1}, result);
    }

    @Test
    void testAnotherCase() {
        TwoSum solver = new TwoSum();
        int[] nums = {3, 2, 4};
        int[] result = solver.twoSum(nums, 6);
        assertArrayEquals(new int[]{1, 2}, result);
    }

    @Test
    void testWithNegativeNumbers() {
        TwoSum solver = new TwoSum();
        int[] nums = {-3, 4, 3, 90};
        int[] result = solver.twoSum(nums, 0);
        assertArrayEquals(new int[]{0, 2}, result);
    }

    @Test
    void testNoSolutionThrows() {
        TwoSum solver = new TwoSum();
        int[] nums = {1, 2, 3};
        assertThrows(IllegalArgumentException.class, () -> solver.twoSum(nums, 10));
    }


}
