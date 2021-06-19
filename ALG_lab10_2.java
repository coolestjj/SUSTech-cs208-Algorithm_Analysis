package com.company;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

public class ALG_lab10_2 {

    private static int[] fa;
    private static long[] groupCount;

    public static void main(String[] args) throws IOException {
        Reader input = new Reader();
        PrintWriter out = new PrintWriter(System.out);

        int n = input.nextInt();
        int m = input.nextInt();

        Edge[] edges = new Edge[n - 1];

        for (int i = 0 ; i < n - 1 ; i++) {
            int u = input.nextInt();
            int v = input.nextInt();
            long w = input.nextLong();
            edges[i] = new Edge();
            edges[i].start = u;
            edges[i].end = v;
            edges[i].weight = w;
        }

        Arrays.sort(edges,new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Long.compare(o1.weight, o2.weight);
            }
        });

        fa = new int[n + 1];
        groupCount = new long[n + 1];

        for (int i = 0 ; i < n + 1 ; i++) {
            fa[i] = i;
            groupCount[i] = 1;
        }

        long[] result = new long[m + 1];
        long temp = 0;

        for (int o = 0 ; o < n - 1 ; o++) {
            int i = edges[o].start;
            int j = edges[o].end;
            long w = edges[o].weight;

            temp += groupCount[find(i)] * groupCount[find(j)];

            result[(int) w] = temp;
            merge(i, j);

        }

        for (int i = 1 ; i < m + 1 ; i++) {
            if (result[i] < result[i - 1]) {
                result[i] = result[i - 1];
            }
        }

        for (int i = 0 ; i < m ; i++) {
            int q = input.nextInt();
            out.print(result[q] + " ");
        }

        out.close();
    }

    private static int find(int x)
    {
        if(x == fa[x])
            return x;
        else{
            fa[x] = find(fa[x]);
            return fa[x];
        }
    }

    private static void merge(int i, int j) {
        int findj = find(j);
        int findi = find(i);
        fa[findj] = findi;
        groupCount[findi] += groupCount[findj];
        groupCount[findj] = 0;
    }

    private static class Edge {
        int start;
        int end;
        long weight;
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
