import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Your implementations of various string searching algorithms.
 *
 * @author Vaibhav Dedhia (903117055)
 * @version 1.0
 */
public class StringSearching {

    /**
     * Knuth-Morris-Pratt (KMP) algorithm that relies on the failure table (also
     * called failure function). Works better with small alphabets.
     *
     * Make sure to implement the failure table before implementing this method.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text is null
     * @param pattern the pattern you are searching for in a body of text
     * @param text the body of text where you search for pattern
     * @return list containing the starting index for each match found
     */
    public static List<Integer> kmp(CharSequence pattern, CharSequence text) {
        if (pattern == null) {
            throw new java.lang.IllegalArgumentException("pattern "
                    + "cannot be null");
        } else if (text == null) {
            throw new java.lang.IllegalArgumentException("text cannot be null");
        }

        List<Integer> list = new ArrayList<>();
        int[] table = buildFailureTable(pattern);
        int i = 0;     // for text
        int j = 0;     // for pattern
        while (i < text.length() && pattern.length() - j <= text.length() - i) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if (j == pattern.length()) {
                    list.add(i - j);
                    j = table[j - 1];
                }
            } else {
                if (j != 0) {
                    j = table[j - 1];
                } else {
                    i = i + 1;
                }
            }
        }
        return list;
    }

    /**
     * Builds failure table that will be used to run the Knuth-Morris-Pratt
     * (KMP) algorithm.
     *
     * The table built should be the length of the input text.
     *
     * Note that a given index i will be the largest prefix of the pattern
     * indices [0..i] that is also a suffix of the pattern indices [1..i].
     * This means that index 0 of the returned table will always be equal to 0
     *
     * Ex. ababac
     *
     * table[0] = 0
     * table[1] = 0
     * table[2] = 1
     * table[3] = 2
     * table[4] = 3
     * table[5] = 0
     *
     * If the pattern is empty, return an empty array.
     *
     * @throws IllegalArgumentException if the pattern is null
     * @param pattern a {@code CharSequence} you're building a failure table for
     * @return integer array holding your failure table
     */
    public static int[] buildFailureTable(CharSequence pattern) {
        if (pattern == null) {
            throw new java.lang.IllegalArgumentException("pattern"
                    + " cannot be null");
        }
        int[] table = new int[pattern.length()];

        int j = 0;
        table[0] = 0;
        for (int i = 1; i < pattern.length(); i++) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                table[i] = j + 1;
                j = j + 1;
            } else {
                while (j > 0) { // && pattern.charAt(j) != pattern.charAt(i)) {
                    j = table[j - 1];
                    if (j == 0) {
                        break;
                    }
                    if (pattern.charAt(j) == pattern.charAt(i)) {
                        table[i] = j + 1;
                        j = j + 1;
                        break;
                    }
                }
                if (j == 0) {
                    table[i] = 0;
                }
            }
        }
        return table;
    }




    /**
         * Boyer Moore algorithm that relies on last occurrence table.
     * Works better with large alphabets.
     *
     * Make sure to implement the last occurrence table before implementing this
     * method.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text is null
     * @param pattern the pattern you are searching for in a body of text
     * @param text the body of text where you search for the pattern
     * @return list containing the starting index for each match found
     */
    public static List<Integer> boyerMoore(CharSequence pattern,
            CharSequence text) {
        if (pattern == null || pattern.length() == 0) {
            throw new java.lang.IllegalArgumentException("pattern "
                    + "cannot be null");
        } else if (text == null) {
            throw new java.lang.IllegalArgumentException("text cannot be null");
        }
        List<Integer> list = new ArrayList<>();
        if  (pattern.length() > text.length()) {
            return list;
        }
        Map<Character, Integer> table = buildLastTable(pattern);



        int textIndex = pattern.length() - 1;
        int patternIndex = pattern.length() - 1;

        while (textIndex < text.length()) {
            char tc = text.charAt(textIndex);
            char pc = pattern.charAt(patternIndex);

            if (tc != pc) {
                textIndex += pattern.length() - Math.min(patternIndex,
                        table.getOrDefault(tc, -1) + 1);
                patternIndex = pattern.length() - 1;
            } else {
                if (patternIndex != 0) {
                    textIndex--;
                    patternIndex--;
                } else {
                    list.add(textIndex);
                    textIndex += pattern.length();
                    patternIndex = pattern.length() - 1;
                }
            }
        }
        return list;
    }

    /**
     * Builds last occurrence table that will be used to run the Boyer Moore
     * algorithm.
     *
     * Note that each char x will have an entry at table.get(x).
     * Each entry should be the last index of x where x is a particular
     * character in your pattern.
     * If x is not in the pattern, then the table will not contain the key x,
     * and you will have to check for that in your Boyer Moore implementation.
     *
     * Ex. octocat
     *
     * table.get(o) = 3
     * table.get(c) = 4
     * table.get(t) = 6
     * table.get(a) = 5
     * table.get(everything else) = null, which you will interpret in
     * Boyer-Moore as -1
     *
     * If the pattern is empty, return an empty map.
     *
     * @throws IllegalArgumentException if the pattern is null
     * @param pattern a {@code CharSequence} you are building last table for
     * @return a Map with keys of all of the characters in the pattern mapping
     *         to their last occurrence in the pattern
     */
    public static Map<Character, Integer> buildLastTable(CharSequence pattern) {
        if (pattern == null) {
            throw new java.lang.IllegalArgumentException("pattern"
                    + " cannot be null");
        }
        Map<Character, Integer> table = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char a = pattern.charAt(i);
            table.put(a, i);
        }
        return table;
    }




    /**
     * Prime base used for Rabin-Karp hashing.
     * DO NOT EDIT!
     */
    private static final int BASE = 661;

    /**
     * A map from integers representing an exponent to integers
     * representing {@code BASE} to that exponent. Used to recall certain
     * powers of the BASE when generating or updating the hash.
     */
    private static Map<Integer, Integer> baseMap = new HashMap<>();

    /**
     * Runs Rabin-Karp algorithm. Generate the pattern hash, and compare it with
     * the hash from a substring of text that's the same length as the pattern.
     * If the two hashes match, compare their individual characters, else update
     * the text hash and continue.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text is null
     * @param pattern a string you're searching for in a body of text
     * @param text the body of text where you search for pattern
     * @return list containing the starting index for each match found
     */
    public static List<Integer> rabinKarp(CharSequence pattern,
            CharSequence text) {
        if (pattern == null || pattern.length() == 0) {
            throw new java.lang.IllegalArgumentException("pattern"
                   +  " cannot be null");
        } else if (text == null) {
            throw new java.lang.IllegalArgumentException("text cannot be null");
        }


        int patHash = generateHash(pattern, pattern.length());
        int textHash = generateHash(text, pattern.length());
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < text.length() - pattern.length(); i++) {
            if (textHash == patHash) {
                int flag = 0;
                for (int j = 0; j < pattern.length(); j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        flag = 1;
                    }
                }
                if (flag == 0) {
                    list.add(i);
                }
            }
            textHash = updateHash(textHash, pattern.length(),
                    text.charAt(i), text.charAt(i + pattern.length()));
        }

        return list;
    }

    /**
     * Hash function used for Rabin-Karp. The formula for hashing a string is:
     *
     * sum of: c * BASE ^ (pattern.length - 1 - i), where c is the integer
     * value of the current character, and i is the index of the character
     *
     * For example: Hashing "bunn" as a substring of "bunny" with base 661 hash
     * = b * 661 ^ 3 + u * 661 ^ 2 + n * 661 ^ 1 + n * 661 ^ 0 = 98 * 661 ^ 3 +
     * 117 * 661 ^ 2 + 110 * 661 ^ 1 + 110 * 661 ^ 0 = 28354061115
     *
     * However, note that that will roll over to -1710709957, because 
     * the largest number that can be represented by an int is 2147483647.
     *
     * Do NOT use {@code Math.pow()} in this method.
     *
     * @throws IllegalArgumentException if current is null
     * @throws IllegalArgumentException if length is negative, 0, or greater
     *     than the length of current
     * @param current substring you are generating hash function for
     * @param length the length of the string you want to generate the hash for,
     * starting from index 0. For example, if length is 4 but current's length
     * is 6, then you include indices 0-3 in your hash (and pretend the actual
     * length is 4)
     * @return hash of the substring
     */
    public static int generateHash(CharSequence current, int length) {
        if (current == null) {
            throw new java.lang.IllegalArgumentException("pattern"
                    + " cannot be null");
        } else if (current.length() <= 0 || length > current.length()) {
            throw new java.lang.IllegalArgumentException("illegal "
                    + "length of current");
        }

        int hash = 0;
        for (int i = 0; i < length; i++) {
            int mult = 1;
            for (int j = 0; j < length - i - 1; j++) {
                mult = mult * BASE;
            }
            baseMap.put(length - 1 -  i, mult);
            hash += current.charAt(i) * mult;
        }
        return hash;
    }

    /**
     * Updates a hash in constant time to avoid constantly recalculating
     * entire hash. To update the hash:
     *
     *  remove the oldChar times BASE raised to the length - 1, multiply by
     *  BASE, and add the newChar.
     *
     * For example: Shifting from "bunn" to "unny" in "bunny" with base 661
     * hash("unny") = (hash("bunn") - b * 661 ^ 3) * 661 + y * 661 ^ 0 =
     * (-1710709957 - 98 * 661 ^ 3) * 661 + 121 * 661 ^ 0 = -19838975385074
     *
     * However, the number will roll over to -521444850.
     *
     * This method must run in O(1) time, here meaning nothing but basic 
     * logical and arithmetic operations. Remember that 
     *
     * Do NOT use {@code Math.pow()} in this method.
     *
     * @throws IllegalArgumentException if length is negative or 0
     * @param oldHash hash generated by generateHash
     * @param length length of pattern/substring of text
     * @param oldChar character we want to remove from hashed substring
     * @param newChar character we want to add to hashed substring
     * @return updated hash of this substring
     */
    public static int updateHash(int oldHash, int length, char oldChar,
            char newChar) {
        if (length <= 0) {
            throw new java.lang.IllegalArgumentException("illegal");
        }
        int newhash = (oldHash - oldChar * baseMap.get(length - 1))
                * BASE + newChar;
        return newhash;
    }
}
