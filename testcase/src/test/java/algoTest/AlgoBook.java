package algoTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AlgoBook {

    @Test
    @DisplayName("탐욕법")
    void start_1() throws Exception{
        //given
        int input = 25;
        int k = 3;
        int result = 0;

        while (true){
            int target = (input / k) * k;
            result += (input - target);
            if(input < k) break;
            result += 1;
            input /= k;
        }

        System.out.println("input = " + result);
        //when

        //then
    }
    
    @Test
    @DisplayName("상하좌우")
    void start_2() throws Exception{
        //given
        int dx[] = {0, -1, 0, 1};
        int dy[] = {1, 0, -1, 0};
        int x = 2;
        int y = 2;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            System.out.println("nx+\"_\"+ny = " + nx+"_"+ny);
            
        }
        //when
        
        //then
    }
    
}
