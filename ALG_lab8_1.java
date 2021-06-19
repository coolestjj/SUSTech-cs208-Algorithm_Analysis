package com.company;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class ALG_lab8_1 {
    public static void main(String[] args) {

        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }

    static class Task {
        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            for (int i = 0 ; i < n ; i++) {
                String str = in.next();
                int[] array = new int[26];
                for (int j = 0 ; j < str.length() ; j++) {
                    char letter = str.charAt(j);
                    int letterNum = charToByteAscii2(letter) - 97;
                    array[letterNum]++;
                }
                Queue<Integer> priorityQueue = new PriorityQueue<Integer>();
                for (int j = 0 ; j < array.length ; j++) {
                    if (array[j] != 0) {
                        priorityQueue.add(array[j]);
                    }
                }

                int result = 0;
                if (priorityQueue.size() > 1) {
                    while (priorityQueue.size() > 1) {
                        int adder1 = priorityQueue.poll();
                        int adder2 = priorityQueue.poll();
                        int temp = adder1 + adder2;
                        priorityQueue.add(temp);
                        result += temp;
                    }
                }
                else {
                    result = 1;
                }

                out.println(result);
            }
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
