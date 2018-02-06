    import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Your implementation of a binary search tree.
 *
 * @author VAIBHAV DEDHIA 903117055
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty BST.
     * YOU DO NOT NEED TO IMPLEMENT THIS CONSTRUCTOR!
     */
    public BST() {
    }

    /**
     * Initializes the BST with the data in the Collection. The data in the BST
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }
    }

    /**
     * A helper method to add a new Node to binary search tree
     *
     * @param data the data to add to the tree
     * @param current the node
     * @return it returns the node.
     */
    private BSTNode<T> addHelper(T data, BSTNode<T> current) {
        if (current == null) {
            size = size + 1;
            return new BSTNode<T>(data);
        } else if (data.compareTo(current.getData()) < 0) {
            current.setLeft(addHelper(data, current.getLeft()));
        } else if (data.compareTo(current.getData()) > 0) {
            current.setRight(addHelper(data, current.getRight()));
        }
        return current;
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data to be added"
                   +  " cannot be null");
        }
        this.root = addHelper(data, root);
    }



    /**
     * A helper method to remove a new Node to binary search tree
     *
     * @param data the data to add to the tree
     * @param current the node
     * @param val the node to hold the data of the node removed
     * @throws java.util.NoSuchElementException if data doesnt exist
     * @return it returns the node.
     */
    private BSTNode<T> removeHelper(T data, BSTNode<T>
                                    current, BSTNode<T> val) {
        if (current == null) {
            throw new java.util.NoSuchElementException("data does not exist "
                   + "in binary search tree ");
        }
        if (current.getData().equals(data)) {
            if (current.getRight() == null) {
                val.setData(current.getData());
                this.size = this.size - 1;
                return current.getLeft();
            } else if (current.getLeft() == null) {
                val.setData(current.getData());
                this.size = this.size - 1;
                return current.getRight();
            } else {
                val.setData(current.getData());
                BSTNode<T> explore = current.getLeft();
                while (explore.getRight() != null) {
                    explore = explore.getRight();
                }
                current.setData(explore.getData());
                BSTNode<T> del = new BSTNode<T>(null);
                current.setLeft(removeHelper(explore.getData(),
                        current.getLeft(), del));
            }
        } else if (data.compareTo(current.getData()) > 0) {
            current.setRight(removeHelper(data, current.getRight(), val));
        } else {
            current.setLeft(removeHelper(data, current.getLeft(), val));
        }
        return current;
    }
    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data to be added"
                    + " cannot be null");
        }
        BSTNode<T> val = new BSTNode<T>(null);
        root = removeHelper(data, root, val);
        return val.getData();
    }


    /**
     * A helper method to get data of a Node in binary search tree
     *
     * @param data the data to add to the tree
     * @param current the node
     * @throws java.util.NoSuchElementException if data doesnt exist
     * @return it returns the data.
     */
    private T getHelper(T data, BSTNode<T> current) {
        if (current == null) {
            throw new java.util.NoSuchElementException("data does not "
                  +  "exist in binary search tree ");
        }
        if (current.getData().equals(data)) {
            return current.getData();
        } else if (data.compareTo(current.getData()) > 0) {
            return getHelper(data, current.getRight());
        } else {
            return getHelper(data, current.getLeft());
        }
    }
    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data to be searched "
                    + "for cannot be null");
        }
        T val = getHelper(data, root);
        return val;
    }



    /**
     * A helper method to get check if data exists in binary search tree
     *
     * @param data the data to add to the tree
     * @param current the node
     * @throws java.util.NoSuchElementException if data doesnt exist
     * @return it returns the boolean value.
     */
    private boolean containsHelper(T data, BSTNode<T> current) {
        if (current == null) {
            return false;
        }
        if (current.getData().equals(data)) {
            return true;
        } else if (data.compareTo(current.getData()) > 0) {
            return containsHelper(data, current.getRight());
        } else {
            return containsHelper(data, current.getLeft());
        }
    }
    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("cannot "
                    + "search for data with null");
        }
        return containsHelper(data, root);
    }



    @Override
    public int size() {
        return this.size;
    }



    /**
     * A helper method for preorder traversals
     *
     * @param current the node
     * @return it returns the list for traversals.
     */
    private List<T> preorderHelper(BSTNode<T> current) {
        List<T> myList = new ArrayList<T>();
        if (current != null) {
            myList.add(current.getData());
            myList.addAll(preorderHelper(current.getLeft()));
            myList.addAll(preorderHelper(current.getRight()));
        }
        return myList;
    }
    @Override
    public List<T> preorder() {
        return preorderHelper(root);
    }



    /**
     * A helper method for postorder traversals
     *
     * @param current the node
     * @return it returns the list for traversals.
     */
    private List<T> postorderHelper(BSTNode<T> current) {
        List<T> myList = new ArrayList<T>();
        if (current != null) {
            myList.addAll(postorderHelper(current.getLeft()));
            myList.addAll(postorderHelper(current.getRight()));
            myList.add(current.getData());
        }
        return myList;
    }
    @Override
    public List<T> postorder() {
        return postorderHelper(root);
    }



    /**
     * A helper method for inorder traversals
     *
     * @param current the node
     * @return it returns the list for traversals.
     */
    private List<T> inorderHelper(BSTNode<T> current) {
        List<T> myList = new ArrayList<T>();
        if (current != null) {
            myList.addAll(inorderHelper(current.getLeft()));
            myList.add(current.getData());
            myList.addAll(inorderHelper(current.getRight()));
        }
        return myList;
    }
    @Override
    public List<T> inorder() {
        return inorderHelper(root);
    }



    /**
     * A helper method for finding path between 2 nodes.
     *
     * @param data the start point for the path
     * @param current the node
     * @param myList the list which holds the path
     * @return it returns the data value which has no significance.
     */
    private T pathLocator1(T data, BSTNode<T> current, LinkedList<T> myList) {
        if (current == null) {
            throw new java.util.NoSuchElementException("data does"
                    + " not exist in binary search tree ");
        }
        if (current.getData().equals(data)) {
            return current.getData();
        } else if (data.compareTo(current.getData()) > 0) {
            myList.addFirst(current.getRight().getData());
            return pathLocator1(data, current.getRight(), myList);
        } else {
            myList.addFirst(current.getLeft().getData());
            return pathLocator1(data, current.getLeft(), myList);
        }
    }


    /**
     * A helper method for finding path between 2 nodes.
     * *
     * @param data the end point for the path
     * @param current the node
     * @param myList the list which holds the path
     * @return it returns the data value which has no significance.
     */
    private T pathLocator2(T data, BSTNode<T> current, LinkedList<T> myList) {
        if (current == null) {
            throw new java.util.NoSuchElementException("data "
                    + "does not exist in binary search tree ");
        }
        if (current.getData().equals(data)) {
            return current.getData();
        } else if (data.compareTo(current.getData()) > 0) {
            myList.addLast(current.getRight().getData());
            return pathLocator2(data, current.getRight(), myList);
        } else {
            myList.addLast(current.getLeft().getData());
            return pathLocator2(data, current.getLeft(), myList);
        }
    }

    /**
     * A helper method for finding common ancestor of two nodes
     * @param data1 the end point for the path
     * @param data2 the node
     * @return it returns the the common node.
     */
    private BSTNode<T> findCommonNode(T data1, T data2) {
        BSTNode<T> current = root;

        while (data1.compareTo(current.getData()) < 0
                && data2.compareTo(current.getData()) < 0) {
            current = current.getLeft();
        }
        while (data1.compareTo(current.getData()) > 0
                && data2.compareTo(current.getData()) > 0) {
            current = current.getRight();
        }
        return current;
    }
    @Override
    public List<T> findPathBetween(T data1, T data2) {
        if (data1 == null || data2 == null) {
            throw new IllegalArgumentException("data to be "
                  +  "added cannot be null");
        }
        LinkedList<T> myList = new LinkedList<T>();

        if (this.root == null) {
            throw new java.util.NoSuchElementException("data doesnt exist");
        }
        BSTNode<T> current = findCommonNode(data1, data2);
        myList.add(current.getData());

        if (data1.compareTo(current.getData()) != 0) {
            pathLocator1(data1, current, myList);
        }
        if (data2.compareTo(current.getData()) != 0) {
            pathLocator2(data2, current, myList);
        }
        return myList;
    }



    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }


    /**
     * A helper method for finding the height of a bst.
     * *
     * @param current the node
     * @return it returns the height.
     */
    private int heightHelper(BSTNode<T> current) {
        int lheight = 0;
        int rheight = 0;
        if (current == null) {
            return 0;
        } else {
            lheight = heightHelper(current.getLeft());
            rheight = heightHelper(current.getRight());
            return  (lheight > rheight) ? (lheight + 1) : (rheight + 1);
        }
    }
    @Override
    public int height() {
        return heightHelper(root) - 1;
    }



    @Override
    public BSTNode<T> getRoot() {
        return root;
    }
}
