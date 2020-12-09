package CS400HashTable;// --== CS400 File Header Information ==--
// Name: Jacob D Lorenz
// Email: jlorenz2@wisc.edu
// Team: BD
// TA: Brianna Cochran
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.NoSuchElementException;

/**
 * A suite of tests to inspect various functionality of the HashTableMap implementation of MapADT
 */
public class TestHashTable {

    /**
     * This test creates a new HashTableMap of default capacity (10). It then seeds the HashTableMap with the exact number of
     * key:value pairs required to trigger 1 map resizing. After confirming the HashTableMap resized properly by a factor of 2, it
     * clears the entire map, and checks to make sure the size goes to 0 and capacity stays the same as it was after the
     * resize.
     *
     * Tests the following functionality: constructor(), put(), resizing, clear(), size(), getCapacity(), getLOAD_FACTOR()
     *
     * @return true - If all functionality works as expected
     * @return false - If the capacity or size after an expected resize and clear is not in line with expectations
     */
    public static boolean test1() {
        int initialCapacity = 10;
        HashTableMap<Integer, String> testMap = new HashTableMap<>(); // Size 10
        double growthFactor = testMap.getLOAD_FACTOR();
        int currentCapacity = testMap.getCapacity();

        int numEntries1 = (int) (currentCapacity * growthFactor);

        // The following loop should populate the map with exactly the number of entries needed to make it grow
        for (int i = 0; i < numEntries1; i++) {
            testMap.put(i, "This is entry " + i);
        }

        currentCapacity = testMap.getCapacity();

        // Capacity should be twice as large as initially
        if (currentCapacity != initialCapacity * 2) {
            return false;
        }

        // Clear the map - Size should go to 0 and capacity should stay the same
        testMap.clear();
        currentCapacity = testMap.getCapacity();
        int currentSize = testMap.size();

        if ((currentCapacity != initialCapacity * 2) || currentSize != 0) {
            return false;
        }

        return true;
    }

    /**
     * This test creates a new HashTableMap of capacity 20. It then puts 10 values into the HashTableMap and checks to ensure
     * the size updates after each put operation. After confirming all 10 values are inserted, the test goes through
     * and removes each key and checks the size after each delete operation.
     *
     * Tests the following functionality: constructor(capacity), put(), remove(), size()
     *
     * @return true - If all functionality works as expected
     * @return false - If the size of the map is not as expected after each put or remove operation
     */
    public static boolean test2() {
        int initialCapacity = 20;
        MapADT<Integer, String> testMap = new HashTableMap<>(initialCapacity); // Size 20

        // Insert 10 values and check the size after each time
        for (int i = 1; i < 11; i++) {
            testMap.put(i, "This is entry " + i);
            if (testMap.size() != i) {
                return false;
            }
        }

        // 10 values are in the map, delete them and check size after every delete
        for (int i = 10; i > 0 ; i--) {
            testMap.remove(i);
            if (testMap.size() != i-1) {
                return false;
            }
        }

        return true;
    }

    /**
     * This test creates a new HashTableMap of capacity 20. It then inserts 10 key:value pairs into the map and confirms
     * the size updates properly after each put.
     * After inserting the key:value pairs, the test goes through the map to ensure containsKey() returns true for each
     * of the previously inserted keys.
     * After confirming the map contains a key:value pair for each key, the test attempts to insert another key:value
     * pair for each of the previously inserted keys, and checks to make sure the size does not change
     *
     * Tests the following functionality: constructor(capacity), put(), containsKey(), duplicate put(), size()
     *
     * @return true - If all functionality works as expected
     * @return false - If the map size does not update after a proper put, does not contain an expected key, or changes when trying to put a dupicate key
     */
    public static boolean test3() {
        int initialCapacity = 20;
        MapADT<Integer, String> testMap = new HashTableMap<>(initialCapacity); // Size 20

        // Insert 10 values and check the size after each time
        for (int i = 1; i < 11; i++) {
            testMap.put(i, "This is entry " + i);
            if (testMap.size() != i) {
                return false;
            }
        }

        // Check that the map contains each of the inserted values
        for (int i = 1; i < 11; i++) {
            boolean containsKey = testMap.containsKey(i);
            if (!containsKey) {
                return false;
            }
        }

        int sizeAfterPopulating = testMap.size();

        // Insert 10 duplicate values and check the size after each time
        for (int i = 1; i < 11; i++) {
            testMap.put(i, "This is duplicate entry " + i);
            if (testMap.size() != sizeAfterPopulating) {
                return false;
            }
        }

        return true;
    }

    /**
     * This test creates a new HashTableMap of capacity 20. After inserting 10 key:value pairs and confirming the updating size,
     * the test goes through and ensures it can get() a value for each of the previously inserted keys.
     * After confirming it can get() each key:value pair, each key:value pair is removed from the map.
     * After removing each key:value pair, the test attempts to again remove each key:value pair, and expects
     * to receive a null response for each duplicate remove()
     *
     * Tests the following functionality: constructor(capacity), put(), size(), get(), remove(), remove() on a non-existent key
     *
     * @return true - If all functionality works as expected
     * @return false - If the size does not update properly on put(), if get() fails on an existing key, or if a duplicate removal does not return null
     */
    public static boolean test4() {
        int initialCapacity = 20;
        MapADT<Integer, String> testMap = new HashTableMap<>(initialCapacity); // Size 20

        // Insert 10 values and check the size after each time
        for (int i = 1; i < 11; i++) {
            testMap.put(i, "This is entry " + i);
            if (testMap.size() != i) {
                return false;
            }
        }

        // Gets each of the 10 elements and checks for their existence
        for (int i = 1; i < 11; i++) {
            try {
                String value = testMap.get(i);
            } catch (Exception e) {
                return false;
            }
        }

        // Remove all the values the first time
        for (int i = 10; i > 0 ; i--) {
            testMap.remove(i);
        }

        // Remove the values a second time, each should be null
        for (int i = 10; i > 0 ; i--) {
            String value = testMap.remove(i);
            if (value != null) {
                return false;
            }
        }

        return true;
    }

    /**
     * This test creates a new HashTableMap of capacity 20. Without inserting a single key:value pair, the test attempts
     * to get() 10 keys:value pairs that should not be in the map. The test expects each of the 10 gets() to throw a
     * NoSuchElementException.
     *
     * Tests the following functionality: constructor(capacity), get() of a non-existent key:value pair
     *
     * @return true - If all functionality works as expected
     * @return false - If get() of a non-existent key value pair does not throw a NoSuchElementException
     */
    public static boolean test5() {
        int initialCapacity = 20;
        MapADT<Integer, String> testMap = new HashTableMap<>(initialCapacity); // Size 20

        // Attempt to get 10 key:value pairs that should not exist in the map
        for (int i = 0; i < 10; i++) {
            try {
                String value = testMap.get(i);
                // If the exception is thrown, this should break into the catch clause nd never make it to this return statement
                return false;
            } catch (Exception e) {
                if (!(e instanceof NoSuchElementException)) {
                    return false;
                }
            }
        }

        return true;
    }

}
