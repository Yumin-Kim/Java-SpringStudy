package cocdeup100;

import java.util.Scanner;

public class InputScannerTestAlgo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        float x = scanner.nextFloat();
//
//        String format = String.format("%.6f", x);

//        System.out.println(format);

        String s = scanner.nextLine();
        String[] s1 = s.split(" ");

        int i = Integer.parseInt(s1[0]);
        int i1 = Integer.parseInt(s1[1]);

        System.out.println(i+" "+i1);

        scanner.close();
    }
}
