import java.util.*;

class D6_25010_인기검색어 {
    int N;
    String[] history = new String[10005]; // 전체 입력 기록
    int totalCount;

    // 집합
    class Cluster implements Comparable<Cluster> {
        String representative; // 대표 검색어
        int totalFreq;         // 집합 내 모든 단어의 빈도 합

        public Cluster(String representative, int totalFreq) {
            this.representative = representative;
            this.totalFreq = totalFreq;
        }

        @Override
        public int compareTo(Cluster o) {
            // 1. 전체 빈도수 내림차순
            if (this.totalFreq != o.totalFreq) return o.totalFreq - this.totalFreq;
            // 2. 빈도수 같으면 대표 검색어 사전순 오름차순
            return this.representative.compareTo(o.representative);
        }
    }

    void init(int n) {
        this.N = n;
        this.totalCount = 0;
    }

    void addKeyword(String mKeyword) {
        history[totalCount++] = mKeyword;
    }

    int top5Keyword(String mRet[]) {
        int start = Math.max(0, totalCount - N);
        
        // 1. 최근 N개 단어의 빈도수 측정
        Map<String, Integer> freqMap = new HashMap<>(); // 대표 단어, 참조 수 
        List<String> uniqueWords = new ArrayList<>();	// 대표 단어 
        
        // 1. 들어온 단어 중 유니크 단어 사전 
        for (int i = start; i < totalCount; i++) {
            String word = history[i];
            // 새로 들어온 단어 
            if (!freqMap.containsKey(word)) uniqueWords.add(word);
            // 이전에 들어왔던 단어라면 참조 수 +1
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }

        int size = uniqueWords.size();
        boolean[] visited = new boolean[size];
        PriorityQueue<Cluster> pq = new PriorityQueue<>();

        // 2. BFS -> 유사 단어 탐색 
        for (int i = 0; i < size; i++) {
            if (visited[i]) continue;

            // 새로운 집합 탐색 
            Queue<Integer> q = new LinkedList<>();
            q.add(i);
            visited[i] = true;
            
            // 대표 단어, 참조 수, 집합 총 참조 수 
            String rep = uniqueWords.get(i);
            int repFreq = freqMap.get(rep);
            int clusterTotalFreq = 0;

            while (!q.isEmpty()) {
                int currIdx = q.poll();
                String currWord = uniqueWords.get(currIdx);
                
                // 집합 총 참조 수 업데이트
                clusterTotalFreq += freqMap.get(currWord);
                
                // 대표 검색어 갱신 (빈도수 내림차순, 사전순 오름차순)
                int currFreq = freqMap.get(currWord);
                if (currFreq > repFreq || (currFreq == repFreq && currWord.compareTo(rep) < 0)) {
                    rep = currWord;
                    repFreq = currFreq;
                }

                // 유사 단어 탐색
                for (int nextIdx = 0; nextIdx < size; nextIdx++) {
                    if (!visited[nextIdx] && isSimilar(currWord, uniqueWords.get(nextIdx))) {
                        visited[nextIdx] = true;
                        q.add(nextIdx);
                    }
                }
            }
            pq.add(new Cluster(rep, clusterTotalFreq));
        }

        // 3. 상위 5개 추출
        int count = 0;
        while (!pq.isEmpty() && count < 5) {
            mRet[count++] = pq.poll().representative;
        }

        return count;
    }
    
    // 유사 확인 함수 
    private boolean isSimilar(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int diff = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) diff++;
            if (diff > 1) return false;
        }
        return diff == 1;
    }
}