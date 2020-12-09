package BackEnd;

//--== CS400 File Header Information ==--
//Name: Patrick Buck
//Email: pfbuck@wisc.edu
//Team: BC
//TA: Brianna Cochran
//Lecturer: Florian Heimerl
//Notes to Grader: <optional extra notes>

/**
 * 
 * @author Patrick Buck
 *
 * @param <KeyType> Type of data being used to Sort
 * @param <ValueType> Type of data being stored
 */
public class Node <KeyType, ValueType> {
  
  private KeyType key;
  private ValueType value;
  
  public Node(KeyType key , ValueType value) {
    this.key=key;
    this.value=value;
  }
  public KeyType getKey() {
    return key;
  }
  public ValueType getValue() {
    return value;
  }
  
}
