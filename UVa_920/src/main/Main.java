package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Andréas Appelqvist.
 */
public class Main  {
    public static void main(String[] args) throws FileNotFoundException { new Main(); }

    public Main() throws FileNotFoundException {
        //Scanner sc = new Scanner(new File("src/sample_input"));
        Scanner sc = new Scanner(System.in);
        int testcase = Integer.parseInt(sc.nextLine());
        for(int i = 0; i < testcase; i++){
            int pairs = Integer.parseInt(sc.nextLine());
            String line;
            String[] linePair;
            Point highest = new Point("-1", "-1");
            ArrayList<Point> allPoints = new ArrayList<>();
            Point p;

            for(int j = 0; j < pairs; j++){
                line = sc.nextLine();
                linePair = line.split(" ");
                p = new Point(linePair[0], linePair[1]);
                allPoints.add(p);
                if(p.y >= highest.y){
                    highest = p;
                }
            }
            Collections.sort(allPoints);

            double sun = 0;
            Point currentHighest = allPoints.get(allPoints.size()-1);
            for(int j = allPoints.size()-2; j >= 0; j--){
                p = allPoints.get(j);

                if(p.y > currentHighest.y){
                    double x, y, angle, hyp;
                    Point rvalley = allPoints.get(j+1);

                    //Calculate the angle beetween the valley right to the new peak.
                    x = rvalley.x - p.x;
                    y = p.y - rvalley.y;
                    angle = Math.atan(x/y);

                    //With the angle, calculate the hyp - the old peak.y (sun touching)
                    y = p.y - currentHighest.y;
                    hyp = y/Math.cos(angle);
                    sun += hyp;
                    currentHighest = p;

                    if(p == highest){
                        break; //no need after the total highest
                    }
                }
            }
            System.out.println(String.format("%.2f", sun));
        }
    }

    private class Point implements Comparable<Point>{
        public int x;
        public int y;
        public Point(String x, String y){
            this.x = Integer.parseInt(x);
            this.y = Integer.parseInt(y);
        }
        public int[] getPoint(){
            int[] coords = {x,y};
            return coords;
        }

        @Override
        public int compareTo(Point p) {
            return this.x - p.x;
        }
        @Override
        public String toString(){
            return "("+x+", "+y+")";
        }
    }
}
