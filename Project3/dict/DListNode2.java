/* DListNode2.java */
package dict;
/**
 *  A DListNode2 is a node in a DList2 (doubly-linked list).
 */

public class DListNode2 {

  /**
   *  item references the item stored in the current node.
   *  prev references the previous node in the DList.
   *  next references the next node in the DList.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  protected Object vertex;
  public DList2 list;
  public DListNode2 correspondingPair;
  protected DListNode2 prev;
  protected DListNode2 next;

  /**
   *  DListNode2() constructor.
   */
  public DListNode2() {
    vertex = 0;
    list = null;
    prev = null;
    next = null;
  }

  /**
  *   DListNode2() constructor(one parameter) this sets 
  *   vertex1 to its corresponding vertex. 
  **/

  DListNode2(Object vertex1) {
    vertex = vertex1;
    list = null;
    prev = null;
    next = null;
  }

  /**
  *   getVertex() gets the correcsponding vertex 
  **/
  public Object getVertex(){
    return this.vertex;
  }

  /**
  * next() gets the next that corresponds to this.
  **/
  public DListNode2 next(){
    return this.next;
  }

  /**
  *   prev() gets the previous DListNode that corresponds to this. 
  **/
  public DListNode2 prev(){
    return this.prev;
  }

  /**
  *   setNext() sets a DListNode next1 as the next node to this. 
  **/

  public void setNext(DListNode2 next1){
    this.next = next1;
  }

  /**
  *   setPrev() sets a DListNode prev1 as the prev node to this.
  **/
  public void setPrev(DListNode2 prev1){
    this.prev = prev1;
  }

  /**
  *   This sets the corresponding list field to list1. 
  **/
  public void setList(DList2 list1){
    list = list1;
  }

  /**
  *   setVertex() sets the corresponding vertex of the 
  *   DListNode to the object vertex2. 
  **/
  public void setVertex(Object vertex2){
      this.vertex = vertex2;
  }

  /**
  *   list() returns the DList that the DListNode is pointing to. 
  **/
  public DList2 list(){
    return this.list;
  }

  /**
  *   correspondingPair() returns the DListNode that the 
  *   current node is pointing to. 
  **/
  public DListNode2 correspondingPair(){
    return this.correspondingPair;
  }

  /**
  *   toString() method for debugging purposes. 
  **/
  public String toString(){
    String result = "[ ";
    result = result + vertex + " ]";
    return result;
  }

}
