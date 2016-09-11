package ru.sgu.acm.strings.t2044;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Created by Ivan on 10/09/16.
 */
class btree_node {
    public btree_node() {
        value = 0;
        is_leaf = false;
        left = right = null;
    }

    public char value;
    public boolean is_leaf;
    public btree_node left, right;
}

class huffman_decoder {
    public huffman_decoder(btree_node root) {
        last_char = 0;
        head = root;
        position = root;
    }

    public boolean nextbit(boolean bit) {
        last_char = 0;

        position = position == null ? null :
                (bit ? position.left : position.right);

        if(position == null)
            throw new java.lang.IllegalArgumentException("[huffman decoder] unknown code");

        if(position.is_leaf) {
            last_char = position.value;
            position = head;
            return true;
        }

        return false;
    }
    public char get_last_char() {
        return last_char;
    }

    private char last_char;
    private btree_node position, head;
}

class huffman_tree_builder {
    public huffman_tree_builder() {
        root = new btree_node();
    }

    public void add_code(char value, boolean[] code) {
        btree_node position = root, last = null;

        for(boolean c : code)
        {
            if(c) {
                if(position.left == null)
                    position.left = new btree_node();
                position = position.left;
            }
            else {
                if(position.right == null)
                    position.right = new btree_node();
                position = position.right;
            }
        }

        position.value = value;
        position.is_leaf = true;
    }

    public btree_node get_tree_root() {
        return root;
    }

    private btree_node root;
}

class input_parser {
    public input_parser() throws java.io.IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));

        String[] line_items;
        huffman_tree_builder builder = new huffman_tree_builder();
        int character_count = Integer.parseInt(reader.readLine());

        for(int i = 0; i < character_count; ++i) {
            line_items = reader.readLine().split(" ");

            if(line_items.length != 2)
                throw new java.lang.IllegalArgumentException("bad input file (chars lines)");

            builder.add_code(line_items[0].charAt(0), binary_string_to_boolean_array(line_items[1]));
        }

        root = builder.get_tree_root();
        bits = binary_string_to_boolean_array(reader.readLine());
    }

    public boolean[] bits;
    public btree_node root;

    private boolean[] binary_string_to_boolean_array(String s) {
        int i = 0;
        boolean[] result = new boolean[s.length()];

        for(char c : s.toCharArray()) {
            if (c != '1' && c != '0')
                throw new java.lang.IllegalArgumentException("bad input file (encoded string contains bad chars)");
            result[i++] = c == '1';
        }

        return result;
    }
}

public class Solution {
    public static void main(String[] args) throws java.io.IOException {
        input_parser input = new input_parser();
        FileWriter writer = new FileWriter("output.txt");
        huffman_decoder decoder = new huffman_decoder(input.root);

        for(boolean c : input.bits)
            if(decoder.nextbit(c))
                writer.write(decoder.get_last_char());

        writer.close();
    }
}
