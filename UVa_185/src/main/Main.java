package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Andréas Appelqvist.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        new Main();
    }

    public boolean[] leadingLetter;
    public ArrayList<Character> chars;

    public Main() throws FileNotFoundException {
        //Scanner sc = new Scanner(new File("src/sample_input"));
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        while (!line.contains("#")) {
            String[] romans = new String[3];
            int arraypos = 0;
            int stringPointer = 0;
            String splitter;

            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '+' || line.charAt(i) == '=') {
                    splitter = line.substring(stringPointer, i);
                    romans[arraypos] = splitter;
                    stringPointer = i + 1;
                    arraypos++;
                } else if (i >= line.length() - 1) {
                    splitter = line.substring(stringPointer, line.length());
                    romans[arraypos] = splitter;
                }
            }

            if (getRomanValue(romans[0]) + getRomanValue(romans[1]) == getRomanValue(romans[2])) {
                System.out.print("Correct ");
            } else {
                System.out.print("Incorrect ");
            }

            String allChars = romans[0] + romans[1] + romans[2];
            chars = new ArrayList<>();
            for (int i = 0; i < allChars.length(); i++) {
                if (!chars.contains(allChars.charAt(i))) {
                    chars.add(allChars.charAt(i));
                }
            }

            leadingLetter = new boolean[128];
            for(int i = 0; i < romans.length; i++)
                leadingLetter[romans[i].charAt(0)] = true;

            int solutions = getArabic(romans);
            switch (solutions) {
                case 0: //none
                    System.out.println("impossible");
                    break;
                case 1: // one
                    System.out.println("valid");
                    break;
                default: //more then 1
                    System.out.println("ambiguous");
                    break;
            }
            line = sc.nextLine();
        }
    }

    private int getArabic(String[] romans) {
        solutions = 0;
        testEveryValue(0, new int[128], new boolean[10], romans);
        return solutions;
    }

    private int solutions;
    private void testEveryValue(int ch, int[] values, boolean[] used, String[] romans) {
        if (solutions > 1) { return; }
        if (ch == chars.size()) {
            int[] sums = new int[3];
            for (int i = 0; i < sums.length; i++) { sums[i] = 0; }
            String word;
            char temp;
            for (int i = 0; i < romans.length; i++) {
                word = romans[i];
                for (int j = 0; j < romans[i].length(); j++) {
                    temp = word.charAt(j);
                    sums[i] = sums[i] * 10 + values[temp];
                }
            }
            if (sums[0] + sums[1] == sums[2]) {
                solutions++;
            }
            return;
        }

        for (int i = leadingLetter[chars.get(ch)] ? 1 : 0; i < 10; i++) {
            if (solutions < 2 && !used[i]) {
                used[i] = true;
                values[chars.get(ch)] = i;
                testEveryValue(ch + 1, values, used, romans);
                used[i] = false;
            }
        }
    }

    private int getRomanValue(String roman) {
        int value = 0;
        for (int i = 0; i < roman.length() - 1; i++) {
            if (getRomanLetterValue(roman.charAt(i)) < getRomanLetterValue(roman.charAt(i + 1))) {
                value = value - getRomanLetterValue(roman.charAt(i));
            } else {
                value = value + getRomanLetterValue(roman.charAt(i));
            }
        }
        value = value + getRomanLetterValue(roman.charAt(roman.length() - 1));
        return value;
    }

    private int getRomanLetterValue(char letter) {
        switch (letter) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return -1;
        }
    }
}
