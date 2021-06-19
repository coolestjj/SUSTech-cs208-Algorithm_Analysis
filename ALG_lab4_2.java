package com.company;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ALG_lab4_2 {

    public static long result = 0;

    public static void main(String[] args) throws IOException {
        Reader input = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int n = input.nextInt();
        Node[] nodes = new Node[n + 1];
        ajList[] graph = new ajList[n + 1];
        Queue<Integer> queue = new LinkedList<>();

        graph[0] = new ajList();
        for (int i = 1 ; i < n + 1 ; i++) {
            graph[i] = new ajList();
            nodes[i] = new Node(input.nextInt());
        }
        for (int i = 1 ; i < n ; i++) {
            int node1 = input.nextInt();
            int node2 = input.nextInt();
            graph[node1].list.add(node2);
            graph[node2].list.add(node1);
        }

        dfs(nodes, graph, 1);

//          for (int i = 1 ; i < n + 1 ; i++) {
//                out.print(nodes[i].s + " ");
//          }
//          out.println();

        long beauty1 = 0;
        for (int i = 0 ; i < n ; i++) {
            beauty1 += nodes[i + 1].s;
        }
        beauty1 -= nodes[1].s;
        nodes[1].beauty = beauty1;

//          out.println(nodes[1].beauty);

        queue.add(1);
        nodes[1].polled = true;
        while (!queue.isEmpty()) {
            int temp = queue.poll();
            for (int i = 0 ; i < graph[temp].list.size() ; i++) {
                if (!nodes[graph[temp].list.get(i)].polled) {
                    nodes[graph[temp].list.get(i)].beauty = nodes[temp].beauty + nodes[1].s - 2 * nodes[graph[temp].list.get(i)].s;
                    queue.add(graph[temp].list.get(i));
                    nodes[graph[temp].list.get(i)].polled = true;
                }
            }
        }

//           for (int i = 1 ; i < n + 1 ; i++) {
//                out.print(nodes[i].beauty + " ");
//          }
//          out.println();

        for (int i = 1 ; i < n + 1 ; i++) {
            if (result < nodes[i].beauty) {
                result = nodes[i].beauty;
            }
        }

        out.println(result);

        out.close();
    }

    private static void dfs(Node[] nodes, ajList[] graph, int mark) {
        nodes[mark].visited = true;
        int allvisited = 0;
        for (int i = 0; i < graph[mark].list.size(); i++) {
            if (!nodes[graph[mark].list.get(i)].visited) {
                allvisited = 1;
                dfs(nodes, graph, graph[mark].list.get(i));
                nodes[mark].s += nodes[graph[mark].list.get(i)].s;
            }
        }
        if (allvisited == 0) {
            nodes[mark].s = nodes[mark].weight;
        }
        else {
            nodes[mark].s += nodes[mark].weight;
        }
    }


    public static class ajList{
        ArrayList<Integer> list = new ArrayList<>();
        ajList(){}
    }

    private static class Node{
        int weight;
        long s;
        long beauty;
        boolean visited = false;
        boolean polled = false;
        Node (int weight) {
            this.weight = weight;
        }
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
