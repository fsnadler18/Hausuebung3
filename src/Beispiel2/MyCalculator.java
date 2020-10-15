package Beispiel2;

import java.util.concurrent.Callable;

public class MyCalculator implements Callable <Integer> {
    int topBorder;
    int bottomBorder;

    public MyCalculator(int topBorder, int bottomBorder) {
        this.topBorder = topBorder;
        this.bottomBorder = bottomBorder;
    }

    @Override
    public Integer call() throws Exception {
        int sum = calc();
        return sum;
    }

    public int calc(){
        int sum = 0;
        for (int i = this.bottomBorder; i < this.topBorder; i++) {
            sum += i;
        }
        return sum;
    }
}
