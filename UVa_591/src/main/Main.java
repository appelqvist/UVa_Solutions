package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * Created by Andréas Appelqvist on 2016-09-02.
 *
 * UVa Online Judge Problem:
 * 591 Box of Bricks
 * https://uva.onlinejudge.org/external/5/591.pdf
 */

public class Main {
    public static void main(String[] args) {
        try {
            //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader br = new BufferedReader(new FileReader("src/sample_input"));

            int setCount = 0;
            String line;
            while((line = br.readLine()) != null){
                setCount++;

                int nbrOfStacks = Integer.parseInt(line);

                if(nbrOfStacks != 0){

                    System.out.println("Set #"+setCount);
                    line = br.readLine();

                    String[] str = line.split(" ");
                    int[] arr = new int[str.length];
                    int nbrOfMoves = 0;

                    for(int i = 0; i < arr.length; i++){
                        arr[i] = Integer.parseInt(str[i]);
                    }

                    int lowest;
                    int highest;
                    int posOfLowest;
                    int posOfHighest;

                    while(!isEven(arr)){

                        lowest = Integer.MAX_VALUE;
                        highest = Integer.MIN_VALUE;
                        posOfLowest = 0;
                        posOfHighest = 0;

                        for(int i = 0; i < arr.length; i++){
                            if(arr[i] > highest){
                                highest = arr[i];
                                posOfHighest = i;
                            }
                            if(arr[i] < lowest){
                                lowest = arr[i];
                                posOfLowest = i;
                            }
                        }

                        arr[posOfHighest]--;
                        arr[posOfLowest]++;
                        nbrOfMoves++;
                    }

                    System.out.println("The minimum number of moves is "+nbrOfMoves+".");
                    System.out.println();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static boolean isEven(int[] arr){
        int compareVal = arr[0];
        int count = 1;
        while(count < arr.length){
            if(compareVal != arr[count])
                return false;
            count++;
        }
        return true;
    }
}
