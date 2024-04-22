// you need to import everything you use, it’s just already done in the leetcode
//in IDE this is automatically suggested by the environment

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        // variables are usually written in the camelCase
        int numOfEdges = edges.length;
        if (numOfEdges == 0) {
            return true;
        }

        // create graph hashmap
        /*

        the declaration usually uses interfaces
        Map<>, List<>, Set<>

         */
        Map<Integer, List<Integer>> graph = new HashMap<>();

        /*
        for (int iterator = 0; iterator < numOfEdges; iterator++) {
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

        ------ >

        // 1st step, for-each cycle
        for (int[] edge : edges) {
            if (!graph.containsKey(edge[0])) {
                List<Integer> list = new ArrayList<>();
                graph.put(edge[0], list);
            }
            graph.get(edge[0]).add(edge[1]);

            if (!graph.containsKey(edge[1])) {
                List<Integer> list = new ArrayList<>();
                graph.put(edge[1], list);
            }
            graph.get(edge[1]).add(edge[0]);

        }

        ------ >

         */

        // 2nd step, absolutely the same cycle, just a little simplified
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], parameter -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], parameter -> new ArrayList<>()).add(edge[0]);
        }

        // BFS, starting from source
        Deque<Integer> bfsDeque = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();

        bfsDeque.add(source);
        while (!bfsDeque.isEmpty()) {
            Integer currentVertex = bfsDeque.pop();
            List<Integer> currentVertexNeighbours = graph.get(currentVertex);

            for (int vertex : currentVertexNeighbours) {
                if (vertex == destination) {
                    return true;
                }
                if (!visited.contains(vertex)) {
                    visited.add(vertex);
                    bfsDeque.addLast(vertex);
                }
            }
        }
        return false;
    }
}