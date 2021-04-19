# 📕 Solution

```java
import java.util.*;

class Solution {
    public ArrayList<ArrayList<Integer>> graph;
    public int bfs(int start, int end, boolean[] visited){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while(!queue.isEmpty()){
            int node = queue.poll();

        }
    }
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        graph = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }
        for(int[] edge : edge_list){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        int answer = 0;
        boolean[] visited = new boolean[n + 1];
        for(int i = 1; i < k; i++){
            Arrays.fill(visited, false);
            int start = gps_log[i - 1];
            int end = gps_log[i];
            answer += bfs(start, end, visited);
        }
        return answer;
    }
}
```

## ❌ fail

```java

```
