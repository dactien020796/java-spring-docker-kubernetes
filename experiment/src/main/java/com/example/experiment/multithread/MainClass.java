package com.example.experiment.multithread;

public class MainClass {

    public static void main(String[] args) throws InterruptedException {
        MyTask task = new MyTask(1);
        Thread thread = new Thread(task);

        MyTask task2 = new MyTask(2);
        Thread thread2 = new Thread(task2);

        thread2.start();
        thread2.join();
        thread.start();
    }
}
