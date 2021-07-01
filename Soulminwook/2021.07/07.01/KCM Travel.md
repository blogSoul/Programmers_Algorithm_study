# 📕 Solution

dp를 `dp[해당 공항 번호][쓰는 돈의 양] = 가장 적은 시간` 으로 지정해서 만들면 됩니다.

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge>{
    int end;
    int cost;
    int time;
    Edge(int end, int cost, int time){
        this.end = end;
        this.cost = cost;
        this.time = time;
    }
    @Override
    public int compareTo(Edge o){
        if(this.time != o.time){
            return this.time - o.time;
        } else {
            return this.cost - o.cost;
        }
    }
}

class Main {
    public static int N, M;
    public static int[][] visited;
    public static ArrayList<ArrayList<Edge>> graph;
    public static void ShortPath(int start){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0, 0));
        visited[start][0] = 0;
        while(!pq.isEmpty()){
            Edge node = pq.poll();
            int now = node.end;
            int cost = node.cost;
            int time = node.time;
            if(now == N){
                break;
            }
            for(Edge edge : graph.get(now)){
                int edge_end = edge.end;
                int edge_cost = edge.cost + cost;
                int edge_time = edge.time + time;
                if(edge_cost > M) continue;
                if(visited[edge_end][edge_cost] <= edge_time) continue;
                for(int i = edge_cost; i <= M; i++){
                    if(visited[edge_end][i] > edge_time){
                        visited[edge_end][i] = edge_time;
                    }
                }
                visited[edge_end][edge_cost] = edge_time;
                pq.offer(new Edge(edge_end, edge_cost, edge_time));
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine()), MAX = Integer.MAX_VALUE;
        for(int test_case = 0; test_case < T; test_case++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            graph = new ArrayList<>();
            visited = new int[N + 1][M + 1];
            for(int i = 0; i <= N; i++){
                graph.add(new ArrayList<>());
                Arrays.fill(visited[i], MAX);
            }
            int K = Integer.parseInt(st.nextToken());
            for(int i = 0; i < K; i++){
                st = new StringTokenizer(br.readLine(), " ");
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                graph.get(u).add(new Edge(v, c, d));
            }
            ShortPath(1);
            int answer = MAX;
            for(int i = 0; i <= M; i++){
                answer = Math.min(answer, visited[N][i]);
            }
            if(answer == MAX){
                sb.append("Poor KCM\n");
            } else {
                sb.append(answer + "\n");
            }
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
```

## ❌ fail

시간 초과

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Edge {
    int end;
    int cost;
    int time;
    Edge(int end, int cost, int time){
        this.end = end;
        this.cost = cost;
        this.time = time;
    }
}

class Main {
    public static int N, M, answer;
    public static int[] visited;
    public static ArrayList<ArrayList<Edge>> graph;
    public static void dfs(int start, int cost, int time){
        if(start == N){
            if(answer > time) answer = time;
            return;
        }
        if(answer < time) return;
        visited[start] = time;
        for(Edge edge : graph.get(start)){
            if(visited[edge.end] < edge.time) continue;
            if(cost + edge.cost > M) continue;
            dfs(edge.end, cost + edge.cost, time + edge.time);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine()), MAX = 400000000;
        for(int test_case = 0; test_case < T; test_case++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            graph = new ArrayList<>();
            for(int i = 0; i <= N; i++){
                graph.add(new ArrayList<>());
            }
            int K = Integer.parseInt(st.nextToken());
            for(int i = 0; i < K; i++){
                st = new StringTokenizer(br.readLine(), " ");
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                graph.get(u).add(new Edge(v, c, d));
            }
            answer = MAX;
            visited = new int[N + 1];
            for(int i = 1; i <= N; i++){
                visited[i] = MAX;
            }
            dfs(1, 0, 0);
            if(answer == MAX){
                sb.append("Poor KCM\n");
            } else {
                sb.append(answer + "\n");
            }
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
```
