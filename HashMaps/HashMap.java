import java.util.List;
import java.util.Set;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.HashSet;

/**
 * Your implementation of HashMap.
 * 
 * @author Vaibhav Dedhia (903117055)
 * @version 1.0
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private LinkedList<MapEntry<K, V>>[] backingTable;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code INITIAL_CAPACITY}.
     *
     * Do not use magic numbers!
     *
     * Use constructor chaining.
     */
    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        backingTable = (LinkedList<MapEntry<K, V>>[])
                new LinkedList[initialCapacity];
        size = 0;
    }


    // add the growing coding using load factor
    @Override
    public V put(K key, V value) {
        if (key == null) {
            throw new java.lang.IllegalArgumentException("Key cannot be null");
        }
        MapEntry<K, V> entry = new MapEntry<K, V>(key, value);
        int index = (Math.abs(key.hashCode()) % backingTable.length);
        if (this.size / backingTable.length > MAX_LOAD_FACTOR
                         && backingTable.length == INITIAL_CAPACITY) {
            resizeBackingTable(backingTable.length * 2 + 1);
        }
        V val = null;
        if (backingTable[index] == null) {
            backingTable[index] = new LinkedList<MapEntry<K, V>>();
            backingTable[index].addFirst(entry);
            this.size = this.size + 1;
        } else {
            Iterator itr = backingTable[index].iterator();
            while (itr.hasNext()) {
                MapEntry<K, V> element = (MapEntry<K, V>) itr.next();
                if (element.getKey() == key) {
                    val = element.getValue();
                    element.setValue(value);
                    return val;
                }
            }
            backingTable[index].addFirst(entry);
            this.size = this.size + 1;
        }
        return val;
    }




    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key to be searched "
                  +  " for cannot be null");
        }
        int index = (Math.abs(key.hashCode()) % backingTable.length);
        if (backingTable[index] == null) {
            throw new java.util.NoSuchElementException("The key "
                   + "does not exist");
        } else {
            V val = null;
            Iterator itr = backingTable[index].iterator();
            while (itr.hasNext()) {
                MapEntry<K, V> element = (MapEntry<K, V>) itr.next();
                if (element.getKey() == key) {
                    val = element.getValue();
                    itr.remove();
                    return val;
                }
            }
        }
        throw new java.util.NoSuchElementException("the key doesnt exist");
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key to be "
                   + "searched for cannot be null");
        }
        int index = (Math.abs(key.hashCode()) % backingTable.length);
        if (backingTable[index] == null) {
            throw new java.util.NoSuchElementException("The key "
                   + "does not exist");
        } else {
            V val = null;
            Iterator itr = backingTable[index].iterator();
            while (itr.hasNext()) {
                MapEntry<K, V> element = (MapEntry<K, V>) itr.next();
                if (element.getKey() == key) {
                    val = element.getValue();
                    return val;
                }
            }
        }
        throw new java.util.NoSuchElementException("the key doesnt exist");
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key to be "
                   + "searched for cannot be null");
        }
        int index = (Math.abs(key.hashCode()) % backingTable.length);
        if (backingTable[index] == null) {
            return false;
        } else {
            V val = null;
            Iterator itr = backingTable[index].iterator();
            while (itr.hasNext()) {
                MapEntry<K, V> element = (MapEntry<K, V>) itr.next();
                if (element.getKey() == key) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void clear() {
        size = 0;
        backingTable = (LinkedList<MapEntry<K, V>>[]) new
                             LinkedList[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Set<K> keySet() {
        //Set<K> set = new Set<K>(backingTable.keySet());

        Set<K> set = new HashSet<K>();
        for (int i = 0; i < backingTable.length; i++) {
            if (backingTable[i] != null) {
                Iterator itr = backingTable[i].iterator();
                while (itr.hasNext()) {
                    MapEntry<K, V> element = (MapEntry<K, V>) itr.next();
                    set.add(element.getKey());
                }
            }
        }
        return set;
    }

    @Override
    public List<V> values() {
        LinkedList<V> list = new LinkedList<V>();
        for (int i = 0; i < backingTable.length; i++) {
            if (backingTable[i] != null) {
                Iterator itr = backingTable[i].iterator();
                while (itr.hasNext()) {
                    MapEntry<K, V> element = (MapEntry<K, V>) itr.next();
                    list.addLast(element.getValue());
                }
            }
        }
        return list;
    }

    @Override
    public void resizeBackingTable(int length) {
        LinkedList<MapEntry<K, V>>[] oldTable = backingTable;
        if (length < 0) {
            throw new IllegalArgumentException("The length of "
                   + "the table cannot be negative");
        }
        this.backingTable = (LinkedList<MapEntry<K, V>>[])
                                     new LinkedList[length];
        this.size = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null) {
                for (int j = 0; j < oldTable[i].size(); j++) {
                    put(oldTable[i].get(j).getKey(),
                            oldTable[i].get(j).getValue());
                }
            }
        }
    }
    
    @Override
    public LinkedList<MapEntry<K, V>>[] getTable() {
        return backingTable;
    }

}
