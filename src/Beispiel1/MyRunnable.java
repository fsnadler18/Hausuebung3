package Beispiel1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MyRunnable implements Runnable{

    private List <Integer> numbers;
    private int divider;

    public MyRunnable(List<Integer> numbers, int divider) {
        this.numbers = numbers;
        this.divider = divider;
    }

    @Override
    public void run() {
        for (int i : numbers) {
            if ((i % divider) == 0)
                System.out.println(i);
        }
    }
}
