package com.examples;
import java.util.*;

class Graph{
    List<Integer> adj[];
    int V;

    Graph(int V){
    this.V = V;

    }
    void addEdge(int u,int v){
        System.out.printf("addEdge: %d->%d\n",u,v);
        adj[u].add(v);

    }
}


class CritialSectionClass {
    public static void printString(String s){
        for(int i=0;i<s.length();i++){
            System.out.printf("%c",s.charAt(i));
        }
        System.out.printf("");
    }
}

class ThreadCsTest implements Runnable{
    @Override
    public void run(){
        CritialSectionClass.printString("Hello There");
    }
}

class ReportGenerator extends Thread {
    public void run() {
        for (int i = 1; i <= 10; i++) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Report generation stopped.");
                return;
            }
            System.out.println("Generating page " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted while generating report.");
                return;
            }
        }
    }
}

class ThreadOne extends Thread{
    private int counter = 0;

    @Override
    public void run(){
        System.out.printf("ENTER: ThreadOne Run\n");

        try{
            Thread.sleep(1000 * 5);
        }catch(InterruptedException ex){
            System.out.printf("%s is waked from a nice sleep! bad\n",this.getName());
        }


        System.out.printf(".threadName: %s\n",this.getName());
    }
}

//javac -d . TestPlayGround.java && java com/examples/TestPlayGround
//OR
//javac -d . TestPlayGround.java && java com.examples.TestPlayGround
public class TestPlayGround{

    private void tt(){
        int a = 10;
        System.out.printf("the value of a is %d, %s\n",a,new String("Hello"));

        boolean b1 = true;
        // boolean b = True;

        System.out.printf("b1: %b\n",b1);
        int i1 = 10;
        Integer i2  = 12;


        // Boolean b2 = True;

        long l1 = 10;
    }

    private static void threadTest(){
        ThreadOne t1 = new ThreadOne();
        ThreadOne t2 = new ThreadOne();

        t1.setName("t1");

        t1.start();
        t2.start();

        t1.interrupt();
    }

    private static void handleUserCancelInterrupt()throws InterruptedException{
        ReportGenerator reportThread = new ReportGenerator();
        reportThread.start();

        Thread.sleep(3000);  // Simulate user waiting for 3 sec
        reportThread.interrupt();  // User cancels report
    }

    private static void testCritialSection() throws InterruptedException {
        Thread t1 = new Thread(new ThreadCsTest());
        Thread t2 = new Thread(new ThreadCsTest());

        t1.start();
        t2.start();        
    } 

    public static void main(String[] args) throws InterruptedException{
        System.out.printf("ENTER: main\n");

        //threadTest();

        // handleUserCancelInterrupt();

        testCritialSection();

     
        


    }
}