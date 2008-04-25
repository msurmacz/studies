package gif;

import org.jgrapht.graph.*;
import org.jgrapht.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class Poset {
	private DirectedGraph<Integer, DefaultEdge> graph;
	
	public Poset(List<List<Integer>> list) {
		graph = new SimpleDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);

		for (List<Integer> subList : list) {
			Iterator<Integer> it = subList.iterator();
			int source = it.next();
			graph.addVertex(source);
			while (it.hasNext()) {
				int v = it.next();
				graph.addVertex(v);
				graph.addEdge(source, v);
			}
		}
	}
	
	public Set<Integer> getVertices() {
		return graph.vertexSet();
	}
	
	public Set<Integer> getMaxI() {
		Set<Integer> maxI = new TreeSet<Integer>();
		
		for (int v : graph.vertexSet())
			if (graph.outDegreeOf(v) == 0)
				maxI.add(v);
		
		return maxI;
	}

	public Set<Integer> getIMinus() {
		Set<Integer> iMinus = new TreeSet<Integer>(graph.vertexSet());
		iMinus.removeAll(getMaxI());
		
		return iMinus;
	}
	
	/**
	 * Returns set of vertices that are "less then" root (in terms of poset).
	 * <p>
	 * This is kind of Breadth-First Traversal.
	 * 
	 * @param root	where the searching starts
	 * @return	set of vertices less then root
	 */
	public Set<Integer> lessThan(int root) {
		Queue<Integer> q = new LinkedList<Integer>();
		Set<Integer> visited = new HashSet<Integer>();
		
		q.add(root);
		visited.add(root);
		
		while (!q.isEmpty()) {
			Set<DefaultEdge> edges;
			int v = q.remove();
			visited.add(v);
			edges = graph.incomingEdgesOf(v);
			for (DefaultEdge e : edges) {
				int source = graph.getEdgeSource(e);
				if (!visited.contains(source)) {
					q.add(source);
					visited.add(source);
				}
			}
		}
		visited.remove(root);
		
		return new TreeSet<Integer>(visited);
	}
}
