package main;

import java.io.*;

/**
 * Created by Andréas Appelqvist on 2016-10-11.
 * UVa 11054
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader br = new BufferedReader(new FileReader("src/sample_input"));

        String line = br.readLine();
        while (Integer.parseInt(line) != 0) {
            int inhabitants = Integer.parseInt(line);
            long work = 0;
            long sellSum = 0;
            long buySum = 0;
            line = br.readLine();
            String[] nbrs = line.split(" ");
            for(int i = 0; i < inhabitants; i++){
                int trade = Integer.parseInt(nbrs[i]);
                if(trade > 0){
                    buySum += trade;
                }else if(trade < 0){
                    sellSum += trade*-1; //To get absolute value
                }
                work += (buySum-sellSum) > 0 ? buySum-sellSum : (buySum-sellSum)*-1; //To get the absolute value.
            }
            System.out.println(work);
            line = br.readLine();
        }
    }
}
