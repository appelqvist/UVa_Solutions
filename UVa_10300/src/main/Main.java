package main;

import java.io.*;
import java.util.LinkedList;

/**
 * Created by Andréas Appelqvist on 2016-09-02.
 *
 *  UVa Online Judge Problem:
 *  10300 Ecological Premium
 *  https://uva.onlinejudge.org/external/103/10300.pdf
 *
 */

public class Main {
    public static void main(String[] args) {

        LinkedList<Integer> input = new LinkedList<>();
        try {
            //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader br = new BufferedReader(new FileReader("src/sample_input"));

            String line = "";
            String split[];
            while((line = br.readLine()) != null) {
                if (line.length() > 1) {
                    split = line.split(" ");
                    for (int i = 0; i < split.length; i++)
                        input.addLast(Integer.parseInt(split[i]));
                } else {
                    input.addLast(Integer.parseInt(line));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        int testcases = input.removeFirst();
        int farmers;
        int sum = 0;

        int farmSize = 0;
        int animals = 0;
        int enviroment = 0;

        for(int i = 0; i < testcases; i++){
            farmers = input.removeFirst();
            for(int j = 0; j < farmers; j++ ){
                farmSize = input.removeFirst();
                animals = input.removeFirst();
                enviroment = input.removeFirst();

                //animals not needed
                sum = sum+farmSize*enviroment;
            }
            System.out.println(sum);
            sum = 0;
        }
    }
}
