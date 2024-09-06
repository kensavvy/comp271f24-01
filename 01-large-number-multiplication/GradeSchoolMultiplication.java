import java.util.Arrays;

public class GradeSchoolMultiplication {

    /** Establish useful constant */
    private static final int DEFAULT_BASE = 10;

    /** Method to multiply two integer arrays with a given base number, returning the product as an array
     *  @param int[] x
     *  @param int[] y
     *  @param int bas
     *  @return multiplication result as a single digit array
     */

    public static int[] multiply(final int[] x, final int[] y, final int base) {
        int array1 = x.length;
        // set integer value to the length of int[] x
        int array2 = y.length;
        // set integer value to the lenght of int[] y
        int[] result = new int[array1 + array2];
        // initalize integer array to have as many indices as the combined length of x and y

        for (int i = array1 - 1; i >= 0; i--) {

            for (int j = array2 - 1; j >= 0; j--) {
                int product = x[i] * y[j]; 
                // product of the current digits in arrays x and y
                int sum = result[i + j + 1] + product;
                // sum of the product and the existing value
                result[i + j + 1] = sum % base;
                // ensure the current position in result contains only single digits
                result[i + j] += sum / base;
                // compute the carry-over and add it to the next position

            } // close for loop that iterates backwards across int[] y

        } // close for loop that iterates backwards across int[] x

        int index = 0;
        // intialize integer to track index

        while (index < result.length - 1 && result[index] == 0) {
            index++;
        } // close while loop that increments index until the first non-zero element is found

        int[] fixedArraySize = new int[result.length - index];
        // initialize integer array that starts at the index of the first non-zero element

            for (int i = index; i < result.length; i++) {
                fixedArraySize[i - index] = result[i];
                // copy the current digit in the position that excludes leading zeroes (starts new array at position 0)

            } // close for loop that iterates across the result array

        return fixedArraySize;

    } // method multiply

    public static int[] multiply(final int[] x, final int[] y) {
        return multiply(x, y, DEFAULT_BASE);
    } // method multiply

    public static void main(String[] args) {
        int[] x = {1, 2, 3, 4};
        int[] y = {5, 6, 7, 8};
        int base = 10;
        int[] product = multiply(x,y,base);
        System.out.println(Arrays.toString(product));

    } // method main

} // class GradeSchoolMultiplication
