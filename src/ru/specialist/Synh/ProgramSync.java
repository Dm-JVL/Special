package ru.specialist.Synh;

import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.out;

public class ProgramSync {


    //static volatile int counter = 0;

    public static void main(String[] args) throws InterruptedException {


        Sync sync = new Sync();

        AtomicInteger  c = new AtomicInteger();

        Thread t0 = new Thread(
                () -> {
                    for (int i = 1; i <= 10000; i++) {
                        //out.printf("%s %d\n", Thread.currentThread().getName(), i);
                        //counter++;
                        //counter  = counter + 1;
                        //synchronized (sync) {
                        //	sync.counter++;
                        //}
                        sync.increment();
                        c.incrementAndGet();
                    }
                }
        );

        Thread t1 = new Thread(
                () -> {
                    for (int i = 1; i <= 10000; i++) {
                        //out.printf("%s %d\n", Thread.currentThread().getName(), i);
                        //synchronized (sync) {
                        //	sync.counter++;
                        //}
                        sync.increment();

                        c.incrementAndGet();
                    }
                }
        );

        t0.start();
        t1.start();

        t0.join();
        t1.join();


        out.println(sync.getCounter());
        out.println((c.get()));

    }

}
