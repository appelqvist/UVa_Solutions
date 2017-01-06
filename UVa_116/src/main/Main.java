package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Andreas Appelqvist.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {new Main();}

    private Node[][] nodes;
    public Main() throws FileNotFoundException {
        //Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new File("src/sample_input"));
        int row, col;
        while (sc.hasNext()) {
            row = sc.nextInt();
            col = sc.nextInt();
            this.nodes = new Node[row][col];
            for (int i = 0; i < this.nodes.length; i++) {
                for (int j = 0; j < this.nodes[i].length; j++) {
                    this.nodes[i][j] = new Node(sc.nextInt());
                }
            }

            for (int j = col - 2; j >= 0; j--) {
                for (int i = 0; i < row; i++) {
                    int posOfUp = (row + i - 1) % row;
                    int posOfDown = (row + i + 1) % row;
                    int posOfMid = i;
                    int[] positions = {posOfUp, posOfMid, posOfDown};
                    Arrays.sort(positions);
                    int minValue = Integer.MAX_VALUE;
                    int minPos = -1;
                    for (int pos : positions) {
                        if(nodes[pos][j+1].value <  minValue){
                            minValue = nodes[pos][j+1].value;
                            minPos = pos;
                        }
                    }
                    nodes[i][j].value += nodes[minPos][j+1].value;
                    nodes[i][j].next = minPos;
                }
            }
            int pos = -1;
            int value = Integer.MAX_VALUE;
            for (int i = 0; i < nodes.length; i++) {
                if (nodes[i][0].value < value) {
                    pos = i;
                    value = nodes[i][0].value;
                }
            }
            System.out.println(getPath("", pos, 0));
            System.out.println(nodes[pos][0].value);
        }
    }


    private String getPath(String str, int next, int currentCol) {
        if (next == -1) {
            return str;
        } else {
            if (str.length() > 0) {
                str += " ";
            }
            return getPath(str + (next + 1), nodes[next][currentCol].next, currentCol + 1);
        }
    }

    private class Node {
        public int value;
        public int next;

        public Node(int value) {
            this.value = value;
            this.next = -1;
        }
    }
}