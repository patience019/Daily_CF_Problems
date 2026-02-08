#include <bits/stdc++.h>
using namespace std;

long long quickPow(long long base, long long power, long long mod) {
    if (power == 0) return 1 % mod;
    long long cur = quickPow(base, power / 2, mod);
    long long res = (cur * cur) % mod;
    if ((power & 1) != 0) {
        res = (base * res) % mod;
    }
    return res;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int p, x, y;
    long long a;
    if (!(cin >> p >> x >> y >> a)) return 0;

    vector<int> mods = {1};

    for (int i = 0; i < p; ++i) {
        long long next = (mods.back() * 1LL * x) % p;
        mods.push_back((int)next);
        if (mods.back() == 1) {
            mods.pop_back();
            break;
        }
    }

    int k = (int)mods.size();
    vector<int> pos(p, -1);
    for (int i = 0; i < k; ++i) pos[mods[i]] = i;

    long long ans = 0;
    long long cycle = 1LL * k * p;

    for (int i = 1; i < p; ++i) {
        int v = (int)((quickPow(i, p - 2, p) * 1LL * y) % p);
        if (pos[v] >= 0) {
            long long res = (1LL * (pos[v] - i) * p + i) % cycle;
            if (res < 0) res += cycle;
            if (a >= res) ans += (a - res) / cycle + 1;
        }
    }

    cout << ans;
    return 0;
}
