package ru.specialist.ThreadBase;

import static java.lang.System.out;

class MySuperThread implements Runnable {
    @Override
    public void run() {
        for(int i=1; i <= 100; i++) {

            if (Thread.interrupted()) {
                out.printf("Exit thread %s\n", Thread.currentThread().getName());
                return;
            }
            out.printf("%s %d\n", Thread.currentThread().getName(), i);
        }
    }
}