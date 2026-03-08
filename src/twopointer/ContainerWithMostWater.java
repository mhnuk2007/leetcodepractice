package twopointer;

public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            int width = right - left;
            int minHeight = Math.min(height[left], height[right]);
            int area = minHeight * width;
            maxArea = Math.max(maxArea, area);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        ContainerWithMostWater solution = new ContainerWithMostWater();

        // Test Case 1: General case
        int[] height1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("Test 1: " + solution.maxArea(height1)); // Expected: 49
        // Explanation: Lines at index 1 (8) and 8 (7), area = min(8,7) * 7 = 49

        // Test Case 2: Minimal array
        int[] height2 = {1, 1};
        System.out.println("Test 2: " + solution.maxArea(height2)); // Expected: 1
        // Explanation: min(1,1) * 1 = 1

        // Test Case 3: Increasing heights
        int[] height3 = {1, 2, 3, 4, 5};
        System.out.println("Test 3: " + solution.maxArea(height3)); // Expected: 6
        // Explanation: Lines at index 1 (2) and 4 (5), area = min(2,5) * 3 = 6

        // Test Case 4: Decreasing heights
        int[] height4 = {5, 4, 3, 2, 1};
        System.out.println("Test 4: " + solution.maxArea(height4)); // Expected: 6
        // Explanation: Lines at index 0 (5) and 3 (2), area = min(5,2) * 3 = 6

        // Test Case 5: All same height
        int[] height5 = {4, 4, 4, 4};
        System.out.println("Test 5: " + solution.maxArea(height5)); // Expected: 12
        // Explanation: Any two endpoints, area = min(4,4) * 3 = 12

        // Test Case 6: Two tall lines far apart
        int[] height6 = {9, 1, 1, 1, 1, 1, 1, 1, 9};
        System.out.println("Test 6: " + solution.maxArea(height6)); // Expected: 72
        // Explanation: Lines at index 0 (9) and 8 (9), area = min(9,9) * 8 = 72

        // Test Case 7: Mountain shape
        int[] height7 = {1, 2, 4, 3};
        System.out.println("Test 7: " + solution.maxArea(height7)); // Expected: 4
        // Explanation: Multiple options give area 4
    }
}
