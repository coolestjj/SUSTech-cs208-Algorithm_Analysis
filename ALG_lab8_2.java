package com.company;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class ALG_lab8_2 {
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
            int m = in.nextInt();
            int result1;
            int result2 = 0;
            for (int i = 0 ; i < n ; i++) {
                String command = in.next();
                int num = in.nextInt();
                if (command.equals("AND")) {
                    m = num & m;
                    result2 = num & result2;
                }
                else if (command.equals("OR")) {
                    m = num | m;
                    result2 = num | result2;
                }
                else if (command.equals("XOR")) {
                    m = num ^ m;
                    result2 = num ^ result2;
                }
            }
            result1 = m;
            int finalresult = result1 | result2;
            out.println(finalresult);
        }
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
