import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Your implementation of 4 different graph algorithms.
 *
 * @author Vaibhav Dedhia ( 903117055)
 * @version 1.0
 */
public class GraphAlgs {



    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param vertexList the vertexlist
     * @param adjMatrix the matrix
     * @param vertexset the set
     * @param dfsList the list of visited vertices in order. This list will be
     */
    public static <T> void dfs(Vertex<T> start, List<Vertex<T>> vertexList,
                               Integer[][] adjMatrix,
                               List<Vertex<T>> dfsList) {
        if (!dfsList.contains(start)) {
            dfsList.add(start);
            List<Vertex<T>> vdpairList = new LinkedList<>();
            for (int i = 0; i < adjMatrix[start.getId()].length; i++) {
                if (adjMatrix[start.getId()][i] != null) {
                    vdpairList.add(vertexList.get(i));
                }
            }
            for (int i = 0; i < vdpairList.size(); i++) {
                dfs(vdpairList.get(i), vertexList, adjMatrix, dfsList);
            }
        }
    }
    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * {@code start} which represents the starting vertex. You will be
     * modifying the empty list passed in to contain the vertices in
     * visited order. The start vertex should be at the beginning of the list
     * and the last vertex visited should be at the end.  (You may assume the
     * list is empty in the beginning).
     *
     * This method should utilize the adjacency matrix represented graph.
     *
     * When deciding which neighbors to visit next from a vertex, visit starting
     * with the vertex at index 0 to the vertex at index |V| - 1. Failure to do
     * so may cause you to lose points.
     *
     * *NOTE* You MUST implement this method recursively, or else you will lose
     * most if not all points for this method.
     *
     * You may import/use {@code java.util.Set}, {@code java.util.Map},
     * {@code java.util.List}, and any classes that implement the
     * aforementioned interfaces, as long as it is efficient.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph in an adjacency matrix format to search
     * @param dfsList the list of visited vertices in order. This list will be
     * empty initially. You will be adding to this list as you perform dfs.
     * @return true if the graph is connected (you were able to reach every
     * vertex and edge from {@code start}), false otherwise
     */
    public static <T> boolean depthFirstSearch(Vertex<T> start,
                                            GraphAdjMatrix<T> graph,
                                            List<Vertex<T>> dfsList) {

        if (start == null || graph == null) {
            throw new IllegalArgumentException("start cannot be null");
        }
        if (start.getId() >= graph.getVertices().size()) {
            throw new IllegalArgumentException("start doesnt exist");
        }

        List<Vertex<T>> vertexList = graph.getVertices();
        Integer[][] adjMatrix = graph.getAdjMatrix();

        dfs(start, vertexList, adjMatrix, dfsList);
        if (graph.getVertices().size() == dfsList.size()) {
            return true;
        }
        return false;
    }



    /**
     * Find the single source shortest distance between the start vertex and
     * all vertices given a weighted graph using Dijkstra's shortest path
     * algorithm.
     *
     * Return a map of the shortest distances such that the key of each entry is
     * a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing infinity)
     * if no path exists. You may assume that going from a vertex to itself
     * has a shortest distance of 0.
     *
     * This method should utilize the adjacency list represented graph.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Map}, and {@code java.util.Set} and any class that
     * implements the aforementioned interfaces.
     *
     * You should implement CLASSIC Dijkstra's, which is the version of the
     * algorithm that terminates once you've "visited" all of the nodes.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if start doesn't exist in the graph.
     * @throws IllegalStateException if any of the edges are negative
     * @param <T> the generic typing of the data
     * @param start index representing which vertex to start at (source)
     * @param graph the Graph we are searching using an adjacency List
     * @return a map of the shortest distances from start to every other node
     *         in the graph
     */
    public static <T> Map<Vertex<T>, Integer> shortPathDijk(Vertex<T> start,
                                                      GraphAdjList<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("Inputs cannot be null");
        }
        if (!graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Start node doesn't exist");
        }

        Map<Vertex<T>, List<Edge<T>>> adjList = graph.getAdjList();
        Map<Vertex<T>, Integer> dijkstra = new HashMap<>();

        PriorityQueue<Edge<T>> pq = new PriorityQueue<>();
        for (Vertex<T> vertex : adjList.keySet()) {
            if (vertex.equals(start)) {
                pq.add(new Edge<>(vertex, vertex, 0));
            } else {
                pq.add(new Edge<>(vertex, vertex, Integer.MAX_VALUE));
            }
        }

