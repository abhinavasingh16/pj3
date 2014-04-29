
/* Entry.java */

package dict;

/**
 *  A class for dictionary entries.
 *
 *  DO NOT CHANGE THIS FILE.  It is part of the interface of the
 *  Dictionary ADT.
 **/

public class Entry {

  protected Object key;
  protected Object value;

  /**
  *   Entry() with 2 paramters sets the key 
  *   and value pair to their respective fields. 
  **/
  public Entry(Object key1, Object value1){
    this.key = key1;
    this.value = value1;
  }

  /**
  *   key() gets the key and returns it from a Entry. 
  **/
  public Object key() {
    return key;
  }

  /**
  *   value() gets the value from a key value pair and returns it. 
  **/
  public Object value() {
    return value;
  }

  /**
  *   toString() method for debugging purposes.
  **/
  public String toString() {
  	String result = "[";
  	result = result + " " + this.key() + ":" + this.value() + " " + "]";
  	return result;
  }
}
