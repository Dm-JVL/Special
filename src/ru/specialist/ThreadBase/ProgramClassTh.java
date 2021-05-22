package ru.specialist.ThreadBase;

import ru.specialist.ThreadBase.MySuperThread;
import ru.specialist.ThreadBase.MyThread;

import static java.lang.System.out;

public class ProgramClassTh {

    public static void main(String[] args) {
        Thread t0 = new MyThread(1, 50);
        Thread t1 = new MyThread(51, 100);

        Thread t2 = new Thread(new MySuperThread());

        final int a = 40;
        final int b = 60;

        Thread t3 = new Thread(
                new Runnable() {
                    private int x,y;

                    {
                        this.x = a;
                        this.y = b;
                    }
                    public void run() {
                        x++;
                        y++;
                        for(int i=x; i <= y; i++) {
                            out.printf("%s %d\n", Thread.currentThread().getName(), i);
                        }
                    }
                }
        );
        Thread t4 = new Thread(
                ()-> {
                    int x = a;
                    for(int i=a; i <= b; i++) {
                        out.printf("%s %d\n", Thread.currentThread().getName(), i);
                    }
                }
        );

        t0.setPriority(Thread.MAX_PRIORITY);

        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t2.interrupt();

        //out.printf("isAlive for %s: %s\n", t2.getName(), t2.isAlive());
        out.printf("isAlive for %s: %s\n", t2.getName(), t2.getState());

        out.println(Thread.currentThread().getName());

    }

}