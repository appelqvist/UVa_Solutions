package main;

import java.io.*;
import java.util.*;

/**
 * Created by Andréas Appelqvist.
 */

public class Main {

    public static void main(String[] args) throws IOException { new Main().go(); }

    public void go() throws IOException {
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("src/sample_input")));

        while (true) {
            int pairs = Integer.parseInt(in.readLine());
            if(pairs == 0){
                break;
            }
            double x,y;
            Point[] points = new Point[pairs];
            for(int i = 0; i < pairs; i++){
                String[] row = in.readLine().split(" ");
                x = Double.parseDouble(row[0]);
                y = Double.parseDouble(row[1]);
                points[i] = new Point(x,y);
            }

            double distance = sweepline(points);
            print(distance);

        }
    }

    private double bruteforce(Point[] points){
        double minDist = 40000;

        for (Point p1 : points) {
            for (Point p2 : points) {
                if (p1 != p2) {
                    double distance = distance(p1, p2);

                    if (distance < minDist) {
                        minDist = distance;
                    }
                }
            }
        }

        return minDist;
    }

    public void print(double distance){
        if(distance >= 10000){
            System.out.println("INFINITY");
        }else{
            String result = String.format("%.4f", distance);
            String newresult = result.replace(',', '.');
            System.out.println(newresult);
        }
    }

    public double sweepline(Point[] points){
        double minDistance = 40000;
        Arrays.sort(points, X_COMPARATOR);
        SortedSet<Point> candidates = new TreeSet<>(Y_COMPARATOR);

        int indexOfRangeLeft = 0;
        for (Point current : points){

            while (current.x - points[indexOfRangeLeft].x > minDistance){
                candidates.remove(points[indexOfRangeLeft]);
                indexOfRangeLeft++; //moving pointer one step to the right. And that point is now the furthest to the left.
            }

            Point bottomLimit = new Point(current.x, (current.y - minDistance));     //Filter down the y-axis.
            Point topLimit = new Point(current.x, (current.y + minDistance));

            for(Point p : candidates.subSet(bottomLimit, topLimit)){
                if(!p.equals(current)){ //So we are not testing with our self and get dist 0
                    double newDist = distance(current, p);
                    if(minDistance > newDist){
                        minDistance = newDist;
                    }
                }
            }
            candidates.add(current);
        }

        return minDistance;
    }

    public double distance(Point p1, Point p2){
        double dx = Math.pow((p2.x - p1.x), 2);
        double dy = Math.pow((p1.y - p2.y), 2);

        double res = dx + dy;
        res = Math.pow(res, 0.5);
        return res;
    }

    public class Point {
        public double x,y;
        public Point(double x, double y){this.x = x; this.y = y;};
        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    //Use this comparator for sorting by the x-coords
    public static final Comparator<Point> X_COMPARATOR = new Comparator<Point>() {
        @Override
        public int compare(Point p1, Point p2) {
            if (p1.x != p2.x) {
                return (int)(p1.x - p2.x);
            }
            if (p1.y != p2.y) {
                return (int)(p1.y - p2.y);
            }
            return 0;
        }
    };

    //And this for sorting by the y-coord.
    public static final Comparator<Point> Y_COMPARATOR = new Comparator<Point>() {
        @Override
        public int compare(Point p1, Point p2) {
            if(p1.y != p2.y){
                return (int)(p1.y - p2.y);
            }
            if(p1.x != p2.x){
                return (int)(p1.x - p2.x);
            }
            return 0;
        }
    };
}
