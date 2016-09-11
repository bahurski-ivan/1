package ru.sgu.acm.arrays.t2024;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * Created by Ivan on 11/09/16.
 */

class inputData {
    public inputData() throws java.io.IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int item_count = Integer.parseInt(reader.readLine());
        String[] values = reader.readLine().split(" ");

        if(values.length != item_count)
            throw new java.lang.IllegalArgumentException("bad file (not enough items)");

        items = new int[item_count];
        for(int i = 0; i < item_count; ++i)
            items[i] = Integer.parseInt(values[i]);
    }

    public int[] items;
}

public class Solution {
    public static void main(String[] args) throws java.io.IOException {
        inputData input = new inputData();

        int middle = input.items.length/2;
        int count_to_replace = 0;

        for(int i = 0; i < middle; ++i)
            if(input.items[i] != input.items[input.items.length - i - 1])
                ++count_to_replace;

        StringBuilder sb = new StringBuilder();

        sb.append(count_to_replace);

        PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
        writer.write(sb.toString());
        writer.close();
    }
}
