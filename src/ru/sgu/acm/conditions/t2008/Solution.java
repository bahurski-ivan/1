package ru.sgu.acm.conditions.t2008;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * Created by Ivan on 10/09/16.
 */

class InputData {
    public InputData() throws java.io.IOException, java.lang.IllegalArgumentException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String[] line_items = reader.readLine().split(" ");

        if(line_items.length != 2)
            throw new java.lang.IllegalArgumentException("bad input file (first line)");

        int item_count = Integer.parseInt(line_items[0]);
        capacity = Integer.parseInt(line_items[1]);

        line_items = reader.readLine().split(" ");

        if(line_items.length != item_count)
            throw new java.lang.IllegalArgumentException("bad input file (second line)");

        items = new int[item_count];
        for(int i = 0; i < item_count; ++i)
            items[i] = Integer.parseInt(line_items[i]);
    }

    public int capacity;
    public int[] items;
}

public class Solution {
    public static void main(String[] args) throws java.io.IOException {
        InputData data = new InputData();

        int item_counter = 0, total_weight = 0;

        for(int item : data.items)
            if(data.capacity >= total_weight + item)
            {
                ++item_counter;
                total_weight += item;
            }

        set_output(item_counter, total_weight);
    }

    public static void set_output(int item_count, int weight) throws java.io.IOException {
        StringBuilder sb = new StringBuilder();
        PrintWriter writer = new PrintWriter("output.txt", "UTF-8");

        sb.append(item_count).append(" ").append(weight);

        writer.write(sb.toString());
        writer.close();
    }
}
