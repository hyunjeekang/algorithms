# Minimum Spanning Tree

<table width="100%">
<tr>
<td width="20%"><b>Difficulty</b></td>
<td width="30%"><img src="https://img.shields.io/badge/Diff-⭐⭐⭐-4A5568?style=flat-square"></td>
<td width="20%"><b>Importance</b></td>
<td width="30%"><img src="https://img.shields.io/badge/Imp-⭐⭐⭐-E11D48?style=flat-square"></td>
</tr>
<tr>
<td><b>Status</b></td>
<td><img src="https://img.shields.io/badge/Review_Needed-F59E0B?style=flat-square"></td>
<td><b>Complexity</b></td>
<td><code>K's : O(E log E), P's : O(E log V)</code></td>
</tr>
</table>

<br>

## Summary
> 무향 가중치 그래프에서 사이클 없이 모든 정점을 연결하는 간선들 중 가중치 총합이 최소가 되는 신장 트리를 찾는 알고리즘 <br>
> 부분의 최적 해가 전체의 최적 해가 되는 그리디 알고리즘 기반

<br>

| Kruskal's Algorithm (간선 중심) | Prim's Algorithm (정점 중심) | 
| :---: | :---: |
| ![Kruskal Algorithm](https://upload.wikimedia.org/wikipedia/commons/b/bb/KruskalDemo.gif) | ![Prim Algorithm](https://upload.wikimedia.org/wikipedia/commons/9/9b/PrimAlgDemo.gif) |

<br>

## Key Concepts

- **Greedy**
  - cut property : 어떤 시점이든 트리를 두 부분으로 잘랐을 때 두 부분을 연결하는 간선 중 가장 작은 가중치가 작은 간선을 선택하면 항상 최소 신장 트리에 포함된다.
  - 지금 가장 저렴한 간선을 골라도 미래에 악영향을 주지 않으므로 그리디가 최적해를 보장한다.

- **Kruskal(간선 중심)**
  - 그래프의 모든 간선을 가중치 오름차순으로 정렬한 뒤, 가중치가 가장 작은 간선부터 차례로 확인하며 트리에 추가하는 **전역적(Global) 탐욕 방식**
  - 양 끝 노드가 이미 같은 신장 트리에 속한다면(사이클 발생) 해당 간선은 추가하지 않음
  - **사이클 판별** : `Union-Find` 활용해 $O(1)$에 가깝게 사이클 여부 확인
  - `PQ`에 넣으면서 정렬, `ArrayList`에 넣고 `Collections.sort()` 방법 중 후자가 캐시 지역성과 정렬 최적화로 인해 더 효율적
  - `(출발 정점, 도착 정점, 가중치)`

- **Prim(정점 중심)**
  - **임의의 정점에서 시작**해 현재 신장 트리에 속한 노드들과 외부 노드를 연결하는 간선 중 **가장 짧은 간선**을 찾아 트리를 넓혀 가는 **국소적(Local)** 탐욕 방식
  - **PQ 우선순위 큐 활용** : 현재 트리에서 뻗어나갈 수 있는 간선들을 PQ에 넣고 최소 비용 간선을 뽑는다
  - **vs Dijkstra** : 다익스트라 알고리즘은 **시작점으로부터 누적 거리**가 짧은 것을 고르고, 프림 알고리즘은 **현재 트리 전체를 기준**으로 가장 짧게 연결될 수 있는 간선을 고른다.
  - `(도착 정점, 가중치)`

<br>

## Time Complexity

### Kruskal Algorithm: $O(E \log E)$

- 전체 시간 복잡도는 간선을 정렬하는 시간에 지배
- 간선이 적은 희소 그래프에 유리

1.  **간선 리스트 저장:** `ArrayList`에 추가 $\to O(E)$
2.  **간선 정렬:** `Collections.sort()` 사용 $\to O(E \log E)$
3.  **간선 선택 (최대 $E$번 반복):**
      * 각 간선마다 `Find` 연산으로 사이클 검사, 없으면 `Union` 수행
      * 경로 압축(Path Compression)을 적용한 Union-Find의 시간 복잡도는 애커만 함수의 역함수인 $O(\alpha(V))$로 사실상 상수 시간에 가깝다. $\to O(E)$

<!-- end list -->

  * **결론:** $O(E) + O(E \log E) + O(E) = O(E \log E)$

### Prim Algorithm: $O(E \log V)$ (PQ 이용)

- 전체 시간 복잡도는 간선을 PQ에 넣고 빼는 과정에 지배
- 간선이 많은 밀집 그래프에 유리

1.  **초기화 및 시작점 삽입:** $\to O(1)$
2.  **정점 추출 (최소 간선 선택):**
      * 큐에 간선이 들어갔다 나오는 과정에서 최악의 경우 모든 간선($E$개)이 큐에 삽입되고 추출될 수 있다. $\to O(E \log V)$
3.  **인접 간선 탐색:**
      * 각 정점에서 연결된 간선을 확인하여 PQ에 넣는 과정 $\to O(E \log V)$

<!-- end list -->

  * **결론:** 최악의 경우 $O(E \log V)$

<br>

## Implementation

### 1\. Kruskal Algorithm (Java)

```java
import java.util.*;

class Edge implements Comparable<Edge> {
    int from, to, weight;
    Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight; // 가중치 기준 오름차순 정렬
    }
}

public class KruskalMST {
    static int[] parent;

    // 경로 압축이 적용된 Find
    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        x = find(x); // x의 최상위 부모
        y = find(y); // y의 최상위 부모
        
        if (x != y) {
            // 두 부모 번호를 비교해서 더 작은 쪽이 부모가 되도록 설정
            if (x < y) {
                parent[y] = x;
            } else {
                parent[x] = y;
            }
        }
    }

    public static void main(String[] args) {
        int V = 7; // 정점 개수
        List<Edge> edges = new ArrayList<>();
        // 간선 정보 추가...

        // 1. 간선 가중치 기준 오름차순 정렬
        Collections.sort(edges); 

        parent = new int[V + 1];
        for (int i = 1; i <= V; i++) parent[i] = i;

        int mstWeight = 0;
        int count = 0; // 선택된 간선의 수

        // 2. 가중치가 가장 적은 간선부터 탐색
        for (Edge edge : edges) {
            // Find를 통해 사이클이 생기는지 확인 (부모가 다르면 사이클 X)
            if (find(edge.from) != find(edge.to)) {
                union(edge.from, edge.to);
                mstWeight += edge.weight;
                count++;
                
                // V-1개의 간선이 선택되면 MST 완성
                if (count == V - 1) break; 
            }
        }
        System.out.println("Kruskal MST 총 비용: " + mstWeight);
    }
}
```

### 2\. Prim Algorithm (Java)

```java
import java.util.*;

class Node implements Comparable<Node> {
    int to, weight;
    Node(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight; // 가중치 기준 오름차순 정렬
    }
}

public class PrimMST {
    public static void main(String[] args) {
        int V = 7;
        List<Node>[] adj = new ArrayList[V + 1]; // 인접 리스트
        for (int i = 1; i <= V; i++) adj[i] = new ArrayList<>();
        // 그래프 구성...

        boolean[] visited = new boolean[V + 1]; // 트리에 포함되었는지 여부
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        // 1. 임의의 정점(지금은 1번)에서 시작
        pq.add(new Node(1, 0)); 
        
        int mstWeight = 0;
        int count = 0;

        while (!pq.isEmpty()) {
            // 2. 현재 신장 트리와 연결된 간선 중 가장 가중치가 작은 정점을 꺼냄
            Node current = pq.poll();

            // 이미 트리에 포함된(방문한) 정점이면 패스 (사이클 방지)
            if (visited[current.to]) continue; 

            // 트리 편입 확정 (간선 추가)
            visited[current.to] = true;
            mstWeight += current.weight;
            
            // 모든 정점이 다 연결되면 종료
            if (++count == V) break;

            // 3. 방금 트리에 편입된 정점과 연결된 외부 간선들을 탐색하여 PQ에 추가
            for (Node next : adj[current.to]) {
                if (!visited[next.to]) {
                    pq.add(next);
                }
            }
        }
        System.out.println("Prim MST 총 비용: " + mstWeight);
    }
}
```

<br>

## References

- [vid10 kruskals vs prims](https://youtu.be/vmWSnkBVvQ0?si=sDmRxXUod1b-b_Mq)
