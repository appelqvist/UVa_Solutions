package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Andréas Appelqvist.
 */

public class Main {

    public static void main(String[] args) throws FileNotFoundException { new Main(); }

    public Main() throws FileNotFoundException {

        Scanner scanner = new Scanner(new FileInputStream("src/sample_input"));

        int pairs = scanner.nextInt();
        while(pairs != 0){
            double distance = Integer.MAX_VALUE;
            int x,y;
            Point[] points = new Point[pairs];
            for(int i = 0; i < pairs; i++){
                x = scanner.nextInt();
                y = scanner.nextInt();
                points[i] = new Point(x,y);
            }


            for (Point p1 : points) {
                for (Point p2 : points) {
                    if (p1 != p2) {
                        double dist = distance(p1, p2);

                        if (dist < distance) {
                            distance = dist;

                        }
                    }
                }
            }


            if(distance >= 10000){
                System.out.println("INFINITY");
            }else{
                System.out.println(distance);
            }

            pairs = scanner.nextInt();
        }
    }

    private double distance(Point p1, Point p2){
        double x = Math.pow((p2.x - p1.x), 2);
        double y = Math.pow((p1.y - p2.y), 2);

        x = x + y;
        double r = Math.pow(x, 0.5);
        return r;
    }

    private class Point{
        public int x,y;
        public Point(int x, int y){};
    }

}
