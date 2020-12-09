
// --== CS400 File Header Information ==--
// Name: <Navpreet Singh>
// Email: <nsingh49@wisc.edu>
// Team: <>
// TA: <Bri>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>
import java.util.NoSuchElementException;
import java.util.LinkedList;

public class HashTableMap<KeyType, ValueType> implements MapADT {
	KeyType key;
	ValueType value;
	HashTableMap next;
	private int capacity;
	private int pairNumbers;

	private HashTableMap[] table;

	public HashTableMap(int capacity) {
		this.capacity = capacity;
		if (capacity <= 0) {
			throw new IllegalArgumentException("Not a valid Table size");
		} else {
			setTable(new HashTableMap[capacity]);
		}

	}

	public HashTableMap() {
		this.capacity = 10;
		setTable(new HashTableMap[10]);
	}

	@Override
	public boolean put(Object key, Object value) {
		int hashValue = hash(key);

		HashTableMap list = getTable()[hashValue];
		while (list != null) {
			if (list.key.equals(key)) {
				return false;
			} else {
				return true;
			}
		}

		if (capacity >= 0.80 * size()) {

			grow();
			hashValue = hash(key);
		}
		HashTableMap newNode = new HashTableMap();
		newNode.key = key;
		newNode.value = value;
		newNode.next = getTable()[hashValue];
		getTable()[hashValue] = newNode;
		pairNumbers++;
		return true;

	}

	public void grow() {
		HashTableMap temp[] = getTable();
		int doubleCapacity = capacity * 2;
		setTable(new HashTableMap[doubleCapacity]);
		for (HashTableMap frontNode : temp) {
			while (frontNode != null) {
				put(frontNode.key, frontNode.value);
				frontNode = frontNode.next;
			}

		}
	}

	public int hash(Object key) {
		return (Math.abs(key.hashCode())) % getTable().length;
	}

	@Override
	public ValueType get(Object key) throws NoSuchElementException {
		int hashValue = hash(key);

		HashTableMap list = getTable()[hashValue];
		while (list != null) {

			if (list.key.equals(key))
				return (ValueType) list.value;
			list = list.next;

		}
		throw new NoSuchElementException("this key does not exist");
	}

	@Override
	public int size() {

		return pairNumbers;
	}

	@Override
	public boolean containsKey(Object key) {
		int hashValue = hash(key);

		HashTableMap list = getTable()[hashValue];
		while (list != null) {

			if (list.key.equals(key))
				return true;
			list = list.next;
		}

		return false;
	}

	@Override
	public ValueType remove(Object key) {
		int hashValue = hash(key);

		if (getTable()[hashValue] == null) {

			return null;
		}

		if (getTable()[hashValue].key.equals(key)) {

			getTable()[hashValue] = getTable()[hashValue].next;
			pairNumbers--;
			return value;
		}

		HashTableMap prev = getTable()[hashValue];
		HashTableMap curr = prev.next;
		while (curr != null && !curr.key.equals(key)) {
			curr = curr.next;
			prev = curr;
		}

		if (curr != null) {
			prev.next = curr.next;
			pairNumbers--;
			return value;
		}
		return null;
	}

	@Override
	public void clear() {
		for (int i = 0; i < getTable().length; i++) {
			getTable()[i] = null;
		}

	}

	public HashTableMap[] getTable() {
		return table;
	}

	public void setTable(HashTableMap[] table) {
		this.table = table;
	}
}
