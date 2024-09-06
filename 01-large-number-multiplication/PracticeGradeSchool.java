import java.util.Arrays;

public class PracticeGradeSchool {

    public static int[] multArrToInt(int[] arr1, int[] arr2) {
        int n1 = arr1.length;
        int n2 = arr2.length;
        int[] result = new int[n1 + n2];

        // Reverse the arrays to make the multiplication easier (starting from the least significant digit)
        int[] arr1Reversed = reverseArray(arr1);
        int[] arr2Reversed = reverseArray(arr2);

        // Perform multiplication digit by digit
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                int product = arr1Reversed[i] * arr2Reversed[j];
                int temp = result[i + j] + product;
                result[i + j] = temp % 10; // Store the single digit
                result[i + j + 1] += temp / 10; // Carry over the tens digit
            }
        }
        // Re-Reverse the result array to get the correct order
        result = reverseArray(result);
        
        // Remove leading zeros if present
        int start = 0;
        while (start < result.length - 1 && result[start] == 0) {
            start++;
        }
        return Arrays.copyOfRange(result, start, result.length);
    }

    // Helper method to reverse an array
    private static int[] reverseArray(int[] array) {
        int n = array.length;
        int[] reversed = new int[n];
        for (int i = 0; i < n; i++) {
            reversed[i] = array[n - 1 - i];
        }
        return reversed;
    }
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4};  // Represents the integer 1234
        int[] arr2 = {5, 6, 7, 8};  // Represents the integer 5678
        int[] target = multArrToInt(arr1, arr2);
        System.out.println(Arrays.toString(target)); 
    }
}