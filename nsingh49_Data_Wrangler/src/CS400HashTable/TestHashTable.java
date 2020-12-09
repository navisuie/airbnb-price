// --== CS400 File Header Information ==--
// Name: <Navpreet Singh>
// Email: <nsingh49@wisc.edu>
// Team: <>
// TA: <>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>
public class TestHashTable {
	public static boolean test1() {
		HashTableMap object = new HashTableMap();
		if (object.size() == 0) {
			System.out.println(1);
			return true;
		}
		return false;
	}

	public static boolean test2() {
		HashTableMap object = new HashTableMap();
		if (object.getTable()[1] == null) {
			return true;
		}
		return false;
	}

	public static boolean test3() {
		HashTableMap object = new HashTableMap();
		String[] array = new String[3];
		array[0] = "hi";
		String[] value = new String[3];
		value[0] = "world";
		if (object.put(array[0], value[0])) {
			return true;
		}
		return false;
	}

	public static boolean test4() {
		HashTableMap object = new HashTableMap();
		String[] array = new String[3];
		array[0] = "hi";
		int answer = object.hash(array);
		if (answer == Math.abs(3329) * 10) {
			return true;
		}

		return false;
	}

	public static boolean test5() {
		HashTableMap object = new HashTableMap();
		String[] array = new String[3];
		array[0] = "hi";
		String[] value = new String[3];
		value[0] = "world";
		object.put(array[0], value[0]);
		if (object.containsKey(array[0])) {
			return true;

		}
		return false;
	}

	public static void main(String[] args) {

	}
}