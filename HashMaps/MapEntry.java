import java.util.Objects;

/**
 * A class representing a MapEntry for a HashMap.
 *
 * @version 1.0
 */
public class MapEntry<K, V> {
    private K key;
    private V value;

    /**
     * Create a MapEntry object with the given key and value.
     *
     * @param key key for this entry
     * @param value value for this entry
     */
    public MapEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Gets the key held by this entry.
     *
     * @return key in this entry.
     */
    public K getKey() {
        return key;
    }

    /**
     * Sets the key held by this entry.
     *
     * @param key key to store in this entry.
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     * Gets the value held by this entry.
     *
     * @return value in this entry
     */
    public V getValue() {
        return value;
    }

    /**
     * Sets the value held by this entry.
     *
     * @param value value to store in this entry
     */
    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        // DO NOT USE THIS METHOD IN YOUR CODE!  This is for testing ONLY!
        if (!(o instanceof MapEntry)) {
            return false;
        } else {
            MapEntry<K, V> that = (MapEntry<K, V>) o;
            return Objects.equals(key, that.key) 
                && Objects.equals(value, that.value);
        }
    }

    @Override
    public String toString() {
        return String.format("%s: %s", key.toString(), value.toString());
    }
}