        while (!pq.isEmpty()) {
            Edge<T> edge = pq.poll();
            Vertex<T> current = edge.getV();
            int dist = edge.getWeight();
            dijkstra.put(current, dist);

            List<Edge<T>> vdpairs = graph.getAdjList().get(current);
            for (Edge<T> vd :vdpairs) {
                Vertex<T> u = vd.getU();
                Vertex<T> v = vd.getV();
                int w = vd.getWeight();
                if (w < 0) {
                    throw new IllegalStateException("weight"
                            + " cannot be negative");
                }

                //update priority queue with updated weight
                Iterator<Edge<T>> iter = pq.iterator();
                Edge<T> test = null;
                int flag = 0;
                while (iter.hasNext()) {
                    test = iter.next();
                    if (test.getV().equals(v) && test.getWeight() > w
                            + dijkstra.get(current)) {
                        flag = 1;
                        break;
                    }
                }
                if (flag == 1) {
                    pq.remove(test);
                    pq.add(new Edge<>(v, v, w + dijkstra.get(current)));
                }
            }
        }
        return dijkstra;
    }




    /**
     * Run Prim's algorithm on the given graph and return the MST/MSF
     * in the form of a set of Edges.  If the graph is disconnected, and
     * therefore there is no valid MST, return a minimal spanning forest (MSF).
     *
     * This method should utilize the adjacency list represented graph.
     *
     * A minimal spanning forest (MSF) is just a generalized version of the MST
     * for disconnected graphs. After the MST algorithm finishes, just check to
     * see if there are still some vertices that are not connected to the
     * MST/MSF. If all vertices have been visited, you are done. If not, run
     * the algorithm again on an unvisited vertex.
     *
     * You may assume that all of the edge weights are unique (THIS MEANS THAT
     * THE MST/MSF IS UNIQUE FOR THE GRAPH, REGARDLESS OF STARTING VERTEX!!)
     * Although, if your algorithm works correctly, it should work even if the
     * MST/MSF is not unique, this is just for testing purposes.
     *
     * You should not allow for any self-loops in the MST/MSF. Additionally,
     * you may assume that the graph is undirected.
     *
     * You may import/use {@code java.util.PriorityQueue} and
     * {@code java.util.Set} and any class that
     * implements the aforementioned interfaces.
     *
     * @throws IllegalArgumentException if any input is null
     * @param <T> the generic typing of the data
     * @param graph the Graph we are searching using an adjacency list
     * @return the MST/MSF of the graph
     */
    public static <T> Set<Edge<T>> mstPrim(GraphAdjList<T> graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Inputs cannot be null");
        }

        Map<Vertex<T>, List<Edge<T>>> adjList = graph.getAdjList();
        Map<Vertex<T>, Edge<T>> mapping = new HashMap<>();
        Set<Edge<T>> mst = new HashSet<>();
        PriorityQueue<Edge<T>> pq = new PriorityQueue<>();

        Iterator<Vertex<T>> itr = graph.getVertices().iterator();
        while (itr.hasNext()) {
            Vertex<T> vert  = itr.next();
            pq.add(new Edge<>(vert, vert, Integer.MAX_VALUE));
        }

        while (!pq.isEmpty()) {
            Edge<T> current = pq.poll();
            if (current.getWeight() != Integer.MAX_VALUE) {
                Edge<T> result = mapping.get(current.getV());
                mst.add(result);
                mst.add(new Edge<>(result.getV(),
                        result.getU(), result.getWeight()));
            }
            Vertex<T> currentVertex = current.getU();
            List<Edge<T>> edges = adjList.get(currentVertex);
            for (Edge<T> edge : edges) {
                Vertex<T> u = edge.getU();
                Vertex<T> v = edge.getV();
                int w = edge.getWeight();

                //update priority queue with updated weight
                Iterator<Edge<T>> iter = pq.iterator();
                Edge<T> test = null;
                int flag = 0;
                while (iter.hasNext()) {
                    test = iter.next();
                    if (test.getV().equals(v) && test.getWeight() > w) {
                        flag = 1;
                        break;
                    }
                }
                if (flag == 1) {
                    pq.remove(test);
                    pq.add(new Edge<>(v, v, w));
                    mapping.put(v, edge);
                }
            }
        }
        return mst;
    }



    /**
     * Run Kruskal's algorithm on the given graph and return the MST/MSF
     * in the form of a set of Edges.  If the graph is disconnected, and
     * therefore there is no valid MST, return a minimal spanning forest (MSF).
     *
     * This method should utilize the adjacency list represented graph.
     *
     * A minimal spanning forest (MSF) is just a generalized version of the MST
     * for disconnected graphs. Unlike Prim's algorithm, Kruskal's algorithm
     * will naturally return a MSF if the graph is disconnected.
     *
     * You may assume that all of the edge weights are unique (THIS MEANS THAT
     * THE MST/MSF IS UNIQUE FOR THE GRAPH.) Although, if your algorithm works
     * correctly, it should work even if the MST/MSF is not unique, this is
     * just for testing purposes.
     *
     * You should not allow for any self-loops in the MST/MSF. Additionally,
     * you may assume that the graph is undirected.
     *
     * Kruskal's will also require you to use a Disjoint Set which has been
     * provided for you.  A Disjoint Set will keep track of which vertices are
     * connected to each other by the edges you've chosen for your MST/MSF.
     * Without a Disjoint Set, it is possible for Kruskal's to omit edges that
     * should be in the final MST/MSF.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Set}, and any class that implements the aforementioned
     * interface.
     *
     * @throws IllegalArgumentException if any input is null
     * @param <T> the generic typing of the data
     * @param graph the Graph we are searching using an adjacency list
     * @return the MST/MSF of the graph
     */
    public static <T> Set<Edge<T>> mstKruskal(GraphAdjList<T> graph) {
        if (graph == null) {
            throw new IllegalArgumentException("graph cannot be null");
        }

        Set<Vertex<T>> vertices = graph.getVertices();
        DisjointSet<Vertex<T>> disjointSet = new DisjointSet<>(vertices);

        PriorityQueue<Edge<T>> pq = new PriorityQueue<>();
        Iterator<Edge<T>> itr = graph.getEdges().iterator();

        while (itr.hasNext()) {
            Edge<T> edge = itr.next();
            pq.add(edge);
        }

        Set<Edge<T>> mst = new HashSet<>();

        while (!pq.isEmpty()) {
            Edge<T> edge = pq.poll();
            if (disjointSet.find(edge.getU())
                    != disjointSet.find(edge.getV())) {
                mst.add(edge);
                Vertex<T> u = edge.getU();
                Vertex<T> v = edge.getV();
                int w = edge.getWeight();
                mst.add(new Edge<>(v, u, w));
                disjointSet.union(edge.getU(), edge.getV());
            }
        }

        return mst;
    }
}