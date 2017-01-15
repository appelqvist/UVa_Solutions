package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Andréas Appelqvist.
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        new Main();
    }

    public Main() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("src/sample_input"));
        //Scanner sc = new Scanner(System.in);
        Point[] points;
        boolean critical;

        int corners = sc.nextInt();
        while (corners != 0) {
            critical = false;
            points = new Point[corners];
            for (int i = 0; i < corners; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                points[i] = new Point(x, y);
            }

            boolean leftTurn = false;
            if (turn(points[0], points[1], points[2]) >= 0) {
                leftTurn = true;
            }

            for (int i = 0; i < corners; i++) {
                if (!turn(points[i % corners], points[(i + 1) % corners], points[(i + 2) % corners], leftTurn)) {
                    critical = true;
                    break;
                }
            }

            if (critical) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
            corners = sc.nextInt();
        }
    }

    /**
     * Calculate the relation between the points
     * 0 > = left turn
     * 0 < = right turn
     * 0 = no turn
     */
    private int turn(Point p1, Point p2, Point p3) {
        int r = (p3.x - p2.x) * (p1.y - p2.y) - (p3.y - p2.y) * (p1.x - p2.x);
        //System.out.println("Now: " + p1 + ", " + p2 + ", " + p3 + ": " + r);
        return r;
    }

    /**
     * Checks if the turn is ok
     */
    private boolean turn(Point p1, Point p2, Point p3, boolean leftTurned) {
        int r = turn(p1, p2, p3);
        if (leftTurned) {
            return r >= 0;
        } else {
            return r <= 0;
        }
    }

    private class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
}
