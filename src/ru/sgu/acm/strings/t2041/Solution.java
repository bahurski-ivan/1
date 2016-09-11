package ru.sgu.acm.strings.t2041;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * Created by Ivan on 11/09/16.
 */
public class Solution {
    public static void main(String[] args) throws java.io.IOException {
        String s = getInput();
        String answ = "NO";

        for(int i = 0; i < s.length(); ++i) {
            if(is_paly2(s.substring(0, i+1)) && is_paly2(s.substring(i+1, s.length()))) {
                answ = "YES";
                break;
            }
        }

        PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
        writer.write(answ);
        writer.close();
    }

    static boolean is_paly2(String s) {
        if(s.length() < 2)
            return true;

        int middle = s.length() / 2;

        for(int i = 0; i < middle; ++i)
            if(s.charAt(i) != s.charAt(s.length() - i - 1))
                return false;

        return true;
    }

    static String getInput() throws java.io.IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        return reader.readLine();
    }
}
