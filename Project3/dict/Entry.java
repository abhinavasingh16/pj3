
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

  public Entry(Object key1, Object value1){
    this.key = key1;
    this.value = value1;
  }

  public Object key() {
    return key;
  }

  public Object value() {
    return value;
  }

  public String toString() {
  	String result = "[";
  	result = result + " " + this.key() + ":" + this.value() + " " + "]";
  	return result;
  }
}
