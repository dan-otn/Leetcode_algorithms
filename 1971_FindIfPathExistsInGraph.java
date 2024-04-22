class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        int num_of_edges = edges.length;
        if (num_of_edges == 0) {
            return true;
        }

        // create graph hashmap
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>();

        for (int iterator = 0; iterator < num_of_edges; iterator++) {
            if (!graph.containsKey(edges[iterator][0])) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                graph.put(edges[iterator][0], list);
            }    
            graph.get(edges[iterator][0]).add(edges[iterator][1]);

            if (!graph.containsKey(edges[iterator][1])) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                graph.put(edges[iterator][1], list);
            }    
            graph.get(edges[iterator][1]).add(edges[iterator][0]);
            
        }

        // BFS, starting from source
        ArrayDeque<Integer> bfs_deque = new ArrayDeque<Integer>();
        HashSet<Integer> visited = new HashSet<Integer>();

        bfs_deque.add(source);
        while(!bfs_deque.isEmpty()) {
            Integer current_vertex = bfs_deque.pop();
            ArrayList<Integer> current_vertex_neighbours = graph.get(current_vertex);
            for (int vertex : current_vertex_neighbours) {
                if (vertex == destination) {
                    return true;
                }
                if (!visited.contains(vertex)) {
                    visited.add(vertex);
                    bfs_deque.addLast(vertex);
                }
            }
        }
        return false;
    }
}