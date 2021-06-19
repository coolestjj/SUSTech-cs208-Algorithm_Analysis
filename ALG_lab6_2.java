package com.company;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class ALG_lab6_2 {
    public static void main(String[] args) throws IOException {
        Reader input = new Reader();
        PrintWriter out = new PrintWriter(System.out);

        int n = input.nextInt();
        int m = input.nextInt();

        Node[] nodes = new Node[n + 1];
        for (int i = 0 ; i < n + 1 ; i++) {
            nodes[i] = new Node();
        }

        for (int i = 1 ; i < m + 1 ; i++) {
            int u = input.nextInt();
            int v = input.nextInt();
            long w = input.nextLong();
            nodes[u].number = u;
            nodes[v].number = v;
            nodes[u].edge.add(nodes[v]);
            nodes[u].weight.add(w);

        }

        for (int i = 1 ; i < n + 1 ; i++) {
            nodes[i].red = input.nextLong();
            nodes[i].green = input.nextLong();
        }
        nodes[1].arriveTime = 0;

        boolean[] found = new boolean[n + 1];
        long[] time = new long[n + 1];

        for (int i = 2 ; i < n + 1 ; i++) {
            time[i] = Long.MAX_VALUE;
        }
        found[1] = true;

        Queue<Node> priorityQueue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node node, Node t1) {
                return Long.compare(node.arriveTime, t1.arriveTime);
            }
        });

        priorityQueue.add(nodes[1]);
        while (!priorityQueue.isEmpty()) {
            int index = priorityQueue.poll().number;
            found[index] = true;
            for (int j = 0 ; j < nodes[index].edge.size() ; j++) {

                if (!found[nodes[index].edge.get(j).number]) {
                    long diff = (nodes[index].arriveTime + nodes[index].weight.get(j)) % (nodes[index].edge.get(j).red + nodes[index].edge.get(j).green);
                    if (diff < nodes[index].edge.get(j).red) {
                        nodes[index].edge.get(j).arriveTime = nodes[index].arriveTime + nodes[index].weight.get(j) + (nodes[index].edge.get(j).red - diff);
                    } else {
                        nodes[index].edge.get(j).arriveTime = nodes[index].arriveTime + nodes[index].weight.get(j);
                    }


                    if (time[nodes[index].edge.get(j).number] > nodes[index].edge.get(j).arriveTime) {
                        time[nodes[index].edge.get(j).number] = nodes[index].edge.get(j).arriveTime;
                    }
                    //out.println("from " + index + " to " + nodes[index].edge.get(j).number + ": " + nodes[index].edge.get(j).arriveTime);
                    priorityQueue.add(nodes[index].edge.get(j));
                }
            }
        }

        out.println(time[n]);
        //out.println(Arrays.toString(time));

        out.close();
    }

    public static class Node {
        long red;
        long green;
        long arriveTime;
        int number;
        private ArrayList<Node> edge = new ArrayList<>();
        private ArrayList<Long> weight = new ArrayList<>();
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
