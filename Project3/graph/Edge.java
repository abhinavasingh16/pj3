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
	*	gets the weight for you. 
	**/
	public int weight(){
		return this.weight;
	}

	public VertexPair vertexPair(){
		return this.vertexPair;
	}

	public String toString(){
		String result = "{ ";
		result = result + vertexPair.toString() + " }";
		return result;
	}

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

	public static void main(String[] args){
		Edge newEdge = new Edge(1,2,3);
		Edge testEdge = new Edge(3,4,5);
		Edge testEdge2 = new Edge(1,2,3);
		Edge testEdge3 = new Edge(1,2,4);

		System.out.println("newEdge.equals(testEdge) should return false, you are getting: " + newEdge.equals(testEdge));
		System.out.println("newEdge.equals(testEdge2) should return true, you are getting: " + newEdge.equals(testEdge2));
		System.out.println("newEdge.equals(testEdge3) should return false, you are getting: " + newEdge.equals(testEdge3));
	}
}