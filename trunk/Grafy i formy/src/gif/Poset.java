package gif;

import org.jgrapht.graph.*;
import org.jgrapht.*;

import java.util.Iterator;
import java.util.List;

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

}
