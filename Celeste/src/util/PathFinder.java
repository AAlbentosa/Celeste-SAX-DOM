package util;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class PathFinder {
	
		public PathFinder() {}
		
		public List<String>  createGraph(String[][] pantalla, String player) {
				Graph<String, DefaultEdge> screenGraph = new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
				String finish, start, object;
				
				addVertex(pantalla, screenGraph);
				addEdges(screenGraph);
				
				finish=findPosition("S", pantalla);
				start=findPosition("X", pantalla);
				object=findPosition("o", pantalla);
				
				DijkstraShortestPath<String, DefaultEdge> dijkstraAlg = new DijkstraShortestPath<>(screenGraph);
	        
				GraphPath<String, DefaultEdge> objectPath = dijkstraAlg.getPaths(start).getPath(object);
				GraphPath<String, DefaultEdge> exitPath = dijkstraAlg.getPaths(object).getPath(finish);
	        
				if(objectPath!=null && exitPath!=null)
	        			return getPath(objectPath, exitPath, object);
				
	        	System.out.println("The screen of "+player+" cannot be resolved");
	        	return new ArrayList<String>();
		}
	

		private String findPosition(String key, String[][] screen) {
				for(int x=0; x<screen.length; x++) {
						for(int y=0; y<screen[x].length; y++) {
								if(screen[x][y].equals(key))
										return x+"-"+y;
						}
				}
				return null;
		}

		private void addVertex(String[][] pantalla, Graph<String, DefaultEdge> screenGraph) {
				for(int x=0; x<pantalla.length; x++) {
						for(int y=0; y<pantalla[x].length; y++) {
								if(!pantalla[x][y].equals("."))
										screenGraph.addVertex(x+"-"+y);
						}		
				}
		}
		
		private void addEdges(Graph<String, DefaultEdge> screenGraph) {
				for(String vertexA: screenGraph.vertexSet()) {
						for(String vertexB:screenGraph.vertexSet()) {
								if(existEdge(vertexA, vertexB))
										screenGraph.addEdge(vertexA, vertexB);
						}
				}
		}
		
		private List<String> getPath(GraphPath<String, DefaultEdge> objectPath, GraphPath<String, DefaultEdge> exitPath, String object) {
				List<String> finalPath = new ArrayList<String>(objectPath.getVertexList());
				finalPath.addAll(exitPath.getVertexList());
				finalPath.remove(object);
				return finalPath;
		}

		private boolean existEdge(String vertexA, String vertexB) {
				String[] x=vertexA.split("-");
				String[] y=vertexB.split("-");
				return Math.abs(Integer.parseInt(x[0])-Integer.parseInt(y[0]))+Math.abs(Integer.parseInt(x[1])-Integer.parseInt(y[1]))==1?true:false;
        }
}
