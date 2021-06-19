package com.company;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class ALG_lab9_2 {
    public static void main(String[] args) throws IOException {
        Reader input = new Reader();
        PrintWriter out = new PrintWriter(System.out);

        int n = input.nextInt();
        Node[] nodes = new Node[n];
        Node[] nodes2 = new Node[n];
        int[] happyArray = new int[n];
        for (int i = 0 ; i < n ; i++) {
            nodes[i] = new Node();
            nodes[i] = new Node();
        }
        for (int i = 0 ; i < n ; i++) {
            int u = input.nextInt();
            int v = input.nextInt();
            nodes[i].u = u;
            nodes[i].v = v;
            nodes[i].happy = i;
        }

        MergeSort(nodes, 0, n, nodes2);

        for (int i = 0 ; i < n ; i++) {
            nodes[i].happy -= nodes[i].aj;
            happyArray[nodes[i].happy]++;
        }
        for (int i = 0 ; i < n ; i++) {
            out.println(happyArray[i]);
        }

        out.close();
    }

    private static class Node{
        int u;
        int v;
        int happy;
        int aj;
    }

    private static long MergeSort(Node[] nodes, int l, int r, Node[] nodes2) {
        if (r - l <= 1) {
            return 0;
        }
        int m = (l + r) / 2;
        long lcount = MergeSort(nodes, l, m, nodes2);
        long rcount = MergeSort(nodes, m, r, nodes2);
        int n = l;
        int p = l;
        int q = m;
        long count = 0;
        while (p < m || q < r) {
            if (p >= m) {
                nodes2[n] = nodes[q];
                n++;
                q++;
            } else if (q >= r) {
                nodes2[n] = nodes[p];
                n++;
                p++;
            } else if (nodes[p].u <= nodes[q].u) {
                nodes2[n] = nodes[p];
                n++;
                p++;
            } else {
                nodes2[n] = nodes[q];
                n++;
                q++;
                nodes[q - 1].aj += m - p;
                count = count + m - p;
            }
        }
        for (int i = l ; i < r ; i++) {
            nodes[i] = nodes2[i];
        }
        return count + lcount + rcount;
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
