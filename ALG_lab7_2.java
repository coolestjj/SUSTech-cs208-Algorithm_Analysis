package com.company;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

public class ALG_lab7_2 {

    private static int[] fa;

    public static void main(String[] args) throws IOException {
        Reader input = new Reader();
        PrintWriter out = new PrintWriter(System.out);

        int[] fib = new int[100000];
        fib[1] = 1;
        fib[2] = 1;
        int a = 1;
        int b = 2;
        int c = 3;
        while (c < fib.length) {
            fib[c] = 1;
            a = b;
            b = c;
            c = a + b;
        }

        int t = input.nextInt();
        for (int o = 0 ; o < t ; o++) {
            int n = input.nextInt();
            int m = input.nextInt();
            Edge[] edges = new Edge[m];
            for (int i = 0 ; i < m ; i++) {
                int u = input.nextInt();
                int v = input.nextInt();
                long w = input.nextLong();
                edges[i] = new Edge();
                edges[i].start = u;
                edges[i].end = v;
                edges[i].weight = w;
            }

            fa = new int[n + 1];
            for (int i = 0 ; i < n + 1 ; i++) {
                fa[i] = i;
            }

            for (int i = 0 ; i < m ; i++) {
                int x = find(edges[i].start);
                int y = find(edges[i].end);
                if (x != y) {
                    fa[x] = y;
                }
            }
            int no = 0;
            for (int i = 1 ; i < n + 1 ; i++) {
                if (find(i) != find(1)) {
                    no = 1;
                    break;
                }
            }
            if (no == 1) {
                out.println("No");
                continue;
            }

            Arrays.sort(edges,new Comparator<Edge>() {
                @Override
                public int compare(Edge o1, Edge o2) {
                    return Long.compare(o1.weight, o2.weight);
                }
            });

            long min = 0;
            for (int i = 1 ; i < n + 1 ; i++) {
                fa[i] = i;
            }
            for (int i = 0 , k = 0 ; i < m && k < n - 1 ; i++, k++) {
                int x = find(edges[i].start);
                int y = find(edges[i].end);
                if (x != y) {
                    fa[x] = y;
                    min += edges[i].weight;
                }
            }

            Arrays.sort(edges,new Comparator<Edge>() {
                @Override
                public int compare(Edge o1, Edge o2) {
                    return Long.compare(o2.weight, o1.weight);
                }
            });

            long max = 0;
            for (int i = 1 ; i < n + 1 ; i++) {
                fa[i] = i;
            }
            for (int i = 0 , k = 0 ; i < m && k < n - 1 ; i++, k++) {
                int x = find(edges[i].start);
                int y = find(edges[i].end);
                if (x != y) {
                    fa[x] = y;
                    max += edges[i].weight;
                }
            }

            int marker = 0;
            for (long i = min ; i < max + 1 ; i++) {
                if (fib[(int) i] == 1) {
                    out.println("Yes");
                    marker = 1;
                    break;
                }
            }

            if (marker == 0) {
                out.println("No");
            }

        }

        out.close();
    }

    private static int find(int x) {
        if(x == fa[x])
            return x;
        else{
            fa[x] = find(fa[x]);
            return fa[x];
        }
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
