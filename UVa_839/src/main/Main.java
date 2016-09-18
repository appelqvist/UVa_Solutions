package main;

import java.io.*;

/**
 * Created by Andréas Appelqvist on 2016-09-18.
 * UVa Online Judge 839
 */

public class Main {
    static private BufferedReader reader;

    public static void main(String[] args) {
        try {
            //reader = new BufferedReader(new InputStreamReader(System.in));
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/sample_input")));

            int nbrOfCases = Integer.parseInt(reader.readLine());
            for(int i = 0; i < nbrOfCases; i++ ){
                reader.readLine(); //blank line

                BT fullTree = generateBT();
                if(fullTree.equilibrium()){
                    System.out.println("YES");
                }
                else{
                    System.out.println("NO");
                }

                if(i+1 != nbrOfCases){
                    System.out.println();
                }
            }
        } catch (FileNotFoundException e ) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BT generateBT() throws IOException {
        String[] line;
        int weightLeft, weightRight, distanceLeft, distanceRight;

        BT subtree = new BT();
        line = (reader.readLine().split(" "));
        weightLeft = Integer.parseInt(line[0]);
        distanceLeft = Integer.parseInt(line[1]);
        weightRight = Integer.parseInt(line[2]);
        distanceRight = Integer.parseInt(line[3]);

        //Recursively building up the tree
        subtree.setDistanceLeft(distanceLeft);
        subtree.setDistanceRight(distanceRight);

        if(weightLeft == 0){ //Has a left subtree
            subtree.setLeftSubTree(generateBT());
        }else{
            subtree.setWeightLeft(weightLeft);
        }

        if(weightRight == 0){ //Has a right subtree
            subtree.setRightSubTree(generateBT());
        }else{
            subtree.setWeightRight(weightRight);
        }

        return subtree;
    }
}
