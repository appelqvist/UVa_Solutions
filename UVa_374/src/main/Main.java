package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * Created by Andréas Appelqvist on 2016-10-06.
 * UVa Online Judge Problem:
 * 374 Big Mod
 */
public class Main {
    public static void main(String[] args) throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("src/sample_input"));

        String strB;
        BigInteger b,p,m;
        while((strB = br.readLine()) != null){
            b = new BigInteger(strB);
            p = new BigInteger(br.readLine());
            m = new BigInteger(br.readLine());
            br.readLine();
            System.out.println(b.modPow(p,m));
        }
    }
}
