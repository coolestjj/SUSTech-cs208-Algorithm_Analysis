package com.company;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class ALG_lab2 {
    public static void main(String[] args) throws IOException {
        Reader input=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        ArrayList<Integer> arrayList = new ArrayList<>();
        int T = input.nextInt();
        for (int i = 0 ; i < T ; i++) {
            int n = input.nextInt();
            for (int j = 0 ; j < n ; j++) {
                arrayList.add(input.nextInt());
            }
            Collections.sort(arrayList);
            Collections.reverse(arrayList);
            int[] array1 = new int[3];
            int[] array2 = new int[3];
            int marker = 0;
            array1[0] = arrayList.get(0);
            for (int j = 1 ; j < n ; j++) {
                if (array1[2] != 0 && array2[2] != 0) {
                    break;
                }

                if (array1[1] == 0 && array1[0] % arrayList.get(j) != 0) {
                    array1[1] = arrayList.get(j);
                }
                else if (array1[2] == 0 && array1[1] != 0 && array1[0] % arrayList.get(j) != 0 && array1[1] % arrayList.get(j) != 0) {
                    array1[2] = arrayList.get(j);
                }

                else if (2 * arrayList.get(j) == array1[0] && array2[0] == 0) {
                    array2[0] = arrayList.get(j);
                }

                else if (3 * arrayList.get(j) == array1[0] && array2[1] == 0) {
                    array2[1] = arrayList.get(j);
                }

                else if (5 * arrayList.get(j) == array1[0] && array2[2] == 0) {
                    array2[2] = arrayList.get(j);
                }
            }

            int result1 = array1[0] + array1[1] + array1[2];
            int result2 = array2[0] + array2[1] + array2[2];

            if (result1 >= result2) {
                out.println(result1);
            }
            else {
                out.println(result2);
            }

            arrayList.clear();
        }
        out.close();
    }

    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
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

        public double nextDouble() throws IOException
        {
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

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }
}
