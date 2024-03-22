package com.examples;

import java.util.*;

//javac -d . DataStructureJava.java && java com/examples/DataStructureJava
public class DataStructureJava {



    void printCollection(){
        List<Integer> list1 = new ArrayList<Integer>();
        list1.add(34);
        list1.add(1);

        //print collection way1
        for(Integer k:list1){
            System.out.printf("%d,",k);
        }
        System.out.println();


        //print collection way2
        Iterator it = list1.iterator();
        while(it.hasNext()){
            System.out.printf("%d ",it.next()); //other methode on iterator is also available
        }
        System.out.println();

        //print collection way3
        for(int i=0;i<list1.size();i++){
            System.out.printf("%d ",list1.get(i));

        }
        System.out.println();

        //way4
        list1.forEach(e -> {
            System.out.printf("%d ",e);
        });

        //way5
        System.out.println(list1);

        //way6
        list1.stream()
            .peek(x -> System.out.printf("%d,",x));

    }


    public static void main(String args[]){

    /* Data Strucute is provided by Collection interface + Collection Classes in java
        java collection interface defines the contracts. And java collection classes implements these interface to provide desired data-structrue.

        java-collectin-interface-summary:   /javanotes/img/java-collection-interface-summary
        java-collection-classes-summary     /javanotes/img/java-collection-classes0-summary
    
    */
    
    /* #2 array in java */
        // ##array declaration
    int arr1[] = new int[10]; //c++ style array declaration on heap
    int []arr2 = new int[20]; 
    int arr3[] = {1,3,4,6,7};
    
        // int arr3[10]; //c++ style array declaration on stack (NOT ALLOWED in JAVA as in Java everything is on Heap for garbage collection)
    for(int i=0;i<arr1.length;i++) //C++ hash arr.size() and arr.length() wheres Java and JavaScript has arr.length
        System.out.printf("%d ",arr1[i]);
    
        // ##updating element
    arr1[2] = 34;

        //##2D array declaration
    int mem[][] = { {1,2,3}, {2,4,6},{1,3,5}};
    int mem2[][] = new int[10][101]; //2D

    for(int i=0;i<mem.length;i++)
    {
        for(int j=0;j<mem[i].length;j++)
        {
            System.out.printf("%d ",mem[i][j]);;
        }
        System.out.println();
    }

    
    // ##Helper Methode for array is defined in java.util.Arrays package as static functions
    // .fill(arr,value) .sort(arr) .sort(arr,comperator) .toString(arr) .binarySearch(arr,key) .binarySearch(arr,key,comperator)
    Arrays.fill(arr1,-1);
    Arrays.sort(arr1);

    Integer arr4[] = {1,3,5,6,7,8,14};
    Arrays.sort(arr4,(a,b)-> Integer.compare(a, b));
    Arrays.sort(arr4,(a,b)->  a<b ? -1 : a==b? 0: +1);

    // Arrays.sort(arr1,(a,b) -> Integer.compare(a,b)); //compilation error as sort does not accept primitive accepting comperator?

    System.out.printf("arr4: %s\n", Arrays.toString(arr4));

    int idx1 = Arrays.binarySearch(arr4,9); //return +idx if element if found, else -idx where idx is the inseratino point for element if the array be 1based
    System.out.printf("Arrays.binarySearch idx = %d\n",idx1);


    /******* #3 ArrayList (LinkedList class implements List) ********/
    /* Java ArrayList can be thought as c++ vector*/
    System.out.println("\nArrayList\n");
    ArrayList<Integer> vec1 = new ArrayList<>(); //personal preferred way
    List<Integer> vec2 = new ArrayList<>(); //preferred way: programming to interface

    ArrayList<Integer> vec3 = new ArrayList<>(10); //predefined size
    
    ArrayList<ArrayList<Integer>> vec4 = new ArrayList<>(10); //2d arraylist
    for(int i=0;i<10;i++) vec4.add(new ArrayList<>(12));

        //## Methodes:: add(E), add(idx,E),  E get(idx), set(idx,val) .sort(comperator) MORE:? refer /img/java-collection-interface-summary.png
    vec1.add(12);
    vec1.add(45);
    vec1.add(-9);
    vec1.get(0);
    vec1.set(0,11);
    vec1.sort(Integer::compare); //lambda reference to static methode
    vec1.sort((a,b) -> a<b?-1:a==b?0:+1); //pass lambda comperator

    System.out.printf("vec1: %s\n",vec1); //it works because Collection has toString implementation of List
    

    /******* #4 LinkedList (LinkedList class implements List) ********/
    LinkedList<Integer> linkedlist1 = new LinkedList<>();
    List<Integer> linkedlist2 = new LinkedList<>();

        //Methodes:: same as ArrayList as both implements List only
    

    /******* #5 Queue (LinkedList class implements Queue) ********/
    Queue<Integer> q1 = new LinkedList<>(); //programming to interface
    
        //Methodes::  offer(E), E peek(), E poll(), E remove()
    q1.offer(12);
    q1.offer(13);

    System.out.printf("q1: %s\n",q1);

    /******* #6 Deque (ArrayDeque implements Deque , Deque extends Queue) ********/
    Deque<Integer> dq1 = new ArrayDeque<>(); //programming to interface, preferred
    ArrayDeque<Integer> dp2 = new ArrayDeque<>();

        //Methodes: boolean offerFirst(E), E  peekFirst() , E pollFirst() 
        //          boolean offerLast(E), E peekLast(), E pollLast()                    

    /******* #7 PriorityQueue (PrirityQueue class implements Queue) ********/
    Queue<Integer> pq1 = new PriorityQueue<Integer>(); //programming to interface, provides flexibilty
    PriorityQueue<Integer> pq2 = new PriorityQueue<Integer>(); //preferred: programming to concrete class, provides more clarity

    PriorityQueue<Integer> pq3 = new PriorityQueue<Integer>(Integer::compare);
    PriorityQueue<Integer> pq4 = new PriorityQueue<Integer>((a,b) -> Integer.compare(a,b));

        //Methodes:: boolean offer(E), E peek() , E poll()


    /******* #8 Stack (Stack is standalone class) ********/
    Stack<Integer> st1 = new Stack<>();

    //Methodes:: https://github.com/openjdk/jdk/blob/master/src/java.base/share/classes/java/util/Stack.java
    //           push(E), E pop() , E peek(), empty()


    /******* #9 Set (HashSet,LinkedHashSet,TreeSet classes implements Set; Set extends Collection) ********/
    Set<Integer> set1 = new HashSet<>();

    HashSet<Integer> set2 = new HashSet<>(); //preferred: provides clarity
    LinkedHashSet<Integer> set3 = new LinkedHashSet<>();
    TreeSet<Integer> set4 = new TreeSet<>();

    //Methodes:: boolean add(E), boolean remove(E) , contains(E) , isEmpty()
    set1.add(23);
    set1.add(56);
    System.out.printf("set1.contains: %b\n",set1.contains(23));

    for(Integer x:set1)
        System.out.printf("%d,",x);
    System.out.println();

    
    /******* #10 Map (HashMap,TreeMap classes implements Map; Map extends Collection) ********/
    Map<Integer,Integer> map1 = new HashMap<>();
    Map<Integer,Integer> map2 = new TreeMap<>();

    //Methodes:: K put(V), V get(K), boolean containsKey(K)
    //           optional: forEach(BiConsumer<K,V> consumer) , Set<Map.Entries<K,V>> entrySet() , Collection<V>  values() , Set<K> keySet()
    map1.put(0,0);
    map1.put(1,10);
    map1.put(2,20);

    map1.put(2,220); //replace existing key


    System.out.printf("map entries are\t:");
    map1.forEach((k,v) -> System.out.printf("(%d->%d) ",k,v));
    System.out.println();

    for(Map.Entry<Integer,Integer> mentry : map1.entrySet() )
    {
        System.out.printf("(%d->%d) ",mentry.getKey(),mentry.getValue());
    }
    System.out.println();


    /******* #11 .stream() with collections ********/
    //checkout file StreamExpression.java




    }
}
/* 
pq_ef1: https://github.com/openjdk/jdk/blob/master/src/java.base/share/classes/java/util/PriorityQueue.java
pq_ref2: https://docs.oracle.com/javase/8/docs/api/java/util/PriorityQueue.html
*/
