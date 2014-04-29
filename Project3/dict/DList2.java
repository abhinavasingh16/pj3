/* DList2.java */
package dict;
/**
 *  A DList2 is a mutable doubly-linked list.  Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the head
 *  of the list.
 */

public class DList2 {

  /**
   *  head references the sentinel node.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  protected DListNode2 head;
  protected int size;

  /* DList2 invariants:
   *  1)  head != null.
   *  2)  For any DListNode2 x in a DList2, x.next != null.
   *  3)  For any DListNode2 x in a DList2, x.prev != null.
   *  4)  For any DListNode2 x in a DList2, if x.next == y, then y.prev == x.
   *  5)  For any DListNode2 x in a DList2, if x.prev == y, then y.next == x.
   *  6)  size is the number of DListNode2s, NOT COUNTING the sentinel
   *      (denoted by "head"), that can be accessed from the sentinel by
   *      a sequence of "next" references.
   */

  /**
   *  DList2() constructor for an empty DList2.
   */
  public DList2() {
    head = new DListNode2();
    head.next = head;
    head.prev = head;
    size = 0;
  }

  /**
   *  DList2() constructor for a one-node DList2.
   */
  public DList2(Object vertex1) {
    head = new DListNode2();
    head.next = new DListNode2();
    head.next.vertex = vertex1;
    head.prev = head.next;
    head.next.prev = head;
    head.prev.next = head;
    size = 1;
  }

  /**
   *  getSize() gets the size of the DList2.
   **/

  public int getSize(){
    return this.size;
  }

  public void setSize(int size1){
    this.size = size1;
  }

  /**
  *   next() gives you the node next to head. 
  **/
  public DListNode2 next(){
    return head.next;
  }

  /**
  *   head() gives you the current node.
  **/
  public DListNode2 head(){
    return this.head;
  }

  /**
   *  insertFront() inserts an item at the front of a DList2.
   */
  public void insertFront(Object vertex1) {
    DListNode2 newNode = new DListNode2(vertex1);
    head.next.prev = newNode;
    head.next.prev.next = head.next;
    head.next.prev.prev = head;
    head.next = newNode;
    size++;
  }

  //insertEnd() inserts an item at the end of a DList2 behind the sentinel. 
  public void insertEnd(Object vertex1){
    DListNode2 newNode = new DListNode2(vertex1);
    head.prev.next = newNode;
    head.prev.next.next = head;
    head.prev.next.prev = head.prev;
    head.prev = head.prev.next;
    size++;
  }

  public void removeNode(Object vertex1){
    if (size == 0) {
    } else if (size == 1){
      head.next = head;
      head.prev = head;
      size--;
    } else{
        DListNode2 pointer = head;
        while (pointer.next != head){
          Object compObject = pointer.next.vertex;
          if (compObject == vertex1){
            pointer.next.next.prev = head;
            pointer.next = pointer.next.next;
            size--;
            break;
          }else{
            pointer = pointer.next; 
          }
        }
    }
  }

  /**
   *  removeFront() removes the first item (and first non-sentinel node) from
   *  a DList2.  If the list is empty, do nothing.
   */
  public void removeFront() {
    if (size == 0) {
    } else {
      head.next.next.prev = head;
      head.next = head.next.next;
      size--;
    }
  }

  public String toString(){
    String result = "{ ";
    for (int i = 0; i <= size; i++){
      result = result + "[";
      result = result + head.vertex + "]" + "   ";
      head = head.next;
    }
    result = result + "}";
    return result;
  }
}
