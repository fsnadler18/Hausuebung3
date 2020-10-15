package Beispiel1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Bsp1Main {


    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();

        readFile(numbers);

        int chunks = 0;
        int divider = 0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("chunks:");
                chunks = Integer.parseInt(scanner.nextLine());
                System.out.println("divider:");
                divider = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException nfe) {
                System.out.println("This is not a number;");
                continue;
            }
            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(chunks);
            int size = (int) Math.ceil((double) numbers.size() / (double) chunks);
            List<Integer> partialList;

            for (int i = 0; i < numbers.size(); i += size) {
                if (i + size < numbers.size()) {
                    partialList = numbers.subList(i, (i + size));
                } else {
                    partialList = numbers.subList(i, numbers.size());
                }
                MyRunnable mr = new MyRunnable(partialList, divider);
                executor.execute(mr);
            }
            executor.shutdown();
            return;
        }

    }

    public static void readFile(ArrayList<Integer> ar) {
        boolean fileFound = false;
        String filePath;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your Filepath:");

        while (!fileFound) {
            try {
                filePath = scanner.nextLine();
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));
                fileFound = true;
                String line;

                while (br.readLine() != null) {
                    line = br.readLine();
                    String[] numbers = line.split(":");

                    for (int i = 0; i < numbers.length; i++) {
                        try {
                            ar.add(Integer.parseInt(numbers[i]));
                        } catch (NumberFormatException nfe) {
                            continue;
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Couldn't find file, pls reenter your filepath:");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
