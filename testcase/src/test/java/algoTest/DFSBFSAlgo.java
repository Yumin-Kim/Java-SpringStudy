package algoTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DFSBFSAlgo {

    static class Soution{
        private Integer bfsSoution(int n , int[][] computers ){
            int answer = 0;
            boolean[] checks = new boolean[n];

            for (int i = 0; i < n; i++) {
                if (!checks[i]) {
                    bfs(computers, i, checks);
                    answer++;
                }
            }

            return answer;
        }

        private boolean[] bfs(int[][] computers, int i, boolean[] checks) {
            checks[i] = true;

            for (int j = 0; j < computers.length; j++) {
                if (computers[i][j] == 1 && i != j && checks[j] == false) {
                    checks = bfs(computers, j, checks);
                }
            }
            return checks;
        }
    }

    @Test
    @DisplayName("BFS문제 프로그래머스 네트워크")
    void start_1() throws Exception{
        Soution actual = new Soution();
        assertEquals(2, actual.bfsSoution(3, new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
    }

}
