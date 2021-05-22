package ru.specialist.Locks;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProgramLocks {

    public static void main(String[] args) {
        Lock l = new ReentrantLock();
        Condition greater50 = l.newCondition();
        Condition greater70 = l.newCondition();

        Thread t0 = new Thread(
                ()->{
                    System.out.printf("%s %d");
                }

        );
    }
}
