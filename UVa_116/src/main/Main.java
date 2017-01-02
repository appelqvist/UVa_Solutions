package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Andreas Appelqvist.
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        //Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new File("src/sample_input"));

        int col = sc.nextInt();
        int row = sc.nextInt();

        System.out.println("col: "+col+", row: "+row);

    }

}
