package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Andréas Appelqvist on 5/14/17.
 */
public class Main {

    public enum type {
        WALL, FLOOR, ALIEN, START
    }

    public static void main(String[] args) {
        try {
            new Main().program();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void program() throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/sample_input")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int testCases = Integer.parseInt(input);
        int mazeWidth;
        int mazeHeight;
        Node[][] maze;
        for (int i = 0; i < testCases; i++) {
            //Building the maze
            input = br.readLine();
            mazeWidth = Integer.parseInt(input.split(" ")[0]);
            mazeHeight = Integer.parseInt(input.split(" ")[1]);

            maze = new Node[mazeHeight][mazeWidth];
            char c;
            Stack<Node> aliens = new Stack<>();
            ArrayList<Node> allNodes = new ArrayList<>();
            Node start = null;
            Node n;

            for (int j = 0; j < maze.length; j++) {
                char[] inputrow = br.readLine().toCharArray();
                for (int k = 0; k < maze[j].length; k++) {
                    n = new Node(type.WALL, j, k);
                    if (k < inputrow.length) {
                        c = inputrow[k];
                        if (c == 'A') {
                            n.type = type.ALIEN;
                            aliens.push(n);
                        } else if (c == 'S') {
                            n.type = type.START;
                            start = n;
                        } else if (c == ' ') {
                            n.type = type.FLOOR;
                        }
                    }
                    allNodes.add(n);
                    maze[j][k] = n;
                }
            }

            //Construct a graph
            //Nodes with neighbours
            for (int k = 0; k < maze.length; k++) {
                for (int l = 0; l < maze[k].length; l++) {
                    n = maze[k][l];
                    if (n.type != type.WALL) {
                        //Look up if not wall
                        if (k - 1 >= 0 && maze[k - 1][l].type != type.WALL) {
                            makeNeighbours(n, maze[k - 1][l]);
                        }
                        //Look left if not wall
                        if (l - 1 >= 0 && maze[k][l - 1].type != type.WALL) {
                            makeNeighbours(n, maze[k][l - 1]);
                        }
                    }
                }
            }

            //See start position as a alien.
            Node[] allAliens = new Node[aliens.size() + 1];
            allAliens[0] = start;
            if(start != null)
                allAliens[0].pos = 0;
            for (int j = 1; j < allAliens.length; j++) {
                allAliens[j] = aliens.pop();
                allAliens[j].pos = j;
            }

            ArrayList<Edge> edges = new ArrayList<>();
            //BFS for every alien and start in the graph.
            //Put them in a list.
            for (Node from : allAliens) {
                for (Node to : allAliens) {
                    if (from != to) {
                        for (Node n1 : allNodes) {       //Set all nodes as unvisited
                            n1.visited = false;
                            n1.level = 0;
                        }
                        LinkedList<Node> queue = new LinkedList<>();
                        queue.add(from);
                        from.visited = true;
                        while (!queue.isEmpty()) {
                            //System.out.println(queue);
                            Node node = queue.removeFirst();
                            if (node == to) {
                                edges.add(new Edge(from, to, node.level));
                                //System.out.println("From: " + from + " to: " + to + " costs:" + node.level);
                                break;
                            }
                            for (Node neighbour : node.neighbours) {
                                if (!neighbour.visited) {
                                    neighbour.level = node.level + 1;
                                    neighbour.visited = true;
                                    queue.addLast(neighbour);
                                }
                            }
                        }
                    }
                }
            }

            //Kruskals alogritm to find MST.
            LinkedList<Edge> MST = kruskalsMST(allAliens, edges);
            int sum = 0;
            for(Edge e : MST){
                sum += e.weight;
            }
            System.out.println(sum);
        }
    }


    public LinkedList<Edge> kruskalsMST(Node[] allAliens, ArrayList<Edge> edges){
        LinkedList<Edge> MST = new LinkedList<>();
        Collections.sort(edges);
        DisjointSet disjointSet = new DisjointSet(allAliens.length);

        for(Edge e : edges){
            if(disjointSet.find(e.from.pos) != disjointSet.find(e.to.pos)){
                disjointSet.union(e.from.pos,e.to.pos);
                MST.add(e);
            }
        }
        return MST;
    }

    private class DisjointSet{
        private int[] parent;
        private int n;

        public DisjointSet(int n){
            parent = new int[n];
            this.n = n;
            initSet();
        }

        private void initSet(){
            for(int i = 0; i < n; i++){
                parent[i] = i;
            }
        }

        public int find(int x){
            if(parent[x] != x){
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y){
            int xr = find(x);
            int yr = find(y);

            //Do nothing
            if(xr == yr){
                return;
            }else{ //give y root x root
                parent[yr] = xr;
            }

        }
    }

    private class Edge implements Comparable<Edge> {
        public int weight;
        public Node from;
        public Node to;

        public Edge(Node from, Node to, int weight) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.weight - edge.weight;
        }

        public String toString() {
            return "from: " + from + " to: " + to + " costs:" + weight;
        }
    }

    public void makeNeighbours(Node n, Node m) {
        n.addNeighbours(m);
        m.addNeighbours(n);
    }

    private class Node {
        public type type;
        public LinkedList<Node> neighbours;
        public boolean visited;
        public int level = 0;
        public int[] mazeIndex;
        public int pos;

        public Node(type type, int x, int y) {
            this.type = type;
            this.neighbours = new LinkedList<>();
            visited = false;
            mazeIndex = new int[2];
            mazeIndex[0] = x;
            mazeIndex[1] = y;
        }

        public void addNeighbours(Node n) {
            neighbours.add(n);
        }

        @Override
        public String toString() {
            //return "[" + mazeIndex[0] + ", " + mazeIndex[1] + "]";
            return "["+type.toString().charAt(0)+"] ";
        }
    }
}
