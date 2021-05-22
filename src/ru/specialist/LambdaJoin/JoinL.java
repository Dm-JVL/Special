package ru.specialist.LambdaJoin;

import static java.lang.System.out;

public class JoinL {

    public static void main(String[] args) throws InterruptedException {
        Thread t0 = new Thread(
                /**
                 * Реализация в форме лямбд
                 * */
                () -> {
                    for (int i = 1; i <= 100; i++) {
                        out.printf("%s %d\n", Thread.currentThread().getName(), i);
                    }
                }
        );

        Thread t1 = new Thread(
                () -> {
                    try {
                        t0.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    for (int i = 1; i <= 100; i++) {
                        out.printf("%s %d\n", Thread.currentThread().getName(), i);
                    }
                }
        );

        t0.start();
        Thread.sleep(100); // Состояние - TIMED_WAITING
        t1.start();

        t0.join();  // Состояние - WAITING
        // Thread.currentThread() => WAITING
        // до тех пока t0 не перейдёт в TERMINATED

        t1.join(20000); // TIMED_WAITING


        out.println(Thread.currentThread().getName());

    }

}
