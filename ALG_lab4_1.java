package com.company;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class ALG_lab4_1 {

    public static class ajList{
        ArrayList<Integer> list = new ArrayList<>();
        ajList(){}
    }

    public static void main(String[] args) throws IOException {
        Reader input = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int n = input.nextInt();
        int m = input.nextInt();
        ajList[] graph = new ajList[n + 1];
        int[] inDegree = new int[n + 1];
        Queue<Integer> priorityQueue = new PriorityQueue<>();
        Queue<Integer> ResultQue = new LinkedList<>();
        for (int i = 0 ; i < n + 1 ; i++) {
            graph[i] = new ajList();
        }
        for (int i = 0 ; i < m ; i++) {
            int from = input.nextInt();
            int to = input.nextInt();
            graph[from].list.add(to);
            inDegree[to]++;
        }
        for (int i = 1 ; i < n + 1 ; i++) {
            if (inDegree[i] == 0) {
                priorityQueue.add(i);
            }
        }

        while(!priorityQueue.isEmpty()) {
            int outNum = priorityQueue.poll();
            ResultQue.add(outNum);
            for (int j = 0 ; j < graph[outNum].list.size() ; j++) {
                inDegree[graph[outNum].list.get(j)]--;
                if (inDegree[graph[outNum].list.get(j)] == 0) {
                    priorityQueue.add(graph[outNum].list.get(j));
                }
            }
        }

        while(!ResultQue.isEmpty()) {
            out.print(ResultQue.poll());
            out.print(" ");
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
