package ru.sgu.acm.conditions.t2004;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * Created by Ivan on 10/09/16.
 */
public class Solution {
    public static void main(String[] args) throws java.io.IOException {
        int year = read_input();

        set_output(
                (year < 1000 || year > 2100) ? false :
                        (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
        );
    }

    public static int read_input() throws java.io.IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        return Integer.parseInt(reader.readLine());
    }
    public static void set_output(boolean result) throws java.io.IOException {
        PrintWriter writer = new PrintWriter("output.txt", "UTF-8");

        writer.write(result ? "1" : "0");
        writer.close();
    }
}
