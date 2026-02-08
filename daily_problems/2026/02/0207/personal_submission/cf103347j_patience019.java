import java.io.*;
import java.util.*;

公共 class Main {
    static long quickPow(long base, long power, long mod) {
        if (power == 0) return 1 % mod;
        long cur = quickPow(base, power / 2, mod);
        long res = (cur * cur) % mod;
        if ((power & 1) != 0) {
            res = (base * res) % mod;
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int p = fs.nextInt();
        int x = fs.nextInt();
        int y = fs.nextInt();
        long a = fs.nextLong();

        ArrayList<Integer> mods = new ArrayList<>();
        mods.add(1);

        for (int i = 0; i < p; i++) {
            long next = (mods.get(mods.size() - 1) * 1L * x) % p;
            mods.add((int) next);
            if (mods.get(mods.size() - 1) == 1) {
                mods.remove(mods.size() - 1);
                break;
            }
        }

        int k = mods.size();
        int[] pos = new int[p];
        Arrays.fill(pos, -1);
        for (int i = 0; i < k; i++) {
            pos[mods.get(i)] = i;
        }

        long ans = 0;
        long cycle = 1L * k * p;

        for (int i = 1; i < p; i++) {
            int v = (int) ((quickPow(i, p - 2, p) * 1L * y) % p);
            if (pos[v] >= 0) {
                long res = (1L * (pos[v] - i) * p + i) % cycle;
                if (res < 0) res += cycle;
                if (a >= res) ans += (a - res) / cycle + 1;
            }
        }

        System.out.print(ans);
    }

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream is) {
            in = is;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ' && c != -1);
            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }
            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }

        long nextLong() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ' && c != -1);
            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }
            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }
}
