package cocdeup100;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Algo {

    public static int n;
    public static ArrayList<Integer> arrayList = new ArrayList<>();
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        n = scanner.nextInt();
//
//        for (int i = 0; i < n; i++) {
//            arrayList.add(scanner.nextInt());
//        }
//        Collections.sort(arrayList);
//        int result = 0;
//        int count = 0;
//        for (int i = 0; i < n; i++) {
//            count += 1;
//            if (count >= arrayList.get(i)) {
//                result += 1;
//                count = 0;
//            }
//        }
//        System.out.println(result);

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] plans = scanner.nextLine().split(" ");
        int x = 1, y = 1;
        // LRUD
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};
        char[] moveTypes = {'L', 'R', 'U', 'D'};

        for (int i = 0; i < plans.length; i++) {
            char plan = plans[i].charAt(0);
            int nx = -1, ny = -1;
            for (int j = 0; j < 4; j++) {
                if (plan == moveTypes[j]) {
                    nx = x + dx[j];
                    ny = y + dy[j];
                }
            }
            if (nx < 1 || ny < 1 || nx < n || ny < n ) {
                x = nx;
                y = ny;
            }
        }
        System.out.println(x +"   " +y);

    }
}
