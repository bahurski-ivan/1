package lesson3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * Created by Ivan on 17/09/16.
 */

public class homework {
    private static final String INPUT_FILE_NAME = "input.txt";

    private static class uniqueWordsTable {

        public TreeMap<String, Integer> view;

        public uniqueWordsTable() {
            view = new TreeMap<>(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    if(o1.length() < o2.length())
                        return -1;
                    if(o1.length() > o2.length())
                        return 1;
                    else
                        return o1.compareTo(o2);
                }
            });
        }

        public void add(String word) {
            Integer value = view.get(word);

            if(value == null) {
                view.put(word, 1);

                if(longestWordLength < word.length())
                    longestWordLength = word.length();
            }

            else view.put(word, value + 1);
        }

        public void printFrequencyTable(java.io.PrintStream writer) {
            final String FIRST_ROW = "word";
            final String SECOND_ROW = "count";

            if(FIRST_ROW.length() > longestWordLength)
                longestWordLength = FIRST_ROW.length();

            writer.print(FIRST_ROW);
            int spaceCount = longestWordLength - FIRST_ROW.length() + 1;
            for (int i = 0; i < spaceCount; ++i) writer.write(' ');
            writer.print("| ");
            writer.println(SECOND_ROW);
            int sepCount = longestWordLength + 3 + SECOND_ROW.length();
            for (int i = 0; i < sepCount; ++i) writer.write('-');
            writer.println();


            for(Map.Entry<String, Integer> entry : view.entrySet())
                writer.println(entryToString(entry));
        }

        public void printUniqueWords(java.io.PrintStream writer) {
            for(Map.Entry<String, Integer> entry : view.entrySet())
                writer.println(entry.getKey());
        }


        private int longestWordLength = 0;

        private String entryToString(Map.Entry<String, Integer> entry) {
            StringBuilder sb = new StringBuilder();

            int spaceCount = longestWordLength - entry.getKey().length() + 1;

            sb.append(entry.getKey());
            for (int i = 0; i < spaceCount; ++i)
                sb.append(' ');
            sb.append("| ").append(entry.getValue());

            return sb.toString();
        }
    }

    public static void main(String[] args) throws java.io.IOException {
        inputHandler input = new inputHandler();
        uniqueWordsTable words = new uniqueWordsTable();

        for (String line : input.lines)
            for (wordIterator it = new wordIterator(line.trim()); it.hasNext(); )
                words.add(it.next());

        System.out.println("unique word count: " + words.view.size());
        System.out.println();

        System.out.println("unique word: ");
        words.printUniqueWords(System.out);
        System.out.println();

        System.out.println("words frequencies table: ");
        System.out.println();
        words.printFrequencyTable(System.out);
        System.out.println();


        System.out.println("lines in reverse order: ");
        for(reverseLinesIterator it = new reverseLinesIterator(input.lines); it.hasNext(); ) {
            String line = it.next();
            System.out.println('"' + line + '"');
        }


        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));

        System.out.println();
        System.out.println("enter line number to print or 'exit' to stop:");

        do {
            System.out.print("> ");

            String command = br.readLine();

            if(command.equals("exit"))
                return;

            System.out.print("  ");

            int lineNumber = 0;
            boolean numberNotValid = false;

            try {
                lineNumber = Integer.parseInt(command);
            } catch(NumberFormatException e) {
                numberNotValid = true;
                System.out.println("bad integer format: " + e.getMessage());

            }

            if(!numberNotValid) {
                if (lineNumber < 0 || lineNumber >= input.lines.size())
                    System.out.println("line number must be in range of [0;" + (input.lines.size() - 1) + "]");
                else
                    System.out.println('"' + input.lines.get(lineNumber) + '"');
            }

        } while(true);
    }

    private static class reverseLinesIterator implements Iterator<String> {
        reverseLinesIterator(ArrayList<String> lines) {
            this.lines = lines;
            position = lines.size() - 1;
        }

        @Override
        public boolean hasNext() {
            return position >= 0;
        }

        @Override
        public String next() {
            if(!hasNext())
                throw new NoSuchElementException();
            return this.lines.get(position--);
        }

        private int position;
        private ArrayList<String> lines;
    }

    private static class wordIterator implements Iterator<String> {

        wordIterator(String line) {
            setLine(line, 0);
        }

        public void setLine(String line, int startPosition) {
            this.line = line;
            this.position = startPosition;
        }

        @Override
        public boolean hasNext() {
            return position < line.length();
        }

        @Override
        public String next() {
            if(!hasNext())
                throw new NoSuchElementException();

            int start = position;
            char[] buff = line.toCharArray();

            do {
                for (; position < buff.length && !Character.isWhitespace(buff[position]); ++position) ;
            }
            while(position == start && position < buff.length);

            return position > buff.length ? null : line.substring(start, position++);
        }

        private String line;
        private int position;
    }

    private static class inputHandler {
        public inputHandler() throws java.io.IOException {
            String line;
            BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE_NAME));

            while( (line = reader.readLine()) != null )
                lines.add(line);
        }

        public ArrayList<String> lines = new ArrayList<>();
    }
}
