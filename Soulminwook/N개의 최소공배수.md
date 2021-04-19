# 📕 Solution

```java
import java.util.*;

class Solution {
    public int gcd;
    public void GCD(int a, int b){
        if(a == b || a == 0){
            gcd = b;
            return;
        } else if(a > b){
            GCD(a - (a / b) * b, b);
        } else {
            GCD(b - (b / a) * a, a);
        }
    }
    public int solution(int[] arr) {
        int answer = arr[0];
        for(int a : arr){
            GCD(answer, a);
            answer = a * answer / gcd;
        }
        return answer;
    }
}
```

## ❌ fail

```java

```
