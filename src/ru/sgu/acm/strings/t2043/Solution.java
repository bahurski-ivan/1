package ru.sgu.acm.strings.t2043;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Ivan on 11/09/16.
 */

class tag_tree_node {
    public String tagname = "";
    public ArrayList<tag_tree_node> nodes = new ArrayList<tag_tree_node>();
}
class tag_parser {
    tag_parser(String tags) {
        tags.replaceAll("\\s+", "");
        String[] r = tags.split("<");

        tag_tree_node position = root;

        for(String s : r) {

            if(s.length() == 0) continue;

            if(s.charAt(0) == '/')
            { position = tag_stack.pop(); }
            else {
                tag_tree_node new_node = new tag_tree_node();
                new_node.tagname = s.substring(0, s.indexOf('>'));
                position.nodes.add(new_node);
                tag_stack.push(position);
                position = new_node;
            }
        }
    }

    Stack<tag_tree_node> tag_stack = new Stack<>();
    tag_tree_node root = new tag_tree_node();
}

class tag_tree_output {
    public tag_tree_output(tag_tree_node root) throws java.io.IOException {
        for(tag_tree_node it: root.nodes)
            recursiveOutput(it, 0);

        writer.close();
    }

    private void recursiveOutput(tag_tree_node node, int level) {
        printTag(node.tagname, level, false);
        for(tag_tree_node it: node.nodes)
            recursiveOutput(it, level+1);
        printTag(node.tagname, level, true);
    }
    private void printTag(String tagname, int level, boolean is_closing) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < level; ++i)
            sb.append("  ");
        sb.append("<" + (is_closing ? "/" : "") + tagname + ">");

        writer.write(sb.toString() + "\n");
    }
    private PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
}

public class Solution {
    public static void main(String[] args) throws java.io.IOException {
        tag_parser parser = new tag_parser(getInput());
        tag_tree_output output = new tag_tree_output(parser.root);
    }

    static String getInput() throws java.io.IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        return reader.readLine();
    }
}
