package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by Andréas Appelqvist on 09/09/16.
 * UVa Online Judge Problem:
 * 10878 Decode the tape
 * https://uva.onlinejudge.org/external/108/10878.pdf
 *
 */
public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> asciiValues = new LinkedList<Integer>();
        try{
            //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader br = new BufferedReader(new FileReader("src/sample_input"));

            String line = "";
            int lineSum = 0;
            int pow = 7;

            while((line = br.readLine()) != null){
                if(!line.contains("_")){
                    for(int i = 1; i < line.length()-1; i++){
                        if(line.charAt(i) == 'o'){
                            lineSum += Math.pow(2, pow);
                            pow--;
                        }else if(line.charAt(i) == ' '){
                            pow--;
                        }
                    }
                    asciiValues.addLast(lineSum);
                    //reset for next line
                    lineSum = 0;
                    pow = 7;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        int ascii;
        while(!asciiValues.isEmpty()){
            ascii = asciiValues.removeFirst();
            System.out.print(Character.toString((char)ascii));
        }
    }
}
