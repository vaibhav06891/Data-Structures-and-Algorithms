import java.util.List;
import java.util.Set;

/**
 * The interface for an AVL tree.
 * @version 1.0
 */
public interface AVLInterface<T extends Comparable<? super T>> {
    /**
     * Add the data as a leaf to the AVL. Should traverse the tree to find the
     * appropriate location. If the data is already in the tree, then nothing
     * should be done (the duplicate shouldn't get added, and size should not be
     * incremented).
     *
     * Remember to recalculate heights going up the tree, rebalancing if
     * necessary.
     *
     * @throws java.lang.IllegalArgumentException if the data is null
     * @param data the data to be added
     */
    void add(T data);

    /**
     * Removes the data from the tree.  There are 3 cases to consider:
     * 1: the data is a leaf.  In this case, simply remove it.
     * 2: the data has one child.  In this case, simply replace the node with
     * the child node.
     * 3: the data has 2 children.  There are generally two approaches:
     * replacing the data with either the largest element in the left subtree
     * (commonly called the predecessor), or replacing it with the smallest
     * element in the right subtree (commonly called the successor). For this
     * assignment, use the successor.
     *
     * Remember to recalculate heights going up the tree, rebalancing if
     * necessary.
     *
     * @throws java.lang.IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not in the tree
     * @param data data to remove from the tree
     * @return the data removed from the tree.  Do not return the same data
     * that was passed in.  Return the data that was stored in the tree.
     */
    T remove(T data);

    /**
     * Returns the data in the tree matching the parameter passed in.
     *
     * @throws java.lang.IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data data to get in the AVL tree
     * @return the data in the tree equal to the parameter.  Do not return the
     * same data that was passed in.  Return the data that was stored in the
     * tree.
     */
    T get(T data);

    /**
     * Returns whether or not the parameter is contained within the tree.
     *
     * @throws java.lang.IllegalArgumentException if the data is null
     * @param data data to find in the AVL tree
     * @return whether or not the parameter is contained within the tree
     */
    boolean contains(T data);

    /**
     * Get the number of elements in the tree.
     *
     * @return the number of elements in the tree
     */
    int size();

    /**
     * Get the preorder traversal of the tree.
     *
     * @return a preorder traversal of the tree, or an empty list
     */
    List<T> preorder();

    /**
     * Get the postorder traversal of the tree.
     *
     * @return a postorder traversal of the tree, or an empty list
     */
    List<T> postorder();

    /**
     * Generate a level-order traversal of the tree.
     *
     * To do this, add the root node to a queue. Then, while the queue isn't
     * empty, remove one node, add its data to the list being returned, and add
     * its left and right child nodes to the queue. If what you just removed is
     * {@code null}, ignore it and continue with the rest of the nodes.
     *
     * Should run in O(n).
     *
     * @return a level order traversal of the tree
     */
    List<T> levelorder();
    
    /**
     * Get a set of all elements in the tree such that
     * lower < elements < upper. Note that the data is
     * of a Comparable generic type, not primitives.
     * 
     * You will be guaranteed that if lower and upper are valid
     * then lower is comparative less than upper
     * 
     * @throws IllegalArgumentException if either lower or upper
     * are null
     * @param lower The lower bound of the threshold
     * @param upper The upper bound of the threshold
     * @return a set of elements that are within the threshold
     */
    Set<T> threshold(T lower, T upper);

    /**
     * Clear the tree.
     */
    void clear();

    /**
     * Return the height of the root of the tree.
     * 
     * This method does not need to traverse the entire tree.
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    int height();
    
    /**
     * THIS METHOD IS ONLY FOR TESTING PURPOSES.
     * DO NOT USE IT IN YOUR CODE
     * DO NOT CHANGE THIS METHOD
     *
     * @return the root of the tree
     */
    AVLNode<T> getRoot();
}
