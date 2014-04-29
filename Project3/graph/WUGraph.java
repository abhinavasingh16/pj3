
/* WUGraph.java */

package graph;
import dict.*;

/**
 * The WUGraph class represents a weighted, undirected graph.  Self-edges are
 * permitted.
 */

public class WUGraph {

  public HashTableChained vertexTable;
  public HashTableChained edgeTable;
  public int vertexNumber;
  public int edgeNumber;

  /**
   * WUGraph() constructs a graph having no vertices or edges.
   *
   * Running time:  O(1).
   */
  public WUGraph(){
    vertexTable = new HashTableChained(50);
    edgeTable = new HashTableChained(50);
    this.vertexNumber = 0;
    this.edgeNumber = 0; 
  }

  /**
   * vertexCount() returns the number of vertices in the graph.
   *
   * Running time:  O(1).
   */
  public int vertexCount(){
    return this.vertexNumber;
  }

  /**
   * edgeCount() returns the total number of edges in the graph.
   *
   * Running time:  O(1).
   */
  public int edgeCount(){
    return this.edgeNumber;
  }
  /**
   * getVertices() returns an array containing all the objects that serve
   * as vertices of the graph.  The array's length is exactly equal to the
   * number of vertices.  If the graph has no vertices, the array has length
   * zero.
   *
   * (NOTE:  Do not return any internal data structure you use to represent
   * vertices!  Return only the same objects that were provided by the
   * calling application in calls to addVertex().)
   *
   * Running time:  O(|V|).
   */
  public Object[] getVertices(){
    if (this.vertexCount() == 0){
      return new Object[0];
    }else{
      Object[] selectArray = new Object[this.vertexCount()];
      DList2 list = this.vertexTable.getInternalVertices();
      DListNode2 pointer = list.head().next();
      int index = 0; 
      while (pointer != list.head()){
        selectArray[index] = pointer.getVertex();
        index++;
        pointer = pointer.next();
      }
      return selectArray;
    }
  }


  /**
   * addVertex() adds a vertex (with no incident edges) to the graph.
   * The vertex's "name" is the object provided as the parameter "vertex".
   * If this object is already a vertex of the graph, the graph is unchanged.
   *
   * Running time:  O(1).
   */
  public void addVertex(Object vertex){
    if (this.isVertex(vertex)){
      return;
    }
    int vertexHashCode = vertex.hashCode();
    vertexTable.insert(vertex,vertex);
    int hashCode = vertex.hashCode();
    int whichBucket = vertexTable.compFunction(hashCode);
    SListNode pointer = vertexTable.baseArray[whichBucket].head();
    while (pointer != null){
      Entry test = pointer.entry();
      Object testsObject = test.key();
      if (testsObject.equals(vertex)){
        pointer.item = vertex;
        break;
      }
      pointer = pointer.next();
    }
    this.vertexNumber++;
  }

  /**
   * removeVertex() removes a vertex from the graph.  All edges incident on the
   * deleted vertex are removed as well.  If the parameter "vertex" does not
   * represent a vertex of the graph, the graph is unchanged.
   *
   * Running time:  O(d), where d is the degree of "vertex".
   */
  public void removeVertex(Object vertex){
    if (!this.isVertex(vertex)){
      return;
    }
    int vertexHashCode = vertex.hashCode();
    int whichBucket = vertexTable.compFunction(vertexHashCode);
    SListNode pointer = vertexTable.baseArray[whichBucket].head();
    int holder = 0; 
    while (pointer != null){
      Entry test = pointer.entry();
      Object testsObject = test.key();
      if (testsObject.equals(vertex)){
        if (pointer.listNode.list == null){
          break;
        }else{
          holder = pointer.listNode.list.getSize();
          break;
        }
      }
      pointer = pointer.next();
    }
    vertexTable.remove(vertex);
    this.vertexNumber--;
    this.edgeNumber = this.edgeNumber - holder;
  }

  /**
   * isVertex() returns true if the parameter "vertex" represents a vertex of
   * the graph.
   *
   * Running time:  O(1).
   */
  public boolean isVertex(Object vertex){
    if (vertexTable.find(vertex) == null){
      return false;
    }else{
      return true;
    }
  }

