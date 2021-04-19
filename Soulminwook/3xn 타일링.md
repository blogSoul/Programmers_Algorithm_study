# 📕 Solution

```java
class Solution {
    public int solution(int n) {
        if(n % 2 != 0) return 0;
        int MOD = 1000000007, size = n / 2;
        long[] arr = new long[size + 1];
        arr[0] = 1;
        arr[1] = 3;
        for(int i = 2; i <= size; i++){
            arr[i] += 3 * arr[i - 1];
            for(int j = 0; j < i - 1; j++){
                arr[i] += 2 * arr[j];
            }
            arr[i] %= MOD;
        }
        return (int)arr[size];
    }
}
```

## ❌ fail

```java

```
