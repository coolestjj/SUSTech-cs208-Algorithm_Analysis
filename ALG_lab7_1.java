package com.company;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class ALG_lab7_1 {

    public static void main(String[] args) throws IOException {
        Reader input = new Reader();
        PrintWriter out = new PrintWriter(System.out);

        Queue<Tasks> priorityQueue = new PriorityQueue<>(new Comparator<Tasks>() {
            @Override
            public int compare(Tasks t1, Tasks t2) {
                return Long.compare(t2.w, t1.w);
            }
        });

        int n = input.nextInt();

        for (int i = 0 ; i < n ; i++) {
            Tasks task = new Tasks();

            long s = input.nextLong();
            task.s = s;

            long t = input.nextLong();
            task.t = t;

            task.w = input.nextLong();

            priorityQueue.add(task);
        }

        Tasks[] Match = new Tasks[100000000];

        ArrayList<Tasks> S = new ArrayList<>();
        while (!priorityQueue.isEmpty()) {
            Tasks a = new Tasks();
            a = priorityQueue.poll();
            if (LinearMatch(a, a.s ,Match)) {
                S.add(a);
            }
        }

        long result = 0;
        for (int i = 0 ; i < S.size() ; i++) {
            result += S.get(i).w;
        }

        out.println(result);

        out.close();
    }

    private static class Tasks {
        long s;
        long t;
        long w;
    }

    private static boolean LinearMatch(Tasks task, long x, Tasks[] Match) {
        if (x > task.t) {
            return false;
        }
        if (Match[(int) x] == null) {
            Match[(int)x] = task;
            return true;
        }
        Tasks task2 = new Tasks();
        task2 = Match[(int) x];
        if (task.t > task2.t) {
            return LinearMatch(task, x + 1, Match);
        }
        else {
            if (LinearMatch(task2, x + 1 , Match)) {
                Match[(int) x] = task;
                return true;
            }
        }
        return false;
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
