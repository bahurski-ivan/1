package ru.sgu.acm.conditions.t2007;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * Created by Ivan on 11/09/16.
 */
public class Solution {
    public static void main(String[] args) throws java.io.IOException {
        int num = read_input(), i;
        for(i = 0; (i < 32) && (((num >> i) & 1) == 0); ++i);
        set_output(i);
    }

    public static int read_input() throws java.io.IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        return Integer.parseInt(reader.readLine());
    }
    public static void set_output(int result) throws java.io.IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(result);
        PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
        writer.write(sb.toString());
        writer.close();
    }
}
