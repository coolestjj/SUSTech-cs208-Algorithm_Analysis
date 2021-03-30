import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class ALG_lab1 {

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
            String[] M = new String[n];
            Queue<Integer> FreeQue = new LinkedList<>();
            HashMap<String, Integer> men = new HashMap<>();
            for (int i = 0; i < n; i++) {
                String str = in.next();
                men.put(str, i);
                M[i] = str;
                FreeQue.add(i);
            }
            String[] W = new String[n];
            HashMap<String, Integer> women = new HashMap<>();
            for (int i = 0; i < n; i++) {
                String str = in.next();
                women.put(str, i);
                W[i] = str;
            }

            int[] wife = new int[n]; //wife[n] = m : n is m's wife.
            int[] husband = new int[n]; // husband[m] = n : m is n's husband.
            for (int i = 0; i < n; i++) {
                wife[i] = -1;
                husband[i] = -1;
            }
            int[] count = new int[n];

            int[][] menList = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    String Wname = in.next();
                    int Wid = women.get(Wname);
                    menList[i][j] = Wid;
                }
            }

            int[][] WomenList = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    String Mname = in.next();
                    int Mid = men.get(Mname);
                    WomenList[i][Mid] = j;
                }
            }

            while (!FreeQue.isEmpty()) {
                int m = FreeQue.poll();

                int proposeCount = count[m];
                int w = menList[m][proposeCount];
                if (wife[w] == -1) { // If w is no one's wife.
                    wife[w] = m;
                    husband[m] = w;

                    count[m]++;
                } else if (WomenList[w][m] < WomenList[w][wife[w]]) {
                    FreeQue.add(wife[w]);

                    wife[w] = m;
                    husband[m] = w;

                    count[m]++;
                } else {
                    FreeQue.add(m);
                    count[m]++;
                }
            }

            for (int i = 0; i < n; i++) {
                out.print(M[i]);
                out.print(" ");

                int WomenId = husband[i];
                String WomenName = W[WomenId];

                out.println(WomenName);
            }
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
