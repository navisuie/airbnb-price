// --== CS400 File Header Information ==--
// Name: Luis Cazarin Quiroga
// Email: cazarinquiro@wisc.edu
// Team: BC
// TA: Brianna Cochran
// Lecturer: Florian Heimerl
// Notes to Grader:

import java.util.LinkedList;
import java.util.NoSuchElementException;


/**
 * hash table class that holds generic key and value type using chained linked lists
 * 
 *
 * @param <KeyType>   a generic key type
 * @param <ValueType> a generic value type
 */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

    private int size; // number of nodes in table
    private int capacity; // array's capacity
    private LinkedList[] hashTable; // array of linked lists - holds node


    /**
     * Constructor 
     * @param capacity - array's capacity
     */
    public HashTableMap(int capacity) {
        this.capacity = capacity;
        hashTable = new LinkedList[capacity];
        size = 0;
    }

    /**
     *  Constructor
     *  default capacity = 10
     */
    public HashTableMap() {
        this.capacity = 10;
        size = 0;
        hashTable = new LinkedList[capacity];
    }

    /**
     * 
     * Class used to store keys and values
     *
     */
    private class HashPair {
        KeyType key;
        ValueType value;
        public Object get(KeyType key2) {
            return value;
        }
    }

    /**
     * Adds HashPairs into the hashtable
     */
    @Override
    public boolean put(KeyType key, ValueType value) {

        int index = Math.abs(key.hashCode() % capacity); // gets hash code + index

        LinkedList<HashPair> list = hashTable[index];


        if (list == null) { // if list null then new hash table made

            list = new LinkedList<HashPair>();

            HashPair node = new HashPair(); // create new node

            node.value = value;
            node.key = key;
            list.add(node);

            hashTable[index] = list;
            size++; // increase size

            if (size >= capacity * 0.8) { // if load capacity > 80%, double capacity and rehash
                doubleCapacity();
                rehashTable();
            }
            return true;
        }

        int count = 0;
        while (count < list.size()) { // checks if key already in hash table
            if (list.get(count).key.equals(key)) {
                return false;
            }
            count++;
        }

        HashPair cart = new HashPair(); // linkedlist exists and key unique

        cart.value = value;
        cart.key = key;
        list.add(cart); // so new data pair is added to linked list

        size++; // increase size;


        if (size >= capacity * 0.70) { // if load capacity over 80%, double the capacity and rehash
            doubleCapacity();
            rehashTable();
        }

        return true;
    }

    /**
     * doubles capacity if load capacity over 70%
     */
    private void doubleCapacity() {
        this.capacity *= 2;
    }

    /**
     *  Rehashes keys when expanding hashtable
     */
    private void rehashTable() {

        LinkedList[] copy = hashTable; // copying table
        hashTable = new LinkedList[capacity];

        size = 0;


        for (int i = 0; i < (capacity / 2); ++i) { // iterate copy and rehash a new hashtable

            LinkedList<HashPair> list = copy[i];

            if (list != null) {
                int j = 0;

                while (j < list.size()) {

                    put(list.get(j).key, list.get(j).value);
                    j++;
                }
            }
        }
    }

    /**
     *  Gets value based on paramter key
     */
    @Override
    public ValueType get(KeyType key) throws NoSuchElementException {

        int index = Math.abs(key.hashCode() % capacity); // checks code and index

        LinkedList<HashPair> list = hashTable[index];

        if (list != null) {

            int i = 0;

            while (i < list.size()) {

                if (list.get(i).key.equals(key)) {
                    return list.get(i).value; // key !DNE
                }
                i++;
            }
        }
        throw new NoSuchElementException("The key does not exist."); // exception thrown key DNE
    }

    /**
     *  Checks if the hash table has key
     *  returns true if true, false if false
     */
    @Override
    public boolean containsKey(KeyType key) {

        int index = Math.abs(key.hashCode() % capacity); // checks code and index
        LinkedList<HashPair> list = hashTable[index];

        if (list != null) { // if list != null iterate list to check key !DNE

            int i = 0;

            while (i < list.size()) {
                if (list.get(i).key.equals(key)) {
                    return true; // key !DNE
                }
                i++;
            }
        }
        return false;
    }


    /**
     * Removes value that matches key
     * returns the value 
     * returns null if key does not exist
     */
    @Override
    public ValueType remove(KeyType key) {


        int index = Math.abs(key.hashCode() % capacity); // checks hash code and table index
        LinkedList<HashPair> list = hashTable[index];

        if (list == null) {
            return null;
        }
        // checking to see if key exists
        int i = 0;
        while (i < list.size()) { // checks if key there
            if (list.get(i).key.equals(key)) {
                --size;
                return list.remove(i).value;
            }
            i++;
        }
        return null;// return null if key DNE
    }

    /**
     * clears hash table, setting values to null
     */
    @Override
    public void clear() {
        for (int i = 0; i < capacity; ++i) {
            hashTable[i] = null;
        }
        size = 0;
    }

    @Override
    public int size() {
        return this.size;

    }

    public int find(KeyType key) {
        int index = (key.hashCode() < 0 ? 0 - key.hashCode() : key.hashCode()) % capacity;
        LinkedList<HashPair> temp = hashTable[index];
        if (temp != null) {
            for (int i = 0; i < temp.size(); i++) {
                if (temp.get(i).get(key).equals(key)) {
                    return i;
                }
            }
        }
        return -1;
    }
}


