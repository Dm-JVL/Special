package ru.specialist.ISinArcNotify;


import static java.lang.System.out;

//Sync
//	double x = 1d;
//  boolean phase = false;
//
//Sin
//  for(10 iter)
//	x = sin(x); println(x)
//ASin
//for(10 iter)
// x = asin(x); println(x);


public class Thread5070 {


    public static void main(String[] args) throws InterruptedException {
        class Sync {
            int counter;
        }

        Sync sync = new Sync();

        Thread t0 = new Thread(
                ()-> {
                    for(int i=1; i <= 100; i++) {
                        out.printf("%s %d\n", Thread.currentThread().getName(), i);
                        synchronized (sync) {
                            sync.counter = i;
//							sync.notify();
                            sync.notifyAll();
                        }
                        Thread.yield();
                    }
                }
        );
        Thread t1 = new Thread(
                ()-> {

                    synchronized (sync) {
                        try {
                            while (sync.counter < 50) {
                                out.println("still waiting...");
                                sync.wait(1);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }


                    for(int i=1; i <= 100; i++) {
                        out.printf("%s %d\n", Thread.currentThread().getName(), i);

                    }
                }
        );
        Thread t2 = new Thread(
                ()-> {

                    synchronized (sync) {
                        try {
                            while (sync.counter < 70) {
                                sync.wait();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }


                    for(int i=1; i <= 100; i++) {
                        out.printf("%s %d\n", Thread.currentThread().getName(), i);

                    }
                }
        );

        t1.start();
        //Thread.sleep(500);
        t0.start();
        t2.start();
    }

}

