package Beispiel2;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.*;

public class Beispiel2Main {


    public static void main(String[] args) {
        int n = 0;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Future<Integer>> al = new ArrayList<Future<Integer>>();
        System.out.println("Please enter the number, which you want to get th" +
                "e sum of all lower numbers from:");
        while (true) {
            try {
                n = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException nfe) {
                System.out.println("Not a number, please enter a valid number:");
                continue;
            }
            int threads = (int) Math.ceil(n / 100);
            if(threads < 1)
                threads = 1;
            ExecutorService executor = Executors.newFixedThreadPool(threads);
            int top = 0;
            int bottom = 0;
            for (int i = 0; i < n; i += 100) {
                if ((i + 100) < n) {
                    top = i + 100;
                    bottom = i;
                } else {
                    top = n;
                    bottom = i;
                }
                Callable<Integer> c = new MyCalculator(top, bottom);
                Future<Integer> future = executor.submit(c);
                al.add(future);
            }
            executor.shutdown();
            int sum = 0;
            for (Future<Integer> f:al) {
                try {
                    sum += f.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Sum: " + sum);
            System.out.println(" ");
            int testSum = 0;
            for (int i = 0; i < n; i++) {
                testSum += i;
            }
            System.out.println("Tetstsum: " + testSum);
            return;
        }
    }
}
