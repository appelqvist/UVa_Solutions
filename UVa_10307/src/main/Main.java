package main;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by Andréas Appelqvist on 2017-01-17.
 */
public class Main {

    public enum Enum {
        WALL, FLOOR, ALIEN, START
    }

    private Node[][] maze;
    private Node start;
    private ArrayList<Node> allAlliens = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new Main().program();
    }

    public void program() throws IOException {
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/sample_input")));
        String input = br.readLine();
        int testcases = Integer.parseInt(input);
        for (int i = 0; i < testcases; i++) {
            int col, row;
            input = br.readLine();
            String[] splitted = input.split(" ");
            col = Integer.parseInt(splitted[0]);
            row = Integer.parseInt(splitted[1]);
            System.out.println("cols: " + col + ", rows: " + row);
            maze = new Node[row][col];
            Node n;
            for (int j = 0; j < maze.length; j++) {
                char[] inputrow = br.readLine().toCharArray();
                for (int k = 0; k < maze[j].length; k++) {
                    n = new Node(Enum.WALL);
                    n.mazeIndex = new int[]{j, k};
                    if (k < inputrow.length) {
                        char c = inputrow[k];
                        if (c == 'A') {
                            n.type = Enum.ALIEN;
                            allAlliens.add(n);
                        } else if (c == 'S') {
                            n.type = Enum.START;
                            start = n;
                        } else if (c == ' ') {
                            n.type = Enum.FLOOR;
                        }
                    }
                    maze[j][k] = n;
                }
            }

            for (int j = 1; j < maze.length - 1; j++) {
                for (int k = 1; k < maze[j].length - 1; k++) {
                    if (maze[j][k].type != Enum.WALL) { //If current node isnt a wall
                        if (maze[j - 1][k].type != Enum.WALL) { //Look up and if it is a not wall there make neighbor.
                            maze[j][k].neighbors.add(maze[j - 1][k]);
                        }
                        if (maze[j][k + 1].type != Enum.WALL) { //Look left
                            maze[j][k].neighbors.add(maze[j][k + 1]);
                        }
                        if (maze[j][k - 1].type != Enum.WALL) { //Look right
                            maze[j][k].neighbors.add(maze[j][k - 1]);
                        }
                        if (maze[j + 1][k].type != Enum.WALL) { //Look down
                            maze[j][k].neighbors.add(maze[j + 1][k]);
                        }
                    }
                }
            }

            for (int j = 0; j < maze.length; j++) {
                for (int k = 0; k < maze[j].length; k++) {
                    System.out.print(maze[j][k] + " ");
                }
                System.out.println();
            }

            runBFS();
        }
    }

    private void runBFS() {
        allAlliens.add(start);
        int reverser = 1; //So i dont need to loop and set all unvisited.
        PriorityQueue<NodeCost> Q = new PriorityQueue<>();
        for(int i = 0; i < allAlliens.size(); i++){
            Node begin = allAlliens.get(i);
            System.out.println("ny: "+begin);
            Q.offer(new NodeCost(begin,0));
            boolean firstRound = true;
            while(!Q.isEmpty()){
                NodeCost nc = Q.poll();
                int currentCost = nc.cost;
                Node node = nc.n;
                node.visited *= -1;

                if (!firstRound && (node.type == Enum.ALIEN || node.type == Enum.START)){
                    node.totalCost = currentCost;
                    System.out.println("from: "+begin+", to:"+node+", cost:"+node.totalCost);
                }

                for(int j = 0; j < node.neighbors.size(); j++){
                    if(node.neighbors.get(j).visited * reverser < 0){
                        Q.offer(new NodeCost(node.neighbors.get(j), currentCost+1));
                    }
                }
                firstRound = false;
            }
        reverser *= -1;
        }
    }

    public class NodeCost implements Comparable<NodeCost>{
        Node n;
        int cost;
        public NodeCost(Node n, int cost){this.n = n; this.cost = cost;}

        @Override
        public int compareTo(NodeCost o) {
            return cost - o.cost;
        }
    }

    public class Node{
        Enum type;
        int totalCost;
        ArrayList<Node> neighbors = new ArrayList<>();
        int visited = -1; //1 yes, -1 no
        int[] mazeIndex = new int[2];

        public Node(Enum type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "[" + mazeIndex[0]+","+mazeIndex[1]+ "]";
        }
    }

}
