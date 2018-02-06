/**
 * Your implementation of Recursion
 *
 * @author VAIBHAV DEDHIA (903117055)
 * @version 1.1
 */
public class Recursion {
    // DO NOT ADD ANY ADDITIONAL INSTANCE VARIABLES

    /**
     * helper method to check if a given string is a Palindrome.
     * @param text
     * @return
     */

    private static boolean computePalindrome(String text) {
        if (text == null) {
            throw new IllegalArgumentException("data cannot be null");
        }
        if (text.length() == 0 || text.length() == 1) {
            return true;
        }
        if (text.charAt(0) == text.charAt(text.length() - 1)) {
            return computePalindrome(text.substring(1, text.length() - 1));
        }


        return false;
    }
    /**
     * Returns a boolean value representing whether the passed in character
     * sequence is a valid palindrome. A palindrome is defined as such:
     * A word, phrase, or sequence that reads the same backward as forward.
     *
     * Palindromes are recursively defined as such:
     * Case 1: An empty string or single character is considered a palindrome
     * Case 2: A string is a palidrome if and only if the first and last
     * characters are the same and the substring between the first and last
     * characters is itself a palindrome (recursively check if the substring
     * is a palindrome).
     *
     * This method must be computed recursively! Failure to do so will result
     * in zero credit for this method. You must use a private helper method
     * for the recursion.
     *
     * @param text The sequence that will be tested to see if it is a palindrome
     * @return Whether the passed in word is a palindrome
     * @throws IllegalArgumentException if text is null
     */
    public boolean isPalindrome(String text) {
        return computePalindrome(text);
    }

    /**
     * A helper method to compute GCD
     * @param x
     * @param y
     * @return
     */



    public int gcd(int x, int y) {
        if (x < 0 || y < 0) {
            return -1;
        }
        return computeGCD(x, y);
    }
    /**
     * Returns the greatest common divisor of integers x and y. The greatest
     * common divisor can be determined by the recursive function as follows:
     *
     * Case 1: gcd(x, y) = gcd(x-y, y) where x > y
     * Case 2: gcd(x, y) = gcd(x, y-x) where x < y
     * Case 3: gcd(x, y) = x = y where x == y
     * Case 4 (Edge case): gcd(x, y) = {x if y == 0 or y if x == 0}
     *
     * This method must be computed recursively! Failure to do so will result
     * in zero credit for this method. You must use a private helper method 
     * for the recursion.
     *
     * @param x The first integer
     * @param y The second integer
     * @return The greatest common divisor of x and y, or -1 if either
     * x or y is negative
     */

    private static int computeGCD(int x, int y) {
        if (x == y) {
            return x;
        } else if (x == 0 || y == 0) {
            return x + y;
        } else if (x > y) {
            return computeGCD(x - y, y);
        } else {
            return computeGCD(x, y - x);
        }
    }
}