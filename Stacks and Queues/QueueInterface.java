/**
 * Interface detailing the methods required for implementing a queue.
 *
 * @version 1.0
 */
public interface QueueInterface<T> {

    /**
     * The initial capacity of a queue with fixed-size backing storage.
     */
    public static final int INITIAL_CAPACITY = 11;

    /**
     * Dequeue from the queue. For ArrayQueue, this 
     * should be done from the {@code front} variable.
     *
     * This method should be implemented in O(1) time.
     *
     * @return the data from the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    T dequeue();

    /**
     * Add the given data to the queue. For ArrayQueue, 
     * this should be done to the {@code back} variable.
     *
     * This method should be implemented in (if array-backed, amortized) O(1)
     * time.
     *
     * @param data the data to add
     * @throws IllegalArgumentException if data is null
     */
    void enqueue(T data);

    /**
     * Return true if this queue contains no elements, false otherwise.
     *
     * This method should be implemented in O(1) time.
     *
     * @return true if the queue is empty; false otherwise
     */
    boolean isEmpty();

    /**
     * Return the size of the queue.
     *
     * This method should be implemented in O(1) time.
     *
     * @return number of items in the queue
     */
    int size();
}
