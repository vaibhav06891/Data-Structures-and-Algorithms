/**
 * Your implementation of a linked stack.
 *
 * @author VAIBHAV DEDHIA (903117055)
 * @version 1.0
 */
public class LinkedStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private int size;

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public T pop() {
        T val;
        if (this.size == 0) {
            throw new java.util.NoSuchElementException("cannot dequeue"
                    + "when the stack is null");
        } else {
            val = this.head.getData();
            this.head = this.head.getNext();
            this.size = this.size - 1;
            return val;
        }
    }

    @Override
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }
        this.head = new LinkedNode<T>(data, this.head);
        this.size = this.size + 1;
    }

    @Override
    public int size() {
        return this.size;

    }

    /**
     * Returns the head of this stack.
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
}