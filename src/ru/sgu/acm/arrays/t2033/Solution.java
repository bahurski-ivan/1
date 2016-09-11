package ru.sgu.acm.arrays.t2033;

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
        int result_size = input.items.length;

        int carry = 0;
        input.items[input.items.length - 1] += 1;

        for(int i = input.items.length - 1; i >= 0; --i) {
            input.items[i] += carry;

            if(input.items[i] > 9) {
                input.items[i] -= 10;
                carry = 1;
            }
            else carry = 0;
        }

        if(carry != 0)
            ++result_size;

        StringBuilder sb = new StringBuilder();
        sb.append(result_size).append('\n');

        PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
        writer.write(sb.toString());

        sb = new StringBuilder();

        if(carry != 0)
            sb.append("1 ");
        for(int i = 0; i < input.items.length-1; ++i)
            sb.append(input.items[i]).append(" ");
        sb.append(input.items[input.items.length-1]);

        writer.write(sb.toString());
        writer.close();
    }


}