  /**
   * degree() returns the degree of a vertex.  Self-edges add only one to the
   * degree of a vertex.  If the parameter "vertex" doesn't represent a vertex
   * of the graph, zero is returned.
   *
   * Running time:  O(1).
   */
  public int degree(Object vertex){
    if (!this.isVertex(vertex)){
      return 0;
    }
    int hashCode = vertex.hashCode();
    int whichBucket = vertexTable.compFunction(hashCode);
    SListNode pointer = vertexTable.baseArray[whichBucket].head();
    while(pointer != null){
      Entry ent = pointer.entry();
      if (ent.key().equals(vertex)){
        if (pointer.listNode.list == null){
          return 0;
        }
        DList2 degrees = pointer.listNode.list;
        return degrees.getSize();
      }
      pointer = pointer.next();
    }
    return 0;
  }

  /**
   * getNeighbors() returns a new Neighbors object referencing two arrays.  The
   * Neighbors.neighborList array contains each object that is connected to the
   * input object by an edge.  The Neighbors.weightList array contains the
   * weights of the corresponding edges.  The length of both arrays is equal to
   * the number of edges incident on the input vertex.  If the vertex has
   * degree zero, or if the parameter "vertex" does not represent a vertex of
   * the graph, null is returned (instead of a Neighbors object).
   *
   * The returned Neighbors object, and the two arrays, are both newly created.
   * No previously existing Neighbors object or array is changed.
   *
   * (NOTE:  In the neighborList array, do not return any internal data
   * structure you use to represent vertices!  Return only the same objects
   * that were provided by the calling application in calls to addVertex().)
   *
   * Running time:  O(d), where d is the degree of "vertex".
   */
  public Neighbors getNeighbors(Object vertex){
    if (!this.isVertex(vertex) || this.degree(vertex) == 0){
      return null;
    }
    int hashCode = vertex.hashCode();
    int whichBucket = vertexTable.compFunction(hashCode);
    SListNode pointer = vertexTable.baseArray[whichBucket].head();
    while (pointer != null){
      Entry pointersEntry = pointer.entry();
      Object pointersKey = pointersEntry.key();
      if (pointersKey.equals(vertex)){
        DListNode2 listNode = pointer.listNode();
        DList2 list = listNode.list;
        DListNode2 dPointer = list.head().next();
        Object[] nList = new Object[list.getSize()];
        int[] weights = new int[list.getSize()];
        int i = 0; 
        while (dPointer != list.head()){
          Edge gotEdge = ((Edge) dPointer.getVertex());
          VertexPair gotPair = gotEdge.vertexPair();
          Object insertObject = gotPair.object2();
          int insertWeight = gotEdge.weight();
          if (vertex.equals(insertObject)){
            insertObject = gotPair.object1();
          }
          nList[i] = insertObject;
          weights[i] = insertWeight;
          i++;
          dPointer = dPointer.next();
        }
        Neighbors neighbor = new Neighbors(nList,weights);
        return neighbor;
      }
      pointer = pointer.next();
    }
    return null;
  }

  /**
   * addEdge() adds an edge (u, v) to the graph.  If either of the parameters
   * u and v does not represent a vertex of the graph, the graph is unchanged.
   * The edge is assigned a weight of "weight".  If the graph already contains
   * edge (u, v), the weight is updated to reflect the new value.  Self-edges
   * (where u == v) are allowed.
   *
   * Running time:  O(1).
   */
  private void switchWeight(Object u, Object v, int weight){
    VertexPair newPair = new VertexPair(u,v);
    Edge newEdge = new Edge(u,v,weight);
    Entry newEntry = new Entry(newEdge,newEdge);
    int hashCode = newPair.hashCode();
    int whichBucket = edgeTable.compFunction(hashCode);
    SListNode pointer =  edgeTable.baseArray[whichBucket].head();
    while (pointer != null){
        Entry vertexEntry =  pointer.entry();
        Edge pointersEdge = ((Edge) vertexEntry.key());
        VertexPair testPair = pointersEdge.vertexPair();
        if (testPair.equals(newPair)){
            pointer.listNode.setVertex(newEdge);
            pointer.listNode2.setVertex(newEdge);
            pointer.setEntry(newEntry);
        }
        pointer = pointer.next();
    }
  }

