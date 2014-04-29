
/* HashTableChained.java */

package dict;
import graph.Edge;
import graph.VertexPair;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  public SList[] baseArray;
  private DList2 internalVertices;
  private int dictSize;

  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/
    public HashTableChained(int sizeEstimate) {
        int numBuckets = sizeEstimate;
        while (!isPrime(numBuckets)){
          numBuckets++;
        }
        this.baseArray = new SList[numBuckets];
        this.internalVertices = new DList2();
        this.dictSize = 0; 
        for (int i = 0; i < numBuckets; i++){
          this.baseArray[i] = new SList();
        }
    }

  
  /**
  *   isPrime() checks to see if a number is prime. 
  **/
  private boolean isPrime(int n){
    int limit = (int)(Math.sqrt((double)n));
    for (int divisor = 2; divisor <= limit; divisor++){
      if (n%divisor == 0){
        return false;
      }
    }
    return true;
  }


  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/
  public HashTableChained() {
    int numBuckets = 101;
    this.baseArray = new SList[numBuckets];
    this.internalVertices = new DList2();
    this.dictSize = 0; 
    for(int i = 0; i < numBuckets; i++){
      this.baseArray[i] = new SList();
    }
  }

  /**
  *   A series of getters have been included for security purposes. 
  *   getBaseArray() gets the baseArray field and returns it. 
  *   getInternalValues() gets the internal DList representation and returns it. 
  **/
  public SList[] getBaseArray(){
    return this.baseArray;
  }

  public DList2 getInternalVertices(){
    return this.internalVertices;
  }

  /**
  *   setInternalvertices() sets the internalDList to another DList. 
  **/
  public void setInternalvertices(DList2 intV){
    this.internalVertices = intV;
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  public int compFunction(int code) {
    int a = ((600*code+99)%2147483647)%(this.baseArray.length);
    if (a<0){
      return -a;
    }else{
      return a;
    }
  }


  /**
  *   addCompFunction() is a private method that is used in the insert method. 
  **/

  private static int addCompFunction(int code, int length){
    int a = ((600*code+99)%2147483647)%(length);
    if (a < 0){
      return -a;
    }else{
      return a;
    }
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    return this.dictSize;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    if (this.dictSize == 0){
      return true;
    }else{
      return false; 
    }
  }

  /**
  *   loadFactor() dynamically determines the load factor of a hashTable.
  **/

  private double loadFactor(){
    return ((double) this.size()/this.baseArray.length);
  }

  
  /**
  *   toString() prints all of the entries of the hashTable in a array 
  *   like representation. 
  **/

  public String toString(){
    String result = "[" + " "; 
    for(int i = 0; i < this.baseArray.length; i++){
      SListNode pointer = this.baseArray[i].head;
      while (pointer != null){
        result = result + " " + "[ " + pointer.entry.key() + ":" + pointer.entry.value() + "]";
        pointer = pointer.next;
      }
    }
    result = result + " " + "]";
    return result;
  }
  
  /**
  *   shrinkAndRehash() halves the leangth of baseArray 
  *   and rehashes everything in the hashtable.
  **/
  private void shrinkAndRehash(){
    int newLength = this.baseArray.length/2; 
    while (!isPrime(newLength)){
      newLength++;
    }
    HashTableChained newTable = new HashTableChained(newLength);
    for(int i = 0; i < this.baseArray.length; i++){
      SListNode pointer = this.baseArray[i].head;
      while(pointer != null){
        int hashCode = pointer.entry.key.hashCode();
        int bucketNo = addCompFunction(hashCode,newLength);
        newTable.internalVertices.insertFront(pointer.entry.value);
        SListNode newNode = new SListNode(pointer.entry);
        newNode.listNode = newTable.internalVertices.head.next;
        newTable.baseArray[bucketNo].insertNodeEnd(newNode);
        pointer = pointer.next;
      }
    }
    this.dictSize = this.dictSize*2;
    this.internalVertices = newTable.internalVertices;
    this.baseArray = newTable.baseArray;
  }


  /**
  *   rehash() doubles the length of baseArray and rehashes 
  *   everything in the hashTable. 
  **/
  private void rehash(){
    int newLength = this.baseArray.length * 2;
    while (!isPrime(newLength)){
      newLength++;
    }
    HashTableChained newTable = new HashTableChained(newLength);
    for (int i = 0; i < this.baseArray.length; i++){
      SListNode pointer = this.baseArray[i].head;
      while(pointer != null){
        int hashCode = pointer.entry.key.hashCode();
        int bucketNo = addCompFunction(hashCode,newLength);
        newTable.internalVertices.insertFront(pointer.entry.value);
        SListNode newNode = new SListNode(pointer.entry);
        newNode.listNode = newTable.internalVertices.head.next;
        newTable.baseArray[bucketNo].insertNodeEnd(newNode);
        pointer = pointer.next;
      }
    }
    this.dictSize = this.dictSize*2;
    this.internalVertices = newTable.internalVertices;
    this.baseArray = newTable.baseArray;
  }


  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    if (this.loadFactor() > 0.75){
      this.rehash();
    }
    Entry newEntry = new Entry(key,value);
    int hashCode = key.hashCode();
    int bucketNo = this.compFunction(hashCode);
    this.internalVertices.insertFront(value);
    SListNode newNode = new SListNode(newEntry);
    newNode.listNode = this.internalVertices.head.next;
    this.baseArray[bucketNo].insertNodeEnd(newNode);
    this.dictSize++;
    return newEntry;
  }

  /**
  *   insertEdge() inserts a SListNode into the edgeHashTable and points it to the given destinations. 
  *
  *   @param the first vertex of the edge to be inserted. 
  *   @param the second vertex of the edge to be inserted. 
  *   @param the weight of the edge to be inserted. 
  *   @param is the first DListNode2 containing the edge which the SListNode points to. 
  *   @param is the second DListNode2 containing the edge on a different corresponding vertex which the SListNode points to. 
  *   @return an Entry. 
  *
  **/

  public Entry insertEdge(Object u, Object v, int weight, DListNode2 pointer1, DListNode2 pointer2){
    if (this.loadFactor() > 0.75){
      this.rehash();
    }
    VertexPair newPair = new VertexPair(u,v);
    int hashCode = newPair.hashCode();
    int whichBucket = this.compFunction(hashCode);
    Edge newEdge = new Edge(u,v,weight);
    Entry newEntry = new Entry(newEdge,newEdge);
    SListNode newNode = new SListNode(pointer1,pointer2,newEntry);
    this.internalVertices.insertFront(newPair);
    this.baseArray[whichBucket].insertNodeEnd(newNode);
    this.dictSize++;
    return newEntry;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    int hashCode = key.hashCode();
    int whichBucket = this.compFunction(hashCode);
    if (this.baseArray[whichBucket].length() == 0){
      return null;
    }
    SListNode pointer = this.baseArray[whichBucket].head();
    while (pointer != null){
      Entry pointersEntry = pointer.entry();
      Object pointersKey = pointersEntry.key();
      if (pointersKey.equals(key)){
        return pointersEntry;
      }
      pointer = pointer.next();
    }
    return null;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    if(this.loadFactor() < 0.1 && dictSize > 5){
      this.shrinkAndRehash();
    }
    int hashCode = key.hashCode();
    int whichBucket = this.compFunction(hashCode);
    if (this.baseArray[whichBucket].length() == 0){
      return null;
    }else{
        SListNode pointer = this.baseArray[whichBucket].head();
        while (pointer != null){
          Entry pointersEntry = pointer.entry();
          Object pointersKey = pointersEntry.key();
          if (pointersKey.equals(key)){
            Entry store = pointersEntry;
            this.baseArray[whichBucket].removeNode(pointer);
            this.internalVertices.removeNode(store.value());
            this.dictSize--;
            return store;
          }
          pointer = pointer.next();
        }
        return null;
    }
  }

  /**
  *   makes the hashTable Empty. Not used in this project so it was not filled out. 
  **/

  public void makeEmpty(){
    return;
  }
}
