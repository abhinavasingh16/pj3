/* SListNode.java */

/**
 *  SListNode is a class used internally by the SList class.  An SList object
 *  is a singly-linked list, and an SListNode is a node of a singly-linked
 *  list.  Each SListNode has two references:  one to an object, and one to
 *  the next node in the list.
 *
 *  @author Kathy Yelick and Jonathan Shewchuk
 */
package dict;

public class SListNode {
  public DListNode2 listNode;
  public DListNode2 listNode2;
  public DList2 bigList;
  protected SListNode next;
  protected Entry entry;
  public Object item;


  /**
   *  SListNode() (with three parameters) constructs a list node referencing the
   *  Entry and the corresponding DListNode, whose next list node is to be "next".
   **/

  SListNode(DListNode2 nodePointer, Entry entry1, SListNode next) {
    this.listNode = nodePointer;
    this.next = next;
    this.entry = entry1;
  }

  /**
   *  SListNode() (with two parameters) constructs a list node referencing the
   *  entry and its corresponding DListNode. 
   */

  SListNode(DListNode2 nodePointer, Entry entry1) {
    this.listNode = nodePointer;
    this.entry = entry1;
    this.next = null;
  }

  /**
  *   SListNode() With 3 corresponding parameters constructs a list node refrencing 
  *   the entry and two corresponding DListNode's. 
  **/

  SListNode(DListNode2 nodePointer1, DListNode2 nodePointer2, Entry entry1){
    this.entry = entry1;
    this.listNode = nodePointer1;
    this.listNode2 = nodePointer2;
  }

  /**
  *   SListNode() (with one parameter) constructs a list node refrencing the entry. 
  *   It leaves everything else as null for the time being. 
  **/ 
  public SListNode(Entry entry1){
    this.entry = entry1;
    this.listNode = null;
    this.next = null;
  }

  /**
  *   A stimple toString() method for debugging. Returns a string. 
  **/
  public String toString(){
    String result = entry.toString();
    return result;
  }

  /**
  *   entry() returns the entry at head 
  **/
  public Entry entry(){
    return this.entry;
  }

  /**
  *   sets the Entry of a SListNode to something else. 
  **/
  public void setEntry(Entry entry1){
      this.entry = entry1;
  }

  /**
  *   gets the item() at the corresponding SListNode. 
  **/
  public Object item(){
    return this.item;
  }
  /**
  *   next() gets the node that this SListNode points to next. 
  **/
  public SListNode next(){
    return this.next;
  }
  
  /**
  *   listNode() gets the listNode stored by the SListNode. 
  **/
  public DListNode2 listNode(){
    return this.listNode;
  }
  
  /**
  *   listNode2() gets the second listNode that the SListNode points to. 
  **/
  public DListNode2 listNode2(){
    return this.listNode2;
  }
}