  public void addEdge(Object u, Object v, int weight){
    if(!this.isVertex(u) || !this.isVertex(v)){
      return;
    }
    else if (this.isEdge(u,v) || this.isEdge(v,u)){
       if (this.weight(u,v) == weight || this.weight(v,u) == weight){
          return;
       }else{
        switchWeight(u,v,weight);
        return;
       }
    }
    else{

      VertexPair newPair = new VertexPair(u,v);
      Edge newEdge = new Edge(u,v,weight);
      int hashCode = newPair.hashCode();
      int whichBucket = edgeTable.compFunction(hashCode);
      SList edgeSList = edgeTable.baseArray[whichBucket];

      int uCode = u.hashCode();
      int vCode = v.hashCode();
      int uBucket = vertexTable.compFunction(uCode);
      int vBucket = vertexTable.compFunction(vCode);
      SListNode uPoint = vertexTable.baseArray[uBucket].head();
      SListNode vPoint = vertexTable.baseArray[vBucket].head();
      DListNode2 uSave = new DListNode2();
      DListNode2 vSave = new DListNode2();

      while (uPoint != null){
        Entry uEntry = uPoint.entry();
        Object uKey = uEntry.key();
        if (uKey.equals(u)){
          DListNode2 listNode = uPoint.listNode();
          DList2 edgeDList = listNode.list();
          if (edgeDList == null){
            uPoint.listNode.list = new DList2(newEdge);
            uSave = uPoint.listNode.list.next();
            break;
          }else{
            uPoint.listNode.list.insertFront(newEdge);
            uSave = uPoint.listNode.list.next();
            break;
          }
        }
        uPoint = uPoint.next();
      }

      if (u.equals(v)){
        uSave.correspondingPair = uSave;
        edgeTable.insertEdge(u,v,weight,uSave,uSave);
        this.edgeNumber++; 
        return;
      }

      while (vPoint != null){
        Entry vEntry = vPoint.entry();
        Object vKey = vEntry.key();
        if (vKey.equals(v)){
          DListNode2 otherListNode = vPoint.listNode();
          DList2 edgeListV = otherListNode.list();
          if (edgeListV == null){
            vPoint.listNode.list = new DList2(newEdge);
            vSave = vPoint.listNode.list.next();
            break;
          }else{
            vPoint.listNode.list.insertFront(newEdge);
            vSave = edgeListV.head().next();
            vSave.correspondingPair = uSave;
            uSave.correspondingPair = vSave;
            break;
          }
        }
        vPoint = vPoint.next();
      }
      edgeTable.insertEdge(u,v,weight,uSave,vSave);
      this.edgeNumber++;
    }
  }

  /**
   * removeEdge() removes an edge (u, v) from the graph.  If either of the
   * parameters u and v does not represent a vertex of the graph, the graph
   * is unchanged.  If (u, v) is not an edge of the graph, the graph is
   * unchanged.
   *
   * Running time:  O(1).
   */

  private void removeFromTable(Object u, Object v){
    if (!this.isVertex(u) || !this.isVertex(v)){
      return;
    }else{
      int weight = this.weight(u,v);
      Edge removalEdge = new Edge(u,v,weight);
      VertexPair hashPair = removalEdge.vertexPair();
      int hashCode = hashPair.hashCode();
      int whichBucket = edgeTable.compFunction(hashCode);
      SListNode pointer = edgeTable.baseArray[whichBucket].head();
      
      while (pointer != null){
        Entry pointersEntry = pointer.entry();
        Edge pointersEdge = ((Edge) pointersEntry.key());
        if (removalEdge.equals(pointersEdge)){
          edgeTable.baseArray[whichBucket].removeNode(pointer);
        }
        pointer = pointer.next();
      }
    }
  }
  
  public void removeEdge(Object u, Object v){
    if (!this.isVertex(u) || !this.isVertex(v)){
      return;
    }else if (!this.isEdge(u,v) || !this.isEdge(v,u)){
      return;
    }

    else{
      this.edgeNumber--;
      int weight = this.weight(u,v);
      Edge removalEdge = new Edge(u,v,weight);
      int uCode = u.hashCode();
      int vCode = v.hashCode();
      int uBucket = vertexTable.compFunction(uCode);
      int vBucket = vertexTable.compFunction(vCode);
      SListNode uPointer = vertexTable.baseArray[uBucket].head();
      SListNode vPointer = vertexTable.baseArray[vBucket].head();

      while (uPointer != null){
        Entry uEntry = uPointer.entry();
        Object uKey = uEntry.key();
        if (u.equals(uKey)){
          DList2 removeUList = uPointer.listNode.list;
          DListNode2 removeUHead = uPointer.listNode.list.head();
          DListNode2 removeUPointer = uPointer.listNode.list.head().next();
          while (removeUPointer != uPointer.listNode.list.head()){
            Edge removeUPointerEdge = ((Edge) removeUPointer.getVertex());
            if(removalEdge.equals(removeUPointerEdge)){
              if (removeUList.getSize() == 1){
                uPointer.listNode.list.removeFront();
                break;
              }else{
                removeUPointer.prev().setNext(removeUPointer.next());
                removeUPointer.next().setPrev(removeUPointer.prev());
                uPointer.listNode.list.setSize(removeUList.getSize()-1);
                break;
              }
            }
            removeUPointer = removeUPointer.next();
          }
        }
        uPointer = uPointer.next();
      }

      while (vPointer != null){
        Entry vEntry = vPointer.entry();
        Object vKey = vEntry.key();
        if (v.equals(vKey)){
          DList2 removeList = vPointer.listNode.list;
          DListNode2 removeHead = vPointer.listNode.list.head();
          DListNode2 removePointer = vPointer.listNode.list.head().next();
          while (removePointer != vPointer.listNode.list.head()){
            Edge removePointerEdge = ((Edge) removePointer.getVertex());
            if(removalEdge.equals(removePointerEdge)){
              if (removeList.getSize() == 1){
                vPointer.listNode.list.removeFront();
                break;
              }else{
                removePointer.prev().setNext(removePointer.next());
                removePointer.next().setPrev(removePointer.prev());
                vPointer.listNode.list.setSize(removeList.getSize()-1);
                break;
              }
            }
            removePointer = removePointer.next();
          }
        }
        vPointer = vPointer.next();
      }
      this.removeFromTable(u,v);
    }
  }

