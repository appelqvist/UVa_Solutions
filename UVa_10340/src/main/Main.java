package main;

import java.io.*;

/**
 * Created by Andréas Appelqvist on 2016-10-11.
 * UVa 10340
 */
public class Main {
    public static void main(String[] args) throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("src/sample_input"));

        String fullInput;
        while ((fullInput = br.readLine()) != null) {
            boolean isOK = false;
            String[] str = fullInput.split(" ");
            String first = str[0];
            String second = str[1];
            int charPos = 0;
            for (int i = 0; i < second.length(); i++) {
                if (first.charAt(charPos) == second.charAt(i)) {
                    charPos++;
                }
                if (charPos == first.length()) {
                    isOK = true;
                    break;
                }
            }
            System.out.println(isOK ? "Yes" : "No");
        }
    }
}
