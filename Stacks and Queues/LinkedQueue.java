/**
 * Your implementation of a linked queue.
 *
 * @author VAIBHAV DEDHIA (903117055)
 * @version 1.0
 */
public class LinkedQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    @Override
    public T dequeue() {

        // remove from front as remove from back will be more expensive
        T val;
        if (this.size == 0) {
            throw new java.util.NoSuchElementException("cannot dequeue "
                    + "when the queue is null");
        }
        val = this.head.getData();
        this.head = this.head.getNext();
        this.size = this.size - 1;
        if (this.size == 0) {
            this.tail = null;
        }
        return val;
    }

    @Override
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }
        // add to back. Since it has to be opposite of dequeue operation.
        LinkedNode<T> current = new LinkedNode<T>(data, null);
        if (this.head == null) {
            this.head = current;
        } else {
            this.tail.setNext(current);
        }
        this.size = this.size + 1;
        this.tail = current;
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
     * Returns the head of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the head node
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the tail node
     */
    public LinkedNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}