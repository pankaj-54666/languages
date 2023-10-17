package com.examples.thread;

//Thread using extends
class ThreadTestOne extends Thread{

    @Override
    public void run() {
        System.out.printf("ThreadOne Started\n");
    }
}

//thread using interface
class ThreadTestTwo implements Runnable{

    @Override
    public void run() {
        System.out.printf("ThreadTwo Started\n");
    }
};

//javac -d . LearnThread.java && java com/examples/thread/ThreadTest
public class LearnThread{


    public static void main(String args[]){

        ThreadTestOne obj1 = new ThreadTestOne();
        obj1.run();



        ThreadTestTwo obj2 = new ThreadTestTwo();
        obj2.run();

    }
}

//ref: https://github.com/mohammedabdulbari/Java-SE/blob/master/ThreadTest1.java