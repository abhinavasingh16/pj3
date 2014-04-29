/* SList.java */

/**
 *  The SList class is a modified singly-linked implementation of the linked list
 *  abstraction.  SLists are mutable data structures, which can grow at either
 *  end.
 *
 *  @author Kathy Yelick and Jonathan Shewchuk
 **/

package dict;

public class SList {

  protected SListNode head;
  protected int size;

  /**
   *  SList() constructs an empty list.
   **/

  public SList() {
    size = 0;
    head = null;
  }

  /**
   *  isEmpty() indicates whether the list is empty.
   *  @return true if the list is empty, false otherwise.
   **/

  public boolean isEmpty() {
    return size == 0;
  }

  /**
   *  length() returns the length of this list.
   *  @return the length of this list.
   **/

  public int length() {
    return size;
  }

  /**
  *   head() returns the head.
  **/
  public SListNode head(){
    return this.head;
  }

  /**
   *  insertFront() inserts item "obj" at the beginning of this list.
   *  @param obj the item to be inserted.
   **/

  public void insertFront(Entry entry1) {
    head = new SListNode(entry1);
    size++;
  }

  /**
   *  insertEnd() inserts item "obj" at the end of this list.
   *  @param obj the item to be inserted.
   **/

  public void insertEnd(Entry entry1) {
    if (head == null) {
      head = new SListNode(entry1);
    } else {
      SListNode node = head;
      while (node.next != null) {
        node = node.next;
      }
      node.next = new SListNode(entry1);
    }
    size++;
  }

  

  /**
  *   insertNodeEnd() inserts the node "node" at the end of the SList.
  *   @param node is the node to be inserted. 
  **/

  public void insertNodeEnd(SListNode node){
    if (head == null){
      head = node;
    }else{
      SListNode pointer = head;
      while (pointer.next != null){
        pointer = pointer.next;
      }
      pointer.next = node;
    }
    size++;
  }

  /**
  *   removeNode() removes the first node in the SList, and returns the node.  
  *   It returns null otherwise. 
  **/
  public Entry removeNode(){
    if (head == null){
      return null;
    }else if(head.next == null){
      Entry entryStorage = head.entry;
      head = null;
      size = 0; 
      return entryStorage;
    }else{
      Entry entryStorage = head.entry;
      head = head.next;
      size--;
      return entryStorage;
    }
  }

  /**
  *   removeNode() with 1 param removes the specified node in an SList. 
  **/
  public void removeNode(SListNode node){
    if (head == null){
      return;
    }else if(head.next == null){
      head = null;
      size = 0;
      return;
    }else{
      SListNode pointer = this.head;
      while(pointer != null){
        if(pointer == this.head){
          this.head = pointer.next;
          pointer.next = null;
          size--;
          return;
        }
        if (pointer.next.next != null && pointer.next == node){
          pointer.next = pointer.next.next;
          size--;
          return;
        }else if(pointer.next.next == null && pointer.next == node){
          pointer.next = null;
          size--;
          return;
        }
        pointer = pointer.next;
      }
    }
  }

  /**
   *  nth() returns the item at the specified position.  If position < 1 or
   *  position > this.length(), null is returned.  Otherwise, the item at
   *  position "position" is returned.  The list does not change.
   *  @param position the desired position, from 1 to length(), in the list.
   *  @return the item at the given position in the list.
   **/

  public Entry nth(int position) {
    SListNode currentNode;

    if ((position < 1) || (head == null)) {
      return null;
    } else {
      currentNode = head;
      while (position > 1) {
        currentNode = currentNode.next;
        if (currentNode == null) {
          return null;
        }
        position--;
      }
      return currentNode.entry;
    }
  }

  /**
   *  toString() converts the list to a String.
   *  @return a String representation of the list.
   **/

  public String toString() {
    int i;
    Object obj;
    String result = "[  ";

    SListNode cur = head;

    while (cur != null) {
      obj = cur.entry;
      result = result + obj.toString() + "  ";
      cur = cur.next;
    }
    result = result + "]";
    return result;
  }
}
