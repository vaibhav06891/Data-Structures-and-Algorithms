/**
 * Your implementation of an array-backed stack.
 *
 * @author VAIBHAV DEDHIA (903117055)
 * @version 1.0
 */
public class ArrayStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayStack.
     */
    public ArrayStack() {
        this.backingArray = (T[]) new Object[13];
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Pop from the stack.
     *
     * Do not shrink the backing array.
     *
     * @see StackInterface#pop()
     */
    @Override
    public T pop() {
        T val;
        if (this.size == 0) {
            throw new java.util.NoSuchElementException("Cannot pop "
                     + "when the Stack is null");
        } else {
            val = backingArray[this.size - 1];
            this.size = this.size - 1;
            backingArray[this.size] = null;
        }
        return val;
    }

    /**
     * Push the given data onto the stack.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to (double the current length) + 1; in essence, 2n + 1, where n
     * is the current capacity.
     *
     * @see StackInterface#push(T)
     */
    @Override
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }
        T[] largerArr;
        if (this.size == this.backingArray.length) {
            largerArr = (T[]) new Object[this.backingArray.length * 2 + 1];
            for (int i = 0; i < this.size; i++) {
                largerArr[i] = backingArray[i];
            }

            backingArray = largerArr;
        }
        this.backingArray[size] = data;
        this.size = this.size + 1;
    }

    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns the backing array of this stack.
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