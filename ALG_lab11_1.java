package com.company;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class ALG_lab11_1 {


    public static void main(String[] args) throws IOException {
        Reader input = new Reader();
        PrintWriter out = new PrintWriter(System.out);

        long[][] table = new long[61][3];
        table[0][0] = 1;
        table[0][1] = 0;
        table[0][2] = 0;
        for (int i = 1 ; i < 61 ; i++) {
            table[i][0] = table[i - 1][0] + table[i - 1][2];
            table[i][1] = table[i - 1][0] + table[i - 1][1];
            table[i][2] = table[i - 1][2] + table[i - 1][1];
        }

//        out.println(Arrays.deepToString(table));

        int T = input.nextInt();
        for (int i = 0; i < T; i++) {
            long n = input.nextLong();
            out.println(find(n, table)[0] + " " + find(n, table)[1] + " " + find(n, table)[2]);
        }
        out.close();
    }

    private static long cifang(long a) {
        long n = a - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        n |= n >>> 32;
        return n - (n >> 1);
    }

    private static int findIndex(long i) {
        return 63 - Long.numberOfLeadingZeros(i);
    }

    private static long[] find(long i, long[][] table) {
        long[] array = new long[3];

        if (i == 0) {
            return array;
        }
        else {
            long[] array1 = new long[3];
            long[] array2;

            int index = findIndex(i);

            long a = 1L <<index;
//            long b = i - a;
            array1[0] = table[index][0];
            array1[1] = table[index][1];
            array1[2] = table[index][2];

            array2 = find(i-a, table);

            array[0] = array1[0] + array2[2];
            array[1] = array1[1] + array2[0];
            array[2] = array1[2] + array2[1];

            return array;
        }
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }
}
