/*
 * Name:    Enkang Yuan
 * Sect.:   CS 3345.503
 * Desc.:   HashTable class that uses linear probing to handle collisions. Insertion, deletion, searches, and rehashing are done using their respective methods.
 */

public class HashTable<K, V> {
    // nested class to represent a hash entry
    private static class HashEntry<K, V> {
        K key;
        V value;
        boolean isDeleted;

        public HashEntry(K key, V value) {
            this.key = key;
            this.value = value;
            this.isDeleted = false;
        }
    }

    private HashEntry<K, V>[] hashtable;
    private int size;
    private int capacity;

    public HashTable() {
        this.capacity = 3;
        this.hashtable = new HashEntry[capacity];
        this.size = 0;
    }
    
    // inserts a key-value pair into the hash table
    public boolean insert(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        if (size == capacity) {
            rehash();
        }

        int hashValue = getHashValue(key);
        int index = hashValue;

        while (hashtable[index] != null && !hashtable[index].isDeleted) {
            if (hashtable[index].key.equals(key)) {
                return false;
            }
            index = (index + 1) % capacity;
        }

        hashtable[index] = new HashEntry<>(key, value);
        size++;
        return true;
    }
    
    // searches for a value based on a key
    public V find(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int hashValue = getHashValue(key);
        int index = hashValue;

        while (hashtable[index] != null) {
            if (!hashtable[index].isDeleted && hashtable[index].key.equals(key)) {
                return hashtable[index].value;
            }
            index = (index + 1) % capacity;
        }

        return null;
    }
    
    // deletes a key-value pair from the hash table
    public boolean delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int hashValue = getHashValue(key);
        int index = hashValue;

        while (hashtable[index] != null) {
            if (!hashtable[index].isDeleted && hashtable[index].key.equals(key)) {
                hashtable[index].isDeleted = true;
                size--;
                return true;
            }
            index = (index + 1) % capacity;
        }

        return false;
    }
    
    // returns the hash value of a key
    public int getHashValue(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        return key.hashCode() % capacity;
    }

    // rehashes the table using a table twice the size of the original
    private void rehash() {
        capacity *= 2;
        HashEntry<K, V>[] oldTable = hashtable;
        hashtable = new HashEntry[capacity];
        size = 0;

        for (HashEntry<K, V> entry : oldTable) {
            if (entry != null && !entry.isDeleted) {
                insert(entry.key, entry.value);
            }
        }
    }
}
