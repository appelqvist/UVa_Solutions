package main;

import java.io.*;

/**
 * Created by Andréas Appelqvist on 2016-10-22.
 * UVa_10943
 */
public class Main {
    private static final int MOL = 1000000;
    public static void main(String[] args) throws IOException {

                                //N   K
        int[][] matrix = new int[101][101]; //So I can use it as 1-100
        //Building the matrix for every values possible
        for(int n = 1; n <= 100; n++){
            matrix[n][1] = 1;
        }

        for(int k = 1; k <= 100; k++){
            matrix[1][k] = k;
        }

        for(int n = 2; n <= 100; n++){
            for(int k = 2; k <= 100; k++){
                int res = matrix[n][k-1] + matrix[n-1][k];
                matrix[n][k] = (res%MOL);
            }
        }

        BufferedReader br = new BufferedReader(new FileReader("src/sample_input"));
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            String[] input = line.split(" ");
            int n = Integer.parseInt(input[0]);
            int k = Integer.parseInt(input[1]);

            if (n != 0 && k != 0) {
                System.out.println(matrix[n][k]);
            }
        }
    }
}
