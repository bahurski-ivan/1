package ru.sgu.acm.arrays.t2034;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * Created by Ivan on 11/09/16.
 */

class InputHanlder {
    public InputHanlder() throws java.io.IOException {
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

interface IResultCallback {
    void OnResult(int start, int end);
}

class ArrayScanner {
    public ArrayScanner(int[] a, IResultCallback result_cb) {

        if(a.length == 1)
        {
            result_cb.OnResult(0, 0);
            return;
        }

        boolean flag;
        for(int j = 0; j < a.length-1; ++j)
        {
            flag = false;
            int diff = 0, i = j + 1;
            for(i = j + 1; i < a.length; ++i) {
                int diff2 = a[j] - a[i];

                if(diff2 != 0) {
                    if(Math.abs(diff2) == 1) {
                        if (diff == 0) diff = diff2;
                        else if(diff != diff2)
                        { result_cb.OnResult(j, i - 1); flag = true; break; }
                    }
                    else
                    { result_cb.OnResult(j, i - 1); flag = true; break; }
                }
            }

            if(!flag)
                if(i == a.length)
                    result_cb.OnResult(j, i - 1);
        }
    }
}

class resultPrinter implements IResultCallback {
    public resultPrinter(int[] a) {
        this.a = a;
    }

    public void OnResult(int start, int end) {
        if(end - start < 0 || !isValid(start, end)) return;

        int length = end - start;
        int my_length = max_end - min_start;

        if(my_length < length) {
            min_start = start;
            max_end = end;
        }
    }

    public  void outputResult() throws java.io.IOException {
        StringBuilder sb = new StringBuilder();
        PrintWriter writer = new PrintWriter("output.txt", "UTF-8");

        sb.append(min_start + 1).append(" ").append(max_end + 1);

        writer.write(sb.toString());
        writer.close();
    }

    private boolean isValid(int start, int end) {

        int diff = 0;
        for(int i = start+1; i <= end; ++i) {
            int diff2 = a[start] - a[i];

            if(diff2 == 0) continue;
            else if(Math.abs(diff2) != 1) return false;

            if(diff == 0)
                diff = diff2;
            else if(diff != diff2)
                return false;
        }

        return true;
    }

    int min_start = 0;
    int max_end = -1;
    int[] a;
}

public class Solution {
    public static void main(String[] args) throws java.io.IOException {
        InputHanlder input = new InputHanlder();
        resultPrinter printer = new resultPrinter(input.items);
        ArrayScanner scanner = new ArrayScanner(input.items, printer);

        printer.outputResult();
    }
}
