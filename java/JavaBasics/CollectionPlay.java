package com.collection.play;

import java.util.*;

public class CollectionPlay {

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

    }

    void playList(){
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        ArrayList<Integer> list2 = new ArrayList<>(List.of(1,3,4,5,3));
        ArrayList<Integer> list3 = new ArrayList<>(list2);

        LinkedList<Integer> list4 = new LinkedList<>();

        list1.add(34);
        //explore other methode available on list

    }

    void playPriorityQueue(){
        System.out.println("\n\n\t::playPriorityQueue");
        PriorityQueue<Integer> pq1 = new PriorityQueue<>();

        class MyInteger  implements Comparable<MyInteger>{
            Integer x;
            String str;

            @Override
            public int compareTo(MyInteger other) {
                return this.x - other.x;
            }
        };

        PriorityQueue<MyInteger> pq2 = new PriorityQueue<MyInteger>();

        pq1.add(3);
        pq1.add(-3);
        pq1.add(0);

        pq1.forEach(e -> System.out.printf("%d ",e));

        System.out.printf("\nMethode: peek = %d, poll=%d",pq1.peek(),pq1.poll());

    }

    public void playSet()
    {
        System.out.println("\n\n\t::playSet");

        TreeSet<Integer> set1 = new TreeSet<>(List.of(10,10,234,23,2,34,234));
        System.out.printf("ceiling: %d\n", set1.ceiling(22));

        HashSet<Integer> set2  = new HashSet<>(List.of(1,1,2,3,4,3,4));

        System.out.println(set1);
    }

    public static void main(String args[]){
        System.out.println("Collection Play Test\n");

        CollectionPlay o = new CollectionPlay();

        o.printCollection();
        o.playPriorityQueue();

        o.playSet();

    }
}
