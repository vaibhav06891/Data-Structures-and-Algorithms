/**
 * Your implementation of a SinglyLinkedList
 *
 * @author VAIBHAV DEDHIA (903117055)
 * @version 1.0
 */
public class SinglyLinkedList<T extends Comparable<? super T>> implements
        LinkedListInterface<T> {
    // Do not add new instance variables.
    private SLLNode<T> head;
    private SLLNode<T> tail;
    private int size;

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }
        this.head = new SLLNode<T>(data, this.head);
        this.size = this.size + 1;
        if (this.tail == null) {
            this.tail = this.head;
        }
    }

    @Override
    public void addAtIndex(T data, int index) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        } else if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Index out of range. "
                   + "It cannot be negative or greater than size of the list");
        } else if (index == 0) {
            this.addToFront(data);
        } else if (index == this.size) {
            this.addToBack(data);
        } else {
            int count = 1;
            SLLNode<T> current = this.head;
            SLLNode<T> newnode = new SLLNode<T>(data);
            while (count < index) {
                current = current.getNext();
                count++;
            }
            newnode.setNext(current.getNext());
            current.setNext(newnode);
            this.size = this.size + 1;
        }
    }


    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }

        SLLNode<T> current = new SLLNode<T>(data, null);
        if (this.head == null) {
            this.head = current;
        } else {
            this.tail.setNext(current);
        }
        this.size = this.size + 1;
        this.tail = current;
    }

    @Override
    public T removeFromFront() {
        T val;
        if (this.size == 0) {
            return null;

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
    public T removeAtIndex(int index) {
       if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index out of range."
                  +  " It cannot be negative or greater than size of the list");
        }
        T val;
        if (this.size == 0) {
            return null;
        } else if (index == 0) {
            val = this.removeFromFront();
        } else if (index == this.size - 1) {
            val = this.removeFromBack();
        } else {
            SLLNode<T> current = this.head;
            SLLNode<T> prev = null;
            int count = 0;
            while (count < index) {
                prev  = current;
                current = current.getNext();
                count = count + 1;
            }
            if (prev != null) {
                prev.setNext(current.getNext());
            } else {
                head = head.getNext();
            }
            val = current.getData();
            this.size = this.size - 1;
        }
        return val;
    }

    @Override
    public T removeFromBack() {
        SLLNode<T> current = this.head;
        SLLNode<T> prev = null;
        if (this.head == null) {
            return null;
        }

        while (current.getNext() != null) {
            prev = current;
            current = current.getNext();
        }
        T val = this.tail.getData();
        if (prev != null) {
            prev.setNext(null);
        } else {
            this.head = null;
        }
        this.tail = prev;
        this.size = this.size - 1;
        return val;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index out of range."
                   + " It cannot be negative or greater than size of the list");
        } else if (index == 0) {
            return this.head.getData();
        } else if (index == size - 1) {
            return this.tail.getData();
        } else {
            int count = 0;
            SLLNode<T> current = this.head;
            while (count < index) {
                current = current.getNext();
                count++;
            }
            return current.getData();
        }
    }

    @Override
    public T findLargestElement() {
        if (this.size == 0) {
            return null;
        }
        T largestElement = this.head.getData();
        SLLNode<T> current = this.head;
        T value;
        int count = 1;
        while (count < this.size) {
            current = current.getNext();
            value = current.getData();
            if (largestElement.compareTo(value) < 0) {
                largestElement = value;
            }
            count = count + 1;
        }

        return largestElement;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[this.size];
        SLLNode<T> current = this.head;
        int count = 0;
        while (count < this.size) {
            array[count] = current.getData();
            current = current.getNext();
            count = count + 1;
        }
        return array;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public SLLNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    @Override
    public SLLNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}
