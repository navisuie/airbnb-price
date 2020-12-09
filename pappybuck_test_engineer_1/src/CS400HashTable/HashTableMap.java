package pappybuck_test_engineer_1.src.CS400HashTable;

import java.util.LinkedList;
import java.util.NoSuchElementException;

// --== CS400 File Header Information ==--
// Name: Patrick Buck
// Email: pfbuck@wisc.edu
// Team: BC
// TA: Brianna Cochran
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

/**
 * 
 * @author Patrick Buck
 *
 * @param <KeyType>   Integer for the Year of the movie
 * @param <ValueType> String for the name of the Movie and the Studio
 */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

  private LinkedList<Node<KeyType, ValueType>>[] hashTable;
  private int capacity, size;


  /**
   * @author Patrick Buck
   */
  public HashTableMap() {
    this(10);
  }

  /**
   * @author Patrick Buck
   * @param capacity Capacity of the hashtable
   */
  public HashTableMap(int capacity) {

    this.capacity = capacity;
    size = 0;
    hashTable = new LinkedList[capacity];

  }

  /**
   * @author Patrick Buck
   * @param key Key being checked
   * @return location of key or -1 if key could not be found
   */
  public int find(KeyType key) {
    int index = (key.hashCode() < 0 ? 0 - key.hashCode() : key.hashCode()) % capacity;
    LinkedList<Node<KeyType, ValueType>> temp = hashTable[index];
    if (temp != null) {
      for (int i = 0; i < temp.size(); i++) {
        if (temp.get(i).getKey().equals(key)) {
          return i;
        }
      }
    }
    return -1;
  }


  /**
   * @author Patrick Buck
   * @param key
   * @param value
   */
  @Override
  public boolean put(KeyType key, ValueType value) {
    int index = (key.hashCode() < 0 ? 0 - key.hashCode() : key.hashCode()) % capacity;

    if (hashTable[index] == null) {
      hashTable[index] = new LinkedList<Node<KeyType, ValueType>>();
    }
    if (containsKey(key)) {
      return false;
    }
    hashTable[index].add(new Node<KeyType, ValueType>(key, value));
    size++;

    if ((double) size / capacity >= 0.8) {
      capacity *= 2;
      LinkedList<Node<KeyType, ValueType>>[] oldHashTable = hashTable;
      clear();
      for (int i = 0; i < oldHashTable.length; i++) {
        if (oldHashTable[i] != null) {
          for (int k = 0; k < oldHashTable[i].size(); k++) {
            put(oldHashTable[i].get(k).getKey(), oldHashTable[i].get(k).getValue());
          }
        }
      }

    }

    return true;
  }

  /**
   * @author Patrick Buck
   * @param key
   */
  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {
    int temp = find(key);
    if (temp != -1) {
      return hashTable[(key.hashCode() < 0 ? 0 - key.hashCode() : key.hashCode()) % capacity]
          .get(temp).getValue();
    }
    throw new NoSuchElementException("Key could not be found");
  }

  @Override
  public int size() {
    return size;
  }

  /**
   * @author Patrick Buck
   */
  @Override
  public boolean containsKey(KeyType key) {
    return find(key) != -1;
  }

  /**
   * @author Patrick Buck
   * @param key Key of the node being removed
   */
  @Override
  public ValueType remove(KeyType key) {
    try {
      ValueType output = get(key);
      hashTable[(key.hashCode() < 0 ? 0 - key.hashCode() : key.hashCode()) % capacity]
          .remove(find(key));
      size--;
      return output;
    } catch (NoSuchElementException e) {
      return null;
    }
  }

  /**
   * @author Patrick Buck
   * 
   */
  @Override
  public void clear() {
    size = 0;
    hashTable = new LinkedList[capacity];

  }

}
