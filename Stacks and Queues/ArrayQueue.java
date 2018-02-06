/**
 * Your implementation of an array-backed queue.
 *
 * @author VAIBHAV DEDHIA (903117055)
 * @version 1.0
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    
    // {@code front} is the index you will dequeue from
    private int front;
    // {@code back} is the index you will enqueue into
    private int back;
    private int size;

    /**
     * Constructs a new ArrayQueue.
     */
    public ArrayQueue() {
        this.backingArray = (T[]) new Object[11];

    }

    /**
     * Dequeue from the front of the queue.
     *
     * Do not shrink the backing array.
     * If the queue becomes empty as a result of this call, you must not
     * explicitly reset front or back to 0.
     *
     * @see QueueInterface#dequeue()
     */
    @Override
    public T dequeue() {
        if (this.size == 0) {
            throw new java.util.NoSuchElementException("cannot dequeue "
                     + "when the queue is null");
        }
        T val = backingArray[this.front % backingArray.length];
        backingArray[this.front % backingArray.length] = null;
        this.front = this.front + 1;
        this.size = this.size - 1;
        return val;
    }

    /**
     * Add the given data to the back of the queue.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to (double the current length) + 1; in essence, 2n + 1, where n
     * is the current capacity. If a regrow is necessary, you should copy
     * elements to the beginning of the new array and reset front to 0.
     *
     * @see QueueInterface#enqueue(T)
     */
    @Override
    public void enqueue(T data) {
        T[] newArray;
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }

        // if the current array is full
        if (this.size == backingArray.length) {
            newArray = (T[]) new Object[backingArray.length * 2 + 1];
            for (int i = 0; i < this.size; i++) {
                newArray[i] = backingArray[front + i];
            }

            backingArray = (T[]) new Object[newArray.length];
            this.front = 0;
            // copy contents back to backingArray
            for (int i = 0; i < this.size; i++) {
                backingArray[i] = newArray[i];
            }
        }

        backingArray[this.back % backingArray.length] = data;
        this.size = this.size + 1;
        this.back = this.back + 1;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;

    }

    /**
     * Returns the backing array of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the backing array
     */
    public Object[] getBackingArray() {
        // DO NOT MODIFY!
        return backingArray;
    }
}
