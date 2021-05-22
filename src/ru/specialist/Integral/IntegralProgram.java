package ru.specialist.Integral;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.function.DoubleFunction;

import static java.lang.Runtime.getRuntime;

public class IntegralProgram {

    public static final int STEPS = 10000000;
    public static final int TASKS = Runtime.getRuntime().availableProcessors();

    public static double singleThread(DoubleFunction<Double> f, double a, double b, int steps) {
        double h = (b - a) / steps;
        double summa = 0d;

        for (int i = 0; i < steps; i++) {
            double x = a + h * i + h / 2;
            double y = f.apply(x);
            summa += y * h;
        }
        return summa;
    }

    /**
     * Применяем функциональные интерфейсы: DoubleFunction
     */
    public static double multiThread(DoubleFunction<Double> f, double a, double b) {
        double h = (b - a) / TASKS;
        Thread[] threads = new Thread[TASKS];
        DoubleAdder r = new DoubleAdder();

        for (int i = 0; i < TASKS; i++) {
            double ax = a + h * i;
            double bx = ax + b;
            threads[i] = new Thread(() -> {
                double s = singleThread(f, ax, bx, STEPS / TASKS);
                r.add(s);
            });
            threads[i].start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return r.sum();
    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println(getRuntime().availableProcessors());

        //singleThread(Math::sin, 0, Math.PI/2, STEPS);
        //singleThread(Math::sin, 0, Math.PI/2, STEPS);
        long t1 = System.currentTimeMillis();
        double r1 = singleThread(Math::sin, 0, Math.PI / 2, STEPS);
        long t2 = System.currentTimeMillis();
        System.out.printf("Single Thread: %f Time: %d\n", r1, t2 - t1);


        //multiThread(Math::sin, 0, Math.PI/2);
        //multiThread(Math::sin, 0, Math.PI / 2);
        long t3 = System.currentTimeMillis();
        double r2 = multiThread(Math::sin, 0, Math.PI / 2);
        long t4 = System.currentTimeMillis();
        System.out.printf("Multi  Thread: %f Time: %d\n", r2, t4 - t3);
    }
}
