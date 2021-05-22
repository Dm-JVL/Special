package ru.specialist.Synh;

class Sync {
    private volatile int counter = 0;

    public synchronized void increment() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }
}

