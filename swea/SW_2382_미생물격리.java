package jadegaja;

import java.io.*;
import java.util.*;

public class SWEA2382 {

    static int T, N, M, K;
    static List<MicroComm> comms;

    static class MicroComm {
        int r, c, micros, dir;
        boolean activate = true;

        public MicroComm(int r, int c, int micros, int dir) {
            this.r = r;
            this.c = c;
            this.micros = micros;
            this.dir = dir;
        }

        // 약품 구역 - 미생물 절반 감소 및 방향 반전
        public void handleMedicine() {
            this.micros /= 2;
            if (this.micros == 0) {
                this.activate = false;
            } else {
               // 방향 반전
                this.dir = (this.dir % 2 == 0) ? this.dir + 1 : this.dir - 1;
            }
        }
    }
    
    public static void simulation() {
        int[] drs = {-1, 1, 0, 0}; // 상하좌우
        int[] dcs = {0, 0, -1, 1};

        for (int time = 0; time < M; time++) {
            Map<Integer, List<MicroComm>> moveMap = new HashMap<>();

            // 1. 모든 군집 이동 및 약품 처리
            for (MicroComm mc : comms) {
                if (!mc.activate) continue;

                mc.r += drs[mc.dir];
                mc.c += dcs[mc.dir];

                // 약품 구역 체크
                if (mc.r == 0 || mc.r == N - 1 || mc.c == 0 || mc.c == N - 1) {
                    mc.handleMedicine();
                }
                
                
                if (mc.activate) {
                    int posKey = mc.r * N + mc.c;
                    List<MicroComm> list = moveMap.get(posKey);
                    if (list == null) {
                        list = new ArrayList<>();     // 새 리스트
                        moveMap.put(posKey, list);
                    }
                    list.add(mc);
                }
            }

            // 2. 같은 칸에 모인 군집 병합
            List<MicroComm> nextComms = new ArrayList<>();
            for (int key : moveMap.keySet()) {
                List<MicroComm> list = moveMap.get(key);

                if (list.size() == 1) {
                    nextComms.add(list.get(0));
                } else {
                    int sumMicros = 0;
                    int maxMicros = -1;
                    MicroComm master = null;

                    for (MicroComm mc : list) {
                        sumMicros += mc.micros;
                        // 미생물 수가 가장 많은 군집 찾기
                        if (mc.micros > maxMicros) {
                            maxMicros = mc.micros;
                            if (master != null) master.activate = false;
                            master = mc;
                        } else {
                            mc.activate = false;
                        }
                    }
                    master.micros = sumMicros; // 합치기
                    nextComms.add(master);
                }
            }
            comms = nextComms; // 살아남은 군집 리스트 갱신
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String line = br.readLine();
        if (line == null) return;
        T = Integer.parseInt(line.trim());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            comms = new ArrayList<>();
            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int micros = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;
                comms.add(new MicroComm(r, c, micros, dir));
            }

            simulation();

            // 결과 합산
            long totalMicros = 0;
            for (MicroComm mc : comms) {
                if (mc.activate) totalMicros += mc.micros;
            }
            sb.append('#').append(t).append(' ').append(totalMicros).append('\n');
        }
        System.out.print(sb);
    }
}