package graph;

public class Edge{

	protected int weight;
	public VertexPair vertexPair;

	/**
	*	Edge() constructor takes three paramaeters fv - first vertex, sv - second vertex, and wt - weight. 
	**/

	public Edge(Object fv, Object sv, int wt){
		this.vertexPair = new VertexPair(fv,sv);
		this.weight = wt;
	}

	/**
	*	gets the weight for you and returns the int. 
	**/
	public int weight(){
		return this.weight;
	}

	/**
	*	vertexPair() returns a vertexPair that corresponds to the vertexPair field. 
	**/
	public VertexPair vertexPair(){
		return this.vertexPair;
	}

	/**
	*	toString() is a simple method that converts the object to a String for debugging purposes. 
	**/
	public String toString(){
		String result = "{ ";
		result = result + vertexPair.toString() + " }";
		return result;
	}


	/**
	*	equals() tests to see if a Edge is equal to another edge. 
	**/
	public boolean equals(Edge compEdge){
		if (this.weight == compEdge.weight()){
			if (vertexPair.equals(compEdge.vertexPair())){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
}