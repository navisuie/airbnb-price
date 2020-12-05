package CS400HashTable;// --== CS400 File Header Information ==--
// Name: Jacob D Lorenz
// Email: jlorenz2@wisc.edu
// Team: BD
// TA: Brianna Cochran
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.NoSuchElementException;

/**
 * A generic implementation of the MapADT interface by means of an array of linked lists
 */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

    /**
     * The private array of linked lists used as the underlying implementation of the HashTableMap
     */
    private HashTableLinkedList<KeyType, ValueType>[] HashTableArray;

    /**
     * A configuration variable used to store / adjust the initial capacity of the default HashTableMap
     */
    private int INITIAL_CAPACITY = 10;

    /**
     * A configuration variable used to store / adjust the load factor of the HashTableMap before resizing
     */
    final double LOAD_FACTOR = 0.80;

    /**
     * A configuration variable used to store / adjust the growth factor of newly resized HashTableMaps
     */
    final int GROWTH_FACTOR = 2;

    /**
     * Stores the current number of key:value pairs in the HashTableMap
     * This number tracks individual key:value pairs, not just uniquely occupied indices
     */
    private int size;

    /**
     * Tracks the current capacity of the HashTableMap
     * May or may not be equal to INITIAL_CAPACITY depending on resizes
     */
    private int currentCapacity;

    /**
     * Parameterized constructor for the HashTableMap class
     * @param capacity - The initial capacity of the HashTableMap
     * @return A HashTableMap with capacity as specified with the capacity parameter
     */
    public HashTableMap(int capacity) {
        if (capacity > 0) {
            this.HashTableArray = new HashTableLinkedList[capacity];
            this.size = 0;
            this.INITIAL_CAPACITY = capacity;
            this.currentCapacity = capacity;
        } else {
            this.HashTableArray = new HashTableLinkedList[1];
            this.size = 0;
            this.INITIAL_CAPACITY = 1;
            this.currentCapacity = 1;
        }
    }

    /**
     * Default constructor for the HashTableMap class
     * @return A HashTableMap with capacity = INITIAL_CAPACITY
     */
    public HashTableMap() {
        this.HashTableArray = new HashTableLinkedList[INITIAL_CAPACITY];
        this.size = 0;
        this.currentCapacity = INITIAL_CAPACITY;
    }

    /**
     * Inserts the key:value pair into the HashTableMap by calculating the hash index of the key
     * Creates a new HashTableLinkedList if the index is empty, otherwise inserts a new HashTableLinkedListNode at the
     * appropriate index's HashTableLinkedList. Duplicate keys will not be inserted into the HashTableMap
     * @param key - The key to use to index into the map
     * @param value - The value to insert into the map
     * @return true - if the key:value pair was inserted into the map
     * @return false - if the key:value pair was not inserted into the map
     */
    public boolean put(KeyType key, ValueType value) {
        if (key == null) {
            return false;
        }
        try {
            // Compute the HashCode for the given Key
            int hashCode = hashCode(key);
            // Check if there is already a list instantiated at the index
            if (this.HashTableArray[hashCode] != null) { // Linked List Exists Here

                if (this.HashTableArray[hashCode].containsKey(key)) {
                    return false;
                }

                // Create the new node to add into list
                HashTableLinkedListNode<KeyType, ValueType> newNode = new HashTableLinkedListNode<>(key, value);
                // Inserts at the front of the list (O(1)).
                this.HashTableArray[hashCode].insert(newNode);
            } else { // Create new Linked List
                // Creating a new list automatically creates a new node and uses it as head
                HashTableLinkedList<KeyType, ValueType> newLinkedList = new HashTableLinkedList<>(key, value);
                // Insert the newly created linked list into the HashTable
                this.HashTableArray[hashCode] = newLinkedList;
            }
            this.size++;
            if (this.size >= (this.currentCapacity * this.LOAD_FACTOR)) {
                increaseHashTableCapacity();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Retrieves the value associated with the specified key
     * @param key - The key to use to index into the map
     * @return ValueType - value of the retrieved key:value pair
     * @throws NoSuchElementException - Thrown if no key:value pair is found matching the specified key
     */
    public ValueType get(KeyType key) throws NoSuchElementException {

        // A null key certainly would not exist in the map
        if (key == null) {
            throw new NoSuchElementException();
        }
        // Hash the key to obtain the index
        int keyIndex = hashCode(key);
        // Check if the index is associated with an instantiated linked list, if not surely NoSuchElement
        if (this.HashTableArray[keyIndex] != null) {
            try {
                return this.HashTableArray[keyIndex].retrieve(key);
            } catch (Exception e) {
                throw new NoSuchElementException();
            }
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Returns the number of key:value pairs in the map. This is not the same as number of unique indices populated
     * @return int - Current number of key:value pairs in the map
     */
    public int size() {
        return this.size;
    }

    /**
     * Loops through the array's linked lists and determines whether a key:value pair exists that is associated with
     * the provided key
     * @param key - The key to use to index into the map
     * @return true - If the map contains a key:value pair associated with the key parameter
     * @return false - If the map does not contain a key:value pair associated with the key parameter
     */
    public boolean containsKey(KeyType key) {

        // A null key certainly would not exist in the map
        if (key == null) {
            return false;
        }

        // Hash the key to obtain the index
        int keyIndex = hashCode(key);
        // Check if the index is associated with an instantiated linked list, if not surely no key
        if (this.HashTableArray[keyIndex] != null) {
            return HashTableArray[keyIndex].containsKey(key);
        } else { // If the array index for this key is null, ie no list, surely the key is not in the array / list
            return false;
        }
    }

    /**
     * Removes the key:value pair from the map if one is found to be associated with the provided key
     * @param key - The key to use to index into the map
     * @return value - Value of the key:value pair removed from the map
     * @return null - If no key:value pair is found in the map that matches the provided key
     */
    public ValueType remove(KeyType key) {

        // A null key certainly would not exist in the map
        if (key == null) {
            return null;
        }

        // Hash the key to obtain the index
        int keyIndex = hashCode(key);
        // Check if the index is associated with an instantiated linked list, if not surely NoSuchElement
        if (this.HashTableArray[keyIndex] != null) {
            try {
                ValueType returnValue = this.HashTableArray[keyIndex].remove(key);
                if (this.HashTableArray[keyIndex].size() == 0) {
                    this.HashTableArray[keyIndex] = null;
                }
                this.size--;
                return returnValue;
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Removes all key:value pairs from the map and leaves an empty map still with the current capacity
     */
    public void clear() {

        for (int i = 0; i < currentCapacity; i++) {
            this.HashTableArray[i] = null;
        }

        this.size = 0;
    }

    /**
     * A private method used to resize and rehash the HashTableMap when it's size has reached the LOAD_FACTOR threshold
     */
    private void increaseHashTableCapacity() {

        HashTableLinkedList<KeyType, ValueType>[] newHashTable = new HashTableLinkedList[this.currentCapacity * this.GROWTH_FACTOR];
        this.currentCapacity = this.currentCapacity * this.GROWTH_FACTOR;

        for (HashTableLinkedList<KeyType, ValueType> curList : this.HashTableArray) {
            if (curList != null) {
                HashTableLinkedListNode<KeyType, ValueType> curNode = curList.getHead();

                while (curNode != null) {
                    KeyType curNodeKey = curNode.getKey();
                    ValueType curNodeValue = curNode.getValue();
                    int newHash = hashCode(curNodeKey);
                    HashTableLinkedListNode<KeyType, ValueType> newNode = new HashTableLinkedListNode<>(curNodeKey, curNodeValue);

                    if (newHashTable[newHash] != null) { // Insert at an existing list
                        newHashTable[newHash].insert(newNode);
                    } else { // New list
                        HashTableLinkedList<KeyType, ValueType> newList = new HashTableLinkedList<>(curNodeKey, curNodeValue);
                        newHashTable[newHash] = newList;
                    }

                    curNode = curNode.getNext();
                }
            }
        }

        HashTableArray = newHashTable;
    }

    /**
     * Determines the key's hash index by taking its hashcode and modding it by the current capacity
     * @param key - The key to use to index into the map
     * @return int - The hashed index for the provided key
     */
    private int hashCode(KeyType key) {
        int returnHash = key.hashCode();
        return Math.abs((returnHash % this.currentCapacity));
    }

    /**
     * Helper method used to retrieve the current capacity of the HashTableMap
     * @return int - The current capacity of the HashTableMap
     */
    public int getCapacity() {
        return this.currentCapacity;
    }

    /**
     * Helper method used to retrieve the current LOAD_FACTOR of the HashTableMap
     * @return double - The current LOAD_FACTOR of the HashTableMap
     */
    public double getLOAD_FACTOR() {
        return this.LOAD_FACTOR;
    }

}
