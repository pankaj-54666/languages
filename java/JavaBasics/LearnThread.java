package com.examples.thread;

class ThreadTestOne extends Thread{

    @Override
    public void run() {
        System.out.printf("ThreadOne Started\n");
    }
}

class ThreadTestTwo implements Runnable{

    @Override
    public void run() {
        System.out.printf("ThreadTwo Started\n");
    }
};

//javac -d . LearnThread.java && java com/examples/thread/LearnThread
public class LearnThread{



    public static void main(String args[]){

        //Task1: Thread using extends
        ThreadTestOne obj1 = new ThreadTestOne();
        obj1.run();


        //Task2: Thread using implement 
        ThreadTestTwo obj2 = new ThreadTestTwo();
        obj2.run();

        //Task3L Thread Methodes

        System.out.printf("threadID: %d, threadName: %s, priority: %d\n",obj1.getId(),obj1.getName(),obj1.getPriority());
        

    }
}

//ref: https://github.com/mohammedabdulbari/Java-SE/blob/master/ThreadTest1.java