  /**
   * isEdge() returns true if (u, v) is an edge of the graph.  Returns false
   * if (u, v) is not an edge (including the case where either of the
   * parameters u and v does not represent a vertex of the graph).
   *
   * Running time:  O(1).
   */
  public boolean isEdge(Object u, Object v){
    if (!this.isVertex(u) || !this.isVertex(v)){
      return false;
    }
    VertexPair hashPair = new VertexPair(u,v);
    int hashCode = hashPair.hashCode();
    int whichBucket = edgeTable.compFunction(hashCode);
    SListNode pointer = edgeTable.baseArray[whichBucket].head();
    if (edgeTable.baseArray[whichBucket].length() == 0 || pointer == null){
      return false;
    }else{
      while (pointer != null){
        Entry pointersEntry = pointer.entry();
        Edge entriesEdge = ((Edge) pointersEntry.key());
        VertexPair entriesKey = entriesEdge.vertexPair();
        if (entriesKey.equals(hashPair)){
          return true;
        }
        pointer = pointer.next();
      }
      return false;
    }
  }

  /**
   * weight() returns the weight of (u, v).  Returns zero if (u, v) is not
   * an edge (including the case where either of the parameters u and v does
   * not represent a vertex of the graph).
   *
   * (NOTE:  A well-behaved application should try to avoid calling this
   * method for an edge that is not in the graph, and should certainly not
   * treat the result as if it actually represents an edge with weight zero.
   * However, some sort of default response is necessary for missing edges,
   * so we return zero.  An exception would be more appropriate, but also more
   * annoying.)
   *
   * Running time:  O(1).
   */
  public int weight(Object u, Object v){
    if(!this.isEdge(u,v) || !this.isVertex(u) || !this.isVertex(v)){
      return 0;
    }
    VertexPair hashPair = new VertexPair(u,v);
    int hashCode = hashPair.hashCode();
    int whichBucket = edgeTable.compFunction(hashCode);
    SListNode pointer = edgeTable.baseArray[whichBucket].head();
    while(pointer != null){
      Entry pointersEntry = pointer.entry();
      Edge entriesEdge = ((Edge) pointersEntry.key());
      VertexPair pair = entriesEdge.vertexPair();
      if (pair.equals(hashPair)){
        return entriesEdge.weight();
      }
      pointer = pointer.next();
    }
    System.out.println("Something is wrong!");
    return -100;
  }

  public static void main(String[] args){
    WUGraph g = new WUGraph();

    System.out.println("__________Testing Degree_________");
    g.addVertex(0);
    g.addVertex(1);
    System.out.println("g.degree(1) should be 0, you are getting: " + g.degree(1));
    g.addVertex(2);
    g.addVertex(3);
    g.addVertex(4);
    g.addVertex(5);
    g.addVertex(6);
    g.addVertex(7);
    g.addVertex(8);
    g.addEdge(1,2,1);
    System.out.println("g.degree(1) should be 1, you are getting: " + g.degree(1));
    g.addEdge(2,3,4);
    g.addEdge(2,1,5);


    

    System.out.println("________Testing Remove________");
    g.removeEdge(1,2);
    System.out.println("g.degree(2) should be 0, you are getting: " + g.degree(1));
  }

}
