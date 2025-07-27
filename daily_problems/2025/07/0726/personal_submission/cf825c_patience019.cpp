#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    long long k;
    cin >> n >> k;

    priority_queue<long long, vector<long long>, greater<long long>> pq;
    for (int i = 0; i < n; ++i) {
        long long x;
        cin >> x;
        pq.push(x);
    }

    int ans = 0;
    while (!pq.empty()) {
        long long v = pq.top();
        if (v <= k) {
            pq.pop();
            k = max(k, v);
        } else {
            while (2 * k < v) {
                k <<= 1;
                ans++;
            }
        
            k = max(k, v);
            pq.pop();
        }
    }

    cout << ans << '\n';
    return 0;
}
