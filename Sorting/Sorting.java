import java.util.Comparator;
import java.util.Random;
import java.util.LinkedList;
import java.util.Iterator;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Vaibhav Dedhia 903117055
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new java.lang.IllegalArgumentException("Array is null");
        }
        if (comparator == null) {
            throw new java.lang.IllegalArgumentException("comparator is null");
        }

        for (int i = 1; i < arr.length; ++i) {
            T key = arr[i];
            int j = i - 1;
            while (j >= 0 && comparator.compare(arr[j], key) > 0) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }

    }



    /**
     * Implement kth select.
     * @param <T> data type to sort
     * @param arr the array
     * @param l left index
     * @param r right index
     * @param k the element
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     * @return the kth smallest element
     */
    private static <T> T kthSmallest(T[] arr, int l, int r, int k,
                     Comparator<T> comparator, Random rand) {
        int pos = partition(arr, l, r, comparator, rand);
        if (pos == k - 1) {
            return arr[pos];
        } else if (pos > k - 1) {
            return kthSmallest(arr, l, pos - 1, k, comparator, rand);
        }
        return kthSmallest(arr, pos + 1, r, k, comparator, rand);
    }

    /**
     * Implement kth select.
     *
     * @param <T> data type to sort
     * @param arr the array
     * @param l left index
     * @param r right index
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     * @return the kth smallest element
     */
    private static <T> int partition(T[]arr, int l, int r,
                                     Comparator<T> comparator, Random rand) {
        int pivotIndex = rand.nextInt(r - l) + l;
        T temp = arr[pivotIndex];
        arr[pivotIndex] = arr[l];
        arr[l] = temp;
        int i = l + 1;
        int j = r;
        while (i <= j) {
            while (i <= r && comparator.compare(arr[i], arr[l]) <= 0) {
                ++i;
            }
            while (j >= l && comparator.compare(arr[j], arr[l]) >= 0) {
                --j;
            }
            if (j > i) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        temp = arr[j];
        arr[j] = arr[l];
        arr[l] = temp;
        return j;
    }

    /**
     * Implement kth select.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = r.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Note that there may be duplicates in the array.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not use the one we have taught you!
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null or k is not in the range of 0 to arr.length - 1
     * @param <T> data type to sort
     * @param k the index + 1 (due to 0-indexing) to retrieve the data 
     * from as if the array were sorted; the 'k' in "kth select"
     * @param arr the array that should be modified after the method
     * is finished executing as needed
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     * @return the kth smallest element
     */
    public static <T> T kthSelect(int k, T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null) {
            throw new java.lang.IllegalArgumentException("array is null");
        } else if (comparator == null) {
            throw new java.lang.IllegalArgumentException("comparator is null");
        } else if (rand == null) {
            throw new java.lang.IllegalArgumentException("rand is null");
        } else if (k > arr.length - 1) {
            throw new java.lang.IllegalArgumentException("k cannot be "
                    + "greater than array size");
        }
        return kthSmallest(arr, 0, arr.length - 1, k, comparator, rand);
    }




    /**
     * Merges 2 sorted sub-arrays
     *
     * @param <T> the type
     * @param arr the array
     * @param l left index
     * @param m middle index
     * @param r right index
     * @param comparator comparator
     */
    public static <T> void merge(T[] arr, int l, int m, int r,
                                 Comparator<T> comparator) {
        int n1 = m - l + 1;
        int n2 = r - m;

        T[] left  = (T[]) new Object[n1];
        T[] right = (T[]) new Object[n2];

        for (int i = 0; i < n1; ++i) {
            left[i] = arr[l + i];
        }
        for (int j = 0; j < n2; ++j) {
            right[j] = arr[m + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = l;

        while (i < n1 && j < n2) {
            if (comparator.compare(left[i], right[j]) <= 0) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = left[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }
    /**
     * Implement kth select.
     * @param <T> type
     * @param arr the array
     * @param l left index
     * @param r right index
     * @param comparator comparator
     */
    public static <T> void sort(T[] arr, int l, int r,
                                Comparator<T> comparator) {
        if (l < r) {
            int m = (l + r) / 2;
            sort(arr, l, m, comparator);
            sort(arr, m + 1, r, comparator);
            merge(arr, l, m, r, comparator);
        }
    }
    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * When necessary due to an odd number of elements, the
     * excess element MUST go on the right side!
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new java.lang.IllegalArgumentException("Array is null");
        }
        if (comparator == null) {
            throw new java.lang.IllegalArgumentException("comparator is null");
        }
        sort(arr, 0, arr.length - 1, comparator);
    }




    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code!
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * Do NOT use {@code Math.pow()} in your sort. Instead, if you need to, use
     * the provided {@code pow()} method below.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new java.lang.IllegalArgumentException("array is null");
        }
        int noOfDigits = 0;
        int maxnumber = 0;
        // calculate the maximum no. of digits
        for (int i = 0; i < arr.length; i++) {
            int number;
            if (arr[i] == Integer.MIN_VALUE) {
                number = Integer.MAX_VALUE;
            } else {
                number = Math.abs(arr[i]);
            }
            if (number > maxnumber) {
                maxnumber = number;
            }
        }
        while (maxnumber != 0) {
            maxnumber = maxnumber / 10;
            noOfDigits++;
        }


        //initialize the linkedlist
        LinkedList<Integer>[] bucket = new LinkedList[19];
        for (int i = 0; i < 19; i++) {
            bucket[i] = new LinkedList<Integer>();
        }

        int signFlag;
        int number;
        int result;
        for (int k = 0; k < noOfDigits; k++) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] < 0) {
                    signFlag = -1;
                } else {
                    signFlag = 1;
                }
                number = Math.abs(arr[i]);
                result = (number / pow(10, k)) % 10;
                result = signFlag * result;
                bucket[result + 9].add(arr[i]);
            }

            int j = 0;
            for (int l = 0; l < 19; l++) {
                Iterator it = bucket[l].iterator();
                while (it.hasNext()) {
                    arr[j] = (Integer) it.next();
                    j = j + 1;
                }
                bucket[l].clear();
            }
        }
        return arr;
    }



    /**
     * Calculate the result of a number raised to a power. Use this method in
     * your radix sorts instead of {@code Math.pow()}.
     *
     * DO NOT MODIFY THIS METHOD.
     *
     * @throws IllegalArgumentException if both {@code base} and {@code exp} are
     * 0
     * @throws IllegalArgumentException if {@code exp} is negative
     * @param base base of the number
     * @param exp power to raise the base to. Must be 0 or greater.
     * @return result of the base raised to that power
     */
    private static int pow(int base, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Exponent cannot be negative.");
        } else if (base == 0 && exp == 0) {
            throw new IllegalArgumentException(
                    "Both base and exponent cannot be 0.");
        } else if (exp == 0) {
            return 1;
        } else if (exp == 1) {
            return base;
        }
        int halfPow = pow(base, exp / 2);
        if (exp % 2 == 0) {
            return halfPow * halfPow;
        } else {
            return halfPow * halfPow * base;
        }
    }
}