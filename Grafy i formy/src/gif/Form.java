package gif;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.*;
import org.jgrapht.alg.ConnectivityInspector;

public class Form {
	private SimpleWeightedGraph<Integer, DefaultWeightedEdge> graph; //DefaultWeightedGraph?
	private ConnectivityInspector<Integer, DefaultWeightedEdge> ci;

	public Form(PlainStruct ps) {
		graph = new SimpleWeightedGraph<Integer, DefaultWeightedEdge>(
				DefaultWeightedEdge.class);
		for (int i = 1; i <= ps.getCoeffNum(); i++)
			graph.addVertex(i);
		for (int[] a : ps.getCoeffs())
			graph.setEdgeWeight(graph.addEdge(a[0], a[1]), a[2]);
		ci = new ConnectivityInspector<Integer, DefaultWeightedEdge>(graph);
	}

	public Form(Poset p) {
		graph = new SimpleWeightedGraph<Integer, DefaultWeightedEdge>(
				DefaultWeightedEdge.class);
		
		
		
		ci = new ConnectivityInspector<Integer, DefaultWeightedEdge>(graph);
	}
	
	public String toString() {
		if (graph.vertexSet().isEmpty())
			return "Forma jest pusta (???). To nie powinno siê zda¿yæ.";

		return toString(graph);
	}

	public double[][] getGramMatrix() {
		int vertNum = graph.vertexSet().size();
		double[][] gram = new double[vertNum][vertNum];
		Set<DefaultWeightedEdge> edges = graph.edgeSet();
		
		for(DefaultWeightedEdge e : edges) {
			int i = graph.getEdgeSource(e) - 1;
			int j = graph.getEdgeTarget(e) - 1;
			double w = graph.getEdgeWeight(e) / 2;
			gram[i][j] = w;
			gram[j][i] = w;
		}
		
		for (int i = 0; i < gram.length; i++)
			gram[i][i] = 1;			
		
		return gram;		
	}
	
	public String printIsConnected() {
		
		return isConnected() ? "Forma jest spójna." : "Forma nie jest spójna.";
	}
	
	public List<String> getComponentStrings() {
		List<String> s = new ArrayList<String>();
		List<Set<Integer>> cVerticesList = ci.connectedSets();
		UndirectedWeightedSubgraph<Integer, DefaultWeightedEdge> subg;

		for (Set<Integer> verticesSet : cVerticesList) {
			subg = new UndirectedWeightedSubgraph<Integer, DefaultWeightedEdge>(
					graph, verticesSet, null);
			s.add(toString(subg));
		}
		return s;		
	}
	
	public boolean isConnected() {
				
		return ci.isGraphConnected();
	}
	
	private String toString(WeightedGraph<Integer, DefaultWeightedEdge> g) {
		StringBuilder s = new StringBuilder();
		Set<DefaultWeightedEdge> edges = g.edgeSet();
		SortedSet<Integer> vertices = new TreeSet<Integer>(g.vertexSet());
		
//		for (int i = 1; i <= g.vertexSet().size(); i++)
//			s.append("x["+ i +"]^2 + ");
		for (int i : vertices)
			s.append("x["+ i +"]^2 + ");
		s.setLength(s.length()-2);
		
		for (DefaultWeightedEdge e : edges) {
			//wyœwietla wagê
			int w = (int)g.getEdgeWeight(e);
			if (w == -1)
				s.append("-");
			else if (w == 1)
				s.append("+");
			else if (w < 0)
				s.append(w);
			else
				s.append("+" + w);
			//wyœwietla wspó³czynniki
			s.append("x[");
			s.append(Math.min(g.getEdgeSource(e), g.getEdgeTarget(e)));
			s.append("]x[");
			s.append(Math.max(g.getEdgeSource(e), g.getEdgeTarget(e)));
			s.append("] ");						
		}
		return s.toString();
	}
	 
}
