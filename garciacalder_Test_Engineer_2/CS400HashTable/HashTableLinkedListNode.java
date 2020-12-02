package CS400HashTable;// --== CS400 File Header Information ==--
// Name: Jacob D Lorenz
// Email: jlorenz2@wisc.edu
// Team: BD
// TA: Brianna Cochran
// Lecturer: Gary Dahl
// Notes to Grader: This class is a custom, generic Node<K,V> used by my doubly linked list implementation

/**
 * A generic implementation of a HashTableLinkedListNode with 2 data points, key and value, to occupy positions in a HashTableLinkedList
 */
public class HashTableLinkedListNode<KeyType, ValueType> {

    /**
     * The key data of the node; immutable
     */
    private final KeyType key;

    /**
     * The value data of the node; immutable
     */
    private final ValueType value;

    /**
     * The next HashTableLinkedListNode in the linked list; null if this is the last node
     */
    public HashTableLinkedListNode<KeyType, ValueType> next;

    /**
     * The previous HashTableLinkedListNode in the linked list; null if this is the head node
     */
    public HashTableLinkedListNode<KeyType, ValueType> previous;

    /**
     * The constructor for a new HashTableLinkedListNode. Since the key and value are immutable after instantiation,
     * this is the default and only available constructor
     * @param key - The key to use for the HashTableLinkedListNode
     * @param value - The value to use for the HashTableLinkedListNode
     * @return - A HashTableLinkedListNode with provided key and value as its data
     */
    public HashTableLinkedListNode(KeyType key, ValueType value) {
        this.key = key;
        this.value = value;
        this.next = null;
        this.previous = null;
    }

    /**
     * Returns the key data of this HashTableLinkedListNode
     * @return key - The key of this HashTableLinkedListNode
     */
    public KeyType getKey() {
        return this.key;
    }

    /**
     * Returns the value data of this HashTableLinkedListNode
     * @return value - The value of this HashTableLinkedListNode
     */
    public ValueType getValue() {
        return this.value;
    }

    /**
     * Sets the provided HashTableLinkedListNode as the next node in the HashTableLinkedList
     * @param nextNode - The HashTableLinkedListNode to set as the next node in the HashTableLinkedList
     * @return true - If the provided nextNode was successfully set as the next node
     */
    public boolean setNext(HashTableLinkedListNode<KeyType, ValueType> nextNode) {
        this.next = nextNode;
        return true;
    }

    /**
     * Returns the HashTableLinkedListNode referenced as 'next' from this HashTableLinkedListNode; null if this
     * HashTableLinkedListNode is the last in the HashTableLinkedList
     * @return HashTableLinkedListNode - The next HashTableLinkedListNode in the HashTableLinkedList
     */
    public HashTableLinkedListNode<KeyType, ValueType> getNext() {
        return this.next;
    }

    /**
     * Sets the provided HashTableLinkedListNode as the previous node in the HashTableLinkedList
     * @param previousNode - The HashTableLinkedListNode to set as the previous node in the HashTableLinkedList
     * @return true - If the provided previousNode was successfully set as the previous node
     */
    public boolean setPrevious(HashTableLinkedListNode<KeyType, ValueType> previousNode) {
        this.previous = previousNode;
        return true;
    }

    /**
     * Returns the HashTableLinkedListNode referenced as 'previous' from this HashTableLinkedListNode; null if this
     * HashTableLinkedListNode is the head of the HashTableLinkedList
     * @return HashTableLinkedListNode - The previous HashTableLinkedListNode in the HashTableLinkedList
     */
    public HashTableLinkedListNode<KeyType, ValueType> getPrevious() {
        return this.previous;
    }

}
