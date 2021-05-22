package ru.specialist.ThreadBase;

import static java.lang.System.out;

public class MyThread extends Thread {
    private int a,b;

    public MyThread(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        for(int i=a; i <= b; i++) {
            out.printf("%s %d\n", getName(), i);
        }
    }
}






