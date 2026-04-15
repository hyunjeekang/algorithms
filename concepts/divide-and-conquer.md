# Divide and Conquer

<table width="100%">
  <tr>
    <td width="20%"><b>Difficulty</b></td>
    <td width="30%"><img src="https://img.shields.io/badge/Diff-⭐⭐⭐⭐-4A5568?style=flat-square"></td>
    <td width="20%"><b>Importance</b></td>
    <td width="30%"><img src="https://img.shields.io/badge/Imp-⭐⭐⭐⭐⭐-E11D48?style=flat-square"></td>
  </tr>
  <tr>
    <td><b>Status</b></td>
    <td><img src="https://img.shields.io/badge/Memorized-10B981?style=flat-square"></td>
    <td><b>Complexity</b></td>
    <td><code>O(N log N)</code> 또는 <code>O(log N)</code></td>
  </tr>
</table>

<br>

> **Summary** <br>
> 방대하고 복잡한 문제를 다룰 때, 한 번에 해결하려 하지 않고 해결할 수 있는 가장 작은 단위(부분 문제)가 될 때까지 쪼갠 뒤(Divide) 해결하고(Conquer), 그 결과를 다시 합쳐서(Combine) 최종 정답 도출

<br>

## Key Concepts

- **핵심 구조**: 
  1. **분할 (Divide)**: 원래 문제를 더 쪼갤 수 없을 때까지 동일한 성격의 더 작은 부분 문제로 나눔.
  2. **정복 (Conquer)**: 가장 작은 단위로 쪼개진 부분 문제를 해결함 (재귀의 탈출 조건/기저 상태).
  3. **결합 (Combine)**: 해결된 부분 문제들의 결과를 모아 원래 문제의 답을 만듦.

- **재귀 활용**
  - 쪼개진 부분 문제는 원래 문제와 크기만 다를 뿐 성격이 완벽히 동일함
  - 따라서 주로 재귀 함수를 통해 구현된다.

- **DP와의 차이**
  - 분할 정복은 Top-Down 방식으로 문제를 쪼갬
  - DP와 달리 부분 문제들이 서로 중복되지 않는다

<br>

## Implementation

```java
// 2차원 공간 4등분 분할 정복

public void divideAndConquer(int row, int col, int size) {
    // 1. 정복 : 현재 구역이 더 이상 쪼갤 필요가 없는 상태인지 확인
    if (colorCheck(row, col, size)) {
        // 조건 만족 시 로직 처리
        return;
    }

    // 2. 분할 : 조건을 만족하지 않으면 절반 크기로 쪼갬
    int newSize = size / 2;

    // 3. 결합 & 재귀 호출: 4개의 구역으로 나누어 다시 탐색
    divideAndConquer(row, col, newSize);           // 1사분면
    divideAndConquer(row, col + newSize, newSize); // 2사분면
    divideAndConquer(row + newSize, col, newSize); // 3사분면
    divideAndConquer(row + newSize, col + newSize, newSize); // 4사분면
}

// 현재 구역 내의 모든 원소가 같은 조건인지 확인
public boolean colorCheck(int row, int col, int size) {
    int color = board[row][col];
    for (int i = row; i < row + size; i++) {
        for (int j = col; j < col + size; j++) {
            if (board[i][j] != color) return false;
        }
    }
    return true;
}
```

<br>

## References

- 관련 문제
  - [색종이 만들기](https://www.acmicpc.net/problem/2630)
  - [쿼드트리](https://www.acmicpc.net/problem/1992)
  - [곱셈](https://www.acmicpc.net/problem/1629)
  - [Z](https://www.acmicpc.net/problem/1074)
