package CS400HashTable;// --== CS400 File Header Information ==--
// Name: Jacob D Lorenz
// Email: jlorenz2@wisc.edu
// Team: BD
// TA: Brianna Cochran
// Lecturer: Gary Dahl
// Notes to Grader: This class is my custom LinkedList<K,V> implementation containing the basic operations needed
//                  to implement the HashMapTable. This class uses generic HashTableLinkedListNode<K,V> nodes to
//                  store the data.

import java.util.NoSuchElementException;

/**
 * A generic implementation of a basic doubly linked list; used by the HashTableMap implementation of MapADT interface
 * This Linked List is comprised of generic HashTableLinkedListNode nodes
 */
public class HashTableLinkedList<KeyType, ValueType> {

    /**
     * The head node of the Linked List
     */
    private HashTableLinkedListNode<KeyType, ValueType> head;

    /**
     * The current number of nodes in the Linked List
     */
    private int size;

    /**
     * The parameterized constructor for a new HashTableLinkedList of size 1 (head node)
     * @param key - The key to use for the head HashTableLinkedListNode of the newly created HashTableLinkedList
     * @param value - The value to use for the head HashTableLinkedListNode of the newly created HashTableLinkedList
     * @return - A HashTableLinkedList of size 1
     */
    public HashTableLinkedList(KeyType key, ValueType value) {
        this.head = new HashTableLinkedListNode<>(key, value);
        this.size = 1;
    }

    /**
     * The default constructor for a HashTableLinkedList of size 0
     * @return - An empty HashTableLinkedList
     */
    public HashTableLinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Inserts the provided HashTableLinkedListNode into the beginning of the list
     * @param newNode - The HashTableLikedListNode to insert into the HashTableLinkedList
     */
    public void insert(HashTableLinkedListNode<KeyType, ValueType> newNode) {

        if (this.head != null) { // List already has a head, insert at head and redirect
            newNode.setNext(this.head);
            this.head.setPrevious(newNode);
        }

        this.head = newNode;
        this.size++;
    }

    /**
     * Looks through the list and removes the node corresponding with the provided key
     * @param key - The key to look for in a node to remove
     * @return value - The value of the removed HashTableLinkedListNode
     * @throws NoSuchElementException - If there is no node found in the list with the provided key
     */
    public ValueType remove(KeyType key) throws NoSuchElementException {

        HashTableLinkedListNode<KeyType, ValueType> nextNode = null;
        HashTableLinkedListNode<KeyType, ValueType> prevNode = null;
        HashTableLinkedListNode<KeyType, ValueType> targetNode = null;
        HashTableLinkedListNode<KeyType, ValueType> curNode = this.head;

        while(curNode != null) {
            if (curNode.getKey().equals(key)) {
                targetNode = curNode;
                break;
            } else {
                curNode = curNode.getNext();
            }
        }

        if (targetNode != null) {
            nextNode = targetNode.getNext();
            prevNode = targetNode.getPrevious();

            if (nextNode != null) {
                nextNode.setPrevious(prevNode);
            }
            if (prevNode != null) {
                prevNode.setNext(nextNode);
            }

            this.size--;
            return targetNode.getValue();
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Looks throw the list and returns the value of the node found corresponding with the provided key.
     * @param key - The key of the HashTableLinkedListNode to retrieve
     * @return value - The value of the HashTableLinkedListNode corresponding with the provided key
     * @throws NoSuchElementException - If there is no node in the list with the provided key
     */
    public ValueType retrieve(KeyType key) throws NoSuchElementException {

        HashTableLinkedListNode<KeyType, ValueType> curNode = this.head;
        HashTableLinkedListNode<KeyType, ValueType> targetNode = null;

        while(curNode != null) {
            if (curNode.getKey().equals(key)) {
                targetNode = curNode;
                break;
            } else {
                curNode = curNode.getNext();
            }
        }

        if (targetNode != null) {
            return targetNode.getValue();
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Returns the HashTableLinkedListNode at the head of the HashTableLinkedList
     * @return HashTableLinkedListNode - The head of the list
     */
    public HashTableLinkedListNode<KeyType, ValueType> getHead() {
        return this.head;
    }

    /**
     * Returns the number of HashTableLinkedListNodes in the HashTableLinkedList
     * @return int - The size of the HashTableLinkedList
     */
    public int size() {
        return this.size;
    }

    /**
     * Searches the HashTableLinkedList for a HashTableLinkedListNode corresponding with the provided key
     * @param key - The key of the HashTableLinkedListNode to search for
     * @return true - If the HashTableLinkedList contains a HashTableLinkedListNode with the provided key
     * @return false - if the HashTableLinkedList does not contain a HashTableLinkedListNode with the provided key
     */
    public boolean containsKey (KeyType key) {

        HashTableLinkedListNode<KeyType, ValueType> curNode = this.head;

        while(curNode != null) {
            if (curNode.getKey().equals(key)) {
                return true;
            } else {
                curNode = curNode.getNext();
            }
        }

        return false;
    }

}
