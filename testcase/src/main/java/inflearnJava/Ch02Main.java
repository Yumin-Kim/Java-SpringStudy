package inflearnJava;

import java.util.Scanner;

public class Ch02Main {



    public static void main(String[] args) {
        Ch02Main main = new Ch02Main();
        Scanner scanner = new Scanner(System.in);
        int n1 = scanner.nextInt();
        int n2 = scanner.nextInt();
        int[][] ints1 = new int[n2][n1];
        for (int i = 1; i < n2; i++) {
            for (int j = 1; j < n1; j++) {
                ints1[i][j] = scanner.nextInt();
            }
        }
        System.out.println("main.solution_1 = " + main.solution_7(n1,n2,ints1));
    }

    private String solution_7(int n1, int n2, int[][] arr) {
        int answer = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n1 ; i++) {
            for (int j = 1; j <= n1; j++) {
                int cnt = 0;
                for (int k = 0; k < n2; k++) {
                    int pi = 0;
                    int pj = 0;
                    for (int l = 0; l < n1; l++) {
                        if (arr[k][l] == i) {
                            pi = l;
                        }
                        if (arr[k][l] == j) {
                            pj = l;
                        }
                    }
                    if (pi < pj) {
                        cnt++;
                    }
                }
                if (cnt == n2) {
                    answer++;
                }
            }

        }

        System.out.println("answer = " + answer);
        return null;
    }

    private String solution_5(int i, int[] ints1) {
        String answer = "";
        for (int x = 0; x < i; x++) {
            int tmp = ints1[x];
            int res = 0;
            while (tmp > 0) {
                int t = tmp % 10;
                res = (res * 10) + t;
                tmp = tmp / 10;
            }
            System.out.println("res = " + res);
            if (isPrime(res)) answer += " " + res;
        }
        System.out.println("answer = " + answer);
        return null;
    }

    private boolean isPrime(int res) {
        if (res == 1) {
            return false;
        }
        for (int i = 2; i < res; i++) {
            if (res % i == 0 ) return false;
        }
        return true;
    }

    private String solution_4(int x) {
        int answer = 0;
        int[] ints = new int[x + 1];
        for (int i = 2; i < x; i++) {
            if (ints[i] == 0) {
                answer++;
                for (int j = i; j < x; j = j + i) ints[j] = 1;
            }
        }
        System.out.println("answer = " + answer);
        return null;
    }

    private String solution_3(int i, int[] ints, int[] ints1) {
        String answer = "";
        System.out.println("ints.length = " + ints.length);
        for (int x = 0; x < ints.length; x++) {
            if (ints[x] == ints1[x]) answer += "D";
            else if (ints[x] == 1 && ints1[x] == 3) answer += "A";
            else if(ints[x] == 2 && ints1[x] == 1) answer += "A";
            else if(ints[x] == 3 && ints1[x] == 2) answer += "A";
            else answer += "B";
        }
        return answer;
    }

    private String solution_2(int i, int[] ints) {
        int tmp = ints[0];
        int count = 1;
        for (int x = 1; x < ints.length; x++) {
            if (ints[x] > tmp) {
                tmp = ints[x];
                count++;
            }
        }
        return count+"";
    }

    private String solution_1(int i, int[] ints) {
        String answer = "";
        for (int x : ints) {
            if (i <= x) {
                answer += x + " ";
            }
        }
        return answer;
    }
}
