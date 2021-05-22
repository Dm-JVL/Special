package ru.specialist.ISinArcNotify;

public class Sin {

    public static void main(String[] args) throws InterruptedException {
        /** Класс монитор
         * Содержит поле для внесения измнений и поле - фазы для
         * разграничения действий потоков
         * */
        class Synch {
            volatile double x = 1d;
            volatile boolean phase = true;
        }

        final Synch syn = new Synch();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (syn) { // блок синхронизации, где осуществляется захват монитора
                    while (!syn.phase)
                        try {
                            syn.wait(); // обеспечиваем ожидание вызова метода notify из другого потока
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    syn.x = Math.sin(syn.x);
                    System.out.println(Thread.currentThread().getName() + ": Sin - " +syn.x);
                    syn.phase = false;
                    syn.notify(); // s.notifyAll();
                }

            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (syn) {
                    while (syn.phase)
                        try {
                            syn.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    syn.x = Math.asin(syn.x);
                    System.out.println(Thread.currentThread().getName() + ": ArcSin - " +syn.x);
                    syn.phase = true;
                    syn.notify();
                }

            }

        });

        t1.start();
        //Thread.sleep(100);
        t2.start();
    }

}
