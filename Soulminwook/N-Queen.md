# 📕 Solution

```java
class Solution {
    public int answer;
    public boolean Check(int cnt, int[] arr){
        for(int i = 0; i < cnt; i++){
            for(int j = i + 1; j < cnt; j++){
                if(arr[i] == arr[j]){
                    return false;
                } else if(Math.abs(arr[i] - arr[j]) == Math.abs(i - j)){
                    return false;
                }
            }
        }
        return true;
    }
    public void dfs(int cnt, int n, int[] arr){
        if(cnt == n){
            if(Check(cnt, arr)){
                answer++;
            }
            return;
        }
        for(int j = 0; j < n; j++){
            arr[cnt] = j;
            cnt++;
            if(Check(cnt, arr)){
                dfs(cnt, n, arr);
            }
            cnt--;
            arr[cnt] = 0;
        }
    }
    public int solution(int n) {
        answer = 0;
        int[] arr = new int[n];
        dfs(0, n, arr);
        return answer;
    }
}
```

## ❌ fail

```java

```
