# 📕 Solution

dfs에는 visited 배열이 들어가야 합니다.

```java
import java.util.*;

class Node {
    int arrive;
    int size;
    int modify;
    Node(int arrive, int size, int modify){
        this.arrive = arrive;
        this.size = size;
        this.modify = modify;
    }
}

class Solution {
    public ArrayList<ArrayList<Integer>> graph;
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        graph = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
            if(i > 0) graph.get(i).add(i);
        }
        for(int[] edge : edge_list){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        int answer = Integer.MAX_VALUE, gps_log_size = gps_log.length;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(gps_log[0], 0, 0));
        int[][] visited = new int[k][n + 1];
        while(!queue.isEmpty()){
            Node node = queue.poll();
            int arrive = node.arrive;
            int size = node.size;
            int modify = node.modify;
            if(visited[size][arrive] == 0){
                visited[size][arrive] = modify;
            } else if(visited[size][arrive] > modify){
                visited[size][arrive] = modify;
            } else {
                continue;
            }
            if(size == k - 1){
                if(arrive == gps_log[gps_log_size - 1] && answer > modify){
                    answer = modify;
                }
                continue;
            }
            for(int next : graph.get(arrive)){
                if(next == gps_log[size + 1]){
                    queue.offer(new Node(next, size + 1, modify));    
                } else {
                    queue.offer(new Node(next, size + 1, modify + 1));
                }
            }    
        }
        return (answer == Integer.MAX_VALUE) ? -1 : answer;
    }
}
```

DP로도 가능합니다. `DP[i][j] = min(DP[i - 1][인접 노드들], DP[i - 1][j]) + (log[i] == j ? 0 : 1)`

```java
import java.util.Arrays;
import java.util.ArrayList;

class Solution {
    public ArrayList<ArrayList<Integer>> graph;

    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            if (i > 0) graph.get(i).add(i);
        }
        for (int[] edge : edge_list) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        int MAX = 3000000;
        int[][] dp = new int[k][n + 1];
        for (int i = 0; i < k; i++) {
            Arrays.fill(dp[i], MAX);
        }
        dp[0][gps_log[0]] = 0;
        for (int i = 1; i < k; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j]);
                for (int next : graph.get(j)) {
                    dp[i][j] = Math.min(dp[i - 1][next], dp[i][j]);
                }
                dp[i][j] += (gps_log[i] == j) ? 0 : 1;
            }
        }
        return (dp[k - 1][gps_log[k - 1]] == MAX) ? -1 : dp[k - 1][gps_log[k - 1]];
    }
}
```

## ❌ fail

```java
import java.util.*;

class Node {
    int arrive;
    int size;
    int modify;
    Node(int arrive, int size, int modify){
        this.arrive = arrive;
        this.size = size;
        this.modify = modify;
    }
}

class Solution {
    public ArrayList<ArrayList<Integer>> graph;
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        graph = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }
        for(int[] edge : edge_list){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        int answer = Integer.MAX_VALUE, gps_log_size = gps_log.length;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(gps_log[0], 0, 0));
        while(!queue.isEmpty()){
            Node node = queue.poll();
            int arrive = node.arrive;
            int size = node.size;
            int modify = node.modify;
            if(size == k - 1){
                if(arrive == gps_log[gps_log_size - 1] && answer > modify){
                    answer = modify;
                }
                continue;
            }
            for(int next : graph.get(arrive)){
                if(next == gps_log[size + 1]){
                    queue.offer(new Node(next, size + 1, modify));    
                } else {
                    queue.offer(new Node(next, size + 1, modify + 1));
                }
            }    
        }
        return (answer == Integer.MAX_VALUE) ? -1 : answer;
    }
}
```
