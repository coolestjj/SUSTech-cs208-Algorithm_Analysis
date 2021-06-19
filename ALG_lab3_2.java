package com.company;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class ALG_lab3_2 {

    public static int result = 0;

    public static void main(String[] args) {

        InputStream inputStream = System.in;// new
        // FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }

    static class Task {
        public void solve(InputReader in, PrintWriter out) {
            int t = in.nextInt();
            for (int i = 0 ; i < t ; i++) {
                int[][] map = new int[26][27];
                String str = in.next();
                int length = str.length();
                int[] array = new int[26];
                int cnt = 0;
                int able = 0;
                for (int j = 0 ; j < length - 1 ; j++) {
                    char letter1 = str.charAt(j);
                    char letter2 = str.charAt(j + 1);
                    if ((letter1 != 'a' && letter1 != 'e' && letter1 != 'i' && letter1 != 'o' && letter1 != 'u') && (letter2 != 'a' && letter2 != 'e' && letter2 != 'i' && letter2 != 'o' && letter2 != 'u')) {
                        able = 1;

                        int letterNum1 = charToByteAscii2(letter1) - 97;
                        int letterNum2 = charToByteAscii2(letter2) - 97;
                        map[letterNum1][letterNum2]++;
                        map[letterNum2][letterNum1]++;

                        if (array[letterNum1] == 0) {
                            array[letterNum1] = cnt + 1;
                            cnt++;
                        }
                        if (array[letterNum2] == 0) {
                            array[letterNum2] = cnt + 1;
                            cnt++;
                        }
                        map[letterNum1][26]++;
                        map[letterNum2][26]++;
                    } //create the big table
                }


                int letterNum = 0;
                for (int k = 0 ; k < 26 ; k++) {
                    if (map[k][26] != 0) {
                        letterNum++;
                    }
                }

                Node[] lines = new Node[letterNum];
                for (int k = 0 ; k < letterNum ; k++) {
                    int[] letter = new int[letterNum];
                    lines[k] = new Node(false , letter);
                }

                for (int m = 0 ; m < 26 ; m++) {
                    if (map[m][26] != 0) {
                        for (int n = 0 ; n < 26 ; n++) {
                            if (map[m][n] != 0) {
                                lines[array[m] - 1].letter[array[n] - 1] = map[m][n];
                            }
                        }
                    }
                }


                if (able == 1) {
                    int currentAns = 0;
                    int counter = 0;
                    dfs(lines, currentAns, counter, true);

                    out.println(result);
                }
                else {
                    out.println(0);
                }

                result = 0;
            }
        }
    }

    private static void dfs(Node[] lines, int currentAns, int counter, boolean Capital) {
        if (Capital) {
            lines[counter].capital = true;
            for (int i = 0 ; i < lines[counter].letter.length ; i++) {
                if (i != counter) {
                    if (lines[counter].letter[i] != 0 && lines[i].capital) {
                        currentAns -= lines[counter].letter[i];
                    }
                    else {
                        currentAns += lines[counter].letter[i];
                    }
                }
            }
        }
        else {
            lines[counter].capital = false;
        }

        if (currentAns > result) {
            result = currentAns;
        }

        counter++;

        if (counter < lines.length) {
            dfs(lines, currentAns, counter, true);
            dfs(lines, currentAns, counter, false);
        }
    }

    private static class Node {
        boolean capital;
        int[] letter;
        Node(boolean capital, int[] letter) {
            this.capital = capital;
            this.letter = letter;
        }
    }

    private static int charToByteAscii2(char ch) {
        return ch;
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public char[] nextCharArray() {
            return next().toCharArray();
        }

        public boolean hasNext() {
            try {
                String string = reader.readLine();
                if (string == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(string);
                return tokenizer.hasMoreTokens();
            } catch (IOException e) {
                return false;
            }
        }

        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }

        public BigDecimal nextBigDecinal() {
            return new BigDecimal(next());
        }
    }
}
