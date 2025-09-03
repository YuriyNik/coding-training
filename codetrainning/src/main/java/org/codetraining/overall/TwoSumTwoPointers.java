package org.codetraining.overall;

import java.util.Arrays;
import java.util.Comparator;

public class TwoSumTwoPointers {

    public int[] twoSum(int[] nums, int target) {
        // массив пар (число, индекс)
        int[][] arr = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            arr[i][0] = nums[i]; // значение
            arr[i][1] = i;       // индекс
        }

        // сортировка по значению
        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));

        int left = 0, right = arr.length - 1;
        while (left < right) {
            int sum = arr[left][0] + arr[right][0];
            if (sum == target) {
                return new int[]{arr[left][1], arr[right][1]};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        throw new IllegalArgumentException("No solution found");
    }
}